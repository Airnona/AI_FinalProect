import java.util.ArrayList;

public class Mirr {
	ArrayList<Node> map, totalList, xList, oList, open;
	String val;
	public Mirr(String val) {
		this.val = val;
	}
	
	public void updateArrays(ArrayList<Node> map, ArrayList<Node> totalUsed, ArrayList<Node> xArr, ArrayList<Node> oArr) {
		this.map = map;
		this.totalList = totalUsed;
		this.xList = xArr;
		this.oList = oArr;
		
//		for(int i = 0;)
	}
	
	public ArrayList<Node> findPossible() {
		this.open = new ArrayList<Node>();
		int upMax, downMax, leftMax, rightMax, row, col;
		for(int i = 0; i < totalList.size(); i++) {
			totalList.get(i).printNode();
		}
		for(int i = 0; i < totalList.size(); i++) {
			upMax = 2;
			downMax = 2;
			leftMax = 2;
			rightMax = 2;
			
			//Set limits for function to check for potential open spaces. Goal: Only up to 2 adjacent spaces from currently entered indices.
			if(totalList.get(i).getRow() == 1)
				upMax = 0;
			if(totalList.get(i).getRow() == 2)
				upMax = 1;
			if(totalList.get(i).getRow() == 8)
				downMax = 0;
			if(totalList.get(i).getRow() == 7)
				downMax = 1;
			if(totalList.get(i).getCol() == 1)
				leftMax = 0;
			if(totalList.get(i).getCol() == 2)
				leftMax = 1;
			if(totalList.get(i).getCol() == 8)
				rightMax = 0;
			if(totalList.get(i).getCol() == 7)
				rightMax = 1;
			
			for(int j = 1; j <= leftMax; j++) {						//Checking left values
				System.out.println("In left forloop");
				row = totalList.get(i).getRow();
				col = totalList.get(i).getCol();
				
				if(j == 2) {										//Checking space left 2
					col -= 2;
					if(!(contains(open, row, col))
					   && !(contains(totalList, row, col))) {
						open.add(findNode(map, row, col));
						continue;
					}
				}
				else {
					col -= 1;
					if(upMax > 0) {									//Checking space above and left 1
						row -= 1;
						if(!(contains(open, row, col))
						   && !(contains(totalList, row, col))) {
							open.add(findNode(map, row, col));
						}
						row += 1;
					}
					
					if(!(contains(open, row, col))
					   && !(contains(totalList, row, col))) {		//Checking space left 1
						open.add(findNode(map, row, col));
					}
					
					if(downMax > 0) {
						row += 1;
						if(!(contains(open, row, col))
						   && !(contains(totalList, row, col))) {	//Checking space down and left 1
							open.add(findNode(map, row, col));
						}
						row -= 1;
					}		
				}
			}
			
			for(int j = 1; j <= rightMax; j++) {						//Checking left values
				row = totalList.get(i).getRow();
				col = totalList.get(i).getCol();
				
				if(j == 2) {										//Checking space left 2
					col += 2;
					if(!(contains(open, row, col))
					   && !(contains(totalList, row, col))) {
						open.add(findNode(map, row, col));
						continue;
					}
				}
				else {
					col += 1;
					if(upMax > 0) {									//Checking space above and left 1
						row -= 1;
						if(!(contains(open, row, col))
						   && !(contains(totalList, row, col))) {
							open.add(findNode(map, row, col));
						}
						row += 1;
					}
					
					if(!(contains(open, row, col))
					   && !(contains(totalList, row, col))) {		//Checking space left 1
						open.add(findNode(map, row, col));
					}
					
					if(downMax > 0) {
						row += 1;
						if(!(contains(open, row, col))
						   && !(contains(totalList, row, col))) {	//Checking space down and left 1
							open.add(findNode(map, row, col));
						}
						row -= 1;
					}		
				}
			}
			
			for(int j = 1; j <= upMax; j++) {
				row = totalList.get(i).getRow();
				col = totalList.get(i).getCol();
				
				if(j == 2) {
					row -= 2;
					if(!(contains(open, row, col))
					   && !(contains(totalList, row, col))) {	//Checking space down and left 1
						open.add(findNode(map, row, col));
						continue;
					}
					row += 2;
				}
				if(j == 1) {
					row -= 1;
					if(!(contains(open, row, col))
					   && !(contains(totalList, row, col))) {	//Checking space down and left 1
						open.add(findNode(map, row, col));
						continue;
					}
					row += 1;
				}
			}
			
			for(int j = 1; j <= downMax; j++) {
				row = totalList.get(i).getRow();
				col = totalList.get(i).getCol();
				
				if(j == 2) {
					row += 2;
					if(!(contains(open, row, col))
					   && !(contains(totalList, row, col))) {	//Checking space down and left 1
						open.add(findNode(map, row, col));
						continue;
					}
					row -= 2;
				}
				if(j == 1) {
					row += 1;
					if(!(contains(open, row, col))
					   && !(contains(totalList, row, col))) {	//Checking space down and left 1
						open.add(findNode(map, row, col));
						continue;
					}
					row -= 1;
				}
			}
		}
		return open;
	}

