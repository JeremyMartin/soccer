import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { PaginationSmallDirective } from "./directive/pagination-small.directive";

@NgModule({
	declarations: [PaginationSmallDirective],
	imports: [CommonModule],
	exports: [PaginationSmallDirective],
})
export class PaginationSmallDirectiveModule {}
