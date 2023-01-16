import { Directive, ElementRef } from "@angular/core";
import { fromEvent } from "rxjs";

@Directive({
	selector: "[btn-small]",
})
export class ButtonSmallDirective {
	private btnSm: string = "btn-sm";

	constructor(private readonly elementRef: ElementRef) {
		this.updateExpend(window.innerWidth);
		fromEvent(window, "resize").subscribe((event: Event) => {
			this.updateExpend((<Window>event.target).innerWidth);
		});
	}

	private updateExpend(width: number) {
		if (!(width < 992)) {
			if (this.elementRef.nativeElement.classList.contains(this.btnSm)) {
				this.elementRef.nativeElement.classList.remove(this.btnSm);
			}
		} else {
			this.elementRef.nativeElement.classList.add(this.btnSm);
		}
	}
}
