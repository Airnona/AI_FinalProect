import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	static ArrayList<Node> map = new ArrayList<Node>();
	
	public static void main(String[] args) {
		createMap();
		printMap();
		while(true) {
			editMap("O");
			printMap();
		}
	}
	
	public static void createMap(){
		for(int i = 1; i < 9; i++) {
			for(int j = 1; j < 9; j++) {
				Node node = new Node(j, i, "-"); //Creates a map by adding all members of the first row, then all of the second row, then... etc
				map.add(node);
			}
		}
		return;
	}
	
	public static void printMap() {
		System.out.println("  1 2 3 4 5 6 7 8");
		char charCounter = 'A';
		
		for(int i = 0; i < map.size(); i++) {
			if(i % 8 == 0) {					//Every 8 prints, print the char counter
				if(i != 0) {					//Every 8 prints after the first, start a new line
					System.out.println();
				}
				System.out.print(charCounter + " ");
				charCounter += 1;				//Increment char counter to next letter
			}
			
			System.out.print(map.get(i).getVal() + " ");
		}
		System.out.println();
	}
	
	public static void editMap(String val) {
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		String input;
		char inputChar;
		int inputCol, index, inputRow = -1;
		
		while(exit == false) {
			System.out.println("Please enter the next move.");
			input = sc.nextLine();
			if(input.length() != 2) {
				System.out.println("Invalid entry. Please only enter a char followed by a num, such as a5, b2, c7, etc.");
				continue;		//Invalid entry, restart
			}
			
			inputChar = input.charAt(0);
			if(inputChar < 'a' || inputChar > 'h') {
				System.out.println("Invalid entry. Please enter a char between 'a' and 'h'.");
				continue;		//Invalid entry, restart
			}
			
			inputCol = Character.getNumericValue(input.charAt(1));
			if(inputCol < 1 || inputCol > 8) {
				System.out.println("Invalid entry. Please enter a number between 1 and 8.");
				continue;		//Invalid entry, restart
			}
			
			if(inputChar == 'a')
				inputRow = 1;
			else if(inputChar == 'b')
				inputRow = 2;
			else if(inputChar == 'c')
				inputRow = 3;
			else if(inputChar == 'd')
				inputRow = 4;
			else if(inputChar == 'e')
				inputRow = 5;
			else if(inputChar == 'f')
				inputRow = 6;
			else if(inputChar == 'g')
				inputRow = 7;
			else if(inputChar == 'h')
				inputRow = 8;
		
			index = (8 * (inputRow - 1)) + inputCol - 1;
			if(map.get(index).getVal() == "-") {
				map.get(index).setVal(val);
			}
			else {
				System.out.println("This spot on the board already has a value. Try again.");
				continue;		//Invalid entry, restart
			}
		
			exit = true;
		}
	}
}