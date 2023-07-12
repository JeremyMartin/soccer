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
	selectErrorSteps,
	selectIsLoadedClubs,
	selectIsLoadedNations,
	selectIsLoadedSteps,
	selectIsLoadingClubs,
	selectIsLoadingNations,
	selectIsLoadingSteps,
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
		(async () => {
			const initialize: AppInit = await this.initializeReferential();
			if (initialize === AppInit.LOADING || initialize === AppInit.ALREADY_LOADED) {
				const check: AppInit = await this.checkReferential();
				if (check === AppInit.ERROR) {
					this.isLoading = false;
					this.hasErrors = true;
				} else {
					this.isLoading = false;
					this.hasErrors = false;
				}
			} else {
				this.isLoading = false;
				this.hasErrors = false;
			}
		})();
	}

	private initializeReferential(): Promise<AppInit> {
		this.isLoading = true;
		return new Promise<AppInit>((resolve) => {
			combineLatest([
				this.storeReferential.select(selectIsLoadingClubs),
				this.storeReferential.select(selectIsLoadedClubs),
				this.storeReferential.select(selectErrorClubs),
				this.storeReferential.select(selectIsLoadingNations),
				this.storeReferential.select(selectIsLoadedNations),
				this.storeReferential.select(selectErrorNations),
				this.storeReferential.select(selectIsLoadingSteps),
				this.storeReferential.select(selectIsLoadedSteps),
				this.storeReferential.select(selectErrorSteps),
			])
				.pipe(take(1))
				.subscribe(
					([loadingClubs, loadedClubs, errorClubs, loadingNations, loadedNations, errorNations, loadingSteps, loadedSteps, errorSteps]) => {
						if (
							!loadingClubs &&
							!loadedClubs &&
							!errorClubs &&
							!loadingNations &&
							!loadedNations &&
							!errorNations &&
							!loadingSteps &&
							!loadedSteps &&
							!errorSteps
						) {
							this.storeReferential.dispatch(initReferential());
							resolve(AppInit.LOADING);
						} else {
							resolve(AppInit.ALREADY_LOADED);
						}
					}
				);
		});
	}

	private checkReferential(): Promise<AppInit> {
		return new Promise<AppInit>((resolve) => {
			combineLatest([
				this.storeReferential.select(selectIsLoadingClubs),
				this.storeReferential.select(selectIsLoadedClubs),
				this.storeReferential.select(selectErrorClubs),
				this.storeReferential.select(selectIsLoadingNations),
				this.storeReferential.select(selectIsLoadedNations),
				this.storeReferential.select(selectErrorNations),
				this.storeReferential.select(selectIsLoadingSteps),
				this.storeReferential.select(selectIsLoadedSteps),
				this.storeReferential.select(selectErrorSteps),
			]).subscribe(
				([loadingClubs, loadedClubs, errorClubs, loadingNations, loadedNations, errorNations, loadingSteps, loadedSteps, errorSteps]) => {
					const isLoaded = !loadingClubs && loadedClubs && !loadingNations && loadedNations && !loadingSteps && loadedSteps;
					const hasErrors = errorClubs !== null && errorNations !== null && errorSteps !== null;
					if (errorClubs !== null) {
						this.errors.add("error.load.club");
					}
					if (errorNations !== null) {
						this.errors.add("error.load.nation");
					}
					if (errorNations !== null) {
						this.errors.add("error.load.step");
					}
					if (isLoaded) {
						resolve(AppInit.SUCCESS);
					}
					if (hasErrors) {
						resolve(AppInit.ERROR);
					}
				}
			);
		});
	}
}

export enum AppInit {
	ALREADY_LOADED,
	ERROR,
	LOADING,
	SUCCESS,
}
