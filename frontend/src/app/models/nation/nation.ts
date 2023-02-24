import { Translatable } from "../commons/translatable";
import { Club } from "../club/club";

export interface Nation extends Translatable {
	clubs: Array<Club>;
}
