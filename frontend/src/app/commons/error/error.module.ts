import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ErrorComponent } from "./component/error.component";
import { TranslateModule } from "@ngx-translate/core";

@NgModule({
	declarations: [ErrorComponent],
	imports: [CommonModule, TranslateModule],
	exports: [ErrorComponent],
})
export class ErrorModule {}
