import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
/*
 * Minesweeper Hint System Coding Assessment for Haverty's
 * This class handles file input and conversion to arrays that can be worked with in the MineField class.
 * 
 * @author Jerry Springfield Jr. <jay.springfield93@gmail.com>
 */

public class FieldInput {
	private static enum CellType{
		NOMINE, MINE
	}
	private static int numberOfFieldRows;
	private static int numberOfFieldColumns;
	private static File mineFieldLayoutFile = null;
	private static String inputCommand = "";
	private static Scanner userInputScanner = new Scanner(System.in);
	
	private static FieldInput mineFieldInput = null;
	
	FieldInput(){
		mineFieldInput = new FieldInput();
		this.numberOfFieldRows = 0;
		this.numberOfFieldColumns = 0;
	}
	
	public static FieldInput getFieldInput(){
		if(mineFieldInput == null){
			mineFieldInput = new FieldInput();
		}
		return mineFieldInput;
	}
	
	public static void setFieldInput(){
		while(!inputCommand.equalsIgnoreCase("quit") && !inputCommand.equalsIgnoreCase("file")){
			promptUserForInput();
			inputCommand = userInputScanner.nextLine();
		}
		if(inputCommand.equalsIgnoreCase("file")){
			setMineFieldLayoutFile();
			userInputScanner.close();
		} else if(inputCommand.equalsIgnoreCase("quit")){
			exitProgram();
		} else{
			setFieldInput();
		}
	}
	
	public static void promptUserForInput(){
		System.out.println("Would you like to read a mine field from a text file or quit the program? (Type either 'file' or 'quit'.)");
		System.out.println("NOTE: Inputting 'file' will make the assumption that the .txt file is in the same directory and is named 'field.txt'.");
	}
	
	public static File getMineFieldLayoutFile(){
		return mineFieldLayoutFile;
	}
	
	public static void setMineFieldLayoutFile(){
		mineFieldLayoutFile = new File("field.txt");
		
	}
	
	public static int getNumberOfFieldRows(){
		return numberOfFieldRows;
	}
	
	public static void setNumberOfFieldRows(File layoutFile){
		BufferedReader fieldFileReader;
		int numberOfRows = 0;
		try {
			fieldFileReader = new BufferedReader(new FileReader(getMineFieldLayoutFile()));
			while(fieldFileReader.readLine() != null){
				numberOfRows++;
			}
			numberOfFieldRows = numberOfRows;
		} catch (IOException e) {
			System.out.println("The field.txt file could not be found. Please make sure a properly formatted field.txt file is in the same directory.");
			e.printStackTrace();
		}
	}
	/*
	public static void setNumberOfFieldRows(int numberOfRows){
		if(numberOfRows > 0){
			numberOfFieldRows = numberOfRows;
		}
	}
	*/
	public static int getNumberOfFieldColumns(){
		return numberOfFieldColumns;
	}
	
	public static void setNumberOfFieldColumns(File layoutFile){
		BufferedReader fieldFileReader;
		String firstLineOfFieldFile;
		try {
			fieldFileReader = new BufferedReader(new FileReader(getMineFieldLayoutFile()));
			firstLineOfFieldFile = fieldFileReader.readLine();
			numberOfFieldColumns = firstLineOfFieldFile.length();
			fieldFileReader.close();
		} catch (IOException e) {
			System.out.println("The field.txt file could not be found. Please make sure a properly formatted field.txt file is in the same directory.");
			e.printStackTrace();
		}
	}
	/*
	public static void setNumberOfFieldColumns(int numberOfColumns){
		if(numberOfColumns > 0){
			numberOfFieldColumns = numberOfColumns;
		}
	}
	*/
	public static CellType [][] fieldFileToArrays(){
		CellType [][] mineField = new CellType [getNumberOfFieldRows()][getNumberOfFieldColumns()];
		BufferedReader fieldFileReader;
		String lineOfFieldFile;
		try {
			fieldFileReader = new BufferedReader(new FileReader(getMineFieldLayoutFile()));
			for(int fieldFileRowIndex = 0; fieldFileRowIndex < getNumberOfFieldRows(); fieldFileRowIndex++){
				lineOfFieldFile = fieldFileReader.readLine();
				for(int fieldFileColumnIndex = 0; fieldFileColumnIndex < getNumberOfFieldColumns(); fieldFileColumnIndex++){
					if(lineOfFieldFile.charAt(fieldFileColumnIndex) == '.'){
						mineField[fieldFileRowIndex][fieldFileColumnIndex] = CellType.NOMINE;
					}else if(lineOfFieldFile.charAt(fieldFileColumnIndex) == '*'){
						mineField[fieldFileRowIndex][fieldFileColumnIndex] = CellType.MINE;
					} else{
						fieldFileReader.close();
						System.err.print("The field.txt file contained a character other than a period ('.') or an asterisk ('*'). Please make sure field.txt only contains those two characters. ");
						throw new ImproperMineFieldInputRuntimeException();
					}
				}
			}
			fieldFileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mineField;
	}
	
	private static int exitProgram(){
		return 1;
	}
	public static void main(String[] args){
		//FieldInput mineField = getFieldInput();
		setMineFieldLayoutFile();
		setNumberOfFieldRows(getMineFieldLayoutFile());
		setNumberOfFieldColumns(getMineFieldLayoutFile());
		System.out.println("The number of rows are: " + getNumberOfFieldRows());
		System.out.println("The number of columns are: " + getNumberOfFieldColumns());
		fieldFileToArrays();
		
	}
}
