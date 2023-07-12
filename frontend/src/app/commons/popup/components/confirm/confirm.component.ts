import { Component, EventEmitter, Input, Output } from "@angular/core";

@Component({
	selector: "app-confirm[id][titleKeyLabel][confirmKeyLabel][eventConfirm]",
	templateUrl: "./confirm.component.html",
})
export class ConfirmComponent {
	@Input()
	id!: string;
	@Input()
	titleKeyLabel!: string;
	@Input()
	confirmKeyLabel!: string;
	@Input()
	confirmClass?: string;
	@Output()
	eventConfirm: EventEmitter<any> = new EventEmitter<any>();

	// private onHideModal = () => {
	// 	setTimeout(() => {
	// 		console.log("onHideModal");
	// 	}, 200);
	// };
	//
	// ngAfterViewInit(): void {
	// 	const modal: HTMLElement | null = document.getElementById(`confirm${this.id}`) as HTMLElement;
	// 	if (modal) {
	// 		modal.addEventListener(HIDE_MODAL, this.onHideModal);
	// 	}
	// }
	//
	// ngOnDestroy(): void {
	// 	const modal: HTMLElement | null = document.getElementById(`confirm${this.id}`) as HTMLElement;
	// 	if (modal) {
	// 		modal.removeEventListener(HIDE_MODAL, this.onHideModal);
	// 	}
	// }

	onClickConfirm(): void {
		this.eventConfirm.emit(true);
	}
}
