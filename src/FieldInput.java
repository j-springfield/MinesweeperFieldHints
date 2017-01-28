import java.io.File;
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
			mineFieldLayoutFile = new File("field.txt");
		} else if(inputCommand.equalsIgnoreCase("type")){
			
		} else{
			setFieldInput();
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
