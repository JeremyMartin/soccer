import { Team } from "../team/team";

export interface Ranking {
	rank: number;
	team: Team;
	play: number;
	win: number;
	draw: number;
	lose: number;
	diff: number;
	diffPenalty: number;
	points: number;
}
