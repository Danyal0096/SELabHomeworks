from store.contracts import (
    EmailSender,
    OrderPricingPort,
    OrderStorePort,
    OrderValidatorPort,
    PaymentPort,
    ReceiptWriter,
    SmsSender,
)
from store.models import Order


class OrderService:
    def __init__(
        self,
        validator: OrderValidatorPort,
        pricing: OrderPricingPort,
        payment: PaymentPort,
        database: OrderStorePort,
        email_sender: EmailSender,
        sms_sender: SmsSender,
        receipt_writer: ReceiptWriter,
    ):
        self.validator = validator
        self.pricing = pricing
        self.payment = payment
        self.database = database
        self.email_sender = email_sender
        self.sms_sender = sms_sender
        self.receipt_writer = receipt_writer

    def process_order(self, order: Order, notify: bool = True) -> Order:
        self.validator.validate(order)
        price = self.pricing.price(order)
        receipt = self.payment.process(order, price.total)

        order.status = "paid"
        self.database.save_order(order)

        if notify:
            message = f"Order {order.id} total ${price.total:.2f} ({receipt})"
            self.email_sender.send_email(order.customer, message)
            self.sms_sender.send_sms(order.customer, message)

        self.receipt_writer.print_receipt(order, price, receipt)
        return order
