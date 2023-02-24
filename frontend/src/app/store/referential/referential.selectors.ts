import { createFeatureSelector, createSelector } from "@ngrx/store";
import { ReferentialState } from "./referential.state";
import { FEATURE_KEYSTORE_REFERENTIAL } from "./referential.reducers";

const referentialSelectors = createFeatureSelector<ReferentialState>(FEATURE_KEYSTORE_REFERENTIAL);

export const selectIsLoadingClubs = createSelector(referentialSelectors, (state: ReferentialState) => state.loadingClubs);
export const selectIsLoadedClubs = createSelector(referentialSelectors, (state: ReferentialState) => state.loadedClubs);
export const selectClubs = createSelector(referentialSelectors, (state: ReferentialState) => state.clubs);
export const selectErrorClubs = createSelector(referentialSelectors, (state: ReferentialState) => state.errorClubs);
export const selectIsLoadingNations = createSelector(referentialSelectors, (state: ReferentialState) => state.loadingNations);
export const selectIsLoadedNations = createSelector(referentialSelectors, (state: ReferentialState) => state.loadedNations);
export const selectNations = createSelector(referentialSelectors, (state: ReferentialState) => state.nations);
export const selectErrorNations = createSelector(referentialSelectors, (state: ReferentialState) => state.errorNations);
