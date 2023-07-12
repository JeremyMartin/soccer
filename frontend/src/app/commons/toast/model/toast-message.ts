import { IconName } from "@fortawesome/fontawesome-svg-core";
import { ToastType } from "./toast-type.enums";

export interface ToastMessage {
	delay: number;
	icon?: IconName;
	message: string;
	type: ToastType;
}
