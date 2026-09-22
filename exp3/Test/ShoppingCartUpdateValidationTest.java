import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShoppingCartUpdateValidationTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void shouldRejectInvalidNamesWithoutChangingCart(String name) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Book", 20.0);

        assertThrows(IllegalArgumentException.class,
            () -> cart.updateItemPrice(name, 30.0));

        assertEquals(1, cart.getItemCount());
        assertEquals(20.0, cart.getTotal());
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void shouldRejectNonFinitePricesWithoutChangingCart(double price) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Book", 20.0);

        assertThrows(IllegalArgumentException.class,
            () -> cart.updateItemPrice("Book", price));

        assertEquals(1, cart.getItemCount());
        assertEquals(20.0, cart.getTotal());
    }

    @Test
    void shouldValidatePriceBeforeLookingUpMissingItem() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Book", 20.0);

        assertThrows(IllegalArgumentException.class,
            () -> cart.updateItemPrice("Missing", Double.NaN));

        assertEquals(1, cart.getItemCount());
        assertEquals(20.0, cart.getTotal());
    }
}
