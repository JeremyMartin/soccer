import { HttpErrorResponse } from "@angular/common/http";
import { ChangeDetectorRef, Component, OnInit, QueryList, ViewChildren } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { take } from "rxjs";
import { BreadcrumbItem } from "../../../../../commons/breadcrumb/model/breadcrumb-item";
import { Language } from "../../../../../commons/language/model/language";
import { LanguageService } from "../../../../../commons/language/service/language.service";
import { LANGUAGE_FR } from "../../../../../commons/language/utilities/language.utilities";
import { EventPaging } from "../../../../../commons/pagination/model/event-paging";
import { ToastService } from "../../../../../commons/toast/service/toast.service";
import { compareFn } from "../../../../../commons/utils/fn-utilities";
import { SortableDirective } from "../../../../../directives/table/sort/directive/sortable.directive";
import { SortColumn, SortDirection, SortEvent } from "../../../../../directives/table/sort/model/sort-event";
import { Tournament } from "../../../../../models/tournament/tournament";
import { LocalizeDatePipe } from "../../../../../pipes/date/pipe/localize-date.pipe";
import { TournamentService } from "../../../../../services/tournament.service";

@Component({
	selector: "app-list",
	templateUrl: "./list.component.html",
	styleUrls: ["./list.component.scss"],
})
export class ListComponent implements OnInit {
	readonly title: string = "page.tournament.list.default";
	readonly breadcrumbItems: Array<BreadcrumbItem> = [
		{ name: "menu.home", action: { path: "/" } },
		{ name: "menu.tournament", action: { path: "/tournament" } },
		{ name: "label.breadcrumb.list" },
	];
	readonly LANGUAGE_FR = LANGUAGE_FR;
	isLoading: boolean = false;
	errors: Set<string> = new Set<string>();
	tournaments: Array<Tournament> = [];
	originalTournaments: Array<Tournament> = [];
	pageSize: number = 20;
	maxPages: number = 6;
	currentPage: number = 1;
	currentTournament!: Tournament | undefined;
	currentLanguage!: Language;
	private datePipe!: LocalizeDatePipe;
	@ViewChildren(SortableDirective)
	private headers!: QueryList<SortableDirective>;
	private sortedColumn: SortColumn = "";
	private sortedDirection: SortDirection = "";

	constructor(
		private readonly cdr: ChangeDetectorRef,
		private readonly languageService: LanguageService,
		private readonly toastService: ToastService,
		private readonly tournamentService: TournamentService,
		private readonly translateService: TranslateService
	) {
		this.datePipe = new LocalizeDatePipe(translateService);
		languageService.currentLanguage.subscribe((language) => (this.currentLanguage = language));
	}

	ngOnInit(): void {
		this.isLoading = true;
		this.tournamentService
			.list()
			.pipe(take(1))
			.subscribe({
				next: (data) => {
					this.originalTournaments = data;
				},
				error: (err: HttpErrorResponse) => {
					console.error(err);
					this.errors.add(err.error.message);
					this.errors.add(err.error.cause);
				},
				complete: () => (this.isLoading = false),
			});
	}

	onClickDelete(tournament: Tournament): void {
		this.currentTournament = tournament;
	}

	confirmDelete(): void {
		if (this.currentTournament && this.currentTournament.id) {
			const tournament: Tournament | undefined = this.originalTournaments.find((t) => t.id === this.currentTournament?.id);
			if (tournament) {
				tournament.deleting = true;
			}
			this.currentTournament.deleting = true;
			this.tournamentService.deleteById(this.currentTournament.id).subscribe({
				next: () => {
					this.toastService.showSuccess("success.delete.tournament");
					this.originalTournaments = this.originalTournaments.filter((t) => t !== this.currentTournament);
				},
				error: (err: HttpErrorResponse) => {
					console.error(err);
					this.toastService.showError(err.error.message);
					if (tournament) {
						tournament.deleting = false;
					}
				},
				complete: () => {
					this.currentTournament = undefined;
					console.log(this.currentTournament);
				},
			});
		}
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
