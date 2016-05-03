package stockPractice;


import java.io.*;
import java.util.Arrays;

/**
 *A drawdown is the peak-to-trough decline during a specific record period of an
 investment, fund or commodity. A drawdown is usually quoted as the percentage
 between the peak and the trough. Use the data in the file StockData.txt. The
 following code can be used for loading it in:
 *
 */
public class StockData {
    static String[] companyIndex;
    static String[] dateIndex;

    public static void main(String[] args){
        FileIO2 io = new FileIO2();
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
        // 466 companies index from 1-466 array size 467
//        System.out.println(array[1].length);//467
//        System.out.println(array[1][467]); // DATE
//        System.out.println(array[array.length-1][467]); // 30/06/2011

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
        highestDrawdown(array);
    }


    public static double getDrawdownValue(double peak, double trough){
        double drawdown;
        drawdown =100*(peak -trough)/peak;// wiki calculate drawdown
        return drawdown;
    }

    /**
     * 1) The S&P 500 company with the lowest drawdown between 2008 and mid-
     * 2011 and the dates between which it occurred
     * 2) The S&P 500 company with the highest drawdown between 2008 and mid-
     * 2011 and the dates between which it occurred
     */
    public static void highestDrawdown(double[][] array){ // get the biggest drawdown of every company
        int numberOfCompanies = array[0].length-1;
        int compIndex = 0;
        double highestDDList[][]=new double[466][6]; // store every company's highest drwadown: every company index, peakdate; troughdate; drawdown value;
        for(int i =1;i<=array[0].length-1;i++){ // i is company index from array data
            double priceToday=1; //dont have each stock price of the initial day? but ratio is correct
            double peak = array[array.length-1][i];
            double trough=array[array.length-1][i];
            int peakDateIndex =1;
            int troughDateIndex = 1;
            double maxDD =-9999.99;
            double drawdown = 0.0;
            for(int j =array.length-1; j>=1;j--){//(1,array.length-1)
                priceToday =(priceToday* array[j][i]/100.00)+priceToday;
                trough = priceToday;
                troughDateIndex =j;
                if(priceToday>peak){
                    peak = priceToday;
                    peakDateIndex = j;
                    continue;
                }
                if(peak ==priceToday){ //peak != trough
                    continue;
                }
                drawdown = getDrawdownValue(peak, priceToday);
                if(drawdown>maxDD){
                    maxDD = drawdown;
                    highestDDList[compIndex][0]= i;
                    highestDDList[compIndex][1]= peakDateIndex;
                    highestDDList[compIndex][2]= troughDateIndex;
                    highestDDList[compIndex][3]= drawdown;
                    highestDDList[compIndex][4]= peak;
                    highestDDList[compIndex][5]= trough;
                }
            }
                compIndex++;
        }
//                print2DArray(highestDDList);
        sort2DArray(highestDDList);
        System.out.println("highest drawdown:");
        System.out.println("company name: "+companyIndex[(int)highestDDList[0][0]]);
        System.out.println("peak date "+dateIndex[(int)highestDDList[0][1]] );
        System.out.println("trough date "+dateIndex[(int)highestDDList[0][2]]);
        System.out.println("peak "+ highestDDList[0][4]);
        System.out.println("trough "+ highestDDList[0][5]);
        System.out.println("highest drawdown:"+ highestDDList[0][3]);
        System.out.println("----------------");
        System.out.println("lowest drawdown:");
        System.out.println("company name: "+companyIndex[(int)highestDDList[numberOfCompanies-1][0]]);
        System.out.println("peak date "+dateIndex[(int)highestDDList[numberOfCompanies-1][1]]);
        System.out.println("trough date "+dateIndex[(int)highestDDList[numberOfCompanies-1][2]]);
        System.out.println("peak "+ highestDDList[numberOfCompanies-1][4]);
        System.out.println("trough "+ highestDDList[numberOfCompanies-1][5]);
        System.out.println("highest drawdown:"+ highestDDList[numberOfCompanies-1][3]);
    }

    public static void print2DArray(double[][] a){
        for(int i=0;i<a.length;i++){//last index no  no data
            System.out.println(Arrays.toString(a[i]));
        }
    }

    public static void sort2DArray(double[][] a){
        for(int i=a.length-1;i>0;i--){// bubble sort
            for(int j=0;j<i;j++){
                if(a[j][3]<a[j+1][3]){
                    //swap
                    double[] temp= a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }
}





class FileIO3{

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