# Experiment 1 — Theoretical Questions and Answers

**Course:** Software Engineering Lab  
**Student:** Danial Farahani  
**Student number:** 97105725  
**Project repository:** [SELabHomeworks](https://github.com/Danyal0096/SELabHomeworks)

The questions below follow the seven theoretical questions in the Experiment 1 handout. Their wording is translated into English; the answers are written for this project's Git workflow.

## 1. What is the `.git` directory? What information does it contain, and which command creates it?

`.git` is the hidden directory containing the metadata that makes an ordinary directory a Git repository. It stores Git objects (including commit, tree, and blob objects), references to branches and tags, the `HEAD` reference, the staging-area index, repository configuration, and other internal information such as logs and hooks. The working files of the project are normally located *outside* `.git`.

Running `git init` inside a project creates a new `.git` directory and initializes a local repository. Running `git clone <repository-url>` also creates a local repository, including its `.git` directory, while copying data from an existing repository. The `.git` directory should not be edited manually during normal development.

## 2. What does *atomic* mean in an atomic commit and an atomic pull request?

An **atomic commit** represents one coherent, self-contained change, such as adding the theme switch or documenting a conflict. Its changes should belong together and ideally leave the project in a usable state. Git records the commit as one immutable object rather than as a partially recorded collection of individual changes.

An **atomic pull request** groups changes around a single clear purpose that can be reviewed and integrated together, rather than mixing unrelated features and fixes. For example, the theme-toggle PR groups the theme functionality and persistence. A pull request itself is a review proposal, **not** a Git commit or a guarantee of database-style transactional behavior; the term *atomic* here describes the scope and coherence of the proposed change.

## 3. Explain the differences among `fetch`, `pull`, `merge`, `rebase`, and `cherry-pick`.

| Command | Purpose | Effect on the current branch |
| --- | --- | --- |
| `git fetch origin` | Downloads remote commits and updates remote-tracking references, such as `origin/dev`. | Does not integrate them into the current branch. |
| `git pull` | Fetches, then integrates changes from the configured upstream branch (by merge or rebase, depending on configuration/options). | Can change the current branch and working tree. |
| `git merge dev` | Combines the history of `dev` into the current branch. | May fast-forward or create a merge commit; conflicts may need resolution. |
| `git rebase dev` | Replays the current branch's commits on top of `dev`. | Rewrites the replayed commits with new commit IDs; avoid casually rebasing already shared history. |
| `git cherry-pick <commit>` | Applies the change introduced by a selected commit to the current branch. | Usually creates a new commit with a new ID; does not merge the source branch's entire history. |

In this experiment, `git pull --ff-only origin dev` synchronized the local development branch without creating an unexpected merge commit. `git merge dev` was used on feature branches when integrating competing changes and resolving conflicts.

## 4. Explain the differences among `reset`, `revert`, `restore`, `switch`, and `checkout`.

| Command | Main use | Important distinction |
| --- | --- | --- |
| `git reset` | Moves `HEAD`/the current branch to another commit and can adjust the index and working tree (`--soft`, `--mixed`, `--hard`). | Rewrites the current branch's reachable history; `--hard` can discard uncommitted changes. |
| `git revert <commit>` | Creates a new commit that reverses a previous commit's changes. | Preserves existing history; generally appropriate for undoing a published commit. |
| `git restore <path>` | Restores a file in the working tree or, with `--staged`, updates its staged version from a chosen source. | Primarily handles file contents, not switching branches. |
| `git switch <branch>` | Changes the current branch; `-c` creates and switches to a new branch. | A branch-focused command. |
| `git checkout` | Older multipurpose command for switching branches or restoring paths (and checking out a commit). | `switch` and `restore` separate its common jobs more clearly. |

For instance, we used `git switch dev` to change branches. If a published commit needed to be undone, `git revert` would usually be safer than resetting and force-pushing shared history.

## 5. What is the index (staging area), and what does `stash` do?

The **index**, also called the **staging area**, records the precise file versions that will go into the next commit. `git add exp1/index.html` copies the selected file's current content into the index; `git commit` then records the staged snapshot. An edited file can therefore have different versions in the last commit, the index, and the working tree.

`git stash` temporarily saves eligible uncommitted changes and restores the working directory and index toward a clean state, making it easier to switch tasks. `git stash pop` reapplies the saved changes and, if successful, removes that stash entry; `git stash apply` reapplies them without removing the entry. By default, untracked files are not included; use `git stash -u` when those should also be saved. Reapplying a stash can produce conflicts.

## 6. What is a snapshot, and how is it related to a commit?

A **snapshot** is the state of the tracked project files at a particular point in time. A Git commit references a tree describing that state and records metadata such as the author, message, and parent commit(s). Thus, a commit identifies a project snapshot **and** connects it to the repository's history.

Git does not need to store a fresh, independent copy of every unchanged file for each commit: unchanged content can be reused through existing objects. Our HTML, CSS, and experiment-card commits each identify a distinct snapshot of the portfolio at that stage of development.

## 7. What are the differences between a local repository and a remote repository?

A **local repository** is the Git repository on the developer's computer. It includes local commits, branches, and the working tree, and it supports committing and inspecting history without an internet connection. A **remote repository** is a repository accessible through another location or service, such as this project's GitHub repository. It enables sharing changes and supports GitHub features such as pull requests and Actions.

The two repositories are **not automatically synchronized**. `git push` sends commits and updates remote refs when permitted; `git fetch` downloads remote history; and `git pull` fetches and integrates it. In this project, `origin` is the name of the GitHub remote, while `main` and `dev` are branch names. A local branch such as `dev` and its remote-tracking reference `origin/dev` can point to different commits until they are synchronized.

---

**Source of questions:** *Experiment 1 — Introduction to Git*, handout, “Questions” section (page 2). The answers are explanatory text prepared for this repository, not quotations from the handout.
