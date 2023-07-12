import { createReducer, on } from "@ngrx/store";
import {
	initReferential,
	loadClubsFailure,
	loadClubsSuccess,
	loadNationsFailure,
	loadNationsSuccess,
	loadStepsFailure,
	loadStepsSuccess,
} from "./referential.actions";
import { initialReferentialState } from "./referential.state";

export const ReferentialReducers = createReducer(
	initialReferentialState,
	on(initReferential, (state) => ({
		...state,
		loadingClubs: true,
		loadingNations: true,
		loadingSteps: true,
	})),
	on(loadClubsSuccess, (state, { clubs }) => ({
		...state,
		clubs: clubs,
		loadedClubs: true,
		loadingClubs: false,
	})),
	on(loadClubsFailure, (state, { error }) => ({
		...state,
		errorClubs: error,
		loadingClubs: false,
	})),
	on(loadNationsSuccess, (state, { nations }) => ({
		...state,
		nations: nations,
		loadedNations: true,
		loadingNations: false,
	})),
	on(loadNationsFailure, (state, { error }) => ({
		...state,
		errorNations: error,
		loadingNations: false,
	})),
	on(loadStepsSuccess, (state, { steps }) => ({
		...state,
		steps: steps,
		loadedSteps: true,
		loadingSteps: false,
	})),
	on(loadStepsFailure, (state, { error }) => ({
		...state,
		errorSteps: error,
		loadingSteps: false,
	}))
);

export const FEATURE_KEYSTORE_REFERENTIAL = "referential";
