import { AfterViewInit, ChangeDetectorRef, Component, Input } from "@angular/core";
import { BreadcrumbItem } from "../../model/breadcrumb-item";

@Component({
	selector: "app-breadcrumb[items]",
	templateUrl: "./breadcrumb.component.html",
	styleUrls: ["./breadcrumb.component.scss"],
})
export class BreadcrumbComponent implements AfterViewInit {
	@Input()
	items!: Array<BreadcrumbItem>;

	constructor(private readonly cdr: ChangeDetectorRef) {
		// cdr.checkNoChanges();
	}

	ngAfterViewInit(): void {
		this.cdr.detectChanges();
	}
}
