# TA clarification request — preserve original test integrity

## Blocking discrepancy: exact 100 discount

- Handout (p. 2): discount is 10% only when subtotal **strictly exceeds 100**. Exactly 100 **must not** be discounted.
- Provided original `ShoppingCartTest.testDiscountAtBoundary_WRONG`: cart contains items priced 40 and 60 and **expects 90.0**, i.e. discounts exactly 100.
- Provided `ShoppingCart.getTotalWithDiscount`: `if (total >= 100)` agrees with original test but violates handout.
- Handout (p. 5): **changing original tests is forbidden**, other than explicitly uncommenting the three update-price tests later in the TDD workflow.

**Question for TA:** how should we handle the incorrect, protected original boundary test when correcting the business rule? May the TA provide a corrected upstream test, approve a specifically documented exception, or authorize retaining the failing original test in final results? We will not secretly edit, delete, disable, or reinterpret the original test.

## Secondary contract tension: missing-item update

- Handout (p. 3): missing-item update must have an explicit, testable **failure** with no side effects.
- Third commented original test named `...ItemNotFound_ShouldDoNothing` calls `updateItemPrice("Eraser",5)` with no expected exception and asserts the original total is unchanged.
- A `boolean updateItemPrice(String,double)` returning `false` on absence could potentially satisfy both, because Java permits callers to ignore return values; however, confirm if return type is constrained by TA.

## Required public Hamgit

The handout requests a public Hamgit repository, while our established course work lives at `https://github.com/Danyal0096/SELabHomeworks`. Confirm whether an exp3-only public Hamgit mirror or full monorepo mirror is acceptable. GitHub by itself does not meet the literal requirement.

**Status:** questions drafted, **not sent to TA**. No TA response or exception is claimed.
