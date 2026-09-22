
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartCapacityTest {

    @Test
    void shouldRejectItemWhenCartIsFull() {
        ShoppingCart cart = new ShoppingCart(2);

        cart.addItem("Book", 10.0);
        cart.addItem("Pen", 20.0);

        assertThrows(
            IllegalStateException.class,
            () -> cart.addItem("Notebook", 30.0)
        );

        assertEquals(2, cart.getItemCount());
        assertEquals(30.0, cart.getTotal());
    }
        
    @Test
    void shouldAllowReplacingExistingItemWhenFull() {
        ShoppingCart cart = new ShoppingCart(1);

        cart.addItem("Book", 10.0);
        cart.addItem("Book", 25.0);

        assertEquals(1, cart.getItemCount());
        assertEquals(25.0, cart.getTotal());
    }

    @Test
    void shouldFreeCapacityAfterRemovingItem() {
        ShoppingCart cart = new ShoppingCart(1);

        cart.addItem("Book", 10.0);
        assertTrue(cart.removeItem("Book"));

        cart.addItem("Pen", 5.0);

        assertEquals(1, cart.getItemCount());
        assertEquals(5.0, cart.getTotal());
    }

    @Test
    void shouldKeepDefaultCartUnrestricted() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("A", 10.0);
        cart.addItem("B", 20.0);
        cart.addItem("C", 30.0);

        assertEquals(3, cart.getItemCount());
        assertEquals(60.0, cart.getTotal());
    }

    @Test
    void shouldRejectInvalidCapacity() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new ShoppingCart(0)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new ShoppingCart(-1)
        );
    }
}   