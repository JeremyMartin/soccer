import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { StickyDirectiveModule } from "./sticky/sticky-directive.module";
import { ActiveDirectiveModule } from "./active/active-directive.module";
import { ButtonDirectiveModule } from "./button/button-directive.module";
import { TableDirectiveModule } from "./table/table-directive.module";

@NgModule({
	declarations: [],
	imports: [CommonModule, ActiveDirectiveModule, ButtonDirectiveModule, StickyDirectiveModule, TableDirectiveModule],
	exports: [],
})
export class DirectiveModule {}
