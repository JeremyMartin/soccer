import { Deletable } from "../commons/deletable";
import { Naming } from "../commons/naming";
import { Group } from "../phase/group";
import { Step } from "../step/step";
import { Team } from "../team/team";

export interface Tournament extends Naming, Deletable {
	date?: Date;
	matchType?: number;
	nbGroup?: number;
	teams?: Array<Team>;
	groups?: Array<Group>;
	step?: Step;
}
