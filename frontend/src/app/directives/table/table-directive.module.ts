import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { SmallDirectiveModule } from "./small/small-directive.module";
import { SortModule } from "./sort/sort.module";

@NgModule({
	declarations: [],
	imports: [CommonModule, SmallDirectiveModule, SortModule],
	exports: [SmallDirectiveModule, SortModule],
})
export class TableDirectiveModule {}
