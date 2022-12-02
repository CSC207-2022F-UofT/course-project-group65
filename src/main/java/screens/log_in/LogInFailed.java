package screens.log_in;

public class LogInFailed extends RuntimeException{
    public LogInFailed(String error) {
        super(error) ;
    }
}
