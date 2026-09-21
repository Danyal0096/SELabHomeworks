from typing import Mapping

from store.contracts import PaymentPort
from store.models import Order


class CreditCardPayment:
    def process(self, order: Order, amount: float) -> str:
        card = order.customer.credit_card
        print(f"[payment] Charging card {card} {amount:.2f}")
        return f"paid_by_credit_card:{amount:.2f}"


class PayPalPayment:
    def process(self, order: Order, amount: float) -> str:
        email = order.customer.email
        print(f"[payment] Charging PayPal {email} {amount:.2f}")
        return f"paid_by_paypal:{amount:.2f}"


class BitcoinPayment:
    def process(self, order: Order, amount: float) -> str:
        address = order.customer.bitcoin_address
        print(f"[payment] Charging BTC {address} {amount:.2f}")
        return f"paid_by_bitcoin:{amount:.2f}"


class PaymentProcessor:
    def __init__(self, handlers: Mapping[str, PaymentPort]):
        self.handlers = dict(handlers)

    def process(self, order: Order, amount: float) -> str:
        method = order.payment_method
        try:
            handler = self.handlers[method]
        except KeyError:
            raise ValueError(f"Unknown payment method: {method!r}") from None
        return handler.process(order, amount)
