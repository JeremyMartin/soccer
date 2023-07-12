import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { ButtonOutlineLightOrDarkDirectiveModule } from "./button-outline-light-or-dark/button-outline-light-or-dark-directive.module";

@NgModule({
	declarations: [],
	imports: [CommonModule, ButtonOutlineLightOrDarkDirectiveModule],
	exports: [ButtonOutlineLightOrDarkDirectiveModule],
})
export class ButtonDirectiveModule {}
