
public class Node {
	int col, row;
	String val;
	
	public Node(int row, int col, String val) {
		this.col = col;
		this.row = row;
		this.val = val;
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
