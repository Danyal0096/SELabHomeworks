from dataclasses import dataclass

from store.models import Order


class DiscountCalculator:
    def calculate(self, order: Order) -> float:
        subtotal = order.subtotal

        if order.customer.is_vip:
            discount = subtotal * 0.20
        elif order.item_count >= 10:
            discount = subtotal * 0.10
        elif "WELCOME10" in order.coupons:
            discount = subtotal * 0.10
        else:
            discount = 0.0

        return round(discount, 2)


@dataclass(frozen=True)
class PriceBreakdown:
    subtotal: float
    discount: float
    shipping: float
    total: float


class OrderPricing:
    def __init__(self, discount_calculator: DiscountCalculator):
        self.discount_calculator = discount_calculator

    def price(self, order: Order) -> PriceBreakdown:
        subtotal = order.subtotal
        discount = self.discount_calculator.calculate(order)
        shipping = 5.0 if subtotal < 100 else 0.0
        total = round(subtotal - discount + shipping, 2)
        return PriceBreakdown(subtotal, discount, shipping, total)
