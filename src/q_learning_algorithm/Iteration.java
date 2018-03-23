package q_learning_algorithm;

import java.util.ArrayList;
import java.util.Random;

public class Iteration {

	public static void vicinage_Count(int gecici, ArrayList<Integer> vicinageCount, int r_Matrix[][] ){
	
		for (int i = 0; i < r_Matrix.length; i++) {
			if (r_Matrix[gecici][i] != -1 ) {
			vicinageCount.add(i);
			}
			
		}
		
		
	}

	public static int maxValue(ArrayList<Integer> nextCount, int action, float q_Matrix[][]){
		Random rasgele = new Random();
		int gecici;
		float max = q_Matrix[action][nextCount.get(0)];
		
		int index = nextCount.get(0);
	     
		if(nextCount.size() >= 2){
			gecici  = rasgele.nextInt(nextCount.size());
			index = nextCount.get(gecici);
			max = q_Matrix[action][nextCount.get(gecici)];
			
		}
		
		
		 for(int i=0; i<nextCount.size(); i++)
	        {
               
	            if(max < q_Matrix[action][nextCount.get(i)])

	            {
	               index = nextCount.get(i); 
	          
	              
	            }
	         
	        }
	
		
		  return index;
		
		 
	}
	public static void switchArray(ArrayList<Integer> vicinageCount, ArrayList<Integer> nextCount){
		vicinageCount.clear();
		
		for (int i = 0; i < nextCount.size(); i++) {
			vicinageCount.add(nextCount.get(i));
			}
		nextCount.clear();
	}
	public static void iteration(float q_Matrix[][], int r_Matrix[][], int finish, int iterationNumber){
		Random state = new Random();
		Random actionRandom = new Random();
		ArrayList<Integer> vicinageCount = new ArrayList<>(); 
		ArrayList<Integer> nextCount = new ArrayList<>(); 
		int gecici,  action, index, i;
		
		   for (int j = 1; j <=iterationNumber; j++) {
			  
			   gecici = state.nextInt((q_Matrix.length));
			    vicinage_Count(gecici, vicinageCount, r_Matrix);
				action = actionRandom.nextInt((q_Matrix.length));
				Break:
				for ( i = 0; i < vicinageCount.size(); i++) {
					
					if(action == vicinageCount.get(i)){
				     vicinage_Count(action, nextCount, r_Matrix);
				     index = maxValue(nextCount, action, q_Matrix);
					 q_Matrix[gecici][action] = r_Matrix[gecici][action] + ( (8 * q_Matrix[action][index])/10);
					 if (action == finish && vicinageCount.get(i) == finish) {
						vicinageCount.clear();
						nextCount.clear();
						i = vicinageCount.size()-1;
						break Break;
					} 
					
					 
					 else{
						gecici = action;
						switchArray(vicinageCount, nextCount);
						action = actionRandom.nextInt((q_Matrix.length));
						i = -1;
						continue Break;
					}
					 
					}
					
				if (action != vicinageCount.get(i) && i == (vicinageCount.size()-1)) {
							action = actionRandom.nextInt((q_Matrix.length));
							nextCount.clear();
							i = -1;
							continue Break;
						}
					
					
				}
				
				
		}
			
		    
			
			
		
	}
	
}
