package objconverter;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * RUN file to create four OBJ files
 *
 * @author Florian Thiery
 */
public class Config {
	
	private String[] args = null;
	
	final static public String inputName = "output_sum"; //.xyz
	final static public String method = "SUM"; // SUM or MAX
	final static public String inputPath = "C:/temp/";
	final static public String outputPath = inputPath;
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ObjPeakConverter.main(args);
		System.out.println("");
		ObjPlainConverter.main(args);
		System.out.println("");
		ObjLayerConverter.main(args);
		System.out.println("");
		ObjSpaceTimePathConverter.main(args);
		System.out.println("");
	}
	
}
