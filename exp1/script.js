const themeToggle = document.querySelector("#theme-toggle");

themeToggle.addEventListener("click", () => {
    const isLight = document.documentElement.dataset.theme === "light";

    document.documentElement.dataset.theme = isLight
        ? "dark"
        : "light";

    themeToggle.textContent = isLight
        ? "Light mode"
        : "Dark mode";

    themeToggle.setAttribute("aria-pressed", String(!isLight));
});