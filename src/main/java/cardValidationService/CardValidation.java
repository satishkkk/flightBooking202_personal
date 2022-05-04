package cardValidationService;

public class CardValidation {

    private static CardValidation instance;

    private CardValidation() {
    }

    public static CardValidation getInstance() {
        if (instance == null) {
            instance = new CardValidation();
        }
        return instance;
    }

    public String validateCard(String cardNumber) {
        Validator v1 = new VisaCardValidator();
        Validator v2 = new MasterCardValidator();
        Validator v3 = new DiscoverCardValidator();
        Validator v4 = new AmexCardValidator();
        Validator v5 = new InvalidCard();

        v1.setSuccessor(v2);
        v2.setSuccessor(v3);
        v3.setSuccessor(v4);
        v4.setSuccessor(v5);

        System.out.println("CardValidation : starting validation for cardNumber : " + cardNumber);
        String cardType = v1.handleValidation(cardNumber);
        System.out.println("Card type : " + cardType);

        return cardType;
    }

}
