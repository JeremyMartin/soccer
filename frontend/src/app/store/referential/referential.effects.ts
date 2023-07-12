import { HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { catchError, map, Observable, of, switchMap } from "rxjs";
import { Club } from "../../models/club/club";
import { Nation } from "../../models/nation/nation";
import { Step } from "../../models/step/step";
import { ClubService } from "../../services/club.service";
import { NationService } from "../../services/nation.service";
import { StepService } from "../../services/step.service";
import {
	initReferential,
	loadClubsFailure,
	loadClubsSuccess,
	loadNationsFailure,
	loadNationsSuccess,
	loadStepsFailure,
	loadStepsSuccess,
} from "./referential.actions";

@Injectable()
export class ReferentialEffects {
	constructor(
		private readonly action$: Actions,
		private readonly clubService: ClubService,
		private readonly nationService: NationService,
		private readonly stepService: StepService
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

	public readonly getReferentialSteps$: Observable<any> = createEffect(() => {
		return this.action$.pipe(
			ofType(initReferential),
			switchMap(() => this.stepService.list()),
			map((steps: Array<Step>) => loadStepsSuccess({ steps: steps })),
			catchError((error: HttpErrorResponse | null) => of(loadStepsFailure({ error: error })))
		);
	});
}
