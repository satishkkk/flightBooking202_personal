package cardValidationService;

public class InvalidCard implements Validator{
    private Validator successor = null;
    @Override
    public String handleValidation(String cardNumber) {
        System.out.println("InvalidCard");
        return "Invalid";
    }

    @Override
    public void setSuccessor(Validator next) {
        this.successor = next;
    }
}
