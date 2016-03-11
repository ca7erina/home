package algorithm.huffmanCompression;

import java.io.*;


public class Compressor {
	public static final String  FOLDER_PATH = "src"+ File.separator+"algorithm"+File.separator+"huffmanCompression"+File.separator;

	public final static String ORIGIN_FILE_NAME = "original";

	public static void main(String[] args) {

		int[] frequencyTable =encodeFile(ORIGIN_FILE_NAME, ORIGIN_FILE_NAME+".encoded");
		decodeFile(ORIGIN_FILE_NAME+".encoded",frequencyTable,ORIGIN_FILE_NAME+".plain");

	}

	public static int[] encodeFile(String orignfilename, String outPutencodedfilename) {
		int[] frequencyTable = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		// read file ;
		try {
			fileReader = new FileReader(FOLDER_PATH+orignfilename);
			bufferedReader = new BufferedReader(fileReader);

			String allContent = "";
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				allContent += line + "\n";

			}


			//encode and writer code
			fileWriter = new FileWriter(FOLDER_PATH+outPutencodedfilename);
			bufferedWriter = new BufferedWriter(fileWriter);
			frequencyTable =Huffman.getFrequencyArray(allContent); 
			String encoded = Huffman.encode(frequencyTable, allContent);
			bufferedWriter.write(encoded);


		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (bufferedReader!= null) {
					bufferedReader.close();

				}
				if (bufferedWriter!= null) {
					bufferedWriter.close();

				}

			}
			catch (IOException ex) {
				System.out.println("Input output exception while processing file");
				ex.printStackTrace();
			}

		}
		return frequencyTable;
	}

	public static void decodeFile(String encodedfilename, int[] frequencyTable, String outPutplainTextFilename) {
		//read encodedfile;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileReader = new FileReader(FOLDER_PATH+encodedfilename);
			bufferedReader = new BufferedReader(fileReader);

			String allCode = "";
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				allCode += line + "\n";

			}

			//decode
			String content = Huffman.decode(frequencyTable, allCode);
			
			//write file
			fileWriter = new FileWriter(FOLDER_PATH+outPutplainTextFilename);
			bufferedWriter = new BufferedWriter(fileWriter);
		
	
			bufferedWriter.write(content);

			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if (bufferedReader!= null) {
					bufferedReader.close();

				}
				if (bufferedWriter!= null) {
					bufferedWriter.close();

				}

			}
			catch (IOException ex) {
				System.out.println("Input output exception while processing file");
				ex.printStackTrace();
			}
		}
		
		
	}

}
