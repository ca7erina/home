package stockPractice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

/**
 * Bruce force solution
 * 1.get a stock and put it in the result set, and then keep buying stocks can lower the volatility. and remember the volatility value of this result set which start with this stock
 * 2.keep repeat step1 and get all the volatilities, and chose the lowest one.
 */
public class StockSolutionBruteForce {
    static String[] companyIndex;
    static String[] dateIndex;
    public static double array[][];
    public static double price[];
    public static double volatility[][];
    private static final double TOTAL = 7777777;//10000000
    private static int numcols;
    private static int numrows;


    public static void main(String[] args) {
        FileIO io = new FileIO();
        String[] original = io.load( "src" + File.separator + "stockPractice" + File.separator + "stockdata2.txt");
        numrows = original.length; // how many dates; 881
        numcols = original[0].split("\t").length; //how many companies 466
        array = new double[numrows][numcols];
        for(int i = 0; i < numrows; i++) { // get array[][] : all data stored in it;
            for(int j = 0; j < numcols; j++) {
                array[i][j] = Double.parseDouble(original[i].split("\t")[j]);
            }
        }

        //get price array lowest 319
        price = new double[numcols];
        String[] pricestr = io.load("src" + File.separator + "stockPractice" + File.separator + "stockprice.txt");
        for(int i = 0; i < numcols; i++) {
            price[i] = Double.parseDouble(pricestr[0].split("\t")[i]);
//            System.out.println(i+" "+price[i]);
        }
        
        //get volatility array{{index,volatility}}
        volatility = new double[numcols][2];
        for(int i = 0; i < numcols; i++) {
            volatility[i][0] = i;//index
            volatility[i][1] = getVotality(array, i);
            //  System.out.println(i+" "+volatility[i]);
        }
        sortVolativity(volatility); //sorted

//        printVolatility(volatility);



        	//setUp results set
        
        getResult();


    }

    public static void getResult(){
    	
    	int finalresult[] = new int[numcols];
    	double min=Double.MAX_VALUE;	
    	for(int i=0;i<numcols;i++){ //index of the stocks
    		int result[]  =getBestResultStartFromThisStock(i);
    		double v = getOverAllVotality(result);
    		if(v<min){
    			System.out.println("lower volatility combination result found:"+v);
    			min=v;
    			finalresult = result;
    		}
    		
    	}
    	
    	
      System.out.println("total left: " + gettotalLeft(finalresult));
      System.out.println("overall votality: " + getOverAllVotality(finalresult));
      printResult(finalresult);
      System.out.println("result: " + Arrays.toString(finalresult));
    	
    }
    
    
    

    /**
     * Put this stock in the result list, and then keep buying the stock from the rest options that can lower the volatility
     * return result array . 
     */
    public static int[] getBestResultStartFromThisStock(int targetStockIndex){
        int result[] = new int[numcols];
        double total = TOTAL;

        double firstPrice= total -price[targetStockIndex];

        if(firstPrice>=0){
            result[targetStockIndex]=1;
            total = firstPrice;

            while(total>0){//the rest buy the one which lower the overall volatilty
                double min = getOverAllVotality(result);
                int min_index=-1;
                for(int i=0;i<volatility.length;i++){//iterate all value, get the lowest vol
                    if(result[(int)volatility[i][0]]>0){ //skip the ones already bought
                        continue;
                    }
                    result[(int)volatility[i][0]]=1;
                    double temp = getOverAllVotality(result);
                    if (temp<=min){
                        min=temp;
                        min_index = (int)volatility[i][0];
                    }
                    result[(int)volatility[i][0]]=0;
                }
                if(min_index==-1){ //no value can lower volatility
                  //  System.out.println("didn't find any can lower vol");
                    break;
                }else{
                    double tempMoney=total -price[min_index];
                    if(tempMoney>0){
                        result[min_index]=1;
                        total=tempMoney;
                       // System.out.println("result add "+min_index+" total vol:"+getOverAllVotality(result));
                    }else{ // no money to buy
                        break;
                    }
                }
            }

        }else{
            // no enough money to buy any stocks
            System.out.println("no enough money to buy any stocks");
        }
        return result;

    }

    
    public static double gettotalLeft(int result[]){
    	double sum =0.0;
    	  for(int i=0;i<result.length;i++){
              if(result[i]>0){
                  sum+=(price[i]*result[i]);
              }
          }
          return TOTAL-sum;
    	
    }

