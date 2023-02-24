import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { TournamentRoutingModule } from "./tournament-routing.module";
import { ListComponent } from "./components/tournament/list/list.component";
import { TranslateModule } from "@ngx-translate/core";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { PopupModule } from "../../commons/popup/popup.module";
import { BreadcrumbModule } from "../../commons/breadcrumb/breadcrumb.module";
import { AddComponent } from "./components/tournament/add/add.component";
import { ViewComponent } from "./components/tournament/view/view.component";
import { DatePipeModule } from "../../pipes/date/date-pipe.module";
import { TableDirectiveModule } from "../../directives/table/table-directive.module";
import { StickyDirectiveModule } from "../../directives/sticky/sticky-directive.module";
import { PaginationModule } from "../../commons/pagination/pagination.module";
import { SmallDirectiveModule } from "../../directives/small/small-directive.module";
import { ButtonSmallDirectiveModule } from "../../directives/small/button/button-small-directive.module";
import { FormStepModule } from "../../commons/form-step/form-step.module";
import { ButtonOutlineLightOrDarkDirectiveModule } from "../../directives/button/button-outline-light-or-dark/button-outline-light-or-dark-directive.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { SpinnerModule } from "../../commons/spinner/spinner.module";
import { NgbTypeaheadModule } from "@ng-bootstrap/ng-bootstrap";

@NgModule({
	declarations: [ListComponent, AddComponent, ViewComponent],
	imports: [
		BreadcrumbModule,
		CommonModule,
		ButtonOutlineLightOrDarkDirectiveModule,
		ButtonSmallDirectiveModule,
		DatePipeModule,
		FontAwesomeModule,
		FormsModule,
		FormStepModule,
		NgbTypeaheadModule,
		PaginationModule,
		PopupModule,
		ReactiveFormsModule,
		SmallDirectiveModule,
		StickyDirectiveModule,
		SpinnerModule,
		TableDirectiveModule,
		TranslateModule,
		TournamentRoutingModule,
	],
})
export class TournamentModule {}
