import { HttpErrorResponse } from "@angular/common/http";
import { Club } from "../../models/club/club";
import { Nation } from "../../models/nation/nation";
import { Step } from "../../models/step/step";

export interface ReferentialState {
	clubs: Array<Club> | null;
	nations: Array<Nation> | null;
	steps: Array<Step> | null;
	loadedClubs: boolean;
	loadedNations: boolean;
	loadedSteps: boolean;
	loadingClubs: boolean;
	loadingNations: boolean;
	loadingSteps: boolean;
	errorClubs: HttpErrorResponse | null;
	errorNations: HttpErrorResponse | null;
	errorSteps: HttpErrorResponse | null;
}

export const initialReferentialState: ReferentialState = {
	clubs: null,
	nations: null,
	steps: null,
	loadedClubs: false,
	loadedNations: false,
	loadedSteps: false,
	loadingClubs: false,
	loadingNations: false,
	loadingSteps: false,
	errorClubs: null,
	errorNations: null,
	errorSteps: null,
};
