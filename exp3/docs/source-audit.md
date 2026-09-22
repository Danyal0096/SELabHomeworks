# Source audit — Experiment 3 initialization

**Scope:** original uploaded ZIP and GitHub `Danyal0096/SELabHomeworks` default branch `main`, inspected 2026-09-22. This is *source inspection*, not JUnit execution.

## Byte-for-byte GitHub comparison

The uploaded archive includes 3 production classes and 1 original test class. After moving them to Maven-style directories, their Git blob SHA-1s match the corresponding GitHub paths at `main` commit `81fa448bd36f4fc9f2fd4fe4eac71539ea257f37`:

| Uploaded archive path | Repository path | Git blob SHA-1 |
| --- | --- | --- |
| `src/Item.java` | `exp3/src/main/java/Item.java` | `d84253f97b4458e8fb012b3043ab0cfdd88e67f5` |
| `src/Main.java` | `exp3/src/main/java/Main.java` | `aedd9cd1370aa653c4c00f5c5119d881a0f223d0` |
| `src/ShoppingCart.java` | `exp3/src/main/java/ShoppingCart.java` | `b722acac63f7bbf608932454f6d89c0875f64938` |
| `Test/ShoppingCartTest.java` | `exp3/src/test/java/ShoppingCartTest.java` | `661e5e2cae9ef018fd61f944ea09245f7be38706` |

Base ZIP has no `pom.xml` or `build.gradle`. Its `.idea/misc.xml` states JDK 17 and its `.iml` references JUnit 5.8.1. Original upstream Git revision and license are not supplied in the ZIP; **do not infer them from the ZIP folder name**.

## Actual code inventory

- `Item`: immutable (`final`) name and `double` price fields, constructor and getters. No validation.
- `ShoppingCart`: `HashMap<String,Double> items` storing one value per name. `addItem` calls `put` (same name **replaces** the price; no quantity field). `removeItem` returns `true` and removes an existing name; returns `false` without removal if absent. `getItemCount` returns map size (distinct keys); `getTotal` adds `double` values in map iteration order. `getTotalWithDiscount` multiplies by `0.9` when `total >= 100`. `updateItemPrice(String,int)` is an **empty existing stub**, not an absent method.
- `Main`: default IntelliJ Hello World demo; does not exercise the cart.

## Original test inventory — active tests (4)

| Test method | Current assertion / scenario | What it does **not** establish |
| --- | --- | --- |
| `testAddItem` | Add Book at 50 → count 1, subtotal 50 | duplicate names, invalid/decimal prices |
| `testRemoveItem` | Add Pen at 5 → remove true, count 0, total 0 | missing/repeated removal |
| `testDiscountAtBoundary_WRONG` | 40 + 60 = 100 → expects discounted result 90 | **contradicts handout**, which requires no discount at exactly 100 |
| `testDiscountAboveThreshold` | 120 → discounted total 108 | subtotal immutability, decimal precision |

**Commented update tests (3; NOT active baseline):**
- `testUpdateItemPrice_ShouldChangePrice`: Book 50 → update to 80 → expects total 80.
- `testUpdateItemPrice_ShouldNotChangeCount`: Pen 10 → update to 20 → expects count 1. As written it can pass even when price does not change, so pair with meaningful independent assertions.
- `testUpdateItemPrice_ItemNotFound_ShouldDoNothing`: Notebook 30 → update missing Eraser to 5 → expects total remains 30. A throwing missing-item policy would conflict with this original test; a `boolean` success/failure return is a possible design choice if approved.

## Hypotheses, not yet three proven bugs

1. **Discount boundary discrepancy established by code vs. handout:** `>=` should be `>` per written requirement; original test explicitly expects incorrect output. Do not fix before handling the test contradiction through TA guidance / transparent documented interpretation.
2. **Distinct-name semantics**: repeated names overwrite previous prices and do not increase count. This is *observed behavior*, not automatically a defect; handout requests a defined policy.
3. **Floating point and invalid numeric inputs**: adding `double` values may entail rounding; `null` names, negative prices, NaN and infinity are not rejected by `addItem`. Whether any is a reportable bug requires a deliberate behavioral contract and failing test.
4. **Removing nonexistent names is already handled** by returning `false`. Do not claim it is one of the three required defects without independent proof of a real failure.

## Independent compilation observation

On a separate Linux sandbox with OpenJDK 21.0.11, `javac -d <output> src/*.java` succeeded for **production files only** and the IntelliJ `Main` printed its boilerplate welcome message. Maven, JUnit dependencies, JaCoCo and PIT were unavailable in that environment. This is neither a JUnit pass nor a baseline coverage/mutation measurement. See `evidence/baseline/production-compile-observation.txt`.
