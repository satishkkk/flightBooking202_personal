package cardValidationService;

public interface Validator {

    public String handleValidation(String cardNumber);

    public void setSuccessor(Validator next);
}
