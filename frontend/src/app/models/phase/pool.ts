import { Naming } from "../commons/naming";
import { Team } from "../team/team";
import { Match } from "../match/match";

export interface Pool extends Naming {
	teams: Set<Team>;
	matchs: Set<Match>;
}
