import { Directive, ElementRef } from "@angular/core";
import { ThemeService } from "../../../../commons/theme/service/theme.service";
import { THEME_DARK } from "../../../../commons/theme/utilities/theme.utilities";

@Directive({
	selector: "[btnOutlineLightOrDark]",
})
export class ButtonOutlineLightOrDarkDirective {
	private btnThemeDark: string = "btn-outline-light";
	private btnThemeLight: string = "btn-outline-secondary";

	constructor(private readonly elementRef: ElementRef, private readonly themeService: ThemeService) {
		this.themeService.colorScheme.subscribe((theme) => {
			if (theme === THEME_DARK) {
				elementRef.nativeElement.classList.remove(this.btnThemeLight);
				elementRef.nativeElement.classList.add(this.btnThemeDark);
			} else {
				elementRef.nativeElement.classList.remove(this.btnThemeDark);
				elementRef.nativeElement.classList.add(this.btnThemeLight);
			}
		});
	}
}
