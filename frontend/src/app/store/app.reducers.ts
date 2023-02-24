import { ActionReducer, ActionReducerMap, MetaReducer } from "@ngrx/store";
import { AppState } from "./app.state";
import { ReferentialReducers } from "./referential/referential.reducers";
import { environment } from "../../environments/environment";

export function logger(reducer: ActionReducer<AppState>): ActionReducer<AppState> {
	return (state, action) => {
		const nextState = reducer(state, action);
		console.groupCollapsed(action.type);
		console.log(`%c prev state`, `color: #9E9E9E; font-weight: bold`, state);
		console.log(`%c action`, `color: #03A9F4; font-weight: bold`, action);
		console.log(`%c next state`, `color: #4CAF50; font-weight: bold`, nextState);
		console.groupEnd();
		return nextState;
	};
}

export const Reducers: ActionReducerMap<AppState> = {
	referential: ReferentialReducers,
};

export const MetaReducers: MetaReducer<AppState>[] = environment.production ? [] : [logger];
