
# Experiment 2 — Object-Oriented Design Principles

**Student:** Danial Farahani
**Student ID:** 97105725

## Objective

Study the SOLID principles through analysis, feature
implementation, refactoring, and comparative evaluation.

## Original Project

Source:
https://github.com/soleyman79/Software-Engineering-Lab-Experiment2

Baseline commit:
6908c62a6b93d40cccf9b50ca1420d9a820f1853

## 1. Initial Implementation

Cash payment was added to `01-Without-OOD-Principles/` before any design refactoring.
The change table records every file modified for this milestone.

| File | Type and location | Change | Why necessary |
| --- | --- | --- | --- |
| `01-Without-OOD-Principles/store/payment.py` | Feature addition: `PaymentProcessor.process` | Added a `cash` branch that prints `[payment] Accepting cash {amount:.2f}` and returns `paid_by_cash:{amount:.2f}`. | The existing string-based dispatcher otherwise rejected `cash` as unknown. |
| `01-Without-OOD-Principles/store/main.py` | Demo addition: `main` | Added a separate cash order (ID 104, one $12.00 notebook) after the original checkouts. | Makes cash payment observable without changing the existing demo orders. |
| `README.md` | Documentation: Sections 1 and 2 | Recorded the implementation, verification, and SOLID analysis. | Provides the experiment's required change record and design assessment. |

Running `python -B -m store.main` from `01-Without-OOD-Principles/` showed
`[payment] Accepting cash 17.00` and `paid_by_cash:17.00` in the notification
and receipt. The notebook subtotal was $12.00, the existing shipping rule added
$5.00, and the total was $17.00. A direct checkout check confirmed the cash
order became `paid` and was saved by the in-memory database. The original laptop
and bundle outputs remained $819.99 and $5.00 respectively; the bundle's
existing pricing behavior was not changed. Direct checks passed for credit
card, PayPal, Bitcoin, and cash receipt strings. `git diff --check` passed.
No automated test suite is present in this copy, and none was added.

## 2. SOLID Analysis

Assessment of `01-Without-OOD-Principles/` after the cash extension:

| Principle | Assessment | Concrete evidence | Suitable refactoring and why |
| --- | --- | --- | --- |
| Single Responsibility (SRP) | Violated | `OrderService.process_order` in `store/order_service.py` validates, calculates shipping and total, requests payment, saves, notifies, and prints a receipt. These duties change for different reasons. | Keep `OrderService` as the checkout coordinator, but delegate validation, total/shipping calculation, and receipt rendering to focused components. Each rule or output format can then change without editing the checkout sequence. |
| Open/Closed (OCP) | Violated | `PaymentProcessor.process` in `store/payment.py` selects methods with an `if`/`elif` chain; adding cash required editing that chain. | Represent payment methods as separate handlers behind a common payment contract and select one through a registry. A new method can then be added as a handler without changing existing payment logic. |
| Liskov Substitution (LSP) | Violated; the notifier case is definite, while bundle semantics depend on the intended contract | `SmsOnlyNotifier` in `store/notification.py` inherits `send_email` and `send_push` but raises `NotImplementedError` for both, so it cannot replace `NotificationService` for those calls. Also, `BundleOrder` in `store/models.py` stores child orders separately while inheriting item-based `subtotal`; `OrderService.process_order` needs a bundle-specific validation exception and the demo bundle totals only $5.00 shipping. | Avoid making an SMS-only sender a subtype of a full notifier. Give bundles an order contract with meaningful aggregate pricing if bundles are intended to represent their children. This makes supported operations and order totals consistent when a subtype is used. |
| Interface Segregation (ISP) | Violated in the notifier inheritance contract, although the SMS-only subtype is unused in checkout | `NotificationService` exposes email, SMS, and push methods; `SmsOnlyNotifier` cannot support two of them and overrides them only to raise errors (`store/notification.py`). | Use separate email, SMS, and push sender contracts. An SMS-only sender then implements only SMS, and checkout requests only the channels it uses. |
| Dependency Inversion (DIP) | Violated | `OrderService.__init__` in `store/order_service.py` directly constructs `DiscountCalculator`, `PaymentProcessor`, `NotificationService`, and `MySqlDatabase`; the checkout policy depends on these concrete collaborators. | Accept collaborators through constructor parameters typed by small contracts, with concrete instances assembled at the entry point. This lets the checkout policy depend on required behavior rather than specific implementations. |

The LSP and ISP notifier findings concern an unused subtype, so they describe a
real substitution problem in the design rather than a failure observed in the
current demo. The bundle pricing result is observed, but whether a bundle must
sum its child orders is not stated explicitly by the code. These findings
describe the BEFORE version; the approved refactoring of the AFTER version is
recorded in Sections 4–6.

