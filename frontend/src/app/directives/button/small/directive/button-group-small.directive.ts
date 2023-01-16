import { Directive, ElementRef } from "@angular/core";
import { fromEvent } from "rxjs";

@Directive({
	selector: "[btn-group-small]",
})
export class ButtonGroupSmallDirective {
	private btnGroupSm: string = "btn-group-sm";

	constructor(private readonly elementRef: ElementRef) {
		this.updateExpend(window.innerWidth);
		fromEvent(window, "resize").subscribe((event: Event) => {
			this.updateExpend((<Window>event.target).innerWidth);
		});
	}

	private updateExpend(width: number) {
		if (!(width < 992)) {
			if (this.elementRef.nativeElement.classList.contains(this.btnGroupSm)) {
				this.elementRef.nativeElement.classList.remove(this.btnGroupSm);
			}
		} else {
			this.elementRef.nativeElement.classList.add(this.btnGroupSm);
		}
	}
}
