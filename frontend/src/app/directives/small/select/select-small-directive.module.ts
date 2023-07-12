import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { SelectSmallDirective } from "./directive/select-small.directive";

@NgModule({
	declarations: [SelectSmallDirective],
	imports: [CommonModule],
	exports: [SelectSmallDirective],
})
export class SelectSmallDirectiveModule {}
