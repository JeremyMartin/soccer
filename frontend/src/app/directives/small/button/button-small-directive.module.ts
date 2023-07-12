import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { ButtonGroupSmallDirective } from "./directive/button-group-small.directive";
import { ButtonSmallDirective } from "./directive/button-small.directive";

@NgModule({
	declarations: [ButtonSmallDirective, ButtonGroupSmallDirective],
	imports: [CommonModule],
	exports: [ButtonSmallDirective, ButtonGroupSmallDirective],
})
export class ButtonSmallDirectiveModule {}
