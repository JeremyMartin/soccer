import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { SpinnerComponent } from "./component/spinner.component";
import { TranslateModule } from "@ngx-translate/core";

@NgModule({
	declarations: [SpinnerComponent],
	imports: [CommonModule, TranslateModule],
	exports: [SpinnerComponent],
})
export class SpinnerModule {}
