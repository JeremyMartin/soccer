import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { PaginationButtonComponent } from "./components/pagination-button/pagination-button.component";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { TranslateModule } from "@ngx-translate/core";
import { ButtonDirectiveModule } from "../../directives/button/button-directive.module";
import { ButtonSmallDirectiveModule } from "../../directives/small/button/button-small-directive.module";
import { FormsModule } from "@angular/forms";
import { SelectSmallDirectiveModule } from "../../directives/small/select/select-small-directive.module";
import { PaginationListComponent } from "./components/pagination-list/pagination-list.component";
import { PaginationSmallDirectiveModule } from "../../directives/small/pagination/pagination-small-directive.module";

@NgModule({
	declarations: [PaginationButtonComponent, PaginationListComponent],
	imports: [
		CommonModule,
		FontAwesomeModule,
		TranslateModule,
		ButtonDirectiveModule,
		ButtonSmallDirectiveModule,
		FormsModule,
		PaginationSmallDirectiveModule,
		SelectSmallDirectiveModule,
	],
	exports: [PaginationButtonComponent, PaginationListComponent],
})
export class PaginationModule {}
