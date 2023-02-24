import { Directive, ElementRef } from "@angular/core";
import { fromEvent } from "rxjs";

@Directive({
	selector: "[select-small]",
})
export class SelectSmallDirective {
	private selectSm: string = "form-select-sm";

	constructor(private readonly elementRef: ElementRef) {
		this.updateExpend(window.innerWidth);
		fromEvent(window, "resize").subscribe((event: Event) => {
			this.updateExpend((<Window>event.target).innerWidth);
		});
	}

	private updateExpend(width: number) {
		if (!(width < 992)) {
			this.elementRef.nativeElement.classList.remove(this.selectSm);
		} else {
			this.elementRef.nativeElement.classList.add(this.selectSm);
		}
	}
}
