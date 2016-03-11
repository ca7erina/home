package cryptography;

import java.io.*;
import java.util.*;

/**
 * assume encoded text no space and punc
 */
public class SolveMystery_temp {
    static String alltext = "";
    static int[] frequency = new int[256];
    static int totalCharCount;

    public final static String ENCODED_FILE_NAME = "mystery";
     //public final static char  SPACE_CODE= ',';
//    public final static String ENCODED_FILE_NAME = "encoded_test";
//    public final static char  SPACE_CODE= ',';

    public static void main(String args[]) {

        totalCharCount = setFrequencyProfile(); //set up frequency array for mystery.txt

        //compare and get target finger print; -> finish and print;

        PriorityQueue<CharFrequency> targetLanguageFrequncy = getTargetLanguage();
        PriorityQueue<CharFrequency> myFrequency = getMysteryFingerPrint();

        // from finger print ,get answer table;
        int[][] answerTable = new int[256][2];

        while(!myFrequency.isEmpty() && !targetLanguageFrequncy.isEmpty()) {
            CharFrequency my = myFrequency.poll();
            CharFrequency fi = targetLanguageFrequncy.poll();

            // myFrequency should has no i;
            if(my.letter=='¡'||my.letter=='þ'){
                System.out.println("--------error");
            }

                answerTable[(int)'¡'][1] = ' ';
            answerTable[(int)'þ'][1] = '.';


            if(my.letter == '.') {
                int index = (int)'.';
                answerTable[index][1] = 'I';
                my = myFrequency.poll();
            }
            if(fi.letter=='I'){
                fi =targetLanguageFrequncy.poll();
            }

//
            if(fi.letter=='V'){
                fi =targetLanguageFrequncy.poll();
            }



            answerTable[my.letter][1] = fi.letter;

            System.out.println((int) my.letter + " " + my.letter + " " + my.frequency + ":" + (int) fi.letter + " " + fi.letter + " " + fi.frequency);
//            System.out.println(my.letter+" "+my.frequency+":"+" "+fi.letter+" "+fi.frequency);


        }

        while(!myFrequency.isEmpty()){
            CharFrequency my = myFrequency.poll();
            if((int)my.letter==133){
                answerTable[133][1] ='V';
                my = myFrequency.poll();
            }
            if(my.letter == '.') {
                int index = (int)'.';
                answerTable[index][1] = 'I';
                my = myFrequency.poll();
            }
        }

        // update by common finish words;
         swap(answerTable, 'D', 'G');

        System.out.println(alltext.length());
        String plaintxtV1 = writeFile(answerTable, alltext);
//        System.out.println(plaintxtV1);


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
            fileReader = new FileReader(ENCODED_FILE_NAME+".txt");
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while((line = bufferedReader.readLine()) != null) {

                alltext += line + "\n";
                line = line.trim();
                //set frequency array
                for(int i = 0; i < line.length(); i++) {
//                    if(line.charAt(i)=='¡'||line.charAt(i)=='þ'){ //skip space which is i, . = p
//                        continue;
//                    }
//                    if(line.charAt(i)==SPACE_CODE){ //skip space which is i, . = p
//                        continue;
//                    }
                    int numberValue = (int)line.charAt(i);

                    frequency[numberValue]++;
                    totalCharCount++;
                }
//                totalCharCount += line.trim().length(); //?
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
        answerTable[a][1] = B;
        answerTable[b][1] = A;
    }

    public static String writeFile(int[][] answerTable, String alltext) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            //write file
            fileWriter = new FileWriter(ENCODED_FILE_NAME+"_plain.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            //replace
            for(int i = 0; i < answerTable.length; i++) {
                if(answerTable[i][1] > 0) {
                    char mysteryChar = (char) i;
                    char decodeChar = (char) answerTable[i][1];
                    System.out.println(i + " " + mysteryChar + ":" + answerTable[i][1] + " " + decodeChar + "");
                    if(mysteryChar == '(' || (mysteryChar == ')') || (mysteryChar == '.') || (mysteryChar == '$')||(mysteryChar == '?')||(mysteryChar == '*')) {

                        alltext = alltext.replaceAll("\\" + mysteryChar, decodeChar + "");
                    } else {
                        alltext = alltext.replaceAll(mysteryChar + "", decodeChar + "");
                    }
                }
            }

            bufferedWriter.write(alltext);
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

        PriorityQueue<CharFrequency> fi = getFingerPrint("finnish");
        PriorityQueue<CharFrequency> sp = getFingerPrint("spanish");
        PriorityQueue<CharFrequency> fc = getFingerPrint("french");
        PriorityQueue<CharFrequency> en = getFingerPrint("english");
        PriorityQueue<CharFrequency> gr = getFingerPrint("german");
        PriorityQueue<CharFrequency> ru = getFingerPrint("russian");
        PriorityQueue<CharFrequency> sw = getFingerPrint("swedish");
        PriorityQueue<CharFrequency> po = getFingerPrint("polish");
        PriorityQueue<CharFrequency> ic = getFingerPrint("icelandic");
        PriorityQueue<CharFrequency> da = getFingerPrint("danish");

        double diff0 = getDifference(getMysteryFingerPrint(), fi);
        PriorityQueue<CharFrequency> result = fi;
        double min = diff0;
        result = getFingerPrint("finnish");
        String language ="finish";
        double diff1 = getDifference(getMysteryFingerPrint(), sp);
        if(diff1 < min) {
            min = diff1;
            result = getFingerPrint("spanish");
            language="spanish";
        }

        double diff2 = getDifference(getMysteryFingerPrint(), fc);
        if(diff2 < min) {
            min = diff2;
            result = getFingerPrint("french");
            language="french";

        }

        double diff3 = getDifference(getMysteryFingerPrint(), en);
        if(diff3 < min) {
            min = diff3;
            result = getFingerPrint("english");
            language="english";

        }

        double diff4 = getDifference(getMysteryFingerPrint(), gr);
        if(diff4 < min) {
            min = diff4;
            result = getFingerPrint("german");
            language="german";
        }

        double diff5 = getDifference(getMysteryFingerPrint(), ru);
        if(diff5 < min) {
            min = diff5;
            result = getFingerPrint("russian");
            language="russian";
        }

        double diff6 = getDifference(getMysteryFingerPrint(), sw);
        if(diff6 < min) {
            min = diff6;
            result = getFingerPrint("swedish");
            language="swedish";
        }

        double diff7 = getDifference(getMysteryFingerPrint(), po);
        if(diff7 < min) {
            min = diff7;
            result = getFingerPrint("polish");
            language="polish";
        }

        double diff8 = getDifference(getMysteryFingerPrint(), ic);
        if(diff8 < min) {
            min = diff8;
            result = getFingerPrint("icelandic");
            language="icelandic";
        }
        double diff9 = getDifference(getMysteryFingerPrint(), da);
        if(diff9 < min) {
            min = diff9;
            result = getFingerPrint("danish");
            language="danish";
        }
        System.out.println(language);
        System.out.println(diff0 + " " + diff1 + " " + diff2 + " " + diff3+" " + diff4 + " " + diff5 + " " + diff6+" " + diff7 + " " + diff8 + " " + diff9);
        return result;
    }

    public static double getDifference(PriorityQueue<CharFrequency> mystrery, PriorityQueue<CharFrequency> aLanguage) {
        double result = 0.0;
        PriorityQueue<CharFrequency> my = mystrery;
        PriorityQueue<CharFrequency> other = aLanguage;

        while(!my.isEmpty() && !other.isEmpty()) {

            CharFrequency m = my.poll();
            CharFrequency o = other.poll();
            double number = Math.pow(m.frequency, 2.0) - Math.pow(o.frequency, 2.0);
//            System.out.println();
            result += Math.sqrt(Math.abs(number));
        }
        return result;
    }

    public static PriorityQueue<CharFrequency> getFingerPrint(String filename) {
        PriorityQueue<CharFrequency> PQ = new PriorityQueue<CharFrequency>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        int total = 0;
        // read file ;
        try {
            fileReader = new FileReader(filename + ".txt");
            bufferedReader = new BufferedReader(fileReader);
            String line = "";

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
//
//class CharFrequency implements Comparable<CharFrequency> {
//    char letter;
//    double frequency;
//
//    public CharFrequency(char letter, double frequency) {
//        this.letter = letter;
//        this.frequency = frequency;
//    }
//
//
//    public int compareTo(CharFrequency o) {
//        if(this.frequency - o.frequency > 0) { //compare the cumulative frequencies
//            return -1;
//        } else if(this.frequency - o.frequency < 0) {
//            return 1;   //return 1 or -1 depending on whether these frequencies are bigger or smaller
//        } else {
//            return 0;   //return 0 if they're the same
//        }
//
//
//    }
//}
//
//
//class FileIO {
//
//    public String[] load(String file) {
//        File aFile = new File(file);
//        StringBuffer contents = new StringBuffer();
//        BufferedReader input = null;
//        try {
//            input = new BufferedReader(new FileReader(aFile));
//            String line = null;
//            int i = 0;
//            while((line = input.readLine()) != null) {
//                contents.append(line);
//                i++;
//                contents.append(System.getProperty("line.separator"));
//            }
//        } catch(FileNotFoundException ex) {
//            System.out.println("Can't find the file - are you sure the file is in this location: " + file);
//            ex.printStackTrace();
//        } catch(IOException ex) {
//            System.out.println("Input output exception while processing file");
//            ex.printStackTrace();
//        } finally {
//            try {
//                if(input != null) {
//                    input.close();
//                }
//            } catch(IOException ex) {
//                System.out.println("Input output exception while processing file");
//                ex.printStackTrace();
//            }
//        }
//        String[] array = contents.toString().split("\n");
//        for(int i = 0; i < array.length; i++) {
//            array[i] = array[i].trim();
//        }
//        return array;
//    }
//
//
//    public void save(String file, String[] array) throws FileNotFoundException, IOException {
//
//        File aFile = new File(file);
//        Writer output = null;
//        try {
//            output = new BufferedWriter(new FileWriter(aFile));
//            for(int i = 0; i < array.length; i++) {
//                output.write(array[i]);
//                output.write(System.getProperty("line.separator"));
//            }
//        } finally {
//            if(output != null) output.close();
//        }
//    }
//
//
//}
