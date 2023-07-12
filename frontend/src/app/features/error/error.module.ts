import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { TranslateModule } from "@ngx-translate/core";
import { NotFoundComponent } from "./component/not-found/not-found.component";
import { ErrorRoutingModule } from "./error-routing.module";

@NgModule({
	declarations: [NotFoundComponent],
	imports: [CommonModule, ErrorRoutingModule, TranslateModule],
})
export class ErrorModule {}
