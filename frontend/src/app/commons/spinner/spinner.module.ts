import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { TranslateModule } from "@ngx-translate/core";
import { SpinnerComponent } from "./component/spinner.component";

@NgModule({
	declarations: [SpinnerComponent],
	imports: [CommonModule, TranslateModule],
	exports: [SpinnerComponent],
})
export class SpinnerModule {}
