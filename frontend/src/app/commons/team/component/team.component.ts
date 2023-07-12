import { Component, Input, ViewEncapsulation } from "@angular/core";
import { Team } from "../../../models/team/team";
import { Language } from "../../language/model/language";
import { LanguageService } from "../../language/service/language.service";
import { LANGUAGE_FR } from "../../language/utilities/language.utilities";

@Component({
	selector: "app-team[team]",
	templateUrl: "./team.component.html",
	encapsulation: ViewEncapsulation.None,
})
export class TeamComponent {
	readonly LANGUAGE_FR = LANGUAGE_FR;
	@Input()
	team!: Team;
	@Input()
	showPlayerName: boolean = false;
	currentLanguage!: Language;

	constructor(private readonly languageService: LanguageService) {
		languageService.currentLanguage.subscribe((language) => (this.currentLanguage = language));
	}
}
