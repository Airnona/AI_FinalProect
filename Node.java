
public class Node {
	int col, row, score;

	String val;
	
	public Node(int row, int col, String val) {
		this.col = col;
		this.row = row;
		this.val = val;
	}

	public void printNode() {
		System.out.println("(" + row + ", " + col + ", " + val + ", " + score + ")");
	}
	
	public String printAuto() {
		String auto = "";
		
		if(this.row == 1)
			auto = "a" + this.col;
		else if(this.row == 2)
			auto = "b" + this.col;
		else if(this.row == 3)
			auto = "c" + this.col;
		else if(this.row == 4)
			auto = "d" + this.col;
		else if(this.row == 5)
			auto = "e" + this.col;
		else if(this.row == 6)
			auto = "f" + this.col;
		else if(this.row == 7)
			auto = "g" + this.col;
		else if(this.row == 8)
			auto = "h" + this.col;
		
		return auto;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
}
