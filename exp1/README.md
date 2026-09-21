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
The initial portfolio website is implemented and deployed. Experiment 1 documentation and final verification are in progress.

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

## Merge Conflict 2 — Footer Content

**Branches:** `feature/footer-source` and `dev`

**Conflicting file:** `exp1/index.html`

### Cause

Two branches independently modified the website's original footer.

- `feature/footer-details` added information about the technologies
  used to build the website.
- `feature/footer-source` added a link to the GitHub repository.

After merging `feature/footer-details` into `dev`, merging
`dev` into `feature/footer-source` produced a content conflict.

### Resolution

I manually combined the two changes, preserving both the technology
description and the repository link.

All conflict markers were removed, and the resolved file was staged
and committed.

### Verification

- `git diff --check` reported no errors.
- `git diff --cached --check` reported no errors.
- `git status` confirmed a clean working tree.

## Live Deployment

**Website:** https://danyal0096.github.io/SELabHomeworks/

**Repository:** https://github.com/Danyal0096/SELabHomeworks

**Production pull request:**
https://github.com/Danyal0096/SELabHomeworks/pull/8

### Deployment Process

The website is automatically deployed using GitHub Actions.

1. Development takes place on feature branches.
2. Completed features are merged into `dev` through pull requests.
3. A release pull request merges `dev` into the protected `main` branch.
4. A push to `main` triggers the deployment workflow.
5. GitHub Actions verifies the required website files.
6. The workflow uploads the contents of `exp1/` and deploys them to GitHub Pages.

### Deployment Verification

The initial deployment completed successfully.

Workflow run:
https://github.com/Danyal0096/SELabHomeworks/actions/runs/35565273805

The deployed website was opened in a browser, confirming
that the HTML and CSS were published successfully.