## 3. Coding Agent and Custom Skill

The repository-local [`solid-review` Skill](.agents/skills/solid-review/SKILL.md)
guides Codex through an evidence-based review of all five SOLID principles. It
requires file, class, and method references; separates definite violations
from uncertain concerns; proposes minimal remedies; and stops for explicit
approval before any application refactoring. After approval, it calls for diff
review and focused verification. Its YAML `name` and `description` identify
when to use it. A single instruction-only `SKILL.md` is sufficient because this
review needs no scripts, reference files, or dependencies.

`skill-creator/scripts/quick_validate.py` reported `Skill is valid!`. An
earlier read-only Codex CLI attempt could not connect to the model service, so
that attempt did not verify activation. In a later fresh VS Code Codex session,
the user explicitly invoked `$solid-review` for
`01-Without-OOD-Principles/`. Codex discovered the repository-local Skill,
loaded `SKILL.md` from disk, and produced a read-only review of all five SOLID
principles with source-code references. This verified native Skill activation
in the VS Code session. It reproduced the Section 2 findings, including the
uncertain bundle semantics, without demonstrable improvement in analytical
quality. The demonstration changed no files.

## 4. Refactoring Plan

The [approved plan](02-Applied-OOD-Principles/REFACTORING_PLAN.md) targets only
`02-Applied-OOD-Principles/`. It calls for focused validation, pricing, and
receipt components; registered payment handlers; separate notification
capabilities; and constructor-injected dependencies. We reviewed and explicitly
approved that plan before implementation. The decision was to preserve the
bundle's $5.00 checkout and defer its business semantics. Cash payment was
reserved for the separate extension step described in Section 6.

## 5. Refactoring Implementation

The approved changes were implemented only in `02-Applied-OOD-Principles/`:

| Files and affected code | Change | Principles addressed |
| --- | --- | --- |
| `store/contracts.py`; `OrderService.__init__` in `store/order_service.py`; `main` in `store/main.py` | Added seven small structural contracts, injected the collaborators into checkout, and assembled concrete instances at the entry point. | DIP, ISP |
| `store/validation.py` (`OrderValidator.validate`), `store/pricing.py` (`OrderPricing.price`, `PriceBreakdown`), `store/receipt.py` (`ReceiptPrinter.print_receipt`) | Moved validation, total calculation, and receipt printing out of `OrderService.process_order` while retaining their rules and output. | SRP |
| `store/payment.py` (`PaymentProcessor.process` and three handlers) | Replaced the payment branch chain with a handler mapping for credit card, PayPal, and Bitcoin. This allowed the later cash handler to be registered without editing existing processing logic. | OCP |
| `store/notification.py` (`SmsOnlyNotifier`) | Removed inheritance from the full notifier and exposed only its supported SMS operation. Checkout requests email and SMS through separate contracts. | LSP, ISP |

`store/models.py` and `store/storage.py` were not changed. The bundle validation
exception was moved intact; no bundle pricing rule was changed. At this
refactoring milestone, no cash handler or cash demo order was added to the
AFTER version.

## 6. Before-and-After Comparison

At the refactoring commit `bc6df53`, running `python -B -m store.main` from the
AFTER directory produced stdout identical to the pre-refactoring baseline,
including the laptop's $819.99 total and the bundle's $0.00 subtotal and $5.00
total. Focused temporary checks passed for the three original payment receipt
strings, unknown payment methods (including cash at that stage), invalid
orders, `notify=False`, paid status and persistence, pricing cases for VIP,
bulk, coupon, and no discount, and registration of an additional payment
handler. Failed checkouts remained pending and unsaved.
SHA-256 hashes of every BEFORE source file were unchanged. No comprehensive
test suite or external dependency was added.

### Cash feature extension in the AFTER version

The cash feature was added after the refactoring commit
`bc6df530df220f3d2c04f9dc0c416d15a6a68c4e`. Its application changes were:

| File | Class or method | Cash change | Why necessary |
| --- | --- | --- | --- |
| `02-Applied-OOD-Principles/store/cash_payment.py` (added) | `CashPayment.process` (added) | Prints `[payment] Accepting cash {amount:.2f}` and returns `paid_by_cash:{amount:.2f}`. | Supplies cash behavior through the existing payment contract without editing another handler or `PaymentProcessor.process`. |
| `02-Applied-OOD-Principles/store/main.py` (modified) | `main` | Imports and registers `CashPayment` under `"cash"`, then checks out order 104 with one $12.00 notebook using the same customer and output as BEFORE. | Makes cash selectable through the existing mapping and demonstrates the feature. |

No conditional branch was added to the AFTER payment processor. No external
dependency was added. `store/payment.py`, its three existing handlers, and
`store/order_service.py` were unchanged by this cash feature.

