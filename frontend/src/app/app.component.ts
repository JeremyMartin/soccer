import { Component, ViewEncapsulation } from "@angular/core";
import { environment } from "../environments/environment";
import { ThemeService } from "./commons/theme/service/theme.service";
import { LanguageService } from "./commons/language/service/language.service";
import { Meta, Title } from "@angular/platform-browser";
import { TranslateService } from "@ngx-translate/core";

@Component({
	selector: "app-root",
	templateUrl: "./app.component.html",
	styleUrls: ["./app.component.scss"],
	encapsulation: ViewEncapsulation.None,
})
export class AppComponent {
	isDebugStyle: boolean = environment.debugStyle;

	constructor(
		private readonly themeService: ThemeService,
		private readonly languageService: LanguageService,
		private readonly translateService: TranslateService,
		private readonly metaService: Meta,
		private readonly titleService: Title
	) {
		themeService.load();
		languageService.load();
		languageService.currentLanguage.subscribe(() => {
			translateService.get("meta.description").subscribe((value: string) => {
				metaService.updateTag({ name: "description", content: value });
			});
			translateService.get("meta.title").subscribe((value: string) => {
				titleService.setTitle(value);
			});
		});
	}
}
