import { Component, Input, ViewEncapsulation } from "@angular/core";

@Component({
	selector: "app-spinner",
	templateUrl: "./spinner.component.html",
	styleUrls: ["./spinner.component.scss"],
	encapsulation: ViewEncapsulation.None,
})
export class SpinnerComponent {
	@Input()
	showMessage: boolean = true;
	@Input()
	isFullPage: boolean = false;
	@Input()
	isSubmit: boolean = false;
}
