package useCases.createBracket;

public class CreateBracketUC {
/*
    * This is a use case for creating a bracket.
 */

    private BracketAssembler assembler;

    public CreateBracketUC() {
        assembler = new BracketAssembler();
    }

    public void createBracket(String bracketType) {
        assembler.setBuilder(bracketType);
    }


}
