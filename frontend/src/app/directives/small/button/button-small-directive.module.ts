import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ButtonSmallDirective } from "./directive/button-small.directive";
import { ButtonGroupSmallDirective } from "./directive/button-group-small.directive";

@NgModule({
	declarations: [ButtonSmallDirective, ButtonGroupSmallDirective],
	imports: [CommonModule],
	exports: [ButtonSmallDirective, ButtonGroupSmallDirective],
})
export class ButtonSmallDirectiveModule {}
