import { Tournament } from "../../../../models/tournament/tournament";

export type SortDirection = "asc" | "desc" | "";

export type SortColumn = keyof Tournament | "";

export interface SortEvent {
	column: SortColumn;
	direction: SortDirection;
}
