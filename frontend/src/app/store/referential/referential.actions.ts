import { HttpErrorResponse } from "@angular/common/http";
import { createAction, props } from "@ngrx/store";
import { Club } from "../../models/club/club";
import { Nation } from "../../models/nation/nation";
import { Step } from "../../models/step/step";
import { ReferentialTypes } from "./referential.types";

export const initReferential = createAction(ReferentialTypes.INIT);
export const loadClubsSuccess = createAction(ReferentialTypes.LOAD_CLUBS_SUCCESS, props<{ clubs: Array<Club> }>());
export const loadClubsFailure = createAction(ReferentialTypes.LOAD_CLUBS_FAILURE, props<{ error: HttpErrorResponse | null }>());
export const loadNationsSuccess = createAction(ReferentialTypes.LOAD_NATIONS_SUCCESS, props<{ nations: Array<Nation> }>());
export const loadNationsFailure = createAction(ReferentialTypes.LOAD_NATIONS_FAILURE, props<{ error: HttpErrorResponse | null }>());
export const loadStepsSuccess = createAction(ReferentialTypes.LOAD_STEPS_SUCCESS, props<{ steps: Array<Step> }>());
export const loadStepsFailure = createAction(ReferentialTypes.LOAD_STEPS_FAILURE, props<{ error: HttpErrorResponse | null }>());
