import { Injectable } from "@angular/core";
import { Language } from "../model/language";
import { BehaviorSubject } from "rxjs";
import { ALL_LANGUAGES, KEY_PREFERS_LANG, LANGUAGE_EN, LANGUAGE_FR } from "../utilities/language.utilities";
import { TranslateService } from "@ngx-translate/core";

@Injectable({
	providedIn: "root",
})
export class LanguageService {
	currentLanguage: BehaviorSubject<Language> = new BehaviorSubject<Language>(LANGUAGE_EN);

	constructor(private readonly translateService: TranslateService) {}

	load(): void {
		this.initLanguage();
		this.currentLanguage.subscribe(({ code }) => this.translateService.use(code));
	}

	update(language: Language) {
		localStorage.setItem(KEY_PREFERS_LANG, language.code);
		this.currentLanguage.next(language);
	}

	private getLanguageByKey(code: string | null): Language | undefined {
		return ALL_LANGUAGES.find((language) => language.code === code);
	}

	private detectLanguage(): void {
		const language = this.getLanguageByKey(this.translateService.currentLang);
		if (language) {
			this.currentLanguage.next(language);
		} else {
			if (/^fr\b/.test(navigator.language)) {
				this.currentLanguage.next(LANGUAGE_FR);
			} else {
				this.currentLanguage.next(LANGUAGE_EN);
			}
		}
	}

	private initLanguage(): void {
		const localStorageLang: string | null = localStorage.getItem(KEY_PREFERS_LANG);
		const language: Language | undefined = this.getLanguageByKey(localStorageLang);
		if (language) {
			this.currentLanguage.next(language);
		} else {
			this.detectLanguage();
		}
	}
}
