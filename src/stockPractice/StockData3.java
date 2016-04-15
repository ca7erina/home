package cs211.lab8;


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
        FileIO2 io = new FileIO2();
        String[] original = io.load("stockdata.txt");
        int numrows=original.length;
        int numcols=original[1].split("\t").length;
        double[][] array = new double[numrows][numcols];
        // get array[][] : all data stored in it;
        for(int i=1;i<numrows;i++){
            for(int j=1;j<numcols;j++){
                array[i][j]=Double.parseDouble(original[i].split("\t")[j]);

            }
        }
//        System.out.println(array[0][0]); // DATE
//        System.out.println(array[1][0]); // 30/06/2011

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
    	if( peak ==0.0){
    		  drawdown = (peak -trough);
    	}else{
    		  drawdown = 100*(peak -trough)/peak;// calculate troughdown
    	}
    	
        if(drawdown==26195.498264320246){
        	System.out.println(peak);
        	System.out.println(trough);
        }
        return drawdown;
    }

    /**
     * 1) The S&P 500 company with the lowest drawdown between 2008 and mid-
     * 2011 and the dates between which it occurred
     * 2) The S&P 500 company with the highest drawdown between 2008 and mid-
     * 2011 and the dates between which it occurred
     */
    public static void highestDrawdown(double[][] array){ // get the biggest drawdown of every company
        int numberOfCompanies = array[0].length;
        double highestDDList[][]=new double[numberOfCompanies][4]; // store every company's highest drwadown: every company index, peakdate; troughdate; drawdown value;
//    	System.out.println(array[1][1]);
//    	System.out.println(array[array.length-1][1]);
        for(int i =1;i<array[1].length;i++){ // i is company index

            double peak = array[array.length-1][1];
            int peakDateIndex =1;
            int troughDateIndex = 1;
            int compIndex = i;
            double maxDD =Double.MIN_VALUE;    

            for(int j =array.length-1; j>=1;j--){ //(2,-array.length)
                troughDateIndex =j;
                if(array[j][i]>peak){
                    peak = array[j][i];
                    peakDateIndex = j;
                    continue;
                }
                if(peak ==array[j][i]){ //peak != trough
                	continue;
                }
          
                double drawdown = getDrawdownValue(peak, array[j][i]);

                if(drawdown>maxDD){
                    maxDD = drawdown;
                    highestDDList[compIndex][0]= compIndex;
                    highestDDList[compIndex][1]= peakDateIndex;
                    highestDDList[compIndex][2]= troughDateIndex;
                    highestDDList[compIndex][3]= drawdown;
                }
            }
        }
        

        //sort highest:buble sort
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
        
      
//      System.out.println(highestDDList.length); 
  
      System.out.println("highest drawdown:");  
      System.out.println("company name: "+companyIndex[(int)highestDDList[1][0]]);
      System.out.println("peak date "+dateIndex[(int)highestDDList[1][1]] );
      System.out.println("trough date "+dateIndex[(int)highestDDList[1][2]]);
      System.out.println("highest drawdown:"+ highestDDList[1][3]);
      System.out.println("----------------");
      System.out.println("lowest drawdown:"); 
      System.out.println("company name: "+companyIndex[(int)highestDDList[465][0]]);
      System.out.println("peak date "+dateIndex[(int)highestDDList[465][1]] );
      System.out.println("trough date "+dateIndex[(int)highestDDList[465][2]]);
      System.out.println("highest drawdown:"+ highestDDList[465][3]);
      
      



        // print result top 5
//        for(int i=1;i<highestDDList.length;i++){
//        	
//        		 System.out.println("company name: "+companyIndex[(int)highestDDList[i][0]]);
//                 System.out.println("peak date "+dateIndex[(int)highestDDList[i][1]] );
//                 System.out.println("trough date "+dateIndex[(int)highestDDList[i][2]]);
//                 System.out.println("highest drawdown:"+ highestDDList[i][3]);	
//                 break;
//        	
//           
//        }
//        
        
        
        

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
