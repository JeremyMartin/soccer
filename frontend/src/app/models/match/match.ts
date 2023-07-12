import { Team } from "../team/team";
import { Identifier } from "../commons/identifier";
import { Step } from "../step/step";

export interface Match extends Identifier {
	home: Team;
	away: Team;
	step: Step;
	homeGoals?: number;
	awayGoals?: number;
	penalty?: boolean;
	homePenalty?: number;
	awayPenalty?: number;
	homeYellowCard?: number;
	homeRedCard?: number;
	awayYellowCard?: number;
	awayRedCard?: number;
	played?: boolean;
}
