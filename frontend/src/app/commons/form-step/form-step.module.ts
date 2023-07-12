import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { TranslateModule } from "@ngx-translate/core";
import { IndicatorComponent } from "./components/indicator/indicator.component";

@NgModule({
	declarations: [IndicatorComponent],
	imports: [CommonModule, TranslateModule],
	exports: [IndicatorComponent],
})
export class FormStepModule {}
