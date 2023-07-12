import { Directive, ElementRef, HostListener } from "@angular/core";

@Directive({
	selector: "[numberOnly]",
})
export class NumberOnlyDirective {
	constructor(private readonly elementRef: ElementRef) {}

	@HostListener("keypress", ["$event"])
	onKeypress(event: KeyboardEvent) {
		return /^[0-9]+$/.test(event.key);
	}

	@HostListener("paste", ["$event"])
	onPaste(event: ClipboardEvent) {
		event.preventDefault();
		if (event.clipboardData) {
			const data = event.clipboardData.getData("text/plain").replace(/[^0-9]/g, "");
			document.execCommand("insertHtml", false, data);
		}
	}
}
