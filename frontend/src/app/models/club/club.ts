import { Naming } from "../commons/naming";
import { Nation } from "../nation/nation";

export interface Club extends Naming {
	shortName: string;
	nation: Nation;
}
