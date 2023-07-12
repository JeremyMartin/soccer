import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { TableSmallDirective } from "./directive/table-small.directive";

@NgModule({
	declarations: [TableSmallDirective],
	imports: [CommonModule],
	exports: [TableSmallDirective],
})
export class TableSmallDirectiveModule {}
