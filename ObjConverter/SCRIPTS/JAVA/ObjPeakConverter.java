package objconverter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Convert Europe XYZ-Grid to OBJ Facesets CC BY-SA 4.0
 *
 * @author Florian Thiery
 */
public class ObjPeakConverter {

	private static int[] values = {2, 123, 244, 355, 466};

	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		// edit
		String input = Config.inputName;
		String method = Config.method;
		// start
		Logger log = Logger.getLogger(ObjPeakConverter.class.getName());
		String input_name = input + ".xyz";
		String output_name = input + "_peak.obj";
		String input_path = Config.inputPath + input_name;
		String output_path = Config.outputPath + output_name;
		int B = 426;
		double X1_SOLL = -1245000.0;
		double Y1_SOLL = 7995000.0;
		double BB_UPPER_LEFT_X = -1250000;
		double BB_UPPER_LEFT_Y = 8000000;
		int halfGridSize = 5000;
		int gridSize = halfGridSize * 2;
		int y_offset = 449;
		double z_water = 0.0;
		double z_landscape = 5.0;
		double z_area = 10.0;
		double hight_plus = 5.0;
		double z_area3 = z_area + 1 * hight_plus;
		double z_area4 = z_area + 2 * hight_plus;
		double z_area5 = z_area + 3 * hight_plus;
		double z_area6 = z_area + 4 * hight_plus;
		double z_area7 = z_area + 5 * hight_plus;
		double z_area8 = z_area + 6 * hight_plus;
		double z_area9 = z_area + 7 * hight_plus;
		double z_area10 = z_area + 8 * hight_plus;
		double z_area11 = z_area + 9 * hight_plus;
		double z_area12 = z_area + 10 * hight_plus;
		double[] rgb_water = {0.14, 0.32, 0.85};
		double[] rgb_landscape = {0.71, 0.44, 0.27};
		double[] rgb_area = {0.90, 0.00, 0.00};
		double[] rgb_area3 = {0.78, 0.09, 0.02};
		double[] rgb_area4 = {0.67, 0.18, 0.03};
		double[] rgb_area5 = {0.56, 0.28, 0.05};
		double[] rgb_area6 = {0.45, 0.37, 0.06};
		double[] rgb_area7 = {0.33, 0.47, 0.08};
		double[] rgb_area8 = {0.22, 0.56, 0.09};
		double[] rgb_area9 = {0.11, 0.65, 0.11};
		double[] rgb_area10 = {0.11, 0.65, 0.11};
		double[] rgb_area11 = {0.11, 0.65, 0.11};
		double[] rgb_area12 = {0.11, 0.65, 0.11};
		try {
			System.out.println("START ObjPeakConverter!");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input_path), "UTF-8"));
			String line = "";
			int lauf = 0;
			List<String> VERTEX_LIST = new ArrayList<String>();
			List<String> FACESET_LIST = new ArrayList<String>();
			double new_X = -42;
			double new_Y = -42;
			System.out.println("write vetices...");
			while ((line = br.readLine()) != null) {
				String[] csv = line.split(" ");
				if (lauf == 0) { // soll - ist
					new_X = X1_SOLL - Double.parseDouble(csv[0]);
					new_Y = Y1_SOLL - Double.parseDouble(csv[1]);
				}
				double x = (Double.parseDouble(csv[0]) + new_X - BB_UPPER_LEFT_X - halfGridSize) / gridSize;
				double y = ((Double.parseDouble(csv[1]) + new_Y - BB_UPPER_LEFT_Y + halfGridSize) / gridSize) + y_offset;
				double z = -42.0;
				double r = -42.0;
				double g = -42.0;
				double b = -42.0;
				if (method.equals("MAX")) {
					if (Integer.parseInt(csv[2]) == 0) { //water
						z = z_water;
						r = rgb_water[0];
						g = rgb_water[1];
						b = rgb_water[2];
					} else if (Integer.parseInt(csv[2]) == 1) { //landscape
						z = z_landscape;
						r = rgb_landscape[0];
						g = rgb_landscape[1];
						b = rgb_landscape[2];
					} else if (Integer.parseInt(csv[2]) == values[0]) {
						z = z_area;
						r = rgb_area[0];
						g = rgb_area[1];
						b = rgb_area[2];
					} else if (Integer.parseInt(csv[2]) == values[1]) {
						z = z_area3;
						r = rgb_area3[0];
						g = rgb_area3[1];
						b = rgb_area3[2];
					} else if (Integer.parseInt(csv[2]) == values[2]) {
						z = z_area4;
						r = rgb_area4[0];
						g = rgb_area4[1];
						b = rgb_area4[2];
					} else if (Integer.parseInt(csv[2]) == values[3]) {
						z = z_area5;
						r = rgb_area5[0];
						g = rgb_area5[1];
						b = rgb_area5[2];
					} else if (Integer.parseInt(csv[2]) == values[4]) {
						z = z_area6;
						r = rgb_area6[0];
						g = rgb_area6[1];
						b = rgb_area6[2];
					}
				}
				if (method.equals("SUM")) {
					if (Integer.parseInt(csv[2]) == 0) { //water
						z = z_water;
						r = rgb_water[0];
						g = rgb_water[1];
						b = rgb_water[2];
					} else if (Integer.parseInt(csv[2]) == 1) { //landscape
						z = z_landscape;
						r = rgb_landscape[0];
						g = rgb_landscape[1];
						b = rgb_landscape[2];
					} else if (Integer.parseInt(csv[2]) == 2) { // 2
						z = z_area;
						r = rgb_area[0];
						g = rgb_area[1];
						b = rgb_area[2];
					} else if (Integer.parseInt(csv[2]) > 2 && Integer.parseInt(csv[2]) <= 125) { // 123,125
						z = z_area3;
						r = rgb_area3[0];
						g = rgb_area3[1];
						b = rgb_area3[2];
					} else if (Integer.parseInt(csv[2]) > 125 && Integer.parseInt(csv[2]) <= 246) { // 244,246
						z = z_area4;
						r = rgb_area4[0];
						g = rgb_area4[1];
						b = rgb_area4[2];
					} else if (Integer.parseInt(csv[2]) > 246 && Integer.parseInt(csv[2]) <= 369) { // 355,357,367,369
						z = z_area5;
						r = rgb_area5[0];
						g = rgb_area5[1];
						b = rgb_area5[2];
					} else if (Integer.parseInt(csv[2]) > 369 && Integer.parseInt(csv[2]) <= 480) { // 466,468,478,480
						z = z_area6;
						r = rgb_area6[0];
						g = rgb_area6[1];
						b = rgb_area6[2];
					} else if (Integer.parseInt(csv[2]) > 480 && Integer.parseInt(csv[2]) <= 601) { // 589,591,599,601
						z = z_area7;
						r = rgb_area7[0];
						g = rgb_area7[1];
						b = rgb_area7[2];
					} else if (Integer.parseInt(csv[2]) > 601 && Integer.parseInt(csv[2]) <= 724) { // 710,722,724
						z = z_area8;
						r = rgb_area8[0];
						g = rgb_area8[1];
						b = rgb_area8[2];
					} else if (Integer.parseInt(csv[2]) > 724 && Integer.parseInt(csv[2]) <= 821) { // 821,823,833,835
						z = z_area9;
						r = rgb_area9[0];
						g = rgb_area9[1];
						b = rgb_area9[2];
					} else if (Integer.parseInt(csv[2]) > 821 && Integer.parseInt(csv[2]) <= 946) { // 944,946
						z = z_area10;
						r = rgb_area10[0];
						g = rgb_area10[1];
						b = rgb_area10[2];
					} else if (Integer.parseInt(csv[2]) > 946 && Integer.parseInt(csv[2]) <= 1067) { // 1065,1067
						z = z_area11;
						r = rgb_area11[0];
						g = rgb_area11[1];
						b = rgb_area11[2];
					} else if (Integer.parseInt(csv[2]) > 1067) { //1188,1190
						z = z_area12;
						r = rgb_area12[0];
						g = rgb_area12[1];
						b = rgb_area12[2];
					}
				}
				String tmp = "v " + x + " " + y + " " + z + " " + r + " " + g + " " + b;
				VERTEX_LIST.add(tmp);
				lauf++;
			}
			System.out.println("write vetices finished...");

			System.out.println("write face sets...");
			for (int i = 0; i < VERTEX_LIST.size(); i++) {
				if ((B + i + 1) < VERTEX_LIST.size()) {
					String tmp2 = "f " + (i) + " " + (i + 1) + " " + (B + i);
					String tmp3 = "f " + (B + i) + " " + (i + 1) + " " + (B + i + 1);
					FACESET_LIST.add(tmp2);
					FACESET_LIST.add(tmp3);
				}
			}
			////////////
			// OUTPUT //
			////////////
			System.out.println("write file...");
			File file = new File(output_path);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			try (BufferedWriter bw = new BufferedWriter(fw)) {
				DecimalFormat nf = new DecimalFormat();
				bw.write("# Vertices: " + nf.format(VERTEX_LIST.size()) + "\r\n");
				bw.write("# Faces: : " + nf.format(FACESET_LIST.size()) + "\r\n");
				System.out.println("Vertices: " + nf.format(VERTEX_LIST.size()));
				System.out.println("Faces: " + nf.format(FACESET_LIST.size()));
				for (String VERTEX_LIST1 : VERTEX_LIST) {
					bw.write(VERTEX_LIST1 + "\r\n");
				}
				for (String FACESET_LIST1 : FACESET_LIST) {
					bw.write(FACESET_LIST1 + "\r\n");
				}
			}
			System.out.println("FINISH!");
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error", e);
		}
	}
}
