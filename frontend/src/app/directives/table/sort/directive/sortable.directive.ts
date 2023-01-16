import { Directive, EventEmitter, Input, Output } from "@angular/core";
import { SortColumn, SortDirection, SortEvent } from "../model/sort-event";

@Directive({
	selector: "th[sortable][column]",
	host: {
		"[class.asc]": "direction === 'asc'",
		"[class.desc]": "direction === 'desc'",
		"(click)": "rotate()",
	},
})
export class SortableDirective {
	@Input() column: SortColumn = "";
	@Input() direction: SortDirection = "";
	@Output() sortEvent = new EventEmitter<SortEvent>();

	private rotation: { [key: string]: SortDirection } = { asc: "desc", desc: "asc", "": "asc" };

	rotate(): void {
		this.direction = this.rotation[this.direction];
		this.sortEvent.emit({ column: this.column, direction: this.direction });
	}
}
