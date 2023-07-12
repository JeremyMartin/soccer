import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { NgbTooltip } from "@ng-bootstrap/ng-bootstrap";
import { TranslateModule } from "@ngx-translate/core";
import { ButtonOutlineLightOrDarkDirectiveModule } from "../../directives/button/button-outline-light-or-dark/button-outline-light-or-dark-directive.module";
import { InputDirectiveModule } from "../../directives/input/input-directive.module";
import { TeamModule } from "../team/team.module";
import { ConfirmComponent } from "./components/confirm/confirm.component";
import { ScoreComponent } from "./components/score/score.component";

@NgModule({
	declarations: [ConfirmComponent, ScoreComponent],
	imports: [
		CommonModule,
		ButtonOutlineLightOrDarkDirectiveModule,
		InputDirectiveModule,
		ReactiveFormsModule,
		TranslateModule,
		NgbTooltip,
		TeamModule,
	],
	exports: [ConfirmComponent, ScoreComponent],
})
export class PopupModule {}
