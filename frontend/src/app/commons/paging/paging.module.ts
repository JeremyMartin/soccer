import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { PaginationComponent } from "./component/pagination.component";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { TranslateModule } from "@ngx-translate/core";
import { ButtonDirectiveModule } from "../../directives/button/button-directive.module";

@NgModule({
	declarations: [PaginationComponent],
	imports: [CommonModule, FontAwesomeModule, TranslateModule, ButtonDirectiveModule],
	exports: [PaginationComponent],
})
export class PagingModule {}
