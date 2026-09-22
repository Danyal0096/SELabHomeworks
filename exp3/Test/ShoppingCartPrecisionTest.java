
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartPrecisionTest {

    @Test
    void decimalPricesShouldProduceExactCentTotal() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("A", 0.10);
        cart.addItem("B", 0.20);

        assertEquals(
            0.30,
            cart.getTotal(),
            0.0,
            "Cart totals must be accurate to the cent"
        );
    }
}