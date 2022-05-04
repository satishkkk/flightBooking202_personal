package cardValidationService;

public class DiscoverCardValidator implements Validator {
    private Validator successor = null;

    @Override
    public String handleValidation(String cardNumber) {
        String cardType = "";
        if (cardNumber.length() > 4 && cardNumber.length() == 16 && "6011".equals(cardNumber.substring(0, 4))) {
            System.out.println("Discover CARD . . length is 16 . . and starts with 6011");
            cardType = "Discover";
        } else {
            if (successor != null) {
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
