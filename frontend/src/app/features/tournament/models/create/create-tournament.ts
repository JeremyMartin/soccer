import { Naming } from "../../../../models/commons/naming";
import { Player } from "../../../../models/player/player";

export interface CreateTournament extends Naming {
	players: Set<Player>;
}
