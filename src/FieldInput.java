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
	static enum CellType{
		NOMINE, MINE
	}
	private static int numberOfFieldRows;
	private static int numberOfFieldColumns;
	private static File mineFieldLayoutFile = null;
	private static String inputCommand = "";
	private static Scanner userInputScanner = new Scanner(System.in);
	
	private static FieldInput mineFieldInput = null;
	
	FieldInput(){
		//mineFieldInput = new FieldInput();
		//this.numberOfFieldRows = 0;
		//this.numberOfFieldColumns = 0;
	}
	
	public FieldInput getFieldInput(){
		if(mineFieldInput == null){
			mineFieldInput = new FieldInput();
		}
		return mineFieldInput;
	}
	
	public void setFieldInput(FieldInput fieldInput){
		while(!inputCommand.equalsIgnoreCase("quit") && !inputCommand.equalsIgnoreCase("file")){
			promptUserForInput();
			inputCommand = userInputScanner.nextLine();
		}
		if(inputCommand.equalsIgnoreCase("file")){
			fieldInput.setMineFieldLayoutFile();
			userInputScanner.close();
		} else if(inputCommand.equalsIgnoreCase("quit")){
			exitProgram();
		} else{
			fieldInput.setFieldInput(fieldInput);
		}
	}
	
	public static void promptUserForInput(){
		System.out.println("Would you like to read a mine field from a text file or quit the program? (Type either 'file' or 'quit'.)");
		System.out.println("NOTE: Inputting 'file' will make the assumption that the .txt file is in the same directory and is named 'field.txt'.");
	}
	
	public static File getMineFieldLayoutFile(){
		return mineFieldLayoutFile;
	}
	
	public void setMineFieldLayoutFile(){
		mineFieldLayoutFile = new File("field.txt");
		
	}
	
	public int getNumberOfFieldRows(){
		return numberOfFieldRows;
	}
	
	public void setNumberOfFieldRows(File layoutFile){
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
	public int getNumberOfFieldColumns(){
		return numberOfFieldColumns;
	}
	
	public void setNumberOfFieldColumns(File layoutFile, FieldInput fieldInput){
		BufferedReader fieldFileReader;
		String firstLineOfFieldFile;
		try {
			fieldFileReader = new BufferedReader(new FileReader(getMineFieldLayoutFile()));
			firstLineOfFieldFile = fieldFileReader.readLine();
			numberOfFieldColumns = firstLineOfFieldFile.length();
			if(!hasCorrectNumberOfColumns(fieldInput)){
				fieldFileReader.close();
				ImproperColumnLengthRuntimeException exception = new ImproperColumnLengthRuntimeException();
				exception.printStackTrace();
				throw new ImproperColumnLengthRuntimeException();
			}
			fieldFileReader.close();
		} catch (IOException e) {
			System.out.println("The field.txt file could not be found. Please make sure a properly formatted field.txt file is in the same directory.");
			e.printStackTrace();
		}
	}
	
	private static boolean hasCorrectNumberOfColumns(FieldInput fieldInput){
		BufferedReader fieldFileReader;
		String lineOfFieldFile;
		try{
			fieldFileReader = new BufferedReader(new FileReader(getMineFieldLayoutFile()));
			while((lineOfFieldFile = fieldFileReader.readLine()) != null){
				if(lineOfFieldFile.length() != fieldInput.getNumberOfFieldColumns()){
					fieldFileReader.close();
					return false;
				}
			}
			fieldFileReader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	/*
	public static void setNumberOfFieldColumns(int numberOfColumns){
		if(numberOfColumns > 0){
			numberOfFieldColumns = numberOfColumns;
		}
	}
	*/
	public CellType [][] getFieldArraysFromFile(FieldInput fieldInput){
		CellType [][] mineField = new CellType [fieldInput.getNumberOfFieldRows()][fieldInput.getNumberOfFieldColumns()];
		BufferedReader fieldFileReader;
		String lineOfFieldFile;
		try {
			fieldFileReader = new BufferedReader(new FileReader(getMineFieldLayoutFile()));
			for(int fieldFileRowIndex = 0; fieldFileRowIndex < fieldInput.getNumberOfFieldRows(); fieldFileRowIndex++){
				lineOfFieldFile = fieldFileReader.readLine();
				for(int fieldFileColumnIndex = 0; fieldFileColumnIndex < fieldInput.getNumberOfFieldColumns(); fieldFileColumnIndex++){
					if(lineOfFieldFile.charAt(fieldFileColumnIndex) == '.'){
						mineField[fieldFileRowIndex][fieldFileColumnIndex] = CellType.NOMINE;
					}else if(lineOfFieldFile.charAt(fieldFileColumnIndex) == '*'){
						mineField[fieldFileRowIndex][fieldFileColumnIndex] = CellType.MINE;
					} else{
						fieldFileReader.close();
						IllegalCharacterInFieldFileRuntimeException exception = new IllegalCharacterInFieldFileRuntimeException();
						exception.printStackTrace();
						throw new IllegalCharacterInFieldFileRuntimeException();
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
		FieldInput fieldInput = new FieldInput();
		fieldInput.setMineFieldLayoutFile();
		fieldInput.setNumberOfFieldRows(getMineFieldLayoutFile());
		fieldInput.setNumberOfFieldColumns(getMineFieldLayoutFile(), fieldInput);
		System.out.println("The number of rows are: " + fieldInput.getNumberOfFieldRows());
		System.out.println("The number of columns are: " + fieldInput.getNumberOfFieldColumns());
		//getFieldArraysFromFile();
		MineField mineField = new MineField();
		mineField.setMineFieldArrayNumberOfRows(fieldInput.getNumberOfFieldRows());
		mineField.setMineFieldArrayNumberOfColumns(fieldInput.getNumberOfFieldColumns());
		System.out.println(mineField.getMineFieldArrayNumberOfRows());
		System.out.println(mineField.getMineFieldArrayNumberOfColumns());
		mineField.setMineFieldArray(fieldInput.getFieldArraysFromFile(fieldInput));
		System.out.println("Help.");
		mineField.printMineFieldArrayHints();
	}
}
