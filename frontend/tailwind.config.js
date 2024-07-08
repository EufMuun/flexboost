const {nextui} = require("@nextui-org/react");

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./node_modules/@nextui-org/theme/dist/**/*.{js,ts,jsx,tsx}" ,
    "./src/**/*.{js,jsx,ts,tsx}",

  ],
  theme: {
    extend: {},
  },
    corePlugins: {
      preflight: false,
    },
  darkMode: "class",
  plugins: [nextui()],
}

