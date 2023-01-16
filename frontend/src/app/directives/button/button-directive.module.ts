import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ButtonOutlineLightOrDarkDirectiveModule } from "./button-outline-light-or-dark/button-outline-light-or-dark-directive.module";
import { ButtonSmallDirectiveModule } from "./small/button-small-directive.module";

@NgModule({
	declarations: [],
	imports: [CommonModule, ButtonOutlineLightOrDarkDirectiveModule, ButtonSmallDirectiveModule],
	exports: [ButtonOutlineLightOrDarkDirectiveModule, ButtonSmallDirectiveModule],
})
export class ButtonDirectiveModule {}
