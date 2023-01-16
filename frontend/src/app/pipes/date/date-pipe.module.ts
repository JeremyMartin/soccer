import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { LocalizeDatePipe } from "./pipe/localize-date.pipe";

@NgModule({
	declarations: [LocalizeDatePipe],
	imports: [CommonModule],
	exports: [LocalizeDatePipe],
})
export class DatePipeModule {}
