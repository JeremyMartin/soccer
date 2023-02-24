import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { SelectSmallDirective } from "./directive/select-small.directive";

@NgModule({
	declarations: [SelectSmallDirective],
	imports: [CommonModule],
	exports: [SelectSmallDirective],
})
export class SelectSmallDirectiveModule {}
