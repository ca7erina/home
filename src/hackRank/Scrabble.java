package hackRank;


import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Make yourself unbeatable at Scrabble by writing a computer program
 * that uses your letters to make the longest words possible and gives you
 * the top 10 suggestions.
 * You will need to load and use the file dictionary.txt which contains all of
 * the words in the English language.
 *
 * The output of your program might look something like this:
 Enter your letters:
 sdreletw

 Here are the top 10 suggestions:
 wrestled
 lewdest
 rewelds
 strewed
 swelter
 welders
 welters
 wrested
 wrestle
 desert
 *
 *
 */
public class Scrabble {
    public static final String  DIC_PATH = "src"+File.separator+"hackRank"+File.separator+"dictionary";

    static final String[][] scoringList = new String[][] { { "A", "1" },
            { "B", "3" }, { "C", "3" }, { "D", "2" }, { "E", "1" },
            { "F", "4" }, { "G", "2" }, { "H", "4" }, { "I", "1" },
            { "J", "8" }, { "K", "5" }, { "L", "1" }, { "M", "3" },
            { "N", "1" }, { "O", "1" }, { "P", "3" }, { "Q", "10" },
            { "R", "1" }, { "S", "1" }, { "T", "1" }, { "U", "1" },
            { "V", "4" }, { "W", "4" }, { "X", "8" }, { "Y", "4" },
            { "Z", "10" } };

    public static void main(String args[]){
        System.out.println("Enter your letters:");
        Scanner myScanner = new Scanner(System.in);
        String letters = myScanner.nextLine().trim();

        int topRank = 10;
        System.out.println("Here are the top "+topRank+" suggestions:");
        FileIO reader = new FileIO();
        String[] dic = reader.load(DIC_PATH);

        PriorityQueue<Node> result= getResult(letters,dic);

        if(result.size()>=10){
            for(int i=0;i<topRank;i++){
                Node temp =  result.poll();
                System.out.println(temp.word);
            }
        }else{
            System.out.println("Not enough result.");
        }

    }

    public static PriorityQueue<Node> getResult(String letters, String[] dic){
        PriorityQueue<Node> result= new PriorityQueue<Node>();
        for(String dicWord:dic){
            Node word = getNode(letters,dicWord.trim());//why trim?
            if (word!=null&&word.score>0){
                result.add(word);
            }
        }
        return result;

    }

    public static int getScoreByMatchingLetter(char letter){
        int result =0;
        for(int i=0;i<scoringList.length;i++){

            if(scoringList[i][0].equals((letter + "").toUpperCase())){
                result = Integer.parseInt(scoringList[i][1]);
            }
        }
       // System.out.println(result);
        return result;

    }


    public static Node getNode(String letters, String dicWord){
        Node result = null;
        String temp= dicWord;
        int score = 0;
        for(int i=0; i<letters.length();i++){
            //find
            char target = letters.charAt(i);
                int index = temp.indexOf(target);
                if(index >=0){ //contain this target char;
                    temp = temp.replaceFirst(target+"","");
                    //score=score+getScoreByMatchingLetter(target);
                    score ++;
                    result = new Node();
                }

        }
        if(temp.length()==0){
            result.word=dicWord;
            if(letters.length()==dicWord.length()){
                score++;
            }
            score = score+dicWord.length();
            result.score = score;
             return result;
        }


        return result;
    }

}

class Node implements Comparable<Node> {
    public String word ;
    public int score = 0;


    public Node()
    {
        word = "";
        score =0;
    }

    public int compareTo(Node object) {
        if(score - object.score > 0) { //score of the Node
            return -1;
        } else if(score - object.score < 0) {
            return 1;   //return 1 or -1 depending on whether these scores are bigger or smaller
        } else {
            //the score is the same, then compare letters
            if(word.compareTo(object.word)<0){
                return -1;
            }else if(word.compareTo(object.word)>0){
                return 1;
            }else{
                return 0;   //return 0 if they're the same score ,same word
            }


        }
    }
}

 class FileIO{

    public String[] load(String file) {
        File aFile = new File(file);
        StringBuffer contents = new StringBuffer();
        BufferedReader input = null;
        try {
            input = new BufferedReader( new FileReader(aFile) );
            String line = null;
            int i = 0;
            while (( line = input.readLine()) != null){
                contents.append(line);
                i++;
                contents.append(System.getProperty("line.separator"));
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("Can't find the file - are you sure the file is in this location: "+file);
            ex.printStackTrace();
        }
        catch (IOException ex){
            System.out.println("Input output exception while processing file");
            ex.printStackTrace();
        }
        finally {
            try {
                if (input!= null) {
                    input.close();
                }
            }
            catch (IOException ex) {
                System.out.println("Input output exception while processing file");
                ex.printStackTrace();
            }
        }
        String[] array = contents.toString().split("\n");
        for(int i=0;i<array.length;i++){
           array[i] = array[i].trim();
        }
        return array;
    }


    public void save(String file, String[] array) throws FileNotFoundException, IOException {

        File aFile = new File(file);
        Writer output = null;
        try {
            output = new BufferedWriter( new FileWriter(aFile) );
            for(int i=0;i<array.length;i++){
                output.write( array[i] );
                output.write(System.getProperty("line.separator"));
            }
        }
        finally {
            if (output != null) output.close();
        }
    }


}
