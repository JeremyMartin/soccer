import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ConfirmComponent } from "./components/confirm/confirm.component";
import { TranslateModule } from "@ngx-translate/core";
import { ButtonOutlineLightOrDarkDirectiveModule } from "../../directives/button/button-outline-light-or-dark/button-outline-light-or-dark-directive.module";

@NgModule({
	declarations: [ConfirmComponent],
	imports: [CommonModule, TranslateModule, ButtonOutlineLightOrDarkDirectiveModule],
	exports: [ConfirmComponent],
})
export class PopupModule {}
