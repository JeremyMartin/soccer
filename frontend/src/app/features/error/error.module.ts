import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { ErrorRoutingModule } from "./error-routing.module";
import { TranslateModule } from "@ngx-translate/core";
import { NotFoundComponent } from "./component/not-found/not-found.component";

@NgModule({
	declarations: [NotFoundComponent],
	imports: [CommonModule, ErrorRoutingModule, TranslateModule],
})
export class ErrorModule {}
