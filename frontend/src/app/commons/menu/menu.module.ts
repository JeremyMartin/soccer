import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { MenuComponent } from "./component/menu.component";
import { TranslateModule } from "@ngx-translate/core";
import { RouterLink } from "@angular/router";
import { ActiveDirectiveModule } from "../../directives/active/active-directive.module";
import { LanguageModule } from "../language/language.module";
import { ThemeModule } from "../theme/theme.module";

@NgModule({
	declarations: [MenuComponent],
	imports: [CommonModule, TranslateModule, RouterLink, ActiveDirectiveModule, LanguageModule, ThemeModule],
	exports: [MenuComponent],
})
export class MenuModule {}
