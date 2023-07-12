import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { NgbToastModule } from "@ng-bootstrap/ng-bootstrap";
import { TranslateModule } from "@ngx-translate/core";
import { ToastComponent } from "./component/toast.component";

@NgModule({
	declarations: [ToastComponent],
	imports: [CommonModule, FontAwesomeModule, NgbToastModule, TranslateModule],
	exports: [ToastComponent],
})
export class ToastModule {}
