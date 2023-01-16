import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { SortableDirective } from "./directive/sortable.directive";

@NgModule({
	declarations: [SortableDirective],
	imports: [CommonModule],
	exports: [SortableDirective],
})
export class SortModule {}
