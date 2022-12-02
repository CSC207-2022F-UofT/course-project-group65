package interface_adapters.create_account;

public class CreateAccountFailed extends RuntimeException{
    public CreateAccountFailed(String error) {super(error); }
}
