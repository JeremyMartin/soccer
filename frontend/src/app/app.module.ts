import { LOCALE_ID, NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { TranslateHttpLoader } from "@ngx-translate/http-loader";
import { registerLocaleData } from "@angular/common";
import localeEn from "@angular/common/locales/en";
import localeFr from "@angular/common/locales/fr";
import { HttpClient, HttpClientModule } from "@angular/common/http";
import { TranslateLoader, TranslateModule, TranslateService } from "@ngx-translate/core";
import { FaIconLibrary } from "@fortawesome/angular-fontawesome";
import {
	faAngleLeft,
	faAngleRight,
	faAnglesLeft,
	faAnglesRight,
	faChevronRight,
	faEye,
	faPlus,
	faShuffle,
	faTrashAlt,
} from "@fortawesome/free-solid-svg-icons";
import { StoreModule } from "@ngrx/store";
import { EffectsModule } from "@ngrx/effects";
import { StoreDevtoolsModule } from "@ngrx/store-devtools";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { MenuModule } from "./commons/menu/menu.module";
import { DebugModule } from "./commons/debug/debug.module";
import { FooterModule } from "./commons/footer/footer.module";
import { NationService } from "./services/nation.service";
import { ClubService } from "./services/club.service";
import { MetaReducers, Reducers } from "./store/app.reducers";
import { environment } from "../environments/environment";
import { SpinnerModule } from "./commons/spinner/spinner.module";
import { FEATURE_KEYSTORE_REFERENTIAL, ReferentialReducers } from "./store/referential/referential.reducers";
import { ReferentialEffects } from "./store/referential/referential.effects";
import { ErrorModule } from "./commons/error/error.module";

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
		StoreModule.forRoot(Reducers, { metaReducers: MetaReducers }),
		StoreModule.forFeature(FEATURE_KEYSTORE_REFERENTIAL, ReferentialReducers),
		environment.production ? [] : StoreDevtoolsModule.instrument({ name: "soccer tournament" }),
		EffectsModule.forRoot([ReferentialEffects]),
		TranslateModule.forRoot({
			// defaultLanguage: /^fr\b/.test(navigator.language) ? "fr" : "en",
			loader: { provide: TranslateLoader, useFactory: HttpLoaderFactory, deps: [HttpClient] },
		}),
		DebugModule,
		ErrorModule,
		FooterModule,
		MenuModule,
		SpinnerModule,
	],
	providers: [
		{
			provide: LOCALE_ID,
			useValue: "en",
		},
		ClubService,
		NationService,
		TranslateService,
	],
	bootstrap: [AppComponent],
})
export class AppModule {
	constructor(private faIconLibrary: FaIconLibrary) {
		faIconLibrary.addIcons(faChevronRight, faPlus, faEye, faTrashAlt, faAngleLeft, faAnglesLeft, faAngleRight, faAnglesRight, faShuffle);
	}
}
