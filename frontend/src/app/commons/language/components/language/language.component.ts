import { Component, ViewEncapsulation } from "@angular/core";
import { Language } from "../../model/language";
import { LanguageService } from "../../service/language.service";
import { LANG_EN, LANG_FR, LANGUAGE_EN, LANGUAGE_FR } from "../../utilities/language.utilities";

@Component({
	selector: "app-language",
	templateUrl: "./language.component.html",
	styleUrls: ["./language.component.scss"],
	encapsulation: ViewEncapsulation.None,
})
export class LanguageComponent {
	readonly EN: string = LANG_EN;
	readonly FR: string = LANG_FR;
	readonly LANGUAGE_EN: Language = LANGUAGE_EN;
	readonly LANGUAGE_FR: Language = LANGUAGE_FR;
	currentLanguage!: Language;

	constructor(private readonly languageService: LanguageService) {
		languageService.currentLanguage.subscribe((language) => (this.currentLanguage = language));
	}

	changeLanguage(language: Language) {
		this.languageService.update(language);
	}
}
