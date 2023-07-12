import { HttpErrorResponse } from "@angular/common/http";
import { ChangeDetectorRef, Component, OnInit, ViewEncapsulation } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Store } from "@ngrx/store";
import { combineLatest, take, throwError } from "rxjs";
import { BreadcrumbItem } from "../../../../../commons/breadcrumb/model/breadcrumb-item";
import { Language } from "../../../../../commons/language/model/language";
import { LanguageService } from "../../../../../commons/language/service/language.service";
import { LANGUAGE_FR } from "../../../../../commons/language/utilities/language.utilities";
import { compareFn } from "../../../../../commons/utils/fn-utilities";
import { Match } from "../../../../../models/match/match";
import { Group } from "../../../../../models/phase/group";
import { Ranking } from "../../../../../models/ranking/ranking";
import { Step } from "../../../../../models/step/step";
import { Team } from "../../../../../models/team/team";
import { Tournament } from "../../../../../models/tournament/tournament";
import { TournamentService } from "../../../../../services/tournament.service";
import { selectSteps } from "../../../../../store/referential/referential.selectors";
import { ReferentialState } from "../../../../../store/referential/referential.state";
import { FINAL_STAGE, QUALIFYING_STAGE } from "../../../utilities/tournament.utilities";

@Component({
	selector: "app-view",
	templateUrl: "./view.component.html",
	styleUrls: ["./view.component.scss"],
	encapsulation: ViewEncapsulation.None,
})
export class ViewComponent implements OnInit {
	readonly title: string = "page.tournament.view.default";
	readonly breadcrumbItems: Array<BreadcrumbItem> = [
		{ name: "menu.home", action: { path: "/" } },
		{ name: "menu.tournament", action: { path: "/tournament" } },
		{ name: "label.breadcrumb.view" },
	];
	readonly LANGUAGE_FR = LANGUAGE_FR;
	isLoading: boolean = false;
	errors: Set<string> = new Set<string>();
	tournament?: Tournament;
	currentLanguage!: Language;
	currentMatch?: Match;
	activeTab: number = 1;
	steps!: Array<Step>;
	allowTab2: boolean = false;

	constructor(
		private readonly activatedRoute: ActivatedRoute,
		private readonly languageService: LanguageService,
		private readonly storeReferential: Store<ReferentialState>,
		private readonly tournamentService: TournamentService,
		private readonly router: Router
	) {
		languageService.currentLanguage.subscribe((language) => (this.currentLanguage = language));
	}

	ngOnInit(): void {
		this.loadTournament(true);
	}

	loadTournament(isLoading: boolean = false): void {
		this.currentMatch = undefined;
		const id = this.activatedRoute.snapshot.paramMap.get("id");
		if (id) {
			this.isLoading = isLoading;
			combineLatest([this.tournamentService.findById(id), this.storeReferential.select(selectSteps)])
				.pipe(take(1))
				.subscribe({
					next: ([tournament, steps]) => {
						if (tournament) {
							this.tournament = tournament;
							this.allowTab2 = tournament.step?.id !== QUALIFYING_STAGE.id;
							if (!tournament.groups) {
								this.activeTab = 2;
							}
						}
						if (steps) {
							this.steps = steps;
						}
						if (this.tournament && this.steps) {
							this.isLoading = false;
						}
					},
					error: (err: HttpErrorResponse) => {
						console.error(err);
						this.errors.add(err.error.message);
						this.errors.add(err.error.cause);
					},
				});
		} else {
			this.errors.add("error.not.found.tournament");
			this.router.navigate(["/tournament"]).then();
		}
	}

	sortTeams(): Array<Team> {
		if (this.tournament && this.tournament.teams) {
			return this.tournament.teams.sort((a, b) => compareFn(a.player.name, b.player.name));
		}
		return [];
	}

	sortGroups(): Array<Group> {
		if (this.tournament && this.tournament.groups) {
			return this.tournament.groups.sort((a, b) => compareFn(a.name, b.name));
		}
		return [];
	}

	sortMatchs(group: Group): Array<Match> {
		if (group && group.matchs) {
			return Array.from(group.matchs).sort((a, b) => compareFn(a.id, b.id));
		}
		return [];
	}

	sortRanking(rankings: Array<Ranking>): Array<Ranking> {
		return rankings.sort((a, b) => compareFn(a.rank, b.rank));
	}

	onClickEdit(match: Match): void {
		this.currentMatch = match;
	}

	clearCurrentMatch(): void {
		this.currentMatch = undefined;
	}

	getPenaltyWinner(match: Match): {
		winner: string | undefined;
		goalsWinner: string | number | undefined;
		goalsLoser: string | number | undefined;
	} {
		if (match.homePenalty && match.awayPenalty) {
			if (match?.homePenalty > match?.awayPenalty) {
				if (match.home?.nation) {
					return {
						winner: this.currentLanguage === LANGUAGE_FR ? match.home?.nation?.nameFr : match.home?.nation?.nameEn,
						goalsWinner: match?.homePenalty,
						goalsLoser: match?.awayPenalty,
					};
				} else {
					return { winner: match.home?.club?.name, goalsWinner: match?.homePenalty, goalsLoser: match?.awayPenalty };
				}
			} else {
				if (match.away?.nation) {
					return {
						winner: this.currentLanguage === LANGUAGE_FR ? match.away?.nation?.nameFr : match.away?.nation?.nameEn,
						goalsWinner: match?.awayPenalty,
						goalsLoser: match?.homePenalty,
					};
				} else {
					return { winner: match?.away?.club?.name, goalsWinner: match?.awayPenalty, goalsLoser: match?.homePenalty };
				}
			}
		}
		return { winner: "", goalsWinner: "", goalsLoser: "" };
	}

	getQualifyingStage(): string {
		const step: Step | undefined = this.steps.find((s) => s.nameEn === "Qualifying stage") || QUALIFYING_STAGE;
		return this.currentLanguage === LANGUAGE_FR ? step.nameFr : step.nameEn;
	}

	getFinalStage(): string {
		const step: Step | undefined = this.steps.find((s) => s.nameEn === "Final Stage") || FINAL_STAGE;
		return this.currentLanguage === LANGUAGE_FR ? step.nameFr : step.nameEn;
	}
}
