import { Component, OnDestroy, TemplateRef, ViewEncapsulation } from "@angular/core";
import { ToastService } from "../service/toast.service";

@Component({
	selector: "app-toast",
	templateUrl: "./toast.component.html",
	styleUrls: ["./toast.component.scss"],
	encapsulation: ViewEncapsulation.None,
})
export class ToastComponent implements OnDestroy {
	constructor(public readonly toastService: ToastService) {}

	ngOnDestroy(): void {
		this.toastService.clear();
	}

	isTemplate(toast: any) {
		return toast.text instanceof TemplateRef;
	}
}
