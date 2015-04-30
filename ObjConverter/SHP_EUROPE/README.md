countries.shp originally from DIVA-GIS ...

* http://www.diva-gis.org/Data
* http://biogeo.ucdavis.edu/data/world/countries_shp.zip

derived files ...

* europe_25832.shp (filtered by "Europe" minus Russia and transformed into EPSG:25832)
* europe_25832_union.shp (europe_25832.shp ST_UNION Europe Geometries)

Europe-Grid ...

* europa_bb_25832.shp (Grid Bounding-Box)
* europa_10000_grid_25832.shp (Grid with grid size 10km, ST_INTERSECTS(grid,europe_25832_union), type_num 0=sea; 1=landscape)
