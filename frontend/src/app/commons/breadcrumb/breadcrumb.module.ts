import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { BreadcrumbComponent } from "./component/breadcrumb/breadcrumb.component";
import { RouterLink } from "@angular/router";
import { TranslateModule } from "@ngx-translate/core";

@NgModule({
	declarations: [BreadcrumbComponent],
	imports: [CommonModule, RouterLink, TranslateModule],
	exports: [BreadcrumbComponent],
})
export class BreadcrumbModule {}
