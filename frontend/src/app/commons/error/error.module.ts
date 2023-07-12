import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { TranslateModule } from "@ngx-translate/core";
import { ErrorComponent } from "./component/error.component";

@NgModule({
	declarations: [ErrorComponent],
	imports: [CommonModule, TranslateModule],
	exports: [ErrorComponent],
})
export class ErrorModule {}
