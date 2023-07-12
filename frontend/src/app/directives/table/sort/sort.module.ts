import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { SortableDirective } from "./directive/sortable.directive";

@NgModule({
	declarations: [SortableDirective],
	imports: [CommonModule],
	exports: [SortableDirective],
})
export class SortModule {}
