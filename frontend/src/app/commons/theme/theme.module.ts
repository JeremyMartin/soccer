import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { TranslateModule } from "@ngx-translate/core";
import { ButtonOutlineLightOrDarkDirectiveModule } from "../../directives/button/button-outline-light-or-dark/button-outline-light-or-dark-directive.module";
import { IconDarkComponent } from "./components/icons/icon-dark/icon-dark.component";
import { IconLightComponent } from "./components/icons/icon-light/icon-light.component";
import { IconOsDefaultComponent } from "./components/icons/icon-os-default/icon-os-default.component";
import { ThemeComponent } from "./components/theme/theme.component";

@NgModule({
	declarations: [ThemeComponent, IconDarkComponent, IconLightComponent, IconOsDefaultComponent],
	imports: [CommonModule, TranslateModule, ButtonOutlineLightOrDarkDirectiveModule],
	exports: [ThemeComponent],
})
export class ThemeModule {}
