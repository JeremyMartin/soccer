import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { fromEvent } from "rxjs";
import { Menu } from "../model/menu";

@Component({
	selector: "app-menu",
	templateUrl: "./menu.component.html",
	styleUrls: ["./menu.component.scss"],
	encapsulation: ViewEncapsulation.None,
})
export class MenuComponent implements OnInit {
	isExpend: boolean = false;
	menus: Array<Menu> = [
		{ id: "home", name: "home", action: { path: "/" } },
		{ id: "tournament", name: "tournament", action: { path: "/tournament" } },
		{ id: "parameters", name: "parameters", action: { path: "/parameter" } },
	];

	ngOnInit(): void {
		this.updateExpend(window.innerWidth);
		fromEvent(window, "resize").subscribe((event: Event) => {
			this.updateExpend((<Window>event.target).innerWidth);
		});
	}

	private updateExpend(width: number) {
		this.isExpend = !(width < 992);
	}
}
