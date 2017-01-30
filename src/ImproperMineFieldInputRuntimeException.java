
public class ImproperMineFieldInputRuntimeException extends RuntimeException{
	public ImproperMineFieldInputRuntimeException(){
		String errorMessage = "The field.txt file contained a character other than a period ('.') or a asterisk ('*'). Please make sure field.txt only contains those two characters.";
	}
}
