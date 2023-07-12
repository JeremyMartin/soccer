import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterLink } from "@angular/router";
import { TranslateModule } from "@ngx-translate/core";
import { BreadcrumbComponent } from "./component/breadcrumb/breadcrumb.component";

@NgModule({
	declarations: [BreadcrumbComponent],
	imports: [CommonModule, RouterLink, TranslateModule],
	exports: [BreadcrumbComponent],
})
export class BreadcrumbModule {}
