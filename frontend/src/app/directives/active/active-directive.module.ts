import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { MenuActiveDirective } from "./directive/menu-active.directive";

@NgModule({
	declarations: [MenuActiveDirective],
	imports: [CommonModule],
	exports: [MenuActiveDirective],
})
export class ActiveDirectiveModule {}
