package genericCheckpointing.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author vaibhav
 * Class to redirect outputs to file
 */
public class FileOut {

	private static PrintWriter pw;

	/**
	 * @param filename
	 * method to set output filename
	 */
	public static void setFile(String filename) {
		try {
			pw = new PrintWriter(new File(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem With Output File");
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * @param output
	 * method to write to file
	 */
	public static void write(String output){
			pw.print(output);
	}
	/**
	 * method to close output file
	 */
	public static void closeFile() {
		pw.close();
	}

	

	public String toString() {
		return "FileOut";
	}
}
