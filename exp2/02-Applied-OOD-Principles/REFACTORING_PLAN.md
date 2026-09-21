# SOLID refactoring plan for the AFTER application

## Summary

Refactor only `02-Applied-OOD-Principles/`. Its pre-refactoring demo charges
$819.99 for the laptop order and $5.00 for the bundle; both outputs must remain
identical. Cash remains unsupported until the separate feature step. The
[README analysis](../README.md#2-solid-analysis) and the repository-local
[`solid-review` Skill](../.agents/skills/solid-review/SKILL.md) informed this
plan.

## Ordered changes

| Step | File and affected code | Change and reason |
| --- | --- | --- |
| 1 | New `store/contracts.py` | Define small `typing.Protocol` contracts for validation (`validate`), pricing (`price`), payment (`process`), storage (`save_order`), email (`send_email`), SMS (`send_sms`), and receipt output (`print_receipt`). `OrderService` will depend on these behaviors instead of constructing concrete services (DIP, ISP). |
| 2 | New `store/validation.py`, `OrderValidator.validate` | Move the two existing checkout checks from `OrderService.process_order` without changing their order, errors, or bundle exception (SRP). |
| 3 | `store/pricing.py`, new `OrderPricing.price` and `PriceBreakdown` | Keep `DiscountCalculator.calculate` unchanged. Move the existing subtotal, discount, shipping, and rounded-total calculation into `OrderPricing`; return those four values in `PriceBreakdown` (SRP). |
| 4 | New `store/receipt.py`, `ReceiptPrinter.print_receipt` | Move `_print_receipt`'s lines and formatting unchanged from `OrderService` (SRP). |
| 5 | `store/payment.py`, `PaymentProcessor` | Extract the existing credit-card, PayPal, and Bitcoin branches into separate handlers with `process(order, amount) -> str`. Give `PaymentProcessor` a method-name-to-handler mapping through its constructor. Its `process` delegates to the selected handler and retains the exact unknown-method `ValueError` (OCP). |
| 6 | `store/notification.py`, `SmsOnlyNotifier` | Keep `NotificationService`'s existing output. Make `SmsOnlyNotifier` an independent class with only `send_sms`, removing its unsupported inherited methods (LSP, ISP). |
| 7 | `store/order_service.py`, `OrderService.__init__` and `process_order` | Inject the seven contracted collaborators. Keep checkout sequencing: validate, price, charge, mark paid, save, optionally email then SMS, print receipt, return the order. Remove `_print_receipt` after moving it (SRP, DIP). |
| 8 | `store/main.py`, `main` | Assemble the concrete collaborators and three payment handlers. Pass one `NotificationService` instance for both email and SMS contracts. Keep the existing demo orders and calls unchanged (DIP). |

`Order`, `BundleOrder`, and storage behavior remain unchanged. The
bundle-specific validation exception moves into `OrderValidator`; the bundle's
intended relationship to child-order prices remains unspecified, so this plan
preserves its $0.00 subtotal and $5.00 checkout.

For the **later cash-payment step**, add a cash handler in a separate module and
register `"cash"` in `main`'s handler mapping. Neither
`PaymentProcessor.process` nor any existing payment handler will need
modification.

## Verification after approval and implementation

- Compare the AFTER demo's complete stdout before and after refactoring with
  `python -B -m store.main`, including payment, notification, and receipt lines.
- Use focused, temporary checks for credit-card, PayPal, and Bitcoin receipt
  strings; invalid and unknown-payment errors; `notify=False`; paid status and
  saved orders; and handler selection through the mapping. Confirm `"cash"`
  still raises the existing unknown-method error.
- Inspect the diff for changes confined to `02-Applied-OOD-Principles/` and
  confirm `01-Without-OOD-Principles/` is untouched. No external dependencies
  or comprehensive test suite are planned.

## Assumptions and deferred decision

The application's in-repository callers are the compatibility boundary;
`main` is updated for the injected constructor. **No architectural decision
blocks this refactor.** A future change to bundle pricing requires a business
decision about whether bundles charge for their child orders. Correcting the
README's outdated Skill-activation statement is a separate documentation step,
outside this refactoring.
