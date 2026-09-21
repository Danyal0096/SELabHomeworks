---
name: solid-review
description: Review an existing object-oriented codebase for evidence-based SOLID issues and plan minimal refactoring. Use for SOLID audits or requested SOLID refactoring, not general bug fixing.
---

# SOLID review

1. Establish the requested code scope and current behavior. Inspect the relevant classes, callers, entry points, and available checks before judging the design.
2. Assess all five principles separately: SRP (independent reasons to change), OCP (how an extension is added), LSP (behavioral substitutability), ISP (capabilities clients or subtypes cannot use), and DIP (direction of high-level dependencies). A large class, conditional, inheritance relationship, or concrete type alone is not proof of a violation.
3. Present a concise finding for each principle: respected, violated, or uncertain. Cite the file, class, and method or property that supports each conclusion. Separate observed behavior from inferred intent, flag unused code, and do not manufacture findings to fill five violation rows.
4. For each violation or arguable concern, propose the smallest suitable refactoring and explain how it addresses the cited evidence while preserving behavior. State any domain decision needed before choosing a solution.
5. Before changing application code, show the proposed files and methods, expected behavior, and focused verification for user review. Require explicit approval of the proposed refactoring; an analysis request does not authorize implementation. Stop here for review-only requests.
6. After approval, implement only the agreed changes. Inspect the resulting diff, run appropriate lightweight checks of affected and existing behavior, and report the changes, results, and remaining uncertainty.
