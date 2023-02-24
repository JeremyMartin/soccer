import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { ClubService } from "../../services/club.service";
import { NationService } from "../../services/nation.service";
import { catchError, map, Observable, of, switchMap } from "rxjs";
import { Club } from "../../models/club/club";
import { Nation } from "../../models/nation/nation";
import { initReferential, loadClubsFailure, loadClubsSuccess, loadNationsFailure, loadNationsSuccess } from "./referential.actions";
import { HttpErrorResponse } from "@angular/common/http";

@Injectable()
export class ReferentialEffects {
	constructor(
		private readonly action$: Actions,
		private readonly clubService: ClubService,
		private readonly nationService: NationService
	) {}

	public readonly getReferentialClubs$: Observable<any> = createEffect(() => {
		return this.action$.pipe(
			ofType(initReferential),
			switchMap(() => this.clubService.list(true)),
			map((clubs: Array<Club>) => loadClubsSuccess({ clubs: clubs })),
			catchError((error: HttpErrorResponse | null) => of(loadClubsFailure({ error: error })))
		);
	});

	public readonly getReferentialNations$: Observable<any> = createEffect(() => {
		return this.action$.pipe(
			ofType(initReferential),
			switchMap(() => this.nationService.list(true)),
			map((nations: Array<Nation>) => loadNationsSuccess({ nations: nations })),
			catchError((error: HttpErrorResponse | null) => of(loadNationsFailure({ error: error })))
		);
	});
}
