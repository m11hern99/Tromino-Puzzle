import java.util.*;

public class Tromino {
	
	static String[][] square;
	public static void main (String [] args) {
		Scanner kb = new Scanner ( System.in);
		while(true) {
			System.out.println("Would you like to solve a puzzle? Enter \"yes\" to continue and \"no\" to end program.");
			String answer = kb.next();
			if(answer.equals("no")) {
				kb.close();
				System.out.println("Program ended.");
				break;
			}
			else if(answer.equals("yes")) {
				System.out.println("Enter \"n\" to create a 2^n square, n must be 1 or greater:");
				int n = kb.nextInt();
				while(!(n >= 1)) {
					System.out.println("Please enter a number equal to 1 or greater: ");
					n = kb.nextInt();
				}
				System.out.println("Enter your i coordinate for subsqaure deletion: ");
				int i = kb.nextInt();
				while(i > Math.pow(2, n) || i <0 ) {
					System.out.println("Please enter a number equal to or greater than 0 and less than " + Math.pow(2, n) + " for i.");
					i = kb.nextInt();
				}
				System.out.println("Enter your j coordinate for subsqaure deletion: ");
				int j = kb.nextInt();
				while(j > Math.pow(2, n) || j <0 ) {
					System.out.println("Please enter a number equal to or greater than 0 and less than " + Math.pow(2, n) + " for j.");
					j = kb.nextInt();
				}
				System.out.println("The answer to this " + (int)Math.pow(2,n) + " by " +(int)Math.pow(2,n) + " sqaure with the subsquare ("
				+ i + "," + j + ") removed is: ");
				Grid(n,0,0,i,j);
				System.out.println();
			}
			else {
				System.out.println("Please ry again. Enter only \"yes\" or \"no\" as an answer.");
			}
		}
	}
	//2D array of 2^n by 2^n grid.
	public static void Grid(int n, int a, int b, int i, int j) {
		int squareSize = (int)Math.pow(2, n);
		square = new String[squareSize][squareSize];
		for(int y=0; y < squareSize; y++) {
			for(int x=0; x <squareSize;x++) {
				if(x == i && y==j) {
					square[x][y] = "null";
				}
				else {
					square[x][y] = "("+x+","+y+")";
				}
			}
		}
		Tri(n,a,b,i,j);
	}
	public static void Tri(int n, int a, int b, int i, int j) {
		int squareSize = (int)Math.pow(2, n);
		// if the grid is 2 x 2. Complete the square with final L shape & stop recursion
		if(n==1) {
			System.out.print("L shape: ");
			for(int y=b; y<(b+squareSize);y++) {
				for(int x=a; x <(a+squareSize);x++) {
					if(!square[x][y].equals("null")) {
						System.out.print(square[x][y] + " ");
						square[x][y] = "null";
					}
				}
				
			}
			System.out.println();
		}
		else {
			//find null of subsquares.
			for(int y=b; y < (b+squareSize); y++) {
				for(int x=a; x <(a+squareSize);x++) {
					if(square[x][y].equals("null")) {
						i=x;
						j=y;
						break;
					}
				}
			}
			//if null falls in quadrant 1 
			if( (a <= i && i < ( a+ squareSize/2) ) && (b <= j && j < (b+squareSize/2) ) ) {
				
				
				//find center
				int x = a + squareSize/2;
				int y = b + squareSize/2;
				System.out.println("L shape: " + square[x-1][y] + " " + square[x][y] + " " + square[x][y-1]);
				square[x-1][y] = "null";
				square[x][y] = "null";
				square[x][y-1] = "null";
				
				//recursive calls
				Tri(n-1,a,b,0,0 ); // 1st quadrant call 
				Tri(n-1,a,b+squareSize/2,0,0); //4th quadrant call
				Tri(n-1,a+squareSize/2, b,0,0 ); //2nd quadrant call
				Tri(n-1,a+squareSize/2,b+squareSize/2,0,0); //3rd quadrant call
				
			}
			// if null falls in quadrant 2
			else if( ((a+squareSize/2) <= i && i < (a+squareSize)) && ( j >= b && j < (b+squareSize/2) ) ){
				
				
				//find center
				int x = a + squareSize/2;
				int y = b + squareSize/2;
				System.out.println("L shape: " + square[x-1][y-1] + " " + square[x-1][y] + " " + square[x][y]);
				square[x-1][y-1] = "null";
				square[x-1][y] = "null";
				square[x][y] = "null";
				
				//recursive calls
				Tri(n-1,a,b,0,0 ); // 1st quadrant call 
				Tri(n-1,a,b+squareSize/2,0,0); //4th quadrant call
				Tri(n-1,a+squareSize/2, b,0,0 ); //2nd quadrant call
				Tri(n-1,a+squareSize/2,b+squareSize/2,0,0); //3rd quadrant call

			}
			// if null falls in quadrant 3
			else if( (i >= (a+squareSize/2) && i < (a+squareSize)) && ( j >= (b+squareSize/2) && j < (b+squareSize))) {
								
				//find center
				int x = a + squareSize/2;
				int y = b + squareSize/2;
				System.out.println("L shape: " + square[x-1][y] + " " + square[x-1][y-1] + " " + square[x][y-1]);
				square[x-1][y] = "null";
				square[x-1][y-1] = "null";
				square[x][y-1] = "null";
				
				//recursive calls
				Tri(n-1,a,b,0,0 ); // 1st quadrant call 
				Tri(n-1,a,b+squareSize/2,0,0); //4th quadrant call
				Tri(n-1,a+squareSize/2, b,0,0 ); //2nd quadrant call
				Tri(n-1,a+squareSize/2,b+squareSize/2,0,0); //3rd quadrant call
				
			}
			// if null falls in quadrant 4
			// for some reason quadrant for is not being reached when n-1.
			else if((i >= a && i < (a+squareSize/2)) && ( j >= (b+squareSize/2) && j < b+squareSize)) {
				
				
				//find center
				int x = a + squareSize/2;
				int y = b + squareSize/2;
				System.out.println("L shape: " + square[x-1][y-1] + " " + square[x][y-1] + " " + square[x][y]);
				square[x-1][y-1] = "null";
				square[x][y-1] = "null";
				square[x][y] = "null";
				
				//recursive calls
				Tri(n-1,a,b,0,0 ); // 1st quadrant call 
				Tri(n-1,a,b+squareSize/2,0,0); //4th quadrant call
				Tri(n-1,a+squareSize/2, b,0,0 ); //2nd quadrant call
				Tri(n-1,a+squareSize/2,b+squareSize/2,0,0); //3rd quadrant call
					
			}
		}
		
	}
	// To print out grid of our 2D for visual purposes.
	public static void print(String[][] square, int n) {
		for(int y=0; y < Math.pow(2, n); y++) {
			for(int x=0; x < Math.pow(2,n);x++) {
				System.out.print(square[x][y] + " ");
			}
			System.out.println();
		}
	}
}