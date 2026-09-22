import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartNameValidationTest {

    @Test
    void shouldRejectNullNameWithoutChangingCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Book", 20.0);

        assertThrows(
            IllegalArgumentException.class,
            () -> cart.addItem(null, 30.0)
        );

        assertEquals(1, cart.getItemCount());
        assertEquals(20.0, cart.getTotal());
    }
    
    @Test
    void shouldRejectEmptyNameWithoutChangingCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Book", 20.0);

        assertThrows(
            IllegalArgumentException.class,
            () -> cart.addItem("", 30.0)
        );

        assertEquals(1, cart.getItemCount());
        assertEquals(20.0, cart.getTotal());
    }

    @Test
    void shouldRejectWhitespaceOnlyNameWithoutChangingCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Book", 20.0);

        assertThrows(
            IllegalArgumentException.class,
            () -> cart.addItem("   ", 30.0)
        );

        assertEquals(1, cart.getItemCount());
        assertEquals(20.0, cart.getTotal());
    }
}