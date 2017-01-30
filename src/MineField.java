/*
 * Minesweeper Hint System Coding Assessment for Haverty's
 * 
 * 
 * @author Jerry Springfield Jr. <jay.springfield93@gmail.com>
 */

public class MineField {
	private char mineFieldArray [][];
	private int mineFieldArrayNumberOfRows;
	private int mineFieldArrayNumberOfColumns;
	private int numberOfMines = 0;
	
	public MineField(){
		FieldInput fieldInput = null;
		fieldInput.getFieldInput();
		fieldInput.setMineFieldLayoutFile();
	}
	
	public char [][] getMineFieldArray(){
		return mineFieldArray;
	}
	/*
	public void setMineFieldArray(CellType [][] mineFieldCellTypeArrays){
		for(int mineFieldRowIndex = 0; mineFieldRowIndex < )
		
	}
	*/
	public int getMineFieldArrayNumberOfRows(){
		return mineFieldArrayNumberOfRows;
	}
	
	public void setMineFieldArrayNumberOfRows(int numberOfRows){
		if(numberOfRows > 0)
			mineFieldArrayNumberOfRows = numberOfRows;
	}
	
	public int getMineFieldArrayNumberOfColumns(){
		return mineFieldArrayNumberOfColumns;
	}
	
	public void setMineFieldArrayNumberOfColumns(int numberOfColumns){
		if(numberOfColumns > 0)
			mineFieldArrayNumberOfColumns = numberOfColumns;
	}
}
