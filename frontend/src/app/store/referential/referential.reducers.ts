import { createReducer, on } from "@ngrx/store";
import { initialReferentialState } from "./referential.state";
import { initReferential, loadClubsFailure, loadClubsSuccess, loadNationsFailure, loadNationsSuccess } from "./referential.actions";

export const ReferentialReducers = createReducer(
	initialReferentialState,
	on(initReferential, (state) => ({
		...state,
		loadingClubs: true,
		loadingNations: true,
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
	}))
);

export const FEATURE_KEYSTORE_REFERENTIAL = "referential";
