/*
 * Minesweeper Hint System Coding Assessment for Haverty's
 * 
 * 
 * @author Jerry Springfield Jr. <jay.springfield93@gmail.com>
 */

public class MineField {
	private String mineFieldArray [][];
	private int mineFieldArrayNumberOfRows;
	private int mineFieldArrayNumberOfColumns;
	
	public MineField(){
		FieldInput fieldInput = new FieldInput();
		fieldInput.setMineFieldLayoutFile();
		mineFieldArrayNumberOfRows = fieldInput.getNumberOfFieldRows();
		mineFieldArrayNumberOfColumns = fieldInput.getNumberOfFieldColumns();
	}
	
	public String [][] getMineFieldArray(){
		return mineFieldArray;
	}
	
	public void setMineFieldArray(FieldInput.CellType [][] mineFieldCellTypeArrays){
		mineFieldArray = new String[getMineFieldArrayNumberOfRows()][getMineFieldArrayNumberOfColumns()];
		for(int mineFieldRowIndex = 0; mineFieldRowIndex < getMineFieldArrayNumberOfRows(); mineFieldRowIndex++){
			for(int mineFieldColumnIndex = 0; mineFieldColumnIndex < getMineFieldArrayNumberOfColumns(); mineFieldColumnIndex++){
				if(mineFieldCellTypeArrays[mineFieldRowIndex][mineFieldColumnIndex] == FieldInput.CellType.MINE){
					mineFieldArray[mineFieldRowIndex][mineFieldColumnIndex] = "*";
				} else if(mineFieldCellTypeArrays[mineFieldRowIndex][mineFieldColumnIndex] == FieldInput.CellType.NOMINE){
					mineFieldArray[mineFieldRowIndex][mineFieldColumnIndex] = getNumberOfSurroundingMines(mineFieldRowIndex, mineFieldColumnIndex, mineFieldCellTypeArrays).toString();
				}
			}
		}
		
	}
	
	private Integer getNumberOfSurroundingMines(int fieldRowIndex, int fieldColumnIndex, FieldInput.CellType [][] mineFieldCellTypeArrays){
		Integer numberOfSurroundingMines = 0;
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
}
