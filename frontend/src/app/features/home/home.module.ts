import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { TranslateModule } from "@ngx-translate/core";
import { TitleModule } from "../../commons/title/title.module";
import { HomeComponent } from "./component/home/home.component";
import { HomeRoutingModule } from "./home-routing.module";

@NgModule({
	declarations: [HomeComponent],
	imports: [CommonModule, HomeRoutingModule, TitleModule, TranslateModule],
})
export class HomeModule {}
