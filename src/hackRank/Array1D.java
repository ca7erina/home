package hackRank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by chenxiaoxue on 11/4/15.
 */
public class Array1D {
    public static void main(String[] args) throws FileNotFoundException {
        int cases =0;
        FileReader fr = new FileReader("Array1DInput.txt");
        BufferedReader br = new BufferedReader(fr);
        try {
            String line;
            cases = Integer.parseInt(br.readLine().trim());
            int index=1;
            while((line = br.readLine()) != null) {

                    String nm[]= line.trim().split("\\s");
                    int n = Integer.parseInt(nm[0]);
                    int m = Integer.parseInt(nm[1]);
                    line =br.readLine();
                    int[] path = new int[n];
                    String temp[] = line.trim().split("\\s");
                    for(int i =0;i<n;i++){
                        path[i]=Integer.parseInt(temp[i]);
                    }
                    if(index>=4840&&index<=4845){
                        System.out.println("index:"+index);
                        System.out.println("n:"+n);
                        System.out.println("m:"+m);
                        System.out.println(Arrays.toString(path));
                        System.out.println(getResult(path,m));
                    }
           //    System.out.println(index+":"+getResult(path,m));
              //  System.out.println(getResult(path,m));

index++;

            }
        } catch(IOException e) {
            e.printStackTrace();
        }





    }

    public static String getResult(int[] path,int m){
        int pointer=0;
        boolean forward=true;
        int jumpRecordIndex[]=new int[path.length];
        int justJumpedBackIndex[] = new int[path.length];

        for(int i=0;i<path.length-1;i++){
            // System.out.println("i:"+i);
            // System.out.println("forward:"+forward);
            if(path[i+1]==0&&forward==true){
                continue;
            }else{

                if((i+m)>=path.length){
                    return "YES";
                }else if(path[i+m]==0&&justJumpedBackIndex[i]!=1){

                    //set jumpRecord
                    if(jumpRecordIndex[i]==1){
                        return "NO";
                    }
                    jumpRecordIndex[i]=1;

                    i=i+m-1; //got to index i+m , but will do i++ here ,so need -1;
                    forward =true;
                    //  System.out.println("jump"+m);
                    continue;
                }else{
                    //can go backward and then jump
                    boolean wentback=false;
                    if(i>=1&&path[i-1]==0){

                        i=i-2;
                        // System.out.println("update i backward:"+(i+1));
                        forward=false;
                        wentback=true;

                    }else if(i-m>=0&&path[i-m]==0){

                        i=i-m-1;//cuz after iteration i++;
                        //System.out.println("update i jumped backward:"+(i+1));
                        forward=false;
                        wentback=true;
                        //justJumpedBackIndex
                        justJumpedBackIndex[i+1]=1;

                    }



                    if(wentback==false){
                        return "NO";
                    }

                }
            }
        }
        return "YES";
    }


}
