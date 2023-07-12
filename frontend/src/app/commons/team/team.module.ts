import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { NgbTooltipModule } from "@ng-bootstrap/ng-bootstrap";
import { TranslateModule } from "@ngx-translate/core";
import { TeamComponent } from "./component/team.component";

@NgModule({
	declarations: [TeamComponent],
	imports: [CommonModule, NgbTooltipModule, TranslateModule],
	exports: [TeamComponent],
})
export class TeamModule {}
