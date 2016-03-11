package cryptography;

import java.io.*;
import java.util.PriorityQueue;

/**
 * Created by chenxiaoxue on 3/9/16.
 */

/**
 *
 */
public class FrequencyCalculatorForPlainText {
    static String alltext = "";
    static int[] frequency = new int[256];
    static int totalCharCount;

    public final static String FILE_NAME = "encodepractice_original";
    public final static char  SPACE_CODE= ' ';
    static final String  FOLDER_PATH = "src"+File.separator+"cryptography"+File.separator+"language"+File.separator;

    public static void main(String args[]) {
        totalCharCount = setFrequencyProfile(); //set up frequency array for mystery.txt
        PriorityQueue<CharFrequency> myFrequency = getMysteryFingerPrint();
        while(!myFrequency.isEmpty() ) {
            CharFrequency my = myFrequency.poll();

            System.out.println((int) my.letter + " " + my.letter + " " + my.frequency );
        }
    }


    public static PriorityQueue<CharFrequency> getMysteryFingerPrint() {
        //get finger print;
        PriorityQueue<CharFrequency> myFrequency = new PriorityQueue<CharFrequency>();
        for(int i = 0; i < frequency.length; i++) {
            if(frequency[i] > 0) {// ascii 127-160
                double result = ((double) frequency[i] / totalCharCount) * 100;
                CharFrequency charFrequency = new CharFrequency((char) i, (double) Math.round(result * 100) / 100);
                myFrequency.add(charFrequency);
            }
        }
        return myFrequency;
    }

    public static int setFrequencyProfile() {
        frequency = new int[256];
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        // read file ;
        int totalCharCount = 0;
        int test = 0;
        int lineNumber = 0;
        try {
            fileReader = new FileReader(FOLDER_PATH+FILE_NAME);
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while((line = bufferedReader.readLine()) != null) {
                line = line.toLowerCase();
                alltext += line + "\n";
                line = line.trim();
                //set frequency array
                for(int i = 0; i < line.length(); i++) {
                    if(line.charAt(i)==SPACE_CODE){ //skip space which is i, . = p
                        continue;
                    }
                    if((int)line.charAt(i)>256){
                        System.out.println(line.charAt(i)+"");
                    }
                    int numberValue = (int)line.charAt(i);

                    frequency[numberValue]++;
                    totalCharCount++;
                }

            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch(IOException ex) {
                System.out.println("Input output exception while processing file");
                ex.printStackTrace();
            }
        }
        return totalCharCount;
    }



}






