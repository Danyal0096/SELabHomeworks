from typing import Protocol

from store.models import Customer, Order
from store.pricing import PriceBreakdown


class OrderValidatorPort(Protocol):
    def validate(self, order: Order) -> None: ...


class OrderPricingPort(Protocol):
    def price(self, order: Order) -> PriceBreakdown: ...


class PaymentPort(Protocol):
    def process(self, order: Order, amount: float) -> str: ...


class OrderStorePort(Protocol):
    def save_order(self, order: Order) -> None: ...


class EmailSender(Protocol):
    def send_email(self, customer: Customer, message: str) -> None: ...


class SmsSender(Protocol):
    def send_sms(self, customer: Customer, message: str) -> None: ...


class ReceiptWriter(Protocol):
    def print_receipt(
        self, order: Order, price: PriceBreakdown, payment_receipt: str
    ) -> None: ...
