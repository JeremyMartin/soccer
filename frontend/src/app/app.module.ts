import { LOCALE_ID, NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { TranslateHttpLoader } from "@ngx-translate/http-loader";
import { registerLocaleData } from "@angular/common";
import localeEn from "@angular/common/locales/en";
import localeFr from "@angular/common/locales/fr";
import { HttpClient, HttpClientModule } from "@angular/common/http";
import { TranslateLoader, TranslateModule, TranslateService } from "@ngx-translate/core";
import { FaIconLibrary, FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import {
	faAngleLeft,
	faAnglesLeft,
	faChevronRight,
	faEye,
	faPlus,
	faTrashAlt,
	faAngleRight,
	faAnglesRight,
} from "@fortawesome/free-solid-svg-icons";
import { MenuModule } from "./commons/menu/menu.module";
import { DebugModule } from "./commons/debug/debug.module";
import { FooterModule } from "./commons/footer/footer.module";

// Register locale
registerLocaleData(localeEn);
registerLocaleData(localeFr);

// AoT requires an exported function for factories
export function HttpLoaderFactory(httpClient: HttpClient) {
	return new TranslateHttpLoader(httpClient, "./assets/i18n/", ".json");
}

@NgModule({
	declarations: [AppComponent],
	imports: [
		BrowserModule,
		AppRoutingModule,
		HttpClientModule,
		TranslateModule.forRoot({
			// defaultLanguage: /^fr\b/.test(navigator.language) ? "fr" : "en",
			loader: { provide: TranslateLoader, useFactory: HttpLoaderFactory, deps: [HttpClient] },
		}),
		FontAwesomeModule,
		MenuModule,
		DebugModule,
		FooterModule,
	],
	providers: [
		{
			provide: LOCALE_ID,
			useValue: "en",
		},
		TranslateService,
	],
	bootstrap: [AppComponent],
})
export class AppModule {
	constructor(private faIconLibrary: FaIconLibrary) {
		faIconLibrary.addIcons(faChevronRight, faPlus, faEye, faTrashAlt, faAngleLeft, faAnglesLeft, faAngleRight, faAnglesRight);
	}
}
