import { Component, ViewEncapsulation } from "@angular/core";
import { CommonPaginationComponent } from "../common-pagination.component";

@Component({
	selector: "app-pagination-list",
	templateUrl: "./pagination-list.component.html",
	styleUrls: ["./pagination-list.component.scss"],
	encapsulation: ViewEncapsulation.None,
})
export class PaginationListComponent extends CommonPaginationComponent {
	onClick(event: Event, page: number): void {
		event.preventDefault();
		this.setPage(page);
	}
}
