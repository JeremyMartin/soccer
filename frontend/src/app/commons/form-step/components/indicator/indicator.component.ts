import { Component, Input, ViewEncapsulation } from "@angular/core";
import { FormStep } from "../../model/form-step";

@Component({
	selector: "app-form-indicator[formSteps][currentFormStep]",
	templateUrl: "./indicator.component.html",
	styleUrls: ["./indicator.component.scss"],
	encapsulation: ViewEncapsulation.None,
})
export class IndicatorComponent {
	@Input()
	formSteps!: Array<FormStep>;
	@Input()
	currentFormStep: number = 1;
	@Input()
	isFinish: boolean = false;
}
