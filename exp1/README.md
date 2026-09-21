# Software Engineering Lab - Experiment 1

## Project
Software Engineering Lab Portfolio

## Description
A static website developed to demonstrate Git version control,
branching, merge conflict resolution, pull requests, and
continuous deployment using GitHub Actions and GitHub Pages.

## Technologies
- HTML
- CSS
- JavaScript
- Git
- GitHub Actions

## Development Status
Project initialized. Development in progress.

## Merge Conflict 1 — Hero Introduction

**Branches:** `feature/hero-navigation` and `dev`

**Conflicting file:** `exp1/index.html`

### Cause

Two branches independently modified the same paragraph in the
website's hero section.

- The `hero-overview` branch introduced a more informative
  description of the coursework.
- The `hero-navigation` branch introduced a different description
  and added a link to the experiments section.

After merging `hero-overview` into `dev`, merging `dev` into
`hero-navigation` produced a content conflict.

### Resolution

I manually resolved the conflicting paragraph by preserving
the improved description from `dev` and the navigation link
from `hero-navigation`.

The conflict markers were removed, and the resolution was
staged and committed.

### Verification

- `git diff --check` reported no errors.
- `git diff --cached --check` reported no errors.
- `git status` confirmed a clean working tree.

**Resolution commit:** `b30e7d7`