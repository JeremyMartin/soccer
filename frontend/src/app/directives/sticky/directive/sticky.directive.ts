import { AfterViewChecked, Directive, ElementRef } from "@angular/core";

@Directive({
	selector: "[sticky]",
})
export class StickyDirective implements AfterViewChecked {
	constructor(private readonly elementRef: ElementRef) {}

	ngAfterViewChecked(): void {
		this.elementRef.nativeElement.classList.add("sticky");
	}
}
