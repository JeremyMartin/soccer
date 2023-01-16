import { Language } from "../model/language";

export const KEY_PREFERS_LANG: string = "prefers-lang";
export const LANG_EN: string = "en";
export const LANG_FR: string = "fr";
export const LANGUAGE_EN: Language = {
	code: LANG_EN,
	name: "English",
};
export const LANGUAGE_FR: Language = {
	code: LANG_FR,
	name: "Français",
};
export const ALL_LANGUAGES: Array<Language> = [LANGUAGE_EN, LANGUAGE_FR];
