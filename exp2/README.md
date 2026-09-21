
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
sum its child orders is not stated explicitly by the code; the proposed bundle
refactoring therefore depends on that domain decision. These are analysis
proposals only; no refactoring has been implemented.

## 3. Coding Agent and Custom Skill

The repository-local [`solid-review` Skill](.agents/skills/solid-review/SKILL.md)
guides Codex through an evidence-based review of all five SOLID principles. It
requires file, class, and method references; separates definite violations
from uncertain concerns; proposes minimal remedies; and stops for explicit
approval before any application refactoring. After approval, it calls for diff
review and focused verification. Its YAML `name` and `description` identify
when to use it. A single instruction-only `SKILL.md` is sufficient because this
review needs no scripts, reference files, or dependencies. This follows the
[official Skill format](https://learn.chatgpt.com/docs/build-skills).

`skill-creator/scripts/quick_validate.py` reported `Skill is valid!`. A fresh,
read-only Codex CLI session was explicitly prompted with `Use $solid-review`
against `01-Without-OOD-Principles/store`. The session started but could not
connect to the model service, so it produced no analysis and native Skill
activation was **not verified**. A network-enabled retry was rejected by
automatic approval review because it could send repository source to an
external service. No application file was changed. A later Codex session can
repeat the explicit invocation when that access is approved and available.

For a local, read-only rehearsal, the Skill instructions were read and applied
to the original project's source. The resulting classifications match Section
2: SRP (`OrderService.process_order`), OCP (`PaymentProcessor.process`), LSP
and ISP (`SmsOnlyNotifier`), and DIP (`OrderService.__init__`) have concrete
violations; the bundle's intended aggregate contract remains uncertain. The
rehearsal added no new finding or demonstrated improvement over the earlier
analysis. It is not evidence that Codex loaded the Skill in the CLI session.

## 4. Refactoring Plan

Pending.

## 5. Refactoring Implementation

Pending.

## 6. Before-and-After Comparison

Pending.

## 7. Coding Agent Evaluation

Pending.