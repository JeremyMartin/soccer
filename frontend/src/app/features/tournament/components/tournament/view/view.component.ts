import { Component } from "@angular/core";
import { BreadcrumbItem } from "../../../../../commons/breadcrumb/model/breadcrumb-item";

@Component({
	selector: "app-view",
	templateUrl: "./view.component.html",
	styleUrls: ["./view.component.scss"],
})
export class ViewComponent {
	breadcrumbItems: Array<BreadcrumbItem> = [
		{ name: "menu.home", action: { path: "/" } },
		{ name: "menu.tournament", action: { path: "/tournament" } },
		{ name: "label.breadcrumb.view" },
	];
}
