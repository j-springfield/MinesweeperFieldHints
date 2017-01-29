import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/*
 * Minesweeper Hint System Coding Assessment for Haverty's
 * 
 * @author Jerry Springfield Jr. <jay.springfield93@gmail.com>
 */

public class FieldInput {
	private static int numberOfFieldRows;
	private static int numberOfFieldColumns;
	private static File mineFieldLayoutFile = null;
	private static String inputCommand = "";
	private static Scanner userInputScanner = new Scanner(System.in);
	
	private static FieldInput mineFieldInput = null;
	
	private FieldInput(){
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
		while(!inputCommand.equalsIgnoreCase("type") && !inputCommand.equalsIgnoreCase("file")){
			promptUserForInput();
			inputCommand = userInputScanner.nextLine();
		}
		if(inputCommand.equalsIgnoreCase("file")){
			setMineFieldLayoutFile();
			userInputScanner.close();
		} else if(inputCommand.equalsIgnoreCase("type")){
			mineFieldLayoutFile = null;
			//add row and col input prompt and scan
		} else{
			setFieldInput();
		}
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
	
	public static void setNumberOfFieldRows(int numberOfRows){
		if(numberOfRows > 0){
			numberOfFieldRows = numberOfRows;
		}
	}
	
	public static int getNumberOfFieldColumns(){
		return numberOfFieldColumns;
	}
	
	public static void setNumberOfFieldColumns(File layoutFile){
		BufferedReader fieldFileReader;
		int numberOfColumns = 0;
		try {
			fieldFileReader = new BufferedReader(new FileReader(getMineFieldLayoutFile()));
			while(fieldFileReader.readLine() != null){
				numberOfColumns++;
			}
			numberOfFieldColumns = numberOfColumns;
		} catch (IOException e) {
			System.out.println("The field.txt file could not be found. Please make sure a properly formatted field.txt file is in the same directory.");
			e.printStackTrace();
		}
	}
	
	public static void setNumberOfFieldColumns(int numberOfColumns){
		if(numberOfColumns > 0){
			numberOfFieldColumns = numberOfColumns;
		}
	}
	
	public static void promptUserForInput(){
		System.out.println("Would you like to read a mine field from a text file, or input each line at a time? (Type either 'file' or 'type'.)");
		System.out.println("NOTE: Inputting 'file' will make the assumption that the .txt file is in the same directory and is named 'field.txt'.");
	}
	
	public static void main(String[] args){
		FieldInput mineField = getFieldInput();
	}
}
