import { Injectable } from "@angular/core";
import { IconName } from "@fortawesome/fontawesome-svg-core";
import { ToastMessage } from "../model/toast-message";
import { ToastType } from "../model/toast-type.enums";

@Injectable({
	providedIn: "root",
})
export class ToastService {
	toasts: Array<ToastMessage> = [];

	private add(delay: number = 10000, icon: IconName | undefined, message: string, type: ToastType) {
		this.toasts.push({ delay: delay, icon: icon, message: message, type: type });
	}

	clear() {
		this.toasts.splice(0, this.toasts.length);
	}

	remove(toast: any) {
		this.toasts = this.toasts.filter((t) => t !== toast);
	}

	showError(message: string) {
		this.add(10000, "circle-exclamation", message, ToastType.ERROR);
	}

	showInfo(message: string) {
		this.add(10000, "circle-info", message, ToastType.INFO);
	}

	showSuccess(message: string) {
		this.add(10000, "check", message, ToastType.SUCCESS);
	}

	showWarn(message: string) {
		this.add(10000, "triangle-exclamation", message, ToastType.WARN);
	}
}
