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
	
	public MineField(){
		FieldInput fieldInput = new FieldInput();
		fieldInput.setMineFieldLayoutFile();
		mineFieldArrayNumberOfRows = fieldInput.getNumberOfFieldRows();
		mineFieldArrayNumberOfColumns = fieldInput.getNumberOfFieldColumns();
	}
	
	public char [][] getMineFieldArray(){
		return mineFieldArray;
	}
	
	public void setMineFieldArray(FieldInput.CellType [][] mineFieldCellTypeArrays){
		for(int mineFieldRowIndex = 0; mineFieldRowIndex < getMineFieldArrayNumberOfRows(); mineFieldRowIndex++){
			for(int mineFieldColumnIndex = 0; mineFieldColumnIndex < getMineFieldArrayNumberOfColumns(); mineFieldColumnIndex++){
				if(mineFieldCellTypeArrays[mineFieldRowIndex][mineFieldColumnIndex] == FieldInput.CellType.MINE){
					mineFieldArray[mineFieldRowIndex][mineFieldColumnIndex] = '*';
				} else if(mineFieldCellTypeArrays[mineFieldRowIndex][mineFieldColumnIndex] == FieldInput.CellType.NOMINE){
					mineFieldArray[mineFieldRowIndex][mineFieldColumnIndex] = (char) getNumberOfSurroundingMines(mineFieldRowIndex, mineFieldColumnIndex, mineFieldCellTypeArrays);
				}
			}
		}
		
	}
	
	private int getNumberOfSurroundingMines(int fieldRowIndex, int fieldColumnIndex, FieldInput.CellType [][] mineFieldCellTypeArrays){
		int numberOfSurroundingMines = 0;
		if(fieldRowIndex - 1 >= 0 && isInBounds(fieldRowIndex - 1, fieldColumnIndex)){
			if(mineFieldCellTypeArrays[fieldRowIndex - 1][fieldColumnIndex] == FieldInput.CellType.MINE){
				numberOfSurroundingMines++;
			}
			if(fieldColumnIndex - 1 >= 0 && isInBounds(fieldRowIndex - 1, fieldColumnIndex - 1)){
				if(mineFieldCellTypeArrays[fieldRowIndex - 1][fieldColumnIndex - 1] == FieldInput.CellType.MINE){
					numberOfSurroundingMines++;
				}
			}
			if(isInBounds(fieldRowIndex - 1, fieldRowIndex + 1)){
				if(fieldColumnIndex + 1 < mineFieldArrayNumberOfColumns){
					if(mineFieldCellTypeArrays[fieldRowIndex - 1][fieldColumnIndex + 1] == FieldInput.CellType.MINE){
						numberOfSurroundingMines++;
					}
				}
			}
		}
		if(fieldRowIndex + 1 < mineFieldArrayNumberOfRows && isInBounds(fieldRowIndex + 1, fieldColumnIndex)){
			if(mineFieldCellTypeArrays[fieldRowIndex + 1][fieldColumnIndex] == FieldInput.CellType.MINE){
				numberOfSurroundingMines++;
			}
			if(fieldColumnIndex + 1 < mineFieldArrayNumberOfColumns && isInBounds(fieldRowIndex + 1, fieldColumnIndex + 1)){
				if(mineFieldCellTypeArrays[fieldRowIndex + 1][fieldColumnIndex + 1] == FieldInput.CellType.MINE){
					numberOfSurroundingMines++;
				}
			}
			if(isInBounds(fieldRowIndex + 1, fieldColumnIndex - 1)){
				if(fieldColumnIndex - 1 >= 0){
					if(mineFieldCellTypeArrays[fieldRowIndex + 1][fieldColumnIndex - 1] == FieldInput.CellType.MINE){
						numberOfSurroundingMines++;
					}
				}
			}
		}
		if(fieldColumnIndex - 1 >= 0 && isInBounds(fieldRowIndex, fieldColumnIndex - 1)){
			if(mineFieldCellTypeArrays[fieldRowIndex][fieldColumnIndex - 1] == FieldInput.CellType.MINE){
				numberOfSurroundingMines++;
			}
		}
		if(fieldColumnIndex + 1 < mineFieldArrayNumberOfColumns && isInBounds(fieldRowIndex, fieldColumnIndex + 1)){
			if(mineFieldCellTypeArrays[fieldRowIndex][fieldColumnIndex + 1] == FieldInput.CellType.MINE){
				numberOfSurroundingMines++;
			}
		}
		return numberOfSurroundingMines;
	}
	
	private boolean isInBounds(int fieldRowIndex, int fieldColumnIndex){
		return((fieldRowIndex >= 0 && fieldRowIndex < mineFieldArrayNumberOfRows) && (fieldColumnIndex >= 0 && fieldColumnIndex < mineFieldArrayNumberOfColumns));
	}
	
	public int getMineFieldArrayNumberOfRows(){
		return mineFieldArrayNumberOfRows;
	}
	
	public void setMineFieldArrayNumberOfRows(int numberOfRows){
		this.mineFieldArrayNumberOfRows = numberOfRows;
	}
	
	public int getMineFieldArrayNumberOfColumns(){
		return mineFieldArrayNumberOfColumns;
	}
	
	public void setMineFieldArrayNumberOfColumns(int numberOfColumns){
		this.mineFieldArrayNumberOfColumns = numberOfColumns;
	}
	
	public void printMineFieldArrayHints(){
		for(int rowIndex = 0; rowIndex < mineFieldArrayNumberOfRows; rowIndex++){
			for(int columnIndex = 0; columnIndex < mineFieldArrayNumberOfColumns; columnIndex++){
				System.out.print(mineFieldArray[rowIndex][columnIndex]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		/*System.out.println("Running test for printing the field. Help.");
		FieldInput fieldInput = new FieldInput();
		fieldInput.setMineFieldLayoutFile();
		fieldInput.setNumberOfFieldRows(fieldInput.getMineFieldLayoutFile());
		fieldInput.setNumberOfFieldColumns(fieldInput.getMineFieldLayoutFile(), fieldInput);
		System.out.println("The number of rows are: " + fieldInput.getNumberOfFieldRows());
		System.out.println("The number of columns are: " + fieldInput.getNumberOfFieldColumns());
		MineField mineField = new MineField();
		mineField.setMineFieldArrayNumberOfRows(fieldInput.getNumberOfFieldRows());
		mineField.setMineFieldArrayNumberOfColumns(fieldInput.getNumberOfFieldColumns());
		System.out.println(mineField.getMineFieldArrayNumberOfRows());
		System.out.println(mineField.getMineFieldArrayNumberOfColumns());
		mineField.setMineFieldArray(fieldInput.getFieldArraysFromFile(fieldInput));
		mineField.printMineFieldArrayHints();*/
	}
}
