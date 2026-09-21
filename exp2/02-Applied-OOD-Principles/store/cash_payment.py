from store.models import Order


class CashPayment:
    def process(self, order: Order, amount: float) -> str:
        print(f"[payment] Accepting cash {amount:.2f}")
        return f"paid_by_cash:{amount:.2f}"
