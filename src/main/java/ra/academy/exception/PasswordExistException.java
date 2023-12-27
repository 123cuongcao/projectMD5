package ra.academy.exception;

public class PasswordExistException extends Exception{
    public PasswordExistException(String mess){
        super(mess);
    }
}
