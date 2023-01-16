import { Component } from "@angular/core";
import { appVersion } from "../../../../environments/version";

@Component({
	selector: "app-footer",
	templateUrl: "./footer.component.html",
	styleUrls: ["./footer.component.scss"],
})
export class FooterComponent {
	year: number = new Date().getFullYear();
	version: string = appVersion;
}
