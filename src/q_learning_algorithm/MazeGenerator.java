package q_learning_algorithm;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.*;
import javax.swing.*;



public class MazeGenerator extends JPanel {

	private static final long serialVersionUID = 1L;
		final int N = 1, S = 2, E = 4, W = 8;
	    final int nCols;
	    final int nRows;
	     int cellSize = 100;
	     int margin = 80;
	    final int[][] maze;
	    final int[][] r_matrix;
	   
	    ArrayList<Integer> solution;
	    final  int start;
	    final int finish;
	    public MazeGenerator(int size, int r_matrix[][], ArrayList<Integer> path_list, int start, int finish ) { 
	    	if (size > 5) {
				cellSize = 80;
				margin = 20;
			}
	        setPreferredSize(new Dimension((size*cellSize)+(2*margin), (size*cellSize)+(2*margin)));
	        setBackground(Color.white);
	        nCols = size;
	        nRows = size;
	        this.start = start;
	        this.finish = finish;
	       maze = new int[nCols][nRows];
	       this.r_matrix = new int[(int) Math.pow(size,2)][(int) Math.pow(size,2)];
	       for (int i = 0; i < r_matrix.length; i++) {
			for (int j = 0; j < r_matrix.length; j++) {
				this.r_matrix[i][j] = r_matrix[i][j];
			}
		}
	       
	        solution = new ArrayList<>();
	        solution.addAll( 0, path_list);
	        System.out.println(solution);
	        generateMaze();
	        
	    
	    }     
	        
	 	 
	    @Override
	    public void paintComponent(Graphics gg) {
	        super.paintComponent(gg);
	        Graphics2D g = (Graphics2D) gg;
	        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
	 
	        g.setStroke(new BasicStroke(8));
	        g.setColor(Color.black);
	 
	        // Labirenti çiz tamamen 
	       for (int r = 0; r < nRows; r++) {
	            for (int c = 0; c < nCols; c++) {
	 
	                int x = margin + c * cellSize;
	                int y = margin + r * cellSize;
	                if ((maze[r][c] & 1) == 0) // N
	                    g.drawLine(x, y, x + cellSize, y);
	 
	                if ((maze[r][c] & 2) == 0) // S
	                    g.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
	 
	                if ((maze[r][c] & 4) == 0) // E
	                    g.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
	 
	                if ((maze[r][c] & 8) == 0) // W
	                    g.drawLine(x, y, x, y + cellSize);
	            }
	        }
	 
	    
	        // draw pathfinding animation
	        int offset = margin + cellSize / 2;
	     

	        
	        int sx = offset + (start % nCols) * cellSize;
	        int sy = offset + (start / nRows) * cellSize;
	        
	 
	        
	        int x = offset + (finish % nCols) * cellSize;
	        int y = offset + (finish / nRows) * cellSize;
	        
	        
	        Path2D path = new Path2D.Float();
	        path.moveTo(sx , sy);
	 
	        for (int pos : solution) {
	            int px = offset + (pos % nCols) * cellSize;
	            int py = offset + (pos / nRows) * cellSize;
	            path.lineTo(px, py);
	        }
	        
	        g.setColor(Color.orange);
	        g.draw(path);
	        
	        g.setColor(Color.blue);
	        g.fillOval(sx - 5, sy - 5, 20, 20);
	        
	        g.setColor(Color.green);
	        g.fillOval(x - 5, y - 5, 20, 20);
	       
	    }
	 
	   void generateMaze() {
	  
		   for (int i = 0; i < r_matrix.length; i++) {
			for (int j = 0; j < r_matrix.length; j++) {
				if (r_matrix[i][j] != -1) {
					if ((i-j) == 1) 
					 maze[i / nCols][i % nRows] += 8;
					 if((i-j) == -1)
						maze[i / nCols][i % nRows] += 4;
					 if ((i-j) == nCols) 
						maze[i / nCols][i % nRows] += 1;
					 if ((i-j) == (-1 * nCols)) 
						maze[i / nCols][i % nRows] += 2;
				}
			}
		}
		   
		   
	    }
	  
	 
	 
 
}
