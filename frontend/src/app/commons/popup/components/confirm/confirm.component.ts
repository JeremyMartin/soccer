import { Component, EventEmitter, Input, Output } from "@angular/core";

@Component({
	selector: "app-confirm[id][titleKeyLabel][confirmKeyLabel][eventConfirm]",
	templateUrl: "./confirm.component.html",
	styleUrls: ["./confirm.component.scss"],
})
export class ConfirmComponent {
	@Input()
	id!: string;
	@Input()
	titleKeyLabel!: string;
	@Input()
	confirmKeyLabel!: string;
	@Output()
	eventConfirm: EventEmitter<any> = new EventEmitter<any>();

	onClickConfirm(): void {
		const btnClose: HTMLElement | null = document.getElementById("btnConfirmClose" + this.id);
		if (btnClose) {
			this.eventConfirm.emit(true);
			btnClose.click();
		}
	}
}
