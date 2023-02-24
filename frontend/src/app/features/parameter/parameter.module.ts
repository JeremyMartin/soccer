import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { ParameterRoutingModule } from "./parameter-routing.module";
import { ParameterComponent } from "./component/parameter/parameter.component";
import { TranslateModule } from "@ngx-translate/core";
import { LanguageModule } from "../../commons/language/language.module";
import { ThemeModule } from "../../commons/theme/theme.module";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { ButtonOutlineLightOrDarkDirectiveModule } from "../../directives/button/button-outline-light-or-dark/button-outline-light-or-dark-directive.module";

@NgModule({
	declarations: [ParameterComponent],
	imports: [
		CommonModule,
		ButtonOutlineLightOrDarkDirectiveModule,
		FontAwesomeModule,
		LanguageModule,
		ParameterRoutingModule,
		ThemeModule,
		TranslateModule,
	],
})
export class ParameterModule {}
