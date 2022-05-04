package cardValidationService;

public class VisaCardValidator implements Validator{
    private Validator successor = null;

    @Override
    public String handleValidation(String cardNumber) {
        String cardType = "";
        int firstDigit = Character.getNumericValue(cardNumber.charAt(0));
        if( (cardNumber.length() == 13 || cardNumber.length() ==16) && firstDigit == 4){
            System.out.println("VISA CARD . . length is 13 or 16 . . and starts with 4");
            cardType = "Visa";
        }else{
            if(successor != null){
                cardType =successor.handleValidation(cardNumber);
            }
        }
        return cardType;
    }

    @Override
    public void setSuccessor(Validator next) {
        this.successor = next;
    }
}
