import { Component, ViewEncapsulation } from "@angular/core";
import { Theme } from "../../model/theme";
import { ThemeService } from "../../service/theme.service";
import { ALL_THEME, THEME_DARK, THEME_LIGHT, THEME_OS_DEFAULT } from "../../utilities/theme.utilities";

@Component({
	selector: "app-theme",
	templateUrl: "./theme.component.html",
	styleUrls: ["./theme.component.scss"],
	encapsulation: ViewEncapsulation.None,
})
export class ThemeComponent {
	readonly OS_DEFAULT: string = THEME_OS_DEFAULT;
	readonly LIGHT: string = THEME_LIGHT;
	readonly DARK: string = THEME_DARK;
	readonly THEMES: Array<Theme> = ALL_THEME;

	currentTheme!: string;

	constructor(private readonly themeService: ThemeService) {
		themeService.userPrefersColorScheme.subscribe((theme) => (this.currentTheme = theme));
	}

	setTheme(theme: string): void {
		this.themeService.update(theme);
	}
}
