import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { environment } from "../environments/environment";
import { ThemeService } from "./commons/theme/service/theme.service";
import { LanguageService } from "./commons/language/service/language.service";
import { Meta, Title } from "@angular/platform-browser";
import { TranslateService } from "@ngx-translate/core";
import { Store } from "@ngrx/store";
import { ReferentialState } from "./store/referential/referential.state";
import { combineLatest, take } from "rxjs";
import {
	selectErrorClubs,
	selectErrorNations,
	selectIsLoadedClubs,
	selectIsLoadedNations,
	selectIsLoadingClubs,
	selectIsLoadingNations,
} from "./store/referential/referential.selectors";
import { initReferential } from "./store/referential/referential.actions";

@Component({
	selector: "app-root",
	templateUrl: "./app.component.html",
	styleUrls: ["./app.component.scss"],
	encapsulation: ViewEncapsulation.None,
})
export class AppComponent implements OnInit {
	isDebugStyle: boolean = environment.debugStyle;
	isLoading: boolean = false;
	hasErrors: boolean = false;
	errors: Set<string> = new Set<string>();

	constructor(
		private readonly themeService: ThemeService,
		private readonly languageService: LanguageService,
		private readonly translateService: TranslateService,
		private readonly metaService: Meta,
		private readonly titleService: Title,
		private readonly storeReferential: Store<ReferentialState>
	) {
		themeService.load();
		languageService.load();
		languageService.currentLanguage.subscribe(() => {
			translateService.get("meta.description").subscribe((value: string) => {
				metaService.updateTag({ name: "description", content: value });
			});
			translateService.get("meta.title").subscribe((value: string) => {
				titleService.setTitle(value);
			});
		});
	}

	ngOnInit(): void {
		this.errors = new Set<string>();
		combineLatest([
			this.storeReferential.select(selectIsLoadingClubs),
			this.storeReferential.select(selectIsLoadedClubs),
			this.storeReferential.select(selectErrorClubs),
			this.storeReferential.select(selectIsLoadingNations),
			this.storeReferential.select(selectIsLoadedNations),
			this.storeReferential.select(selectErrorNations),
		])
			.pipe(take(1))
			.subscribe(([loadingClubs, loadedClubs, errorClubs, loadingNations, loadedNations, errorNations]) => {
				if (!loadingClubs && !loadedClubs && !errorClubs && !loadingNations && !loadedNations && !errorNations) {
					this.isLoading = true;
					this.storeReferential.dispatch(initReferential());
				}
			});
		combineLatest([
			this.storeReferential.select(selectIsLoadingClubs),
			this.storeReferential.select(selectIsLoadedClubs),
			this.storeReferential.select(selectErrorClubs),
			this.storeReferential.select(selectIsLoadingNations),
			this.storeReferential.select(selectIsLoadedNations),
			this.storeReferential.select(selectErrorNations),
		]).subscribe(([loadingClubs, loadedClubs, errorClubs, loadingNations, loadedNations, errorNations]) => {
			const isLoading = !loadingClubs && loadedClubs && !loadingNations && loadedNations;
			const hasErrors = errorClubs !== null && errorNations !== null;
			if (errorClubs !== null) {
				this.errors.add("error.load.club");
			}
			if (errorNations !== null) {
				this.errors.add("error.load.nation");
			}
			if (isLoading || hasErrors) {
				this.isLoading = false;
			}
			if (hasErrors) {
				this.hasErrors = true;
			}
		});
	}
}
