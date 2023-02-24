import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { IndicatorComponent } from "./components/indicator/indicator.component";
import { TranslateModule } from "@ngx-translate/core";

@NgModule({
	declarations: [IndicatorComponent],
	imports: [CommonModule, TranslateModule],
	exports: [IndicatorComponent],
})
export class FormStepModule {}
