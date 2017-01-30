/*
 * Minesweeper Hint System Coding Assessment for Haverty's
 * 
 * 
 * @author Jerry Springfield Jr. <jay.springfield93@gmail.com>
 */

public class IllegalCharacterInFieldFileRuntimeException extends RuntimeException{
	public String IllegalCharacterInFieldFileRuntimeException(){
		String errorMessage = "The field.txt file contained a character other than a period ('.') or a asterisk ('*'). Please make sure field.txt only contains those two characters.";
		return errorMessage;
	}
}
