import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { TranslateModule } from "@ngx-translate/core";
import { ThemeComponent } from "./components/theme/theme.component";
import { IconDarkComponent } from "./components/icons/icon-dark/icon-dark.component";
import { IconLightComponent } from "./components/icons/icon-light/icon-light.component";
import { IconOsDefaultComponent } from "./components/icons/icon-os-default/icon-os-default.component";
import { ButtonOutlineLightOrDarkDirectiveModule } from "../../directives/button/button-outline-light-or-dark/button-outline-light-or-dark-directive.module";

@NgModule({
	declarations: [ThemeComponent, IconDarkComponent, IconLightComponent, IconOsDefaultComponent],
	imports: [CommonModule, TranslateModule, ButtonOutlineLightOrDarkDirectiveModule],
	exports: [ThemeComponent],
})
export class ThemeModule {}
