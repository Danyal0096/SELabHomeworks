
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartPriceValidationTest {

    @Test
    void shouldRejectNaNWithoutChangingCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Book", 20.0);

        assertThrows(
            IllegalArgumentException.class,
            () -> cart.addItem("Invalid", Double.NaN)
        );

        assertEquals(1, cart.getItemCount());
        assertEquals(20.0, cart.getTotal());
    }
    
    @Test
    void shouldRejectPositiveInfinity() {
        assertInvalidPriceRejected(Double.POSITIVE_INFINITY);
    }

    @Test
    void shouldRejectNegativeInfinity() {
        assertInvalidPriceRejected(Double.NEGATIVE_INFINITY);
    }

    @Test
    void shouldRejectNegativePrice() {
        assertInvalidPriceRejected(-10.0);
    }

    @Test
    void shouldNotOverwriteExistingItemWithInvalidPrice() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Book", 20.0);

        assertThrows(
            IllegalArgumentException.class,
            () -> cart.addItem("Book", -5.0)
        );

        assertEquals(1, cart.getItemCount());
        assertEquals(20.0, cart.getTotal());
    }

    private void assertInvalidPriceRejected(double price) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Book", 20.0);

        assertThrows(
            IllegalArgumentException.class,
            () -> cart.addItem("Invalid", price)
        );

        assertEquals(1, cart.getItemCount());
        assertEquals(20.0, cart.getTotal());
    }
}