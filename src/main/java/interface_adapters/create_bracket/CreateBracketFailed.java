package interface_adapters.create_bracket;

public class CreateBracketFailed extends RuntimeException{
    /*
    A class for the output data of the create bracket use case.
     */
   public CreateBracketFailed(String errorMessage) {
       super(errorMessage);
   }
}
