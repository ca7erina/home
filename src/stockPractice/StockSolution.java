package stockPractice;


import java.io.*;
import java.util.Arrays;

/**

 *
 */
public class StockSolution {
    static String[] companyIndex;
    static String[] dateIndex;
    public static double array[][];
    public static double price[];
    public static double volatility[][];


    public static void main(String[] args){
        FileIO2 io = new FileIO2();
        String[] original = io.load("src" + File.separator + "stockPractice" + File.separator + "stockdata2.txt");
        int numrows=original.length; // how many dates; 881
        int numcols=original[0].split("\t").length; //how many companies 466
        array = new double[numrows][numcols];
        // get array[][] : all data stored in it;
        for(int i=0;i<numrows;i++){
            for(int j=0;j<numcols;j++){
                array[i][j]=Double.parseDouble(original[i].split("\t")[j]);
            }
        }

        //get volatility array
        volatility = new double[numcols][2];
        for(int i=0;i<numcols;i++){
            volatility[i][0]=i;//index
            volatility[i][1]=getVotality(array,i);
          //  System.out.println(i+" "+volatility[i]);
        }

        //get companyname array
        String[] companyNameList = new String[numcols];
        String[] names = io.load("src" + File.separator + "stockPractice" + File.separator + "companyNames.txt");
        for(int i=0;i<numcols;i++){
            companyNameList[i]=names[0].split("\t")[i];
//            System.out.println(i+" "+companyNameList[i]);
        }

        //get price array
        price = new double[numcols];
        String[] pricestr = io.load("src" + File.separator + "stockPractice" + File.separator + "stockprice.txt");
        for(int i=0;i<numcols;i++){
            price[i]=Double.parseDouble(pricestr[0].split("\t")[i]);
//            System.out.println(i+" "+price[i]);
        }


        //test
        double total = 1000000; //bigmoney
        // sort by volatility

        sortVolativity(volatility); //sorted
        print2DArray(volatility);
        int result[] = new int[numcols];

        double vota = volatility[0][1];
        boolean flag = true;

        while(flag){
            for(int i =0;i<volatility.length;i++){
                result[(int)volatility[i][0]]=1;
                double currentV =getOverAllVotality(result);

                if(currentV>vota){
                    result[(int)volatility[i][0]]=0; //rollback and stop
                    System.out.println(i);
                    i=0;
                    continue;
                }else if(total>=price[(int)volatility[i][0]]){
                    vota = currentV;
                    total = total-price[(int)volatility[i][0]];
                }else { // total <0
                    flag=false;
                    break;
                }
            }

        }

        System.out.println(total);
        System.out.println(getOverAllVotality(result));

//        while(flag){
//
//            for(double v[]:volatility){
//                result[(int)v[0]]=1;
//                double currentV =getOverAllVotality(result);
//                double minus = total-price[(int)v[0]];
//                if(minus<0){
//                    result[(int)v[0]]=0;
//                    continue;
//                }
//                if(currentV<=vota){
//                    vota = currentV;
//                    total =minus;
//                }else{
//                    result[(int)v[0]]=0;
//                    flag = false;
//                    break;
//                }
//
//
//            }
//
//        }




        //test
//        result = new int[numcols];
//        result[236] = 1;
//
//        System.out.println(getOverAllVotality(result));
//        result = new int[numcols];
//        result[236] = 1;
//        result[140] = 1;
//        System.out.println(getOverAllVotality(result));
    }




    public static void sortVolativity(double[][] volatility){
        for(int i=volatility.length-1;i>0;i--){// bubble sort
            for(int j=0;j<i;j++){
                if(volatility[j][1]>volatility[j+1][1]){
                    //swap
                    double[] temp= volatility[j];
                    volatility[j] = volatility[j+1];
                    volatility[j+1] = temp;
                }
            }
        }
    }

    public static double getAvg(double array[][], int companyindex){
        double sum = 0.0;
            for(int j =array.length-1; j>=0;j--){
                sum += array[j][companyindex];
            }
        return sum/(array.length-1);
    }

    public static double getAvg(double overAllCol[]){ //for overAlllist
        double sum = 0.0;
        for(int j =overAllCol.length-1; j>=0;j--){
            sum += overAllCol[j];
        }
        return sum/(overAllCol.length-1);
    }

    public static double getVotality(double overAllCol[]){
        double sum = 0.0;
        for(int j =overAllCol.length-1; j>=0;j--){//(1,array.length-1)
           sum += Math.pow(overAllCol[j]-getAvg(overAllCol),2);
        }

        double result = Math.sqrt(sum/(overAllCol.length-1-1));
        return result;
    }

    public static double getVotality(double array[][], int companyindex){
        double sum = 0.0;
        for(int j =array.length-1; j>=0;j--){//(1,array.length-1)
            sum += Math.pow(array[j][companyindex]-getAvg(array,companyindex),2);
        }

        double result = Math.sqrt(sum/(array.length-1-1));
        return result;
    }


    /**
     *
     * @param result
     */
    public static double getOverAllVotality(int[] result){
        double overAlldailyChange[]=new double[881]; //number of dates 881
        for(int j=0;j<overAlldailyChange.length;j++){
            double fomularA =0.0;
            double fomularB = 0.0;
            for(int i=0;i<result.length;i++){
                if(result[i]==0){
                    continue;
                }
                fomularA += price[i]*result[i]*array[j][i]; //i is company index; j is date
                fomularB += price[i]*result[i];
            }
            overAlldailyChange[j] = fomularA/fomularB; //j is date;//(A's price * A's dailyChange +B's price*B's dailyChange)/(A'price + B'sPrice)
//                System.out.println(j+" "+dailyChange[j]);
        }

        return getVotality(overAlldailyChange);

    }



    public static void print2DArray(double[][] a){
        for(int i=0;i<a.length;i++){//last index no  no data
            System.out.print(Arrays.toString(a[i]) + price[(int) a[i][0]]);
            System.out.println();
        }
    }


}





class FileIO2{

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