from store.models import Order
from store.pricing import PriceBreakdown


class ReceiptPrinter:
    def print_receipt(
        self, order: Order, price: PriceBreakdown, payment_receipt: str
    ) -> None:
        print(f"--- Receipt for order {order.id} ---")
        for item in order.items:
            print(f"  {item.name:20s} x{item.quantity}  ${item.line_total:.2f}")
        print(f"  Subtotal    ${price.subtotal:.2f}")
        print(f"  Discount   -${price.discount:.2f}")
        print(f"  Shipping    ${price.shipping:.2f}")
        print(f"  TOTAL       ${price.total:.2f}")
        print(f"  Payment     {payment_receipt}")
