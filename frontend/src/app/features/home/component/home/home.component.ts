import { Component } from "@angular/core";
import { ToastService } from "../../../../commons/toast/service/toast.service";

@Component({
	selector: "app-home",
	templateUrl: "./home.component.html",
	styleUrls: ["./home.component.scss"],
})
export class HomeComponent {
	readonly title: string = "page.home.default";
}
