import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { TranslateModule } from "@ngx-translate/core";
import { TitleComponent } from "./component/title.component";

@NgModule({
	declarations: [TitleComponent],
	imports: [CommonModule, TranslateModule],
	exports: [TitleComponent],
})
export class TitleModule {}
