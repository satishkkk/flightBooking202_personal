package cardValidationService;

public class AmexCardValidator implements Validator{
    private Validator successor = null;

    @Override
    public String handleValidation(String cardNumber) {
        String cardType = "";
        int firstDigit = Character.getNumericValue(cardNumber.charAt(0));
        int secondDigit = Character.getNumericValue(cardNumber.charAt(1));

        if(cardNumber.length() == 15 && firstDigit == 3 && (secondDigit == 4 || secondDigit == 7)){
            System.out.println("AMEX CARD . . length is 135 . . and starts with 3  .. . . 2nd digit must be 4 or 7 ");
            cardType = "AMEX";
        }else{
            if(successor != null){
                cardType = successor.handleValidation(cardNumber);
            }
        }
        return cardType;
    }

    @Override
    public void setSuccessor(Validator next) {
        this.successor = next;
    }
}
