import { Directive, ElementRef } from "@angular/core";
import { fromEvent } from "rxjs";

@Directive({
	selector: "[table-small]",
})
export class TableSmallDirective {
	private tableSm: string = "table-sm";

	constructor(private readonly elementRef: ElementRef) {
		this.updateExpend(window.innerWidth);
		fromEvent(window, "resize").subscribe((event: Event) => {
			this.updateExpend((<Window>event.target).innerWidth);
		});
	}

	private updateExpend(width: number) {
		if (!(width < 992)) {
			this.elementRef.nativeElement.classList.remove(this.tableSm);
		} else {
			this.elementRef.nativeElement.classList.add(this.tableSm);
		}
	}
}
