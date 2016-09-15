package genericCheckpointing.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {
	private String fileName;
	private FileReader fileReader;
	private BufferedReader bufferedReader;

	/**
	 * @param filename
	 *            name of the input file
	 */
	public FileProcessor(String filename) {
		setFileName(filename);
		try {
			setFileReader(new FileReader(getFileName())); // taken code
			setBufferedReader(new BufferedReader(fileReader)); // taken code
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot Open The File");
			System.out.println(e.getMessage());
			e.printStackTrace();

		} finally {
		}
	}

	/**
	 * @return line from file Method to read a line from a file and return it
	 */
	public String readLineFromFile() {

		String line = null;

		try {
			line = getBufferedReader().readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem Reading line");
			e.printStackTrace();
		} finally {
		}

		return line;

	}

	/**
	 * Method to close the opened file
	 */
	public void closeFile() {
		try {
			getFileReader().close();
			getBufferedReader().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem Closing The File");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
		}

	}

	/* Getters and Setters */
	/**
	 * @return filename
	 */
	private String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 */
	private void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return fileReader
	 */
	private FileReader getFileReader() {
		return fileReader;
	}

	/**
	 * @param fileReader
	 */
	private void setFileReader(FileReader fileReader) {
		this.fileReader = fileReader;
	}

	/**
	 * @return bufferedReader
	 */
	private BufferedReader getBufferedReader() {
		return bufferedReader;
	}

	/**
	 * @param bufferedReader
	 */
	private void setBufferedReader(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	@Override
	public String toString() {
		return "FileProcessor";
	}
}