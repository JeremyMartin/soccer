import { Component, ViewEncapsulation } from "@angular/core";
import { CommonPaginationComponent } from "../common-pagination.component";

@Component({
	selector: "app-pagination-button[items][eventPage]",
	templateUrl: "./pagination-button.component.html",
	styleUrls: ["./pagination-button.component.scss"],
	encapsulation: ViewEncapsulation.None,
})
export class PaginationButtonComponent extends CommonPaginationComponent {}
