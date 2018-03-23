package q_learning_algorithm;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FindPath {

	public static void Find_Path(int start, int finish, float q_Matrix[][], String path_fileName, ArrayList<Integer> path_list ){
		float max = q_Matrix[start][0];
		int index = -1;
		path_list.clear();
		
		try{
            FileWriter outFile = new FileWriter(path_fileName);
            PrintWriter out = new PrintWriter(outFile);
            out.println("Bulunan Yol: ");
            out.print(start );
            path_list.add(start);
        	while(index != finish){
        		for ( int i = 0; i < q_Matrix[start].length; i++) {
        			if (max <= q_Matrix[start][i]) {
        				max = q_Matrix[start][i];
        				index = i;
        			}
        		}
        		
        		start = index;
        		out.print(" - " + index );
        		path_list.add(index);
        		
        		}
            
            out.close();
        }catch (Exception e){
            System.err.println("Hata: " + e.getMessage());
        }
		
		
	}
	
}
