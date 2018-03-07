
public class Node {
	int col, row, score;

	String val;
	
	public Node(int row, int col, String val) {
		this.col = col;
		this.row = row;
		this.val = val;
	}

	public void printNode() {
		System.out.println("(" + row + ", " + col + ", " + val + ")");
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
