package cardValidationService;

public class MasterCardValidator implements Validator{
    private Validator successor = null;

    @Override
    public String handleValidation(String cardNumber) {
        String cardType = "";
        int firstDigit = Character.getNumericValue(cardNumber.charAt(0));
        int secondDigit = Character.getNumericValue(cardNumber.charAt(1));
        if( (cardNumber.length() == 16) &&  (firstDigit == 5)  && ( secondDigit <= 5 && secondDigit >=1 ) ){
            System.out.println("Master CARD . . length is 16  . . and starts with 5 and 2 nd digit is between (1-5)");
            cardType = "MasterCard";
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
