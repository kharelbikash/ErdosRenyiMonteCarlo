
public class ErdosRenyiMonteCarlo {


	public static void main(String[] args) {

		// Check if args has 3 elements. If not, output an error message
		// and return
		if(args.length != 3){
			System.out.println("Input error! Please input three arguments.");
			return;
		}
		
		// Parse the edge probability in args[0] and check if it is valid. 
		// If not, output an error message and return 
		 double prob =Double.parseDouble(args[0]) ;
		 if (prob <= 0 || prob > 1) {
				System.out.println("Edge probability = " + prob + ": must be in (0, 1]");
				return;
			}

		// Parse the number of edges in args[1] and check if it positive.
		// If not, output an error message and return
		int N =Integer.parseInt(args[1])  ;
		if (N <= 0) {
			System.out.println("Number of nodes: " + N + ": must be positive");
			return;
		}
		// Parse the number of trials in args[2] and check if it positive.
		// If not, output an error message and return
		int T = Integer.parseInt(args[2]) ;
		if (T <= 0) {
			System.out.println("Number of trials: " + T + ": must be positive");
			return;
		}
		
		// Iterate over the T trials.
		// Store the edges chosen at each trial in a graph organised 
		// as either adjacency matrix or adjacency list (you can choose whatever you like).
		// At the end of a trial, update each pdf[i] with the number of 
		// nodes having degree i.
		// At the end of the simulation, average the values stored in pdf[i] over T.
		// (Feel free to implement this differently if you like)
		int[][][] collectorAdjacentMatrix = new int[T][N][N];
		for(int i =0; i<T; i++){
			for(int j=0; j<N;j++){
				for(int k=j+1; k<N; k++){
					double rand = Math.random();
					if(rand <= prob){
						collectorAdjacentMatrix[i][j][k]=1;
						collectorAdjacentMatrix[i][k][j]= 1;
					}
				}
				
			}
		}
		int[][] pdfT = new int[T][N];
		int[][] degreeT	= new int[T][N];
		for(int k =0; k<T;k++){
					for(int i=0; i<N; i++){
						int degree=0;
						for(int j=0; j<N; j++){
						
								if(collectorAdjacentMatrix[k][i][j]== 1){
									degree = degree+1;
								}
								
							}
						degreeT[k][i]=degree;
					}
					
					
					for(int i=0; i<N; i++){
						int pdfCount=0;
						for(int j=0; j<N; j++){
						
								if(degreeT[k][j]== i){
									pdfCount= pdfCount +1;
								}
								
							}
						pdfT[k][i]=pdfCount;
					}
					
		}
		
		double[] pdf  = new double[N];
		for(int i=0; i<N;i++){
			int totalPdf=0;
			for(int j=0; j< T; j++){
				totalPdf = totalPdf + pdfT[j][i];
			}
			pdf[i]= totalPdf/T;
		}
		System.out.print("PDF: ");
		for(int i=0; i<N;i++){
			
			System.out.print("\t" + pdf[i]);
		}
	
		// Print the pdf array on a single line with the individual entries
		// separated by a single white space
		// Terminate the line with a single new line
	}

}


