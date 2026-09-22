
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartAdvancedTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void shouldEnforceCapacityAtDifferentLimits(int capacity) {
        ShoppingCart cart = new ShoppingCart(capacity);

        for (int i = 0; i < capacity; i++) {
            cart.addItem("Item" + i, 10.0);
        }

        assertEquals(capacity, cart.getItemCount());
        assertEquals(capacity * 10.0, cart.getTotal());

        assertThrows(
            IllegalStateException.class,
            () -> cart.addItem("Extra", 50.0)
        );

        assertEquals(capacity, cart.getItemCount());
        assertEquals(capacity * 10.0, cart.getTotal());
    }

    @Test
    void shouldMaintainConsistentStateAcrossOperations() {
        ShoppingCart cart = new ShoppingCart(2);

        cart.addItem("Book", 60.0);
        cart.addItem("Pen", 50.0);

        assertEquals(110.0, cart.getTotal());
        assertEquals(99.0, cart.getTotalWithDiscount());

        assertTrue(cart.updateItemPrice("Book", 40.0));

        assertEquals(90.0, cart.getTotal());
        assertEquals(90.0, cart.getTotalWithDiscount());

        assertTrue(cart.removeItem("Pen"));
        assertEquals(1, cart.getItemCount());

        cart.addItem("Notebook", 70.0);

        assertEquals(2, cart.getItemCount());
        assertEquals(110.0, cart.getTotal());
        assertEquals(99.0, cart.getTotalWithDiscount());

        assertThrows(
            IllegalStateException.class,
            () -> cart.addItem("Extra", 10.0)
        );

        assertEquals(110.0, cart.getTotal());
        assertEquals(2, cart.getItemCount());
    }

    @Test
    void shouldAllowAddingFreeItem() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("FreeSample", 0.0);

        assertEquals(1, cart.getItemCount());
        assertEquals(0.0, cart.getTotal());
    }

    @Test
    void shouldReturnFalseWhenRemovingMissingItem() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Book", 20.0);

        assertFalse(cart.removeItem("Pen"));

        assertEquals(1, cart.getItemCount());
        assertEquals(20.0, cart.getTotal());
    }
}