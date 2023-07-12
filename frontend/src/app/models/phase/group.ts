import { Naming } from "../commons/naming";
import { Ranking } from "../ranking/ranking";
import { Team } from "../team/team";
import { Match } from "../match/match";

export interface Group extends Naming {
	teams: Set<Team>;
	matchs: Set<Match>;
	rankings: Array<Ranking>;
}
