import { Identifier } from "../commons/identifier";
import { Player } from "../player/player";
import { Club } from "../club/club";
import { Nation } from "../nation/nation";

export interface Team extends Identifier {
	player: Player;
	club?: Club;
	nation?: Nation;
}
