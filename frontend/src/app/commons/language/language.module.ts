import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { TranslateModule } from "@ngx-translate/core";
import { ButtonOutlineLightOrDarkDirectiveModule } from "../../directives/button/button-outline-light-or-dark/button-outline-light-or-dark-directive.module";
import { IconFlagEnComponent } from "./components/icons/icon-flag-en/icon-flag-en.component";
import { IconFlagFrComponent } from "./components/icons/icon-flag-fr/icon-flag-fr.component";
import { LanguageComponent } from "./components/language/language.component";

@NgModule({
	declarations: [LanguageComponent, IconFlagEnComponent, IconFlagFrComponent],
	imports: [CommonModule, TranslateModule, ButtonOutlineLightOrDarkDirectiveModule],
	exports: [LanguageComponent],
})
export class LanguageModule {}
