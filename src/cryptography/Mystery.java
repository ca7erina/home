package cryptography;


import java.io.*;
import java.util.PriorityQueue;


/**
 * assume encoded text no space and punc
 */
public class Mystery {
    static String alltext = "";
    static int[] frequency = new int[256];
    static int totalCharCount;
    static int[][] answerTable = new int[256][2];
    static int[] prioirty = new int[256];
    static int lettersAlphabetCount=0;
    static String targetLanguage;
    public static final String  FOLDER_PATH = "src"+File.separator+"cryptography"+File.separator+"language"+File.separator;

    public final static String ENCODED_FILE_NAME = "mystery";
//    public final static String ENCODED_FILE_NAME = "encodepractice_original";

    public static void main(String args[]) {

        totalCharCount = setFrequencyProfile();

        //sort letter frequncy in priority queue
        PriorityQueue<CharFrequency> myFrequency = getMysteryFingerPrint();
        PriorityQueue<CharFrequency> targetLanguageFrequncy = getTargetLanguage(); //get possible language frequency and put in queue


        //show mystery letter frequency and closest language frequency
        while(!myFrequency.isEmpty() && !targetLanguageFrequncy.isEmpty()) {
            CharFrequency my = myFrequency.poll();
            CharFrequency fi = targetLanguageFrequncy.poll();
            System.out.println((int) my.letter + " " + my.letter + " " + my.frequency + ":" + (int) fi.letter + " " + fi.letter + " " + fi.frequency);
        }

        //generate answer key table
        answerTable= generateAnswerTable();
        writePlainOutPutFile(answerTable, alltext); //write plain text based on answerTable

        System.out.println("encoded file contains total character:"+lettersAlphabetCount);
        System.out.println("encoded file txt length:"+alltext.length());
    }

    private static int[][] generateAnswerTable() {
        PriorityQueue<CharFrequency> myFrequency = getMysteryFingerPrint();
        PriorityQueue<CharFrequency> targetLanguageFrequncy = getTargetLanguage();
        int [][] result=new int[256][2];
        //compare mystery letter frequency and closest language frequency
        while(!myFrequency.isEmpty() && !targetLanguageFrequncy.isEmpty()) {
            CharFrequency my = myFrequency.poll();
            CharFrequency fi = targetLanguageFrequncy.poll();
            //make answer key table
            if(my.letter == '.') {
                int index = (int)'.';
                result[index][1] = 'I';
                my = myFrequency.poll();
            }
            if(fi.letter=='I'){
                fi =targetLanguageFrequncy.poll();
            }

            if(fi.letter=='V'){
                fi =targetLanguageFrequncy.poll();
            }
            result[my.letter][1] = fi.letter;

        }
        while(!myFrequency.isEmpty()){
            CharFrequency my = myFrequency.poll();
            if((int)my.letter==133){
                result[133][1] ='V';
                myFrequency.poll();
            }
            if(my.letter == '.') {
                int index = (int)'.';
                result[index][1] = 'I';
                myFrequency.poll();
            }
        }

        // update by common finish words;
//           swap(result, 'C', 'P');
//        swap(result, 'M', 'B');
//
//        swap(result, 'C', 'U');
//        swap(result, 'L', 'D');
//        swap(result, 'F', 'M');
//        swap(result, 'O', 'I');
//        swap(result, 'G', 'Y');
//        swap(result, 'S', 'R');
//        swap(result, 'B', 'W');
//        swap(result, 'B', 'G');
//        swap(result, 'Z', 'X');

        //print answertable
        for(int i=0;i<answerTable.length;i++){
            if(answerTable[i][1]>0){
                System.out.println(i+" "+(char)i+" : "+answerTable[i][1]+" "+(char)answerTable[i][1]);
            }
        }
        return result;
    }


