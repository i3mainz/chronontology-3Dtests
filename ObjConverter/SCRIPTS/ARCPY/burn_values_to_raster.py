#!/usr/bin/env python

""" burn_values_to_raster.py: Takes a raster layer and "burns in" (adds) values defined by an
    additional shapefile. The shapefile contains polygons and the corresponding
    values that should be applied. In case of overlapping polygons, two options are possible:
    1. the highest value will be applied to the newly created raster file
    2. alle values will be summed up. """

__author__ = "Axel Kunz"
__email__ = "axel.kunz@hs-mainz.de"
__credits__ = ["Axel Kunz", "Florian Thiery"]
__license__ = "CC BY-SA 4.0"
__version__ = "1.0"

import os
from random import randint
import arcpy
import numpy

# parameters
wkspace = "C:\\Users\\axel.kunz\\Documents\\ArcGIS\\burn_rasters"
inShape = "areas.shp"
inShapeField = "c_level"
inRaster = "europa.tif"
outRaster = "output.tif"
method = "SUM"  # "MAXIMUM" or "SUM"

# Check out the ArcGIS Spatial Analyst extension license
arcpy.CheckOutExtension("Spatial")

# parameters
print "... getting parameters"
in_shape = arcpy.GetParameterAsText(0)
if not in_shape or in_shape == "":
    in_shape = inShape
in_shape_field = arcpy.GetParameterAsText(1)
if not in_shape_field or in_shape_field == "":
    in_shape_field = inShapeField
raster = arcpy.GetParameterAsText(2)
if not raster or raster == "":
    raster = inRaster
output_raster = arcpy.GetParameterAsText(3)
if not output_raster or output_raster == "":
    output_raster = outRaster

print "... setting workspace"
arcpy.env.workspace = wkspace
#arcpy.env.overwriteOutput = False
arcpy.env.snapRaster = raster


def make_random_values_raster(inRaster):
    """takes raster and creates random value for each cell
    """
    # calc lower left coordinate
    ras_obj = arcpy.Raster(inRaster)
    lower_left = arcpy.Point(ras_obj.extent.XMin, ras_obj.extent.YMin)

    # Convert Raster to numpy array
    np_arr = arcpy.RasterToNumPyArray(inRaster, nodata_to_value=0)

    # replace values of 1 with random value
    for i, row in enumerate(np_arr):
        for j, cell in enumerate(row):
            if cell > 0:
                np_arr[i][j] = randint(1, 9)
            #else:
            #    np_arr[i][j] = 0.0

    # Convert Array to raster (keep the origin and cellsize the same as the input)
    newRaster = arcpy.NumPyArrayToRaster(np_arr, lower_left, ras_obj.meanCellHeight,
                                         ras_obj.meanCellWidth, value_to_nodata=-9999)

    # define projection of output

    # get the coordinate system by describing a feature class
    coord_sys = arcpy.Describe(inRaster).spatialReference
    arcpy.DefineProjection_management(newRaster, coord_sys)

    return newRaster


def create_rasters_from_shape(in_shape):
    """extract rasters under every polygon of shapefile and
       returns a list of tuples including value and name of
       newly created raster files
    """
    list_of_rasters = []
    fields = ["FID", in_shape_field]
    with arcpy.da.SearchCursor(in_shape, fields) as cursor:
        # each value
        for row in cursor:
            fid = row[0]
            value = row[1]

            # make feature layer from raw shapefile
            tmp_shapefile = arcpy.MakeFeatureLayer_management(in_shape)

            # select polygon of each value
            select_string = ('"{}" = {}'.format(in_shape_field, value))
            arcpy.SelectLayerByAttribute_management(tmp_shapefile,
                                                    "NEW_SELECTION",
                                                    select_string)


            # extract by mask
            extracted_raster = arcpy.sa.ExtractByMask(raster, tmp_shapefile)

            outCon = arcpy.sa.Con(extracted_raster, int(value), 0,
                                  "VALUE >= 0")
            #outCon.save("new_{}.tif".format(value))
            #new_tuple = (outCon, fid, int(value))
            #list_of_rasters.append(new_tuple)
            #outCon.save("outcon_{}".format(value))
            list_of_rasters.append(outCon)

    return list_of_rasters


def create_mosaic_from_rasters(list_of_rasters, europe_raster, output_raster, acc_method):
    """returns newly calculated europe raster
    """
    string_of_rasters = ""
    for i, raster in enumerate(list_of_rasters):
        print i, raster
        #arcpy.AddMessage(i, raster)

        if i == 0:
            string_of_rasters = "{}{}".format(string_of_rasters, str(raster))
        else:
            string_of_rasters = "{};{}".format(string_of_rasters, str(raster))

    print string_of_rasters
    #coord_sys = arcpy.Describe(europe_raster).spatialReference

    arcpy.MosaicToNewRaster_management(str(string_of_rasters), wkspace,
                                       output_raster,
                                       "",
                                       "16_BIT_UNSIGNED",
                                       "",
                                       "1",
                                       acc_method,
                                       "FIRST")

if __name__ == "__main__":
    print "... creating raster for each polygon"
    list_of_rasters = create_rasters_from_shape(in_shape)
    print "... applying rasters to base raster"

    if method == "MAXIMUM":
        list_of_rasters.append(raster)  # append europe-raster
        create_mosaic_from_rasters(list_of_rasters, raster, output_raster, method)

    if method == "SUM":
        create_mosaic_from_rasters(list_of_rasters, raster, output_raster, method)
        create_mosaic_from_rasters([output_raster, raster], raster, "output_sum.tif", "MAXIMUM")

    print "done!"
