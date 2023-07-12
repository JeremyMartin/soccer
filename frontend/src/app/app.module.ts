import { registerLocaleData } from "@angular/common";
import { HttpClient, HttpClientModule } from "@angular/common/http";
import localeEn from "@angular/common/locales/en";
import localeFr from "@angular/common/locales/fr";
import { LOCALE_ID, NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { FaIconLibrary } from "@fortawesome/angular-fontawesome";
import {
	faAngleLeft,
	faAngleRight,
	faAnglesLeft,
	faAnglesRight,
	faCheck,
	faChevronDown,
	faChevronRight,
	faCircleExclamation,
	faCircleInfo,
	faEye,
	faPencil,
	faPlus,
	faShuffle,
	faSliders,
	faTrashAlt,
	faTriangleExclamation,
} from "@fortawesome/free-solid-svg-icons";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { EffectsModule } from "@ngrx/effects";
import { StoreModule } from "@ngrx/store";
import { StoreDevtoolsModule } from "@ngrx/store-devtools";
import { TranslateLoader, TranslateModule, TranslateService } from "@ngx-translate/core";
import { TranslateHttpLoader } from "@ngx-translate/http-loader";
import { environment } from "../environments/environment";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { DebugModule } from "./commons/debug/debug.module";
import { ErrorModule } from "./commons/error/error.module";
import { FooterModule } from "./commons/footer/footer.module";
import { MenuModule } from "./commons/menu/menu.module";
import { SpinnerModule } from "./commons/spinner/spinner.module";
import { ToastModule } from "./commons/toast/toast.module";
import { ClubService } from "./services/club.service";
import { MatchService } from "./services/match.service";
import { NationService } from "./services/nation.service";
import { StepService } from "./services/step.service";
import { MetaReducers, Reducers } from "./store/app.reducers";
import { ReferentialEffects } from "./store/referential/referential.effects";
import { FEATURE_KEYSTORE_REFERENTIAL, ReferentialReducers } from "./store/referential/referential.reducers";

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
		NgbModule,
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
		ToastModule,
	],
	providers: [
		{
			provide: LOCALE_ID,
			useValue: "en",
		},
		ClubService,
		MatchService,
		NationService,
		TranslateService,
		StepService,
	],
	bootstrap: [AppComponent],
})
export class AppModule {
	constructor(private faIconLibrary: FaIconLibrary) {
		faIconLibrary.addIcons(
			faAngleLeft,
			faAnglesLeft,
			faAngleRight,
			faAnglesRight,
			faCheck,
			faCircleInfo,
			faCircleExclamation,
			faChevronRight,
			faChevronDown,
			faEye,
			faPencil,
			faPlus,
			faShuffle,
			faSliders,
			faTrashAlt,
			faTriangleExclamation
		);
	}
}
