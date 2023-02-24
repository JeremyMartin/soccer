import { Nation } from "../../models/nation/nation";
import { Club } from "../../models/club/club";
import { HttpErrorResponse } from "@angular/common/http";

export interface ReferentialState {
	clubs: Array<Club> | null;
	nations: Array<Nation> | null;
	loadedClubs: boolean;
	loadedNations: boolean;
	loadingClubs: boolean;
	loadingNations: boolean;
	errorClubs: HttpErrorResponse | null;
	errorNations: HttpErrorResponse | null;
}

export const initialReferentialState: ReferentialState = {
	clubs: null,
	nations: null,
	loadedClubs: false,
	loadedNations: false,
	loadingClubs: false,
	loadingNations: false,
	errorClubs: null,
	errorNations: null,
};
