import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { TableSmallDirectiveModule } from "./table/table-small-directive.module";
import { ButtonSmallDirectiveModule } from "./button/button-small-directive.module";
import { SelectSmallDirectiveModule } from "./select/select-small-directive.module";
import { PaginationSmallDirectiveModule } from "./pagination/pagination-small-directive.module";

@NgModule({
	declarations: [],
	imports: [
		CommonModule,
		ButtonSmallDirectiveModule,
		SelectSmallDirectiveModule,
		PaginationSmallDirectiveModule,
		TableSmallDirectiveModule,
	],
})
export class SmallDirectiveModule {}