    public static PriorityQueue<CharFrequency> getMysteryFingerPrint() {
    int count=0;
        //get finger print;
        PriorityQueue<CharFrequency> myFrequency = new PriorityQueue<CharFrequency>();
        for(int i = 0; i < frequency.length; i++) {
            if(frequency[i] > 0) {// ascii 127-160
                count++;
                double result = ((double) frequency[i] / totalCharCount) * 100;
                CharFrequency charFrequency = new CharFrequency((char) i, (double) Math.round(result * 100) / 100);
                myFrequency.add(charFrequency);
            }
        }
       lettersAlphabetCount = count;
        return myFrequency;

    }

    public static int setFrequencyProfile() {
        frequency = new int[256];
        // read file ;
        int totalCharCount = 0;
        FileIO loader = new FileIO();
        String[] sentences = loader.load(FOLDER_PATH+ENCODED_FILE_NAME);

        //set frequency array

        for(String sentence:sentences ) {
            if(sentence.length() ==0 ) {
                continue;
            }
            for(int j = 0; j < sentence.length(); j++) {
                int numberValue = (int) sentence.charAt(j);
                frequency[numberValue]++;
                totalCharCount++;
            }
            alltext += sentence + "\n";

        }
        return totalCharCount;
    }


    public static int[] getHigherPriorityArray(int[][] answerTable) {
        int[] result = new int[256];
        for(int[] answerkey:answerTable ) {
            if(answerkey[1] > 0) {
                result[answerkey[1]] = 1;
            }

        }
        prioirty = result;
        return result;
    }

    public static String writePlainOutPutFile(int[][] answerTable, String alltext) {
        int higherPriority[] = getHigherPriorityArray(answerTable);
        StringBuilder all = new StringBuilder(alltext);
        FileWriter fileWriter ;
        BufferedWriter bufferedWriter = null;
        try {
            //write file
            fileWriter = new FileWriter(FOLDER_PATH+ENCODED_FILE_NAME + "_plain.txt");
            bufferedWriter = new BufferedWriter(fileWriter);

            //replace higher priority first
            for(int i = 0; i < answerTable.length; i++) {
                char mysteryChar = (char) i;
                char decodeChar = (char) answerTable[i][1];
                for(int j = 0; j < higherPriority.length; j++) {
                    if(higherPriority[j] == 1 && i == j && answerTable[i][1] > 0) {
                        for(int k = 0; k < all.length(); k++) {
                            if(all.charAt(k) == mysteryChar) {
                                all.setCharAt(k, decodeChar);
                            }
                        }
                        answerTable[i][1] = 0;// after set =0, wont be used later.
                    }
                }
            }

            //replace rest
            for(int i = 0; i < answerTable.length; i++) {
                if(answerTable[i][1] > 0) {
                    char mysteryChar = (char) i;
                    char decodeChar = (char) answerTable[i][1];
                    System.out.println(i + " " + mysteryChar + ":" + answerTable[i][1] + " " + decodeChar + "");
                    for(int j = 0; j < all.length(); j++) {
                        if(all.charAt(j) == mysteryChar) {
                            all.setCharAt(j, decodeChar);
                        }
                    }
                }
            }

            bufferedWriter.write(all.toString());

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch(IOException ex) {
                System.out.println("Input output exception while processing file");
                ex.printStackTrace();
            }
        }
        return alltext;
    }

    public static PriorityQueue<CharFrequency> getTargetLanguage() {
        String languages[] = {"finnish","spanish","french","english","german","russian","swedish","polish","icelandic","danish"};
        double diff;
        PriorityQueue<CharFrequency> result = null;
        double min = Double.MAX_VALUE;
        String language="default";
        if(targetLanguage==null){

            for(String lan:languages){
                PriorityQueue<CharFrequency> pq = getFingerPrint(lan);
                diff = getDifference(getMysteryFingerPrint(), pq);
                if(diff<min){
                    min = diff;
                    result = getFingerPrint(lan);
                    language = lan;
                }
                System.out.println(lan+" diff: "+diff);
            }

            System.out.println("--> "+language);
            targetLanguage = language;
        }else{
            result = getFingerPrint(targetLanguage);
        }


        return result;
    }

