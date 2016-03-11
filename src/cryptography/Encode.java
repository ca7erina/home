package cryptography;

import java.io.File;

/**
 * Created by chenxiaoxue on 3/9/16.
 */
public class Encode{
    static final String  FOLDER_PATH = "src"+ File.separator+"cryptography"+File.separator+"language"+File.separator;

    public static void main(String[] args){

        FileIO reader = new FileIO();

        String[] inputs = reader.load(FOLDER_PATH+"encodepractice_original");    //Reading the File as a String array

        for(int i=0;i<inputs.length;i++){
            System.out.println(inputs[i]);
        }

        int[] array = new int[256];

        for(int i=0;i<256;i++){
            array[i]=i;
        }




        for(int i=32;i<256;i++){
            int random=(int)(Math.random()*224)+32;
            int temp = array[i];
            array[i]=array[random];
            array[random]=temp;
        }

        for(int i=0;i<inputs.length;i++){
            String cipher="";
            for(int j=0;j<inputs[i].length();j++){
                if((int)inputs[i].charAt(j)<256){
                    System.out.println((int)inputs[i].charAt(j));
                    cipher+=(char)array[(int)inputs[i].charAt(j)];
                    System.out.println(array[(int)inputs[i].charAt(j)]);
                }
            }
            inputs[i]=cipher;
            System.out.println(cipher);

        }

        try{
            reader.save(FOLDER_PATH+"encodepractice_encoded",inputs);
        }catch (Exception e){
            System.out.println(e.getClass());
        }
    }
}

