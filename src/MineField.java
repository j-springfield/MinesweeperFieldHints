
public class MineField {
	private static enum CellType{
		NOMINE, MINE
	}
	
	private CellType mineFieldArray [][];
	private int mineFieldArrayNumberOfRows;
	private int mineFieldArrayNumberOfColumns;
	
	public MineField(){
		FieldInput fieldInput = null;
		fieldInput.getFieldInput();
		fieldInput.setMineFieldLayoutFile();
	}
	
	public CellType [][] getMineFieldArray(){
		return mineFieldArray;
	}
	
	public void setMineFieldArray(){
		mineFieldArray = new CellType[getMineFieldArrayNumberOfRows()][getMineFieldArrayNumberOfColumns()];
		//while()
	}
	
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