    public static void printResult(int result[]){
        System.out.println("Result is:");
        int index=0;
        for(int i:result){
            if(i>0){
                System.out.println("index:"+index+" , bought:"+i+ " price"+price[index]+ " vola:"+volatility[index][1]);
            }
            index++;
        }
        System.out.println();
    }

   


    public static void sortVolativity(double[][] volatility) {
        for(int i = volatility.length - 1; i > 0; i--) {// bubble sort
            for(int j = 0; j < i; j++) {
                if(volatility[j][1] > volatility[j + 1][1]) {
                    //swap
                    double[] temp = volatility[j];
                    volatility[j] = volatility[j + 1];
                    volatility[j + 1] = temp;
                }
            }
        }
    }


    public static void sortOptionsByPrice(double[][] option) {
        for(int i = option.length - 1; i > 0; i--) {// bubble sort
            for(int j = 0; j < i; j++) {
                if(option[j][2] > option[j + 1][2]) {
                    //swap
                    double[] temp = option[j];
                    option[j] = option[j + 1];
                    option[j + 1] = temp;
                }
            }
        }
    }


    public static double getAvg(double array[][], int companyindex) {
        double sum = 0.0;
        for(int j = array.length - 1; j >= 0; j--) {
            sum += array[j][companyindex];
        }
        return sum / (array.length - 1);
    }

    public static double getAvg(double overAllCol[]) { //for overAlllist
        double sum = 0.0;
        for(int j = overAllCol.length - 1; j >= 0; j--) {
            sum += overAllCol[j];
        }
        return sum / (overAllCol.length - 1);
    }

    public static double getVotality(double overAllCol[]) {
        double sum = 0.0;
        for(int j = overAllCol.length - 1; j >= 0; j--) {//(1,array.length-1)
            sum += Math.pow(overAllCol[j] - getAvg(overAllCol), 2);
        }

        double result = Math.sqrt(sum / (overAllCol.length - 1 - 1));
        return result;
    }

    public static double getVotality(double array[][], int companyindex) {
        double sum = 0.0;
        for(int j = array.length - 1; j >= 0; j--) {//(1,array.length-1)
            sum += Math.pow(array[j][companyindex] - getAvg(array, companyindex), 2);
        }

        double result = Math.sqrt(sum / (array.length - 1 - 1));
        return result;
    }



    /**
     * @param result
     */
    public static double getOverAllVotality(int[] result) {
        double overAlldailyChange[] = new double[881]; //number of dates 881
        for(int j = 0; j < overAlldailyChange.length; j++) {
            double fomularA = 0.0;
            double fomularB = 0.0;
            for(int i = 0; i < result.length; i++) {
                if(result[i] == 0) {
                    continue;
                }
                fomularA += price[i] * result[i] * array[j][i]; //i is company index; j is date
                fomularB += price[i] * result[i];
            }
            overAlldailyChange[j] = fomularA / fomularB; //j is date;//(A's price * A's dailyChange +B's price*B's dailyChange)/(A'price + B'sPrice)
        }

        return getVotality(overAlldailyChange);

    }

    public static void printVolatility(double[][] volatility) {
        for(int i = 0; i < volatility.length; i++) {
        	System.out.println("index:"+volatility[i][0]+"  vol:"+volatility[i][1]+" price:"+price[(int)volatility[i][0]]+" volOverPrice:"+volatility[i][1]/price[(int)volatility[i][0]]);
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


