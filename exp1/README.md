# Software Engineering Lab — Experiment 1

## Project

**Software Engineering Lab Portfolio** — a static website used to practice Git version control, feature branching, pull requests, conflict resolution, branch protection, and automatic deployment.

- **Repository:** https://github.com/Danyal0096/SELabHomeworks
- **Live website:** https://danyal0096.github.io/SELabHomeworks/
- **Theoretical questions (all seven):** [Questions and answers](exp1-theoretical-answers.md)

## Implementation

The site uses HTML, CSS, and JavaScript. It includes responsive experiment-overview cards and a light/dark theme switch that remembers the selected theme using browser storage.

To view the website locally, open `exp1/index.html` in a browser. If browser-specific restrictions affect storage when using `file://`, serve the directory with a local HTTP server instead.

## Development workflow

Changes were implemented on feature branches and merged into `dev` using pull requests. Two independently developed pairs of changes were integrated by manually resolving Git merge conflicts. The production release was merged from `dev` into the protected `main` branch through [PR #8](https://github.com/Danyal0096/SELabHomeworks/pull/8).

The [`main` protection ruleset](https://github.com/Danyal0096/SELabHomeworks/rules/23750485) requires pull requests and blocks force pushes. No reviewer approval is required for this single-developer project.

## Merge Conflict 1 — Hero Introduction

**Branches:** `feature/hero-navigation` and `dev`  
**Conflicting file:** `exp1/index.html`

### Cause

Two branches independently modified the hero paragraph:

- `feature/hero-overview` replaced the original introduction with a description of the coursework; that branch was merged into `dev`.
- `feature/hero-navigation` replaced the same introduction with different wording and added a link to the experiments section.

Merging the updated `dev` into `feature/hero-navigation` produced a content conflict.

### Resolution and verification

I manually combined the description from `dev` with the experiments navigation link, removed the conflict markers, and committed the resolved file. `git diff --check` and `git diff --cached --check` reported no errors; `git status` confirmed a clean working tree.

- [Conflict-resolution commit `b30e7d7`](https://github.com/Danyal0096/SELabHomeworks/commit/b30e7d76dff46ea448c1e1e5bbd3fd16ca6af700)
- [Integration pull request #4](https://github.com/Danyal0096/SELabHomeworks/pull/4)

## Merge Conflict 2 — Footer Content

**Branches:** `feature/footer-source` and `dev`  
**Conflicting file:** `exp1/index.html`

### Cause

Two branches independently replaced the original footer:

- `feature/footer-details` added information about the technologies used to build the website; that branch was merged into `dev`.
- `feature/footer-source` added a link to the GitHub repository.

Merging the updated `dev` into `feature/footer-source` produced a content conflict.

### Resolution and verification

I manually preserved both the technology description and the repository link, removed all conflict markers, and committed the resolved file. `git diff --check` and `git diff --cached --check` reported no errors; `git status` confirmed a clean working tree.

- [Conflict-resolution commit `912fdc5`](https://github.com/Danyal0096/SELabHomeworks/commit/912fdc58a3f6478ffee526cdc521648aed8cafb7)
- [Integration pull request #6](https://github.com/Danyal0096/SELabHomeworks/pull/6)

## Continuous deployment

The [deployment workflow](../.github/workflows/deploy.yml) runs on pushes to `main` (and supports manual triggering). It checks out the repository, verifies `exp1/index.html`, `exp1/style.css`, and `exp1/script.js`, and uploads `exp1/` for GitHub Pages deployment.

1. Implement and review changes through feature-branch pull requests into `dev`.
2. Integrate the release from `dev` into the protected `main` branch by pull request.
3. GitHub Actions publishes the site to GitHub Pages.

**Initial deployment evidence:** [Successful workflow run](https://github.com/Danyal0096/SELabHomeworks/actions/runs/35565273805). The deployed website was also opened in a browser to verify that the page and styles load.

## Written answers

The answers to the handout's seven theoretical questions are in [exp1-theoretical-answers.md](exp1-theoretical-answers.md).
