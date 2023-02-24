import { createAction, props } from "@ngrx/store";
import { ReferentialTypes } from "./referential.types";
import { Club } from "../../models/club/club";
import { Nation } from "../../models/nation/nation";
import { HttpErrorResponse } from "@angular/common/http";

export const initReferential = createAction(ReferentialTypes.INIT);
export const loadClubsSuccess = createAction(ReferentialTypes.LOAD_CLUBS_SUCCESS, props<{ clubs: Array<Club> }>());
export const loadClubsFailure = createAction(ReferentialTypes.LOAD_CLUBS_FAILURE, props<{ error: HttpErrorResponse | null }>());
export const loadNationsSuccess = createAction(ReferentialTypes.LOAD_NATIONS_SUCCESS, props<{ nations: Array<Nation> }>());
export const loadNationsFailure = createAction(ReferentialTypes.LOAD_NATIONS_FAILURE, props<{ error: HttpErrorResponse | null }>());
