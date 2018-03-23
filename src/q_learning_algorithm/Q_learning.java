package q_learning_algorithm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

import q_learning_algorithm.MazeGenerator;


public class Q_learning {
	
	//Grafik Tanýmlamalarý
	JTextField text;
	JTextField text2;
	JTextField text3;
	JButton buton_coz;
	JButton buton_sil;
	JLabel label;
	JLabel label2;
	JLabel label3;
	
	//Algoritmada gerekli olacak olan tanýmlamalar
	int r_Size = 0;
	int start, finish, iteration_count; 
	String r_fileName = "C:/Users/asus/workspace/yazilimm_lab/outR.txt";
	String q_fileName = "C:/Users/asus/workspace/yazilimm_lab/outQ.txt";
	String path_fileName = "C:/Users/asus/workspace/yazilimm_lab/outPath.txt";
	int[][] r_Matrix;
	float[][] q_Matrix;
	ArrayList<Integer> path_list;
	DosyaIslem dosya;
	
     public Q_learning() {
		JFrame jfr = new JFrame("Q Learning Labirent Projesi");
		jfr.getContentPane().setLayout(new FlowLayout());
		jfr.setSize(500,500);
		jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jfr.setLayout(null);
		
		label = new JLabel("Baþlangýç noktasýný giriniz:");
		jfr.getContentPane().add(label);
		label.setBounds(150, 70, 200, 20);
		text = new JTextField(10);
		jfr.getContentPane().add(text);
		text.setBounds(150, 100, 50, 20);
		
		label2 = new JLabel("Bitiþ noktasýný giriniz:");
		jfr.getContentPane().add(label2);
		label2.setBounds(150, 150, 200, 20);
		text2 = new JTextField(10);
		jfr.getContentPane().add(text2);
		text2.setBounds(150, 180, 50, 20);
		
		label3 = new JLabel("Ýterasyon sayýsýný giriniz:");
		jfr.getContentPane().add(label3);
		label3.setBounds(150, 230, 200, 20);
		text3 = new JTextField(10);
		jfr.getContentPane().add(text3);
		text3.setBounds(150, 260, 50, 20);
		
	
		
		buton_coz = new JButton("çöz");
		jfr.getContentPane().add(buton_coz);
		buton_coz.setBounds(110, 310, 100, 50);
		
		buton_sil = new JButton("temizle");
		jfr.getContentPane().add(buton_sil);
		buton_sil.setBounds(230, 310, 100, 50);

		
		  buton_coz.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e){
					
				//	path_list.clear();
					
					start = Integer.parseInt(text.getText());
	        	    finish = Integer.parseInt(text2.getText());
	        		iteration_count = Integer.parseInt(text3.getText());
	        		
    while ((start < 0) ||( start >= r_Size) || (finish < 0) || (finish >= r_Size) || (iteration_count <= 0))
	        {
	 JOptionPane.showMessageDialog(null, "Lütfen Doðru Bir Sayý Giriniz", "HATA", 0);
	        	
	    text.setText("");
	    text2.setText("");
		text3.setText("");
		
		start = Integer.parseInt(text.getText());
	    finish = Integer.parseInt(text2.getText());
		iteration_count = Integer.parseInt(text3.getText());
					}
					
	        	    matrixBaslangicAtama(r_Matrix, q_Matrix);
	        		
					// R matrisini olusturma
					dosya.dosyaAyrýþtýrma(r_Matrix, finish, r_fileName);
					
				    // Q matrisini oluþturma
					Iteration.iteration(q_Matrix, r_Matrix, finish, iteration_count);
				    dosya.dosyaYazma(q_fileName, q_Matrix);
				    
				    //Yollarý Bulma
				    FindPath.Find_Path(start, finish, q_Matrix, path_fileName, path_list);
				    
	        		
	        
	           SwingUtilities.invokeLater(() -> {
		            JFrame f = new JFrame();
		            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		            f.setTitle("Q Learning Labirent Projesi");
		            f.setResizable(false);
		            f.add(new MazeGenerator((int) Math.sqrt(r_Size), r_Matrix, path_list, start,finish), BorderLayout.CENTER);
		            f.pack();
		            f.setLocationRelativeTo(null);
		            f.setVisible(true);
		     
		       });
	      	  
	          	buton_sil.addActionListener(new ActionListener() {
	    			
	    			public void actionPerformed(ActionEvent e){
	    				//f.removeAll();
	    				path_list.clear();
	    				text.setText("");
	    			    text2.setText("");
	    				text3.setText("");
	    			}
	    		});
	          
	          
				}
			});	
		    
     
     jfr.setVisible(true);
		
	}
		
	public void  matrixBaslangicAtama(int r_Matrix[][], float q_Matrix[][]){
		for (int i = 0; i < r_Matrix.length; i++) {
			for (int j = 0; j < r_Matrix[0].length; j++) {
				r_Matrix[i][j] = -1;
			    q_Matrix[i][j] = 0;	
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		
		Q_learning q_object =new Q_learning();
		q_object. dosya = new DosyaIslem();	
		q_object.dosya.matrixName = "R Matrisi : ";
		q_object.r_Size = q_object.dosya.readFile();
		
		
		//Matrisleri olusturma
		q_object. r_Matrix = new int[q_object.r_Size][q_object.r_Size];
		q_object.q_Matrix = new float[q_object.r_Size][q_object.r_Size];
		q_object.path_list = new ArrayList<>();
		System.out.println("R matrisin boyutu: " + q_object.r_Matrix.length);  
	
	}
	
}	



	

