import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { MenuActiveDirective } from "./directive/menu-active.directive";

@NgModule({
	declarations: [MenuActiveDirective],
	imports: [CommonModule],
	exports: [MenuActiveDirective],
})
export class ActiveDirectiveModule {}
