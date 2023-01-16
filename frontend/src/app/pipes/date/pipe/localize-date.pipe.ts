import { Pipe, PipeTransform } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { DatePipe } from "@angular/common";

@Pipe({
	name: "localizeDate",
	pure: false,
})
export class LocalizeDatePipe implements PipeTransform {
	constructor(private readonly translateService: TranslateService) {}

	transform(value: string | number | Date | null | undefined, pattern: string = "short", defaultValue: string = "-"): string {
		if (value) {
			const datePipe: DatePipe = new DatePipe(this.translateService.currentLang);
			let result: string | null = datePipe.transform(value, pattern);
			if (result) {
				result = this.applyFormatIfShortPattern(result, pattern);
				return result;
			}
		}
		return defaultValue;
	}

	private applyFormatIfShortPattern(result: string, pattern: string): string {
		if (pattern === "short") {
			const currentLang: string = this.translateService.currentLang;
			let at: string;
			switch (currentLang) {
				case "fr":
					at = " à ";
					break;
				default:
					at = " at ";
			}
			result = result.replace(" ", at);
			if (currentLang === "en") {
				result = result.replace(",", "");
			}
		}
		return result;
	}
}
