import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { ButtonOutlineLightOrDarkDirective } from "./directive/button-outline-light-or-dark.directive";

@NgModule({
	declarations: [ButtonOutlineLightOrDarkDirective],
	imports: [CommonModule],
	exports: [ButtonOutlineLightOrDarkDirective],
})
export class ButtonOutlineLightOrDarkDirectiveModule {}
