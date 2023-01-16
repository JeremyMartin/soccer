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
import { PagingModule } from "../../commons/paging/paging.module";

@NgModule({
	declarations: [ListComponent, AddComponent, ViewComponent],
	imports: [
		BreadcrumbModule,
		CommonModule,
		DatePipeModule,
		FontAwesomeModule,
		PagingModule,
		PopupModule,
		TableDirectiveModule,
		TranslateModule,
		TournamentRoutingModule,
		StickyDirectiveModule,
	],
})
export class TournamentModule {}
