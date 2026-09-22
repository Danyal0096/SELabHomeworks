# Implemented feature contracts

These are the observable contracts in `src/ShoppingCart.java` and the current tests. The three defect contracts were separately approved by the owner and are detailed in `bug-analysis.md`. A separate record of owner approval for the capacity contract before implementation is not retained here.

## Feature 1 — `updateItemPrice(String,double)`

- On an existing name, return `true`, replace its price, keep the distinct-name count unchanged, and recalculate totals from cart state. On an absent name, return `false` without changing the cart. The return-value tests were added with implementation commit `635ee30`; the original supplied absent-item test checks only an unchanged total.
- Reject null or blank names and negative or non-finite new prices with `IllegalArgumentException` before changing state. Zero is accepted. Input validation occurs before absent-name lookup, so an invalid price for an absent name throws rather than returning `false`.
- Duplicate `addItem` names replace one map entry; an update therefore addresses the one stored value for that name. `getTotal()` accumulates decimal representations with `BigDecimal.valueOf` and exposes a `double` result; fractional-cent rounding is unspecified.
- The original discount behavior remains `total >= 100`. The handout excludes exactly 100, but the owner chose to preserve the original source/test rule. Updating a price changes whether that existing discount applies; it does not permanently alter the base total.

**Test chronology:** `a35395e` uncommented the three supplied tests and added six separate update tests. `614d792` added a decimal update case, producing a retained Maven compilation RED because the old stub accepted only `int`. All **10** cases preceded GREEN `635ee30`; that GREEN also added two return-value tests, which are not counted as preimplementation tests. See `tdd-log.md` and `Test/ShoppingCartUpdatePriceTest.java`.

## Feature 2 — configurable capacity

- `new ShoppingCart(int maxItems)` requires a positive capacity; zero and negative values throw `IllegalArgumentException`. The no-argument constructor uses `Integer.MAX_VALUE` to preserve the original practical default.
- Capacity counts distinct names (`items.size()`). A new name at capacity throws `IllegalStateException` before mutation. Replacing an existing name is allowed even when full. Removing a name frees one slot.
- Name and price validation precede the capacity check. Current tests check that a rejected new item leaves count and total unchanged. The capacity rule does not alter `updateItemPrice` or the original discount calculation.

`85f8d35` added five capacity tests and the constructor/API scaffold in the RED commit; its saved report shows the missing capacity rejection. `da8cce7` added enforcement in GREEN. Later advanced tests in `349b4f7` and `41e4e87` are postimplementation tests. No separate refactor step is evidenced.
