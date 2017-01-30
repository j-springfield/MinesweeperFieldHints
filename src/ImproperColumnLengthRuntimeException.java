/*
 * Minesweeper Hint System Coding Assessment for Haverty's
 * 
 * 
 * @author Jerry Springfield Jr. <jay.springfield93@gmail.com>
 */

public class ImproperColumnLengthRuntimeException extends RuntimeException {
	public String ImproperColumnLengthRuntimeException(){
		String errorMessage = "The field.txt file has a row that is not the same number of columns as the first one, the one assumed to have the proper number of columns.";
		return errorMessage;
	}
}
