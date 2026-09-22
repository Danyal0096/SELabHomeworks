
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartUpdatePriceTest {

    @Test
    void shouldUpdateOnlyTheSpecifiedItem() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Book", 10);
        cart.addItem("Pen", 20);

        cart.updateItemPrice("Book", 40);

        assertEquals(60.0, cart.getTotal());
        assertEquals(2, cart.getItemCount());
    }

    @Test
    void shouldSupportRepeatedPriceUpdates() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Book", 10);

        cart.updateItemPrice("Book", 20);
        cart.updateItemPrice("Book", 35);

        assertEquals(35.0, cart.getTotal());
        assertEquals(1, cart.getItemCount());
    }

    @Test
    void shouldAllowUpdatingPriceToZero() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Book", 10);

        cart.updateItemPrice("Book", 0);

        assertEquals(0.0, cart.getTotal());
        assertEquals(1, cart.getItemCount());
    }

    @Test
    void shouldApplyDiscountAfterPriceIncrease() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Book", 90);

        cart.updateItemPrice("Book", 110);

        assertEquals(110.0, cart.getTotal());
        assertEquals(99.0, cart.getTotalWithDiscount());
    }

    @Test
    void shouldRemoveDiscountAfterPriceDecrease() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Book", 110);

        cart.updateItemPrice("Book", 90);

        assertEquals(90.0, cart.getTotal());
        assertEquals(90.0, cart.getTotalWithDiscount());
    }

    @Test
    void shouldRejectNegativeUpdateWithoutChangingCart() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Book", 20);

        assertThrows(
            IllegalArgumentException.class,
            () -> cart.updateItemPrice("Book", -5)
        );

        assertEquals(20.0, cart.getTotal());
        assertEquals(1, cart.getItemCount());
    }
    
    @Test
    void shouldSupportDecimalPriceUpdates() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Book", 10.0);

        cart.updateItemPrice("Book", 12.75);

        assertEquals(12.75, cart.getTotal());
        assertEquals(1, cart.getItemCount());
    }
}