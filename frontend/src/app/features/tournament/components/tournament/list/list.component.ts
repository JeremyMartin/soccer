import { Component, OnInit, QueryList, ViewChildren } from "@angular/core";
import { Tournament } from "../../../../../models/tournament/tournament";
import { generateTournaments } from "../../../utilities/tournament.utilities";
import { BreadcrumbItem } from "../../../../../commons/breadcrumb/model/breadcrumb-item";
import { SortColumn, SortDirection, SortEvent } from "../../../../../directives/table/sort/model/sort-event";
import { SortableDirective } from "../../../../../directives/table/sort/directive/sortable.directive";
import { compareFn } from "../../../../../commons/utils/fn-utilities";
import { LocalizeDatePipe } from "../../../../../pipes/date/pipe/localize-date.pipe";
import { TranslateService } from "@ngx-translate/core";
import { EventPaging } from "../../../../../commons/pagination/model/event-paging";

@Component({
	selector: "app-list",
	templateUrl: "./list.component.html",
	styleUrls: ["./list.component.scss"],
})
export class ListComponent implements OnInit {
	@ViewChildren(SortableDirective)
	private headers!: QueryList<SortableDirective>;
	private sortedColumn: SortColumn = "";
	private sortedDirection: SortDirection = "";
	breadcrumbItems: Array<BreadcrumbItem> = [
		{ name: "menu.home", action: { path: "/" } },
		{ name: "menu.tournament", action: { path: "/tournament" } },
		{ name: "label.breadcrumb.list" },
	];

	tournaments: Array<Tournament> = [];
	originalTournaments: Array<Tournament> = [];
	pageSize: number = 20;
	maxPages: number = 6;
	currentPage: number = 1;
	currentTournament!: Tournament | undefined;

	private datePipe!: LocalizeDatePipe;

	constructor(private readonly translateService: TranslateService) {
		this.datePipe = new LocalizeDatePipe(translateService);
	}

	ngOnInit(): void {
		this.originalTournaments = generateTournaments();
	}

	onClickDelete(tournament: Tournament): void {
		this.currentTournament = tournament;
	}

	confirmDelete(): void {
		if (this.currentTournament) {
			this.originalTournaments = this.originalTournaments.filter((t) => t !== this.currentTournament);
		}
		this.currentTournament = undefined;
	}

	onClickSort({ column, direction }: SortEvent): void {
		this.sortedColumn = column;
		this.sortedDirection = direction;
		this.headers.forEach((header) => {
			if (header.column !== column) {
				header.direction = "";
			}
			this.sort();
		});
	}

	eventPaging(eventPaging: EventPaging) {
		this.currentPage = eventPaging.currentPage;
		this.tournaments = <Array<Tournament>>eventPaging.items;
	}

	private sort(): void {
		this.originalTournaments = [...this.originalTournaments].sort((a, b) => {
			let res: number = 0;
			switch (this.sortedColumn) {
				case "id":
					res = compareFn(a.id, b.id);
					break;
				// res = a.id - b.id
				case "name":
					res = compareFn(a.name, b.name);
					// res = a.name < b.name ? -1 : a.name > b.name ? 1 : 0;
					break;
				case "date":
					res = compareFn(a.date, b.date);
					break;
			}
			return this.sortedDirection === "asc" ? res : -res;
		});
	}
}
