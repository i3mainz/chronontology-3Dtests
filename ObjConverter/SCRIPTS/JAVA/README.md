# RUN Config.java

RUN and create all four OBJ files

change params
* inputName (name of XYZ file)
* method ("SUM" or "MAX")
* inputPath (absolute file path)

## ObjPeakConverter.java

### input

"BURN-GeoTIF" to "BURN-XYZ" (important SUM or MAX)

example: https://github.com/florianthiery/ObjConverter/tree/master/results/xyz

### output

"PEAK-OBJ" -> dimension like EUROPA-GeoTIF -> Z value = max(c_level) or sum (c_level)

example: https://github.com/florianthiery/ObjConverter/tree/master/results/obj

## ObjLayerConverter.java

uses Addition.java

### input

"BURN-GeoTIF" to "BURN-XYZ" (important SUM or MAX)

example: https://github.com/florianthiery/ObjConverter/tree/master/results/xyz

### output

"LAYER-OBJ" -> dimension like EUROPA-GeoTIF -> Z value = sum (c_level)

6 layers (one Europe, 5 area hotspots with c_level values

example: https://github.com/florianthiery/ObjConverter/tree/master/results/obj

## ObjSpaceTimePathConverter.java

uses Addition.java

### input

"BURN-GeoTIF" to "BURN-XYZ" (important SUM or MAX)

example: https://github.com/florianthiery/ObjConverter/tree/master/results/xyz

### output

"SpaceTimePath-OBJ" -> dimension like EUROPA-GeoTIF -> Z value = sum (c_level)

6 layers (one Europe, 5 area hotspots with c_level values)

example: https://github.com/florianthiery/ObjConverter/tree/master/results/obj

## ObjPlainConverter.java

uses Addition.java

### input

"BURN-GeoTIF" to "BURN-XYZ" (important SUM or MAX)

example: https://github.com/florianthiery/ObjConverter/tree/master/results/xyz

### output

"Plain-OBJ" -> dimension like EUROPA-GeoTIF -> Z value = sum (c_level)

6 layers (one Europe, 5 area hotspots with c_level values)

example: https://github.com/florianthiery/ObjConverter/tree/master/results/obj
