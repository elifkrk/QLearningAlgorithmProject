package q_learning_algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class DosyaIslem {
	
	File inputFile=new File("C:/Users/asus/workspace/yazilimm_lab/input.txt");
	String matrixName;
	@SuppressWarnings("resource")
	public int readFile() throws IOException
    { 
   int lineNumber = 0;
        try
        {
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(inputFile));
        String satir = reader.readLine();
            while (satir!=null) {
             if(satir.length()>0){
              lineNumber++;
             }
             satir = reader.readLine();                
            }    
        }catch(final IOException e){}
        //System.out.println("Verilen Dökümandaki Satýr Sayýsý: "+lineNumber);
        return lineNumber;
        
    }
	public  int[][] dosyaAyrýþtýrma(int r_Matrix[][], int finish, String fileName){
		String strFile = "C:/Users/asus/workspace/yazilimm_lab/input.txt";
		 try
	     {
	             //create BufferedReader to read csv file
	             BufferedReader br = new BufferedReader( new FileReader(strFile));
	             String strLine = "";
	             StringTokenizer st = null;
	             int lineNumber=0, gecici = 0;
	            
	             r_Matrix[finish][finish] = 100;
	             //read comma separated file line by line
	            while( (strLine = br.readLine()) != null)
	             {  
	                     //break comma separated line using ","
	                     st = new StringTokenizer(strLine, ",");
	                     while(st.hasMoreTokens())
	                     { 
	                    		gecici = Integer.parseInt(st.nextToken());
	                    		//System.out.println(gecici);
	                    	     if (gecici == finish ) {
	 	                    		r_Matrix[lineNumber][gecici] = 100;
	 							}
	                    	     
	 	                    	else{
	 	                    	 r_Matrix[lineNumber][gecici] = 0;}
		                     }
	                  lineNumber++;
	                        
	                       
	             }
	             
	             
	             br.close();
	     }
	     catch(Exception e)
	     {
	             System.out.println("Exception while reading txt file: " + e);                  
	     }
		
		dosyaYazma(fileName, r_Matrix);
		
		return r_Matrix;
	}
	
	private  void dosyaYazma(String fileName, int matrix[][]){
		
		try{
            FileWriter outFile = new FileWriter(fileName);
            PrintWriter out = new PrintWriter(outFile);
            out.println(matrixName);
            for (int i = 0; i < matrix.length; i++) {
    			for (int j = 0; j < matrix[0].length; j++) {
    				out.print(matrix[i][j]);
    				out.print("\t");
    				}
    			
    			out.println("\n");
    		}
            
            out.close();
        }catch (Exception e){
            System.err.println("Hata: " + e.getMessage());
        }
	}
	
	public  void dosyaYazma(String fileName, float matrix[][]){
		
		DecimalFormat df = new DecimalFormat("#.##"); 

		matrixName = "Q Matrisi: ";
		try{
            FileWriter outFile = new FileWriter(fileName);
            PrintWriter out = new PrintWriter(outFile);
            out.println(matrixName);
            for (int i = 0; i < matrix.length; i++) {
    			for (int j = 0; j < matrix[0].length; j++) {
    				 
    				out.print(df.format(matrix[i][j]) );
    				//out.print(matrix[i][j]);
    				out.print("\t");
    				}
    			
    			out.println(" ");
    			out.println(" ");
    		}
            
            out.close();
        }catch (Exception e){
            System.err.println("Hata: " + e.getMessage());
        }
	}
}
