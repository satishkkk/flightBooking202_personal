package cardValidationService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class CardValidationTest {

    private static CardValidation instance = CardValidation.getInstance();

    @Test
    void getInstance() {
        CardValidation instance = CardValidation.getInstance();
        assertNotNull(instance);
    }

    @Test
    void validateVisaCard() {
        String s = instance.validateCard("4444444444444");
        assertEquals(s, "Visa");
    }

    @Test
    void validateMasterCard() {
        String s = instance.validateCard("5444444444444456");
        assertEquals(s, "MasterCard");
    }

    @Test
    void validateDiscoverCard() {
        String s = instance.validateCard("6011444455556666");
        assertEquals(s, "Discover");
    }

    @Test
    void validateAmexCard() {
        String s = instance.validateCard("348888999966662");
        assertEquals(s, "AMEX");
    }

    @Test
    void validatebadCard() {
        String s = instance.validateCard("444444444444444444444");
        assertEquals(s, "Invalid");
    }

}