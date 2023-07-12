import { createFeatureSelector, createSelector } from "@ngrx/store";
import { FEATURE_KEYSTORE_REFERENTIAL } from "./referential.reducers";
import { ReferentialState } from "./referential.state";

const referentialSelectors = createFeatureSelector<ReferentialState>(FEATURE_KEYSTORE_REFERENTIAL);

export const selectIsLoadingClubs = createSelector(referentialSelectors, (state: ReferentialState) => state.loadingClubs);
export const selectIsLoadedClubs = createSelector(referentialSelectors, (state: ReferentialState) => state.loadedClubs);
export const selectClubs = createSelector(referentialSelectors, (state: ReferentialState) => state.clubs);
export const selectErrorClubs = createSelector(referentialSelectors, (state: ReferentialState) => state.errorClubs);
export const selectIsLoadingNations = createSelector(referentialSelectors, (state: ReferentialState) => state.loadingNations);
export const selectIsLoadedNations = createSelector(referentialSelectors, (state: ReferentialState) => state.loadedNations);
export const selectNations = createSelector(referentialSelectors, (state: ReferentialState) => state.nations);
export const selectErrorNations = createSelector(referentialSelectors, (state: ReferentialState) => state.errorNations);
export const selectIsLoadingSteps = createSelector(referentialSelectors, (state: ReferentialState) => state.loadingSteps);
export const selectIsLoadedSteps = createSelector(referentialSelectors, (state: ReferentialState) => state.loadedSteps);
export const selectSteps = createSelector(referentialSelectors, (state: ReferentialState) => state.steps);
export const selectErrorSteps = createSelector(referentialSelectors, (state: ReferentialState) => state.errorSteps);
