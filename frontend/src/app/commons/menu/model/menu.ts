import { MenuAction } from "./menu-action";

export interface Menu {
	id: string;
	name: string;
	action?: MenuAction;
	subMenus?: Array<Menu>;
}
