import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	static ArrayList<Node> map = new ArrayList<Node>();
	static ArrayList<Node> totalList = new ArrayList<Node>();
	static ArrayList<Node> oList = new ArrayList<Node>();
	static ArrayList<Node> xList = new ArrayList<Node>();
	
	public static void main(String[] args) {
		createMap();
		printMap();
		runGame();
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
				totalList.add(map.get(index));
				if(val == "O") {
					oList.add(map.get(index));
				}
				else {
					xList.add(map.get(index));
				}
			}
			else {
				System.out.println("This spot on the board already has a value. Try again.");
				continue;		//Invalid entry, restart
			}
		
			exit = true;
		}
	}
	
    public static void runGame() {
//    	ArrayList<Node> totalList = new ArrayList<Node>();
//    	ArrayList<Node> oList = new ArrayList<Node>();
//    	ArrayList<Node> xList = new ArrayList<Node>();
    	ArrayList<Node> open = new ArrayList<Node>();
    	Scanner sc = new Scanner(System.in);

    	String mirrValue, playerValue;
    	boolean exit = false, playerTurn = true;;
    	int input = -1;
    	
    	System.out.println("Is the player going first or second? Enter 1 or 2");
    	input = sc.nextInt();
    	
    	if(input == 1) {						//Player goes first
    		playerValue = "O";
    		mirrValue = "X";
    		
    		editMap(playerValue);
    		printMap();
    		playerTurn = false;					//Switching turns
    	}
    	else {									//AI goes first
    		playerValue = "X";
    		mirrValue = "O";
    		
    		map.get(27).setVal(mirrValue);		//Default entry for AI first turn
    		oList.add(map.get(27));
    		totalList.add(map.get(27));
    		printMap();
    		playerTurn = true;					//Switching turns
    	}
    	
    	Mirr mirr = new Mirr(mirrValue);
    	
    	while(exit != true) {
    		mirr.updateArrays(map, totalList, xList, oList);
//    		System.out.println("Driver printing totalList");
//    		for(int i = 0; i < totalList.size(); i++) {
//    			totalList.get(i).printNode();
//    		}
    		
    		if(playerTurn) {
    			editMap(playerValue);
        		playerTurn = false;
        		printMap();
    		}
    		else {
    			open = mirr.findPossible();
    			
//    			System.out.println("Open size: " + open.size());
//    			for(int i = 0; i < open.size(); i++) {
//    				open.get(i).printNode();
//    			}
    			editMap(mirrValue);
    			printMap();
    			playerTurn = true;
    		}
    		//checkVictory();
    	}
    	printMap();
    	
    }
}