import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { TranslateModule } from "@ngx-translate/core";
import { LanguageModule } from "../../commons/language/language.module";
import { ThemeModule } from "../../commons/theme/theme.module";
import { TitleModule } from "../../commons/title/title.module";
import { ButtonOutlineLightOrDarkDirectiveModule } from "../../directives/button/button-outline-light-or-dark/button-outline-light-or-dark-directive.module";
import { ParameterComponent } from "./component/parameter/parameter.component";
import { ParameterRoutingModule } from "./parameter-routing.module";

@NgModule({
	declarations: [ParameterComponent],
	imports: [
		ButtonOutlineLightOrDarkDirectiveModule,
		CommonModule,
		FontAwesomeModule,
		LanguageModule,
		ParameterRoutingModule,
		ThemeModule,
		TitleModule,
		TranslateModule,
	],
})
export class ParameterModule {}
