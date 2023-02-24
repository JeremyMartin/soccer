import { Component, Input, ViewEncapsulation } from "@angular/core";

@Component({
	selector: "app-error[errors]",
	templateUrl: "./error.component.html",
	styleUrls: ["./error.component.scss"],
	encapsulation: ViewEncapsulation.None,
})
export class ErrorComponent {
	@Input()
	errors!: Set<string>;
	@Input()
	isFullPage: boolean = false;
}
