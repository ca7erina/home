import java.io.*;
import java.text.DecimalFormat;
import java.util.PriorityQueue;

/**
 * Created by chenxiaoxue on 3/7/16.
 */
public class CharInt {
    public static void main(String[] args) {
//        char mychar = '©';
//        System.out.println((int)mychar);
//        System.out.println("\"");
//    for(int i=0;i<257;i++) {
//        System.out.println(i+" "+(char)i);
//    }


        //read encodedfile;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        String allcontent="";
//        PriorityQueue<CharFrequency> myFrequency = new PriorityQueue<CharFrequency>(); //one letter
        PriorityQueue<BiCharFrequency> myFrequency = new PriorityQueue<BiCharFrequency>();//bi
        try {
            fileReader = new FileReader("spanish_trigram_temp.txt");
            bufferedReader = new BufferedReader(fileReader);

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                line =line.trim().replaceAll(" +","");
                System.out.println(line);
                String item[] =line.split(":");
//               CharFrequency cf = new CharFrequency(item[0].charAt(0),item[1]); //one letter
                BiCharFrequency cf = new BiCharFrequency(item[0],item[1]); //bi
                myFrequency.add(cf);
            }



            //write file
            fileWriter = new FileWriter("spanish_trigram.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            while(!myFrequency.isEmpty()){
                BiCharFrequency cf=myFrequency.poll();
//                CharFrequency cf=myFrequency.poll();

                allcontent += cf.letters+" "+cf.frequency+"\n";
            }

            bufferedWriter.write(allcontent);


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
class CharFrequency implements Comparable<CharFrequency>{
    char letter;
    String frequency;

    public CharFrequency(char letter, String frequency){
        this.letter = letter;
        this.frequency = frequency;
    }


    public int compareTo(CharFrequency o) {
        if(Double.parseDouble(this.frequency)-Double.parseDouble(o.frequency)>0){ //compare the cumulative frequencies
            return -1;
        }else if(Double.parseDouble(this.frequency)-Double.parseDouble(o.frequency)<0){
            return 1;   //return 1 or -1 depending on whether these frequencies are bigger or smaller
        }else{
            return 0;   //return 0 if they're the same
        }


    }
}
class BiCharFrequency implements Comparable<BiCharFrequency> {
    String letters;
    String frequency;

    public BiCharFrequency(String letters, String frequency) {
        this.letters = letters;
        this.frequency = frequency;
    }


    public int compareTo(BiCharFrequency o) {
        if(Double.parseDouble(this.frequency) - Double.parseDouble(o.frequency) > 0) { //compare the cumulative frequencies
            return -1;
        } else if(Double.parseDouble(this.frequency) - Double.parseDouble(o.frequency) < 0) {
            return 1;   //return 1 or -1 depending on whether these frequencies are bigger or smaller
        } else {
            return 0;   //return 0 if they're the same
        }


    }
}