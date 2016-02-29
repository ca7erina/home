package algorithm.huffmanConpression;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class Compressor {

	public static void main(String[] args) {

		int[] frequencyTable =encodeFile("original.txt", "original.encoded");
		decodeFile("original.encoded",frequencyTable,"plain.txt");

	}

	public static int[] encodeFile(String orignfilename, String outPutencodedfilename) {
		int[] frequencyTable = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		// read file ;
		try {
			fileReader = new FileReader(orignfilename);
			bufferedReader = new BufferedReader(fileReader);

			String allContent = "";
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				allContent += line + "\n";

			}


			//encode and writer code
			fileWriter = new FileWriter(outPutencodedfilename);
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
			fileReader = new FileReader(encodedfilename);
			bufferedReader = new BufferedReader(fileReader);

			String allCode = "";
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				allCode += line + "\n";

			}

			//decode
			String content = Huffman.decode(frequencyTable, allCode);
			
			//write file
			fileWriter = new FileWriter(outPutplainTextFilename);
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