	public Node findBestValue(ArrayList<Node> arr, myValue, enemyValue) {
		int upMax, downMax, leftMax, rightMax, row, col, totalScore, numDash, numEnemy, numMine;
		String compareVal;
		
		for(int i = 0; i < arr.size(); i++) {
			totalScore = 0;
			row = arr.get(i).getRow();
			col = arr.get(i).getCol();
			
			upMax = setUpMax(row);
			downMax = setDownMax(row);
			leftMax = setLeftMax(col);
			rightMax = setRightMax(col);
			
			for(int j = col - leftMax; j <= col + rightMax - 3; j++) {		//Iterates from the far left, in sequences of 4, to the far right
				//Used to count types of patterns found
				numDash = 0;
				numEnemy = 0;
				numMine = 0;
				
				for(int k = 0; k < 4; k++) {
					compareVal = findNode(arr, row, j + k);
					if(compareVal == "-")
						numDash++;
					if(compareVal == myValue)
						numMine++;
					if(compareVal == enemyValue)
						numEnemy++;
				}
				
				totalValue += findValue(numDash, numMine, numEnemy);
				
			}
			
			for(int j = row - upMax; j <= row + downMax - 3; j++) {		//Iterates from the far left, in sequences of 4, to the far right
				//Used to count types of patterns found
				numDash = 0;
				numEnemy = 0;
				numMine = 0;
				
				for(int k = 0; k < 4; k++) {
					compareVal = findNode(arr, j + k, col);
					if(compareVal == "-")
						numDash++;
					if(compareVal == myValue)
						numMine++;
					if(compareVal == enemyValue)
						numEnemy++;
				}
				
				totalValue += findValue(numDash, numMine, numEnemy);
				
			}
			
		}
		
		return totalValue
		
	}
	
	private int findValue(int numDash, int numMine, int numEnemy) { //THIS DETERMINES POINT ASSIGNMENTS AND PRIORITIES OF THE EQUATION
		int total = 0;
		if(numMine == 3)
			return 9999;	//This means a winning position is found, end everything and win the game.
		if(numMine == 2)
			total += 4;
		if(numMine == 1)
			total += 2;
		
		if(numEnemy == 3)
			return 8888;
		if(numEnemy == 2)
			total += 8;
		if(numEnemy == 1)
			total += 4;
		
		total += numDash;
		
		return total;
	}
	
	private int setUpMax(int row) {
		if(row == 1)
			return 0;
		if(row == 2)
			return 1;
		if(row == 3)
			return 2;
		return 3;
	}
	
	private int setDownMax(int row) {
		if(row == 8)
			return 0;
		if(row == 7)
			return 1;
		if(row == 6)
			return 2;
		return 3;
	}
	
	private int setLeftMax(int col) {
		if(col == 1)
			return 0;
		if(col == 2)
			return 1;
		if(col == 3)
			return 2;
		return 3;
	}
	
	private int setRightMax(int col) {
		if(col == 8)
			return 0;
		if(col == 7)
			return 1;
		if(col == 6)
			return 2;
		return 3;
	}
	
	private static boolean contains(ArrayList<Node> arr, int row, int col) {
		for(int i = 0; i < arr.size(); i++) {
			if(arr.get(i).getRow() == row && arr.get(i).getCol() == col)
				return true;
		}
		return false;
	}
	
	private static Node findNode(ArrayList<Node> arr, int row, int col) {
		for(int i = 0; i < arr.size(); i++) {
			if(arr.get(i).getRow() == row && arr.get(i).getCol() == col)
				return arr.get(i);
		}
		//This shouldn't happen as we should only be searching map, which has all nodes.
		System.out.println("Somehow made it through find node without finding the node. Returning Null");
		return null;
	}
	
}
