import { Theme } from "../model/theme";

export const THEME_OS_DEFAULT: string = "os-default";
export const THEME_LIGHT: string = "light";
export const THEME_DARK: string = "dark";
export const ALL_THEME: Array<Theme> = [
	{ id: THEME_OS_DEFAULT, name: THEME_OS_DEFAULT },
	{ id: THEME_LIGHT, name: THEME_LIGHT },
	{ id: THEME_DARK, name: THEME_DARK },
];
