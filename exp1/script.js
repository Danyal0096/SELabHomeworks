const themeToggle = document.querySelector("#theme-toggle");

function applyTheme(theme) {
    const isLight = theme === "light";

    document.documentElement.dataset.theme = theme;

    themeToggle.textContent = isLight
        ? "Dark mode"
        : "Light mode";

    themeToggle.setAttribute("aria-pressed", String(isLight));
}

// Restore the user's previous preference.
let savedTheme = "dark";

try {
    savedTheme = localStorage.getItem("theme") === "light"
        ? "light"
        : "dark";
} catch (error) {
    console.warn("Theme storage is unavailable:", error);
}

applyTheme(savedTheme);

// Change and save the theme when clicked.
themeToggle.addEventListener("click", () => {
    const currentTheme = document.documentElement.dataset.theme;

    const newTheme = currentTheme === "light"
        ? "dark"
        : "light";

    applyTheme(newTheme);

    try {
        localStorage.setItem("theme", newTheme);
    } catch (error) {
        console.warn("Theme preference could not be saved:", error);
    }
});