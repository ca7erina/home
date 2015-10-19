import jdk.internal.jfr.events.FileReadEvent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by chenxiaoxue on 10/3/15.
 */
public class Temp {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("my score: 15, 2.25");

        double[][] result = getResult();

        System.out.println("average score: "+ getAverageScore(result));

        System.out.println("average ca score: "+ getAverageCAScore(result));
        setRankOfScore(result);

        getMyposition(result);

    }

    private static void getMyposition(double[][] result) {
        for(int i=0;i<result.length;i++){
            if(result[i][1]==15) {
                System.out.println(i + " students before me.");
break;
            }

        }

        for(int i=0;i<result.length;i++){
            if(result[i][1]==14){
                System.out.println(result.length-i-1+" students behind me.");
                break;
            }
        }

    }

    private static double getAverageScore(double result[][]) {
        double sum=0.0;
        for(int i =0; i<result.length;i++){
            sum +=result[i][1];
        }
        return sum/result.length;
    }

    private static double getAverageCAScore(double result[][]) {
        double sum=0.0;
        for(int i =0; i<result.length;i++){
            sum +=result[i][2];
        }
        return sum/result.length;
    }


    private static void setRankOfScore(double result[][]){

        for(int i =0; i<result.length;i++){
            for(int j=0;j<result.length-i-1;j++){
                if(result[j][1]<result[j+1][1]){
                    double temp[];
                    temp = result[j];
                    result[j]=result[j+1];
                    result[j+1]=temp;
                }
            }
        }

//        for(int i = 0; i < result.length; i++) {
//            System.out.println(Arrays.toString(result[i]));
//        }

    }

    private static void getRankOfCAScore(double result[][]){

        for(int i =0; i<result.length;i++){
            for(int j=0;j<result.length-i-1;j++){
                if(result[j][2]<result[j+1][2]){
                    double temp[];
                    temp = result[j];
                    result[j]=result[j+1];
                    result[j+1]=temp;
                }
            }
        }

        for(int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }

    }

    public static double[][] getResult() throws FileNotFoundException {
        double[][] result = new double[229][4];
        int counter = 0;

        FileReader fr = new FileReader("temp.txt");
        BufferedReader br = new BufferedReader(fr);
        try {
            String line;
            while((line = br.readLine()) != null) {
                String[] words = line.trim().split("\\s");
                double[] toNumber = new double[words.length];

                for(int i = 0; i < words.length; i++) {
                    toNumber[i] = Double.parseDouble(words[i].trim());
                }
                result[counter] = toNumber;
                counter++;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

//        for(int i = 0; i < result.length; i++) {
//            System.out.println(Arrays.toString(result[i]));
//        }
        return result;
    }




}