    public static double getDifference(PriorityQueue<CharFrequency> mystrery, PriorityQueue<CharFrequency> aLanguage) {
        double result = 0.0;
        while(!mystrery.isEmpty() && !aLanguage.isEmpty()) {
            CharFrequency m = mystrery.poll();
            CharFrequency o = aLanguage.poll();
            double number = Math.pow(m.frequency, 2.0) - Math.pow(o.frequency, 2.0);
            result += Math.sqrt(Math.abs(number));
        }
        return result;
    }

    public static PriorityQueue<CharFrequency> getFingerPrint(String filename) {

        PriorityQueue<CharFrequency> PQ = new PriorityQueue<CharFrequency>();
        FileReader fileReader;
        BufferedReader bufferedReader = null;

        // read file ;
        try {
            fileReader = new FileReader(FOLDER_PATH+filename);
            bufferedReader = new BufferedReader(fileReader);
            String line;

            while((line = bufferedReader.readLine()) != null) {
                String result[] = line.trim().split(" ");
                double frequency = Double.parseDouble(result[1].trim());
                CharFrequency charFrequency = new CharFrequency(result[0].charAt(0), frequency);
                PQ.add(charFrequency);
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
        return PQ;
    }

    public static void swap(int[][] answerTable, char A, char B) {
        int a = 0;
        int b = 0;
        for(int i = 0; i < answerTable.length; i++) {
            if((char) answerTable[i][1] == A) {
                a = i;
            }
            if((char) answerTable[i][1] == B) {
                b = i;
            }
        }
        System.out.println("swap set:" + a + " " + (char) a + " " + B);
        System.out.println("swap set:" + b + " " + (char) b + " " + A);
        answerTable[a][1] = B;
        answerTable[b][1] = A;
    }

}

class CharFrequency implements Comparable<CharFrequency> {
    char letter;
    double frequency;

    public CharFrequency(char letter, double frequency) {
        this.letter = letter;
        this.frequency = frequency;
    }



@SuppressWarnings("NullableProblems")
    public int compareTo(CharFrequency o) {

            if(this.frequency - o.frequency > 0) { //compare the cumulative frequencies
                return -1;
            } else if(this.frequency - o.frequency < 0) {
                return 1;   //return 1 or -1 depending on whether these frequencies are bigger or smaller
            } else {
                return 0;   //return 0 if they're the same
            }




    }
}


class FileIO {

    public String[] load(String file) {
        File aFile = new File(file);
        StringBuilder contents = new StringBuilder();
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(aFile));
            String line ;
            int i = 0;
            while((line = input.readLine()) != null) {
                contents.append(line);
                i=i+1;
                contents.append(System.getProperty("line.separator"));
            }
        } catch(FileNotFoundException ex) {
            System.out.println("Can't find the file - are you sure the file is in this location: " + file);
            ex.printStackTrace();
        } catch(IOException ex) {
            System.out.println("Input output exception while processing file");
            ex.printStackTrace();
        } finally {
            try {
                if(input != null) {
                    input.close();
                }
            } catch(IOException ex) {
                System.out.println("Input output exception while processing file");
                ex.printStackTrace();
            }
        }
        String[] array = contents.toString().split("\n");
        for(int i = 0; i < array.length; i++) {
            array[i] = array[i].trim();
        }
        return array;
    }

    public void save(String file, String[] array) throws IOException {
        File aFile = new File(file);
        Writer output = null;
        try {
            output = new BufferedWriter(new FileWriter(aFile));

             for(String s:array){
                output.write(s);
                output.write(System.getProperty("line.separator"));
            }
        } finally {
            if(output != null) output.close();
        }
    }


}
