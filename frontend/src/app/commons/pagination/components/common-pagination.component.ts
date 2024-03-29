import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from "@angular/core";
import { compareFn } from "../../utils/fn-utilities";
import { EventPaging } from "../model/event-paging";
import { Pager } from "../model/pager";

@Component({
	selector: "app-master-pagination",
	template: "<div>master pagination</div>",
})
export abstract class CommonPaginationComponent implements OnChanges {
	@Input()
	items!: Array<any>;
	@Input()
	initialPage: number = 1;
	@Input()
	pageSize: number = 10;
	@Input()
	maxPages: number = 6;
	@Output()
	eventPage: EventEmitter<EventPaging> = new EventEmitter<EventPaging>(true);
	pager?: Pager;
	itemsPerPage: Array<number> = [5, 10, 25, 50];

	ngOnChanges(changes: SimpleChanges): void {
		if (
			changes &&
			((changes["items"] && changes["items"].currentValue !== changes["items"].previousValue) ||
				(changes["pageSize"] && changes["pageSize"].currentValue !== changes["pageSize"].previousValue) ||
				(changes["maxPagesShow"] && changes["maxPagesShow"].currentValue !== changes["maxPagesShow"].previousValue))
		) {
			if (!this.itemsPerPage.includes(+this.pageSize)) {
				this.itemsPerPage.push(+this.pageSize);
				this.itemsPerPage = this.itemsPerPage.sort((a, b) => compareFn(a, b));
			}
			this.setPage(this.initialPage);
		}
	}

	//
	setPage(page: number) {
		// if (!this.items.length) {
		// 	return;
		// }
		// get new pager object for specified page
		this.pager = this.paginate(+this.items.length, page, +this.pageSize, this.maxPages);

		// get new page of items from items array
		const items: Array<any> = this.items.slice(this.pager.startIndex, this.pager.endIndex + 1);

		// call change page function in parent component
		this.eventPage.emit({ currentPage: this.pager.currentPage, items: items });
	}

	//
	setItemsPerPage() {
		let page = this.initialPage;
		if (this.pager) {
			page = this.pager.currentPage;
		}
		this.setPage(page);
	}

	private paginate(totalItems: number, currentPage: number = 1, pageSize: number = 10, maxPages: number = 6): Pager {
		// calculate total pages
		const totalPages = Math.ceil(totalItems / pageSize);

		// ensure current page isn't out of range
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > totalPages) {
			currentPage = totalPages;
		}

		let startPage: number, endPage: number;
		if (totalPages <= maxPages) {
			// total pages less than max so show all pages
			startPage = 1;
			endPage = totalPages;
		} else {
			// total pages more than max so calculate start and end pages
			const maxPagesBeforeCurrentPage = Math.floor(maxPages / 2);
			const maxPagesAfterCurrentPage = Math.ceil(maxPages / 2) - 1;
			if (currentPage <= maxPagesBeforeCurrentPage) {
				// current page near the start
				startPage = 1;
				endPage = maxPages;
			} else if (currentPage + maxPagesAfterCurrentPage >= totalPages) {
				// current page near the end
				startPage = totalPages - maxPages + 1;
				endPage = totalPages;
			} else {
				// current page somewhere in the middle
				startPage = currentPage - maxPagesBeforeCurrentPage;
				endPage = currentPage + maxPagesAfterCurrentPage;
			}
		}

		// calculate start and end item indexes
		const startIndex = (currentPage - 1) * pageSize;
		const endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1);

		// create an array of pages to ng-repeat in the pager control
		const pages = Array.from(Array(endPage + 1 - startPage).keys()).map((i) => startPage + i);

		// return object with all pager properties required by the view
		return {
			totalItems,
			currentPage,
			pageSize,
			totalPages,
			startPage,
			endPage,
			startIndex,
			endIndex,
			pages,
		};
	}
}
