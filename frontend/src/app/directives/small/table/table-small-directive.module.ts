import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { TableSmallDirective } from "./directive/table-small.directive";

@NgModule({
	declarations: [TableSmallDirective],
	imports: [CommonModule],
	exports: [TableSmallDirective],
})
export class TableSmallDirectiveModule {}
