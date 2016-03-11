package cryptography;

import java.io.*;
import java.util.PriorityQueue;



/**
 *
 */
public class SolveMystery3 {
    static String alltext = "";
    static String[][] showtimes ;
    static int totalCharCount;
    public static final String FOLDER_PATH = "src"+File.separator+"cryptography"+File.separator+"language"+File.separator;
    public static final String BIGRAM_FILE_NAME = "spanish_trigram";
    public static final String MYSTERY_FILE_NAME = "mystery";


    public static void main(String args[]) {
        totalCharCount = setTriFrequencyProfile();
        PriorityQueue<BiCharFrequency> targetLanguageFrequncy = getFingerPrint(BIGRAM_FILE_NAME);
        PriorityQueue<BiCharFrequency> myFrequency = getMysteryFingerPrint();
        while(!myFrequency.isEmpty() && !targetLanguageFrequncy.isEmpty()) {
            BiCharFrequency my = myFrequency.poll();
            BiCharFrequency fi = targetLanguageFrequncy.poll();
            System.out.println( my.letters + " " + my.frequency + ":" + fi.letters + " " + fi.frequency);
        }
    }


    public static PriorityQueue<BiCharFrequency> getMysteryFingerPrint() {
        //get finger print;
        PriorityQueue<BiCharFrequency> myFrequency = new PriorityQueue<BiCharFrequency>();
        for(int i = 0; i < showtimes.length; i++) {
            if(showtimes[i][1]!=null) {// ascii 127-160
                double result = (Double.parseDouble(showtimes[i][1]) / totalCharCount) * 100;
                BiCharFrequency bicharFrequency = new BiCharFrequency(showtimes[i][0], (double) Math.round(result * 100) / 100);
                myFrequency.add(bicharFrequency);
            }else{
                System.out.println("possible length:"+i);
                break;
            }
        }
        return myFrequency;
    }

    public static int setTriFrequencyProfile() {
        showtimes = new String[256][2];
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        // read file ;
        int totalCharCount = 0;
        int test = 0;
        int lineNumber = 0;
        try {
            fileReader = new FileReader(FOLDER_PATH+MYSTERY_FILE_NAME);
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while((line = bufferedReader.readLine()) != null) {
                alltext += line + "\n";
                line = line.trim();
                //set frequency array
                if(line.length()>5){
                    for(int i = 0; i < line.length()-2; i++) {
                        String letters = line.substring(i,i+3);
                        addTrilettersToFrequencyArray(showtimes,letters);
                        totalCharCount++;
                    }
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

    public static void addTrilettersToFrequencyArray(String[][] showtimes, String bi) {
        int i;
        for(i=0;i<showtimes.length;i++){
            if(showtimes[i][0]==null){//didn't find, no this bi
                showtimes[i][0]=bi;
                showtimes[i][1]="1";
                break;
            }else if(showtimes[i][0].equals(bi)){
                int times = Integer.parseInt(showtimes[i][1]);
                times++;
                showtimes[i][1]=times+"";
                break;
            }
        }

    }




    public static PriorityQueue<BiCharFrequency> getFingerPrint(String filename) {
        PriorityQueue<BiCharFrequency> PQ = new PriorityQueue<BiCharFrequency>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        int total = 0;
        // read file ;
        try {
            fileReader = new FileReader(FOLDER_PATH+filename);
            bufferedReader = new BufferedReader(fileReader);
            String line = "";

            while((line = bufferedReader.readLine()) != null) {
                String result[] = line.trim().split(" ");
                double frequency = Double.parseDouble(result[1].trim());
                BiCharFrequency charFrequency = new BiCharFrequency(result[0], frequency);
                PQ.add(charFrequency);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedReader != null) {
                    bufferedReader.close();

                }
                if(bufferedWriter != null) {
                    bufferedWriter.close();

                }

            } catch(IOException ex) {
                System.out.println("Input output exception while processing file");
                ex.printStackTrace();
            }
        }
        return PQ;
    }


}

class TriCharFrequency implements Comparable<TriCharFrequency> {
    String letters;
    double frequency;

    public TriCharFrequency(String letters, double frequency) {
        this.letters = letters;
        this.frequency = frequency;
    }


    public int compareTo(TriCharFrequency o) {
        if(this.frequency - o.frequency > 0) { //compare the cumulative frequencies
            return -1;
        } else if(this.frequency - o.frequency < 0) {
            return 1;   //return 1 or -1 depending on whether these frequencies are bigger or smaller
        } else {
            return 0;   //return 0 if they're the same
        }
    }
}






