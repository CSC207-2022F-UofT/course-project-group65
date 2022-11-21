package screens.logIn;

public class LogInFailed extends RuntimeException{
    public LogInFailed(String error) {
        super(error) ;
    }
}
