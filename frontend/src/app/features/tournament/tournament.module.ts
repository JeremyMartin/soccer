import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { NgbNav, NgbNavContent, NgbNavItem, NgbNavLink, NgbNavOutlet, NgbTooltip, NgbTypeaheadModule } from "@ng-bootstrap/ng-bootstrap";
import { TranslateModule } from "@ngx-translate/core";
import { BreadcrumbModule } from "../../commons/breadcrumb/breadcrumb.module";
import { ErrorModule } from "../../commons/error/error.module";
import { FormStepModule } from "../../commons/form-step/form-step.module";
import { PaginationModule } from "../../commons/pagination/pagination.module";
import { PopupModule } from "../../commons/popup/popup.module";
import { SpinnerModule } from "../../commons/spinner/spinner.module";
import { TeamModule } from "../../commons/team/team.module";
import { TitleModule } from "../../commons/title/title.module";
import { ButtonOutlineLightOrDarkDirectiveModule } from "../../directives/button/button-outline-light-or-dark/button-outline-light-or-dark-directive.module";
import { ButtonSmallDirectiveModule } from "../../directives/small/button/button-small-directive.module";
import { SmallDirectiveModule } from "../../directives/small/small-directive.module";
import { TableSmallDirectiveModule } from "../../directives/small/table/table-small-directive.module";
import { StickyDirectiveModule } from "../../directives/sticky/sticky-directive.module";
import { TableDirectiveModule } from "../../directives/table/table-directive.module";
import { DatePipeModule } from "../../pipes/date/date-pipe.module";
import { AddComponent } from "./components/tournament/add/add.component";
import { ListComponent } from "./components/tournament/list/list.component";
import { ViewComponent } from "./components/tournament/view/view.component";
import { TournamentRoutingModule } from "./tournament-routing.module";

@NgModule({
	declarations: [ListComponent, AddComponent, ViewComponent],
	imports: [
		BreadcrumbModule,
		CommonModule,
		ButtonOutlineLightOrDarkDirectiveModule,
		ButtonSmallDirectiveModule,
		DatePipeModule,
		ErrorModule,
		FontAwesomeModule,
		FormsModule,
		FormStepModule,
		NgbTypeaheadModule,
		NgbTooltip,
		PaginationModule,
		PopupModule,
		ReactiveFormsModule,
		SmallDirectiveModule,
		StickyDirectiveModule,
		SpinnerModule,
		TableDirectiveModule,
		TableSmallDirectiveModule,
		TeamModule,
		TitleModule,
		TranslateModule,
		TournamentRoutingModule,
		NgbNav,
		NgbNavItem,
		NgbNavLink,
		NgbNavContent,
		NgbNavOutlet,
	],
})
export class TournamentModule {}
