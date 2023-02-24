import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { SortModule } from "./sort/sort.module";

@NgModule({
	declarations: [],
	imports: [CommonModule, SortModule],
	exports: [SortModule],
})
export class TableDirectiveModule {}
