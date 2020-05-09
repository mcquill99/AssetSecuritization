package ASP;

public class insufficientSharesException extends Exception{
    public insufficientSharesException(String errorMessage){
        super(errorMessage);
    }
}
