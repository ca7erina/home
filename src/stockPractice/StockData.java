package stockPractice;

import java.io.*;

/**
 *A drawdown is the peak-to-trough decline during a specific record period of an
 investment, fund or commodity. A drawdown is usually quoted as the percentage
 between the peak and the trough. Use the data in the file StockData.txt. The
 following code can be used for loading it in:
 *
 *
 */
public class StockData {

    static String[] companyIndex;
    static String[] dateIndex;

    public static void main(String[] args){

        FileIO io = new FileIO();
        String[] original = io.load("src" + File.separator + "stockPractice" + File.separator + "stockdata.txt");
        int numrows=original.length;
        int numcols=original[1].split("\t").length;
        double[][] array = new double[numrows][numcols];
        // get array[][] : all data stored in it;
        for(int i=1;i<numrows;i++){
            for(int j=1;j<numcols;j++){
                array[i][j]=Double.parseDouble(original[i].split("\t")[j]);

            }
        }
        System.out.println(array[0][0]); // no value
        System.out.println(array[1][0]); //no value

        // get companyIndex[][];
        companyIndex  = new String[numcols];
        String[] headline = original[ 0].split("\t");
        for(int i=1;i<numcols;i++){
            companyIndex[i]= headline[i];
        }

        // get dateIndex[][];
        dateIndex  = new String[numrows];
        for(int i=1;i<numrows;i++){
            dateIndex[i] = original[i].split("\t")[0];
        }


//        highestDrawdown(array);
lowestDrawdown(array);
    }

    /**
     2) The S&P 500 company with the highest drawdown between 2008 and mid-
     2011 and the dates between which it occurred
     */
    public static void highestDrawdown(double[][] array){ // get the biggest drawdown of every company
        int numberOfCompanies = array[0].length;
        double highestDDList[][]=new double[numberOfCompanies][4]; // store every company's highest drwadown: every company index, peakdate; troughdate; drawdown value;

        for(int i =1;i<array[1].length;i++){ // i is company index
            double peak = array[1][i];
            int peakDateIndex =i;
            int troughDateIndex = 1;
            int compIndex = i;
            double maxDD =0.0;

            for(int j =2; j<array.length;j++){
                troughDateIndex =j;
                if(array[j][i]>peak){
                    peak = array[j][i];
                    peakDateIndex = j;
                    continue;
                }
                double drawdown = 100*(peak -array[j][i])/peak;

                if(drawdown>maxDD){
                    maxDD = drawdown;
                    highestDDList[compIndex][0]= compIndex;
                    highestDDList[compIndex][1]= peakDateIndex;
                    highestDDList[compIndex][2]= troughDateIndex;
                    highestDDList[compIndex][3]= drawdown;
                }

            }
        }

        //sort highest
        for(int i=highestDDList.length-1;i>0;i--){
            for(int j=0;j<i;j++){
                    if(highestDDList[j][3]<highestDDList[j+1][3]){
                        //swap
                        double[] temp= highestDDList[j];
                        highestDDList[j] = highestDDList[j+1];
                        highestDDList[j+1] = temp;
                    }
            }
        }

        // print result top 5
        for(int i=1;i<5;i++){
            System.out.println("company name: "+companyIndex[(int)highestDDList[i][0]]);
            System.out.println("peak date "+dateIndex[(int)highestDDList[i][1]] );
            System.out.println("trough date "+dateIndex[(int)highestDDList[i][2]]);
            System.out.println("highest drawdown:"+ highestDDList[i][3]);
        }

    }



    /**
     * 1) The S&P 500 company with the lowest drawdown between 2008 and mid-
     2011 and the dates between which it occurred
     */
    public static void lowestDrawdown(double[][] array){ // get the biggest drawdown of every company
        int numberOfCompanies = array[0].length;
        double lowestDDList[][]=new double[numberOfCompanies][4]; // store every company's highest drwadown: every company index, peakdate; troughdate; drawdown value;

        for(int i =1;i<array[1].length;i++){ // i is company index
            double peak = array[1][i];
            int peakDateIndex =i;
            int troughDateIndex = 1;
            int compIndex = i;
            double minDD =Double.MAX_VALUE;

            for(int j =2; j<array.length;j++){
                troughDateIndex =j;
                if(array[j][i]>peak){
                    peak = array[j][i];
                    peakDateIndex = j;
                    continue;
                }
                double drawdown = 100*(peak -array[j][i])/peak;

                if(drawdown<minDD){
                    minDD = drawdown;
                    lowestDDList[compIndex][0]= compIndex;
                    lowestDDList[compIndex][1]= peakDateIndex;
                    lowestDDList[compIndex][2]= troughDateIndex;
                    lowestDDList[compIndex][3]= drawdown;
                }

            }
        }


        //sort lowest
        for(int i=lowestDDList.length-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(lowestDDList[j][3]>lowestDDList[j+1][3]){
                    //swap
                    double[] temp= lowestDDList[j];
                    lowestDDList[j] = lowestDDList[j+1];
                    lowestDDList[j+1] = temp;
                }
            }
        }

        // print result top 5
        for(int i=1;i<5;i++){
            System.out.println("company name: "+companyIndex[(int)lowestDDList[i][0]]+" "+lowestDDList[i][0]);
            System.out.println("peak date "+dateIndex[(int)lowestDDList[i][1]]+" "+lowestDDList[i][1] );
            System.out.println("trough date "+dateIndex[(int)lowestDDList[i][2]]+" "+lowestDDList[i][2]);
            System.out.println("highest drawdown:"+ lowestDDList[i][3]);
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