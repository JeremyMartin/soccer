import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ActiveDirectiveModule } from "./active/active-directive.module";
import { ButtonDirectiveModule } from "./button/button-directive.module";
import { SmallDirectiveModule } from "./small/small-directive.module";
import { StickyDirectiveModule } from "./sticky/sticky-directive.module";
import { TableDirectiveModule } from "./table/table-directive.module";

@NgModule({
	declarations: [],
	imports: [CommonModule, ActiveDirectiveModule, ButtonDirectiveModule, SmallDirectiveModule, StickyDirectiveModule, TableDirectiveModule],
	exports: [],
})
export class DirectiveModule {}
