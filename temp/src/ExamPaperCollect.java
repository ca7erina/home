import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by chenxiaoxue on 2/4/16.
 */
public class ExamPaperCollect {

    static ArrayList<Question> questions = new ArrayList<Question>();
    static MyResult result[];
    static final int SUBJECT = 335;

    public static void main(String[] args) {
        try {
            getResult();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        //print all questions
//        for(Question q:questions){
//            System.out.println(q.id);
//            System.out.println(q.content);
//        }

        setRate();
        print();
    }

    /**
     * Remove the No. at the begainning of the question.
     * @param sentence
     * @return
     */
    public static String removeOriginalNumber(int subject, String sentence){
        String result ="";
        if(subject==240){
            if(sentence.charAt(1)== ' '){
                for(int i=2;i<sentence.length();i++){
                    result+=sentence.charAt(i);
                }
            }
            if(sentence.charAt(2)== ' '){
                for(int i=3;i<sentence.length();i++){
                    result+=sentence.charAt(i);
                }
            }
            if(sentence.charAt(3)== ' '){
                for(int i=4;i<sentence.length();i++){
                    result+=sentence.charAt(i);
                }
            }
        }
        if(subject==335){
            Pattern cs335_2= Pattern.compile("(^[0-9][0-9] \\((a|b|c|d)\\)).*");
            Pattern cs335_1= Pattern.compile("(^[0-9] \\((a|b|c|d)\\)).*");
            Pattern cs335_3= Pattern.compile("(^[0-9][0-9]\\. \\((a|b|c|d)\\)).*");
            Pattern cs335_4= Pattern.compile("(^[0-9]\\. \\((a|b|c|d)\\)).*");
            Pattern cs335_5= Pattern.compile("(^[0-9]\\((a|b|c|d)\\)).*");
            if(cs335_1.matcher(sentence).matches()){//2 (a)
                for(int i=6;i<sentence.length();i++){
                    result+=sentence.charAt(i);
                }
            }else if(cs335_2.matcher(sentence).matches()){//10 (a)
                for(int i=7;i<sentence.length();i++){
                    result+=sentence.charAt(i);
                }
            }else if(cs335_3.matcher(sentence).matches()){//10. (a)
                for(int i=7;i<sentence.length();i++){
                    result+=sentence.charAt(i);
                }
            }else if(cs335_4.matcher(sentence).matches()){//1. (a)
                for(int i=6;i<sentence.length();i++){
                    result+=sentence.charAt(i);
                }
            }else if(cs335_5.matcher(sentence).matches()){//1.(a)
                for(int i=5;i<sentence.length();i++){
                    result+=sentence.charAt(i);
                }
            }else{ //(a)
                for(int i=4;i<sentence.length();i++){
                    result+=sentence.charAt(i);
                }
            }
        }



        return result.trim();
    }

    public static boolean matchQuestionLine(int subject, String line){
        boolean result = false;
        switch(subject){
            case 240:
                Pattern cs240 = Pattern.compile("^[0-9]([0-9]|\\s|\\.).*");
                result= cs240.matcher(line).matches();
                break;
            case 335:
                //1 (a)
                Pattern cs335 = Pattern.compile("((^[0-9][0-9]*(\\.)*( )*\\((a|b|c|d)\\))|^\\((a|b|c|d)\\)).*");
                result= cs335.matcher(line).matches();
                break;
        }

      return result;

    }

    /**
     * Get all questions from exam.txt and store them in questions.
     * @return
     * @throws FileNotFoundException
     */
    public static double[][] getResult() throws FileNotFoundException {
        double[][] result = new double[229][4];
        int READ_AHEAD_LIMIT = 100000000;
        FileReader fr = new FileReader("examcs"+SUBJECT+".txt");
        BufferedReader br = new BufferedReader(fr);
        try {
            String line;
            int questionCount=0;
            while((line = br.readLine()) != null) {
                line = line.trim();
//                if(line.matches("^[0-9]([0-9]|\\s|\\.).*")) {
                if(matchQuestionLine(SUBJECT,line)) {
                    String question = removeOriginalNumber(SUBJECT,line) + " ";
                    //mark previous sentence
                    br.mark(READ_AHEAD_LIMIT);
                    while((line = br.readLine()) != null) {
                        if(line.contains("Marks]") || line.contains("CS"+SUBJECT)) {
                            continue;
                        }
                        if(matchQuestionLine(SUBJECT, line)) {
                            break;
                        } else {
                            question += line + " ";
                        }
                    }
                    //get to number line, go back to last sentence
                    br.reset();
                    Question q = new Question();
                    q.id = questionCount++;
                    q.content=question.trim();
                    questions.add(q);
                    //  System.out.println(question);
                }

            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * calculate each sentence similarity with other sentence.
     */
    public static void setRate() {
        result = new MyResult[questions.size()];

        int index = 0;
        for(int i = 0; i < questions.size(); i++) {
            int max = 0;
            MyResult tempResult = new MyResult();
            for(int j = i+1; j < questions.size(); j++) {
//                if(i==j){
//                    continue;
//                }
                String str1[] = questions.get(i).content.split(" ");
                String str2[] = questions.get(j).content.split(" ");

                int count = 0;

                count=getSimilarity(str1, str2);

                if(count > max) {
                    max = count;
                    tempResult.similarity = max;
                    tempResult.sentence1Id = questions.get(i).id;
                    tempResult.sentence2Id = questions.get(j).id;
                }




            }

            if (tempResult.similarity==0){
                tempResult.sentence1Id = questions.get(i).id;
                tempResult.sentence2Id = 0;
            }
            result[index++] = tempResult;
           // System.out.println(tempResult.sentence1Id+" " +tempResult.sentence2Id+" "+tempResult.similarity);



        }

    }
    public static int getSimilarity(String str1[],String str2[]){
        int count=0;
        for(int x = 0; x < str1.length - 2; x++) {
            for(int y = 0; y < str2.length - 2; y++) {
                if(str1[x].equalsIgnoreCase("the")){
                    continue;
                }

                if(str1[x].trim().equalsIgnoreCase(str2[y].trim())) {
                    if(str1[x + 1].trim().equalsIgnoreCase(str2[y + 1].trim())) {
                        if(str1[x + 2].trim().equalsIgnoreCase(str2[y + 2].trim())) {
                            count++;
                        }

                    }
                }

            }

        }
    return count;
    }

    public static void print() {
        MyResult printresult[] = new MyResult[result.length];

        //copy
        for(int i = 0; i < result.length; i++) {
            printresult[i] = result[i];
        }

        for(int i = printresult.length - 1; i > 0; i--) {
            for(int j = 0; j < i; j++) {
                if(printresult[j].similarity < printresult[j + 1].similarity) {
                    MyResult temp;
                    temp = printresult[j];
                    printresult[j] = printresult[j + 1];
                    printresult[j + 1] = temp;
                }
            }
        }

        for(int i = 0; i < printresult.length; i++) {
          //  System.out.println(printresult[i].count);
            if(printresult[i].similarity!=0){
               // System.out.println(printresult[i].similarity);
                System.out.println(questions.get(printresult[i].sentence1Id).content);
                System.out.println("---");
                System.out.println();
                System.out.println(questions.get(printresult[i].sentence2Id).content);
                System.out.println("---");
                System.out.println();
            }else{
                System.out.println(questions.get(printresult[i].sentence1Id).content);
                System.out.println("---");
                System.out.println();
            }


        }
    }
}

class MyResult {
    int sentence1Id;
    int sentence2Id;
    int similarity;
}

class Question{
    int id;
    String content;
}