### Feature-only comparison

The local original snapshot `8871039` is the BEFORE pre-cash reference, and
commit `a054e81` contains its cash extension. The upstream source hash listed
above is not present in the local Git object database, so the comparison uses
that committed local snapshot. The AFTER pre-cash reference is the completed
SOLID refactoring commit `bc6df53`; the earlier refactoring changes are
excluded from the AFTER cash-feature column.

| Change dimension | BEFORE: `8871039` to `a054e81` | AFTER: `bc6df53` to cash extension |
| --- | --- | --- |
| Application files | Modified `store/payment.py` and `store/main.py`; added none. | Added `store/cash_payment.py`; modified `store/main.py`. |
| Classes and methods | Modified `PaymentProcessor.process` to recognize cash; modified `main` to add the notebook order. | Added `CashPayment.process`; modified `main` for registration and the same notebook order. `PaymentProcessor.process` and existing handlers were untouched. |
| Conditional branches | Added one `elif method == "cash"` branch to the existing dispatcher. | Added no branch; added a `"cash": CashPayment()` mapping entry. |
| Dependencies and registration | The processor's existing string-based dispatch gained direct knowledge of the cash method; no registration point existed. | `main` imports the cash handler and registers it through the existing `PaymentPort`-compatible mapping; no external package was needed. |
| Reason for change | The dispatcher had to be edited to accept cash, and the demo needed a cash order. | A new handler supplied the behavior, registration made it selectable, and the demo needed the same cash order. |

The observed changes show that the AFTER design accepts a new payment method
without editing existing payment-processing logic, while BEFORE required a new
branch in the shared dispatcher. This confines the AFTER payment behavior to a
new handler and one registration entry. Both versions still needed a demo
change. No implementation-time, defect-rate, or complexity improvement was
measured.

### Final verification

Running `python -B -m store.main` in each application produced identical stdout.
Both print `[payment] Accepting cash 17.00`, include
`paid_by_cash:17.00` in notifications and receipts, and show a $12.00 notebook
subtotal, $5.00 shipping, and $17.00 total. The laptop remains $819.99 and the
bundle remains $5.00 in both versions. Focused AFTER checks passed for credit
card, PayPal, Bitcoin, and cash receipt strings, and confirmed that the cash
order became `paid` and was saved. SHA-256 hashes of the BEFORE source files
remained unchanged during this extension.

## 7. Coding Agent Evaluation

1. **Which parts did Codex analyze correctly?** Codex identified the documented
   SRP, OCP, LSP, ISP, and DIP concerns in the BEFORE application with source
   references. It treated the notifier's LSP/ISP issue as definite but unused
   in checkout, and the bundle's pricing contract as uncertain. The later
   feature diff confirmed its OCP analysis: BEFORE cash required a branch in
   `PaymentProcessor.process`, while AFTER cash used a new handler and existing
   registration point.

2. **What required correction or human intervention?** The original BEFORE
   cash proposal required human review and approval before implementation; the
   SOLID refactoring plan was likewise reviewed and approved without
   architectural revision. Those were approval decisions, not corrections to
   an incorrect design. Human instructions preserved the bundle's $5.00
   checkout and separated the AFTER cash feature from refactoring. The initial
   CLI Skill demonstration failed to connect to the model service, so its
   activation claim had to await the successful VS Code run. The README's
   earlier unverified-activation and pending-work statements were then updated.
   Git's ownership restriction was an environment issue resolved with
   command-scoped `safe.directory` at the verified repository root, without
   changing global configuration. No incorrect SOLID conclusion or required
   architectural revision is documented.

3. **Which prompts mattered most?** The key requests were to propose and,
   after review, implement cash payment in BEFORE; explicitly use the local
   `$solid-review` Skill for a read-only analysis; prepare a constrained plan
   for AFTER and implement it after approval; then add cash to AFTER and
   compare only the two feature diffs. The exact wording of the initial cash
   request is unavailable here, so its purpose is summarized rather than
   quoted.

4. **What effect did the Skill have?** The Skill passed local format validation,
   but the first CLI demonstration could not verify activation because it
   could not reach the model service. The later VS Code session loaded the
   repository-local Skill and repeated the earlier five-principle findings.
   Its instructions made evidence, uncertainty, and approval boundaries
   explicit; the observed review showed no demonstrable improvement in
   analytical quality over the prior analysis.

5. **What would we change next time?** Keep the assignment handout, exact
   prompts, and review decisions with the project; capture baseline output and
   reachable Git references before changes; and verify Skill activation in the
   intended environment early. Check repository ownership before Git work and
   use command-scoped configuration when needed. These steps would make the
   experiment easier to reproduce and audit; they do not imply an unmeasured
   improvement in Codex's analysis.
