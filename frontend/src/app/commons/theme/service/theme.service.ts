import { Injectable, OnDestroy, Renderer2, RendererFactory2 } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import { THEME_DARK, THEME_LIGHT, THEME_OS_DEFAULT } from "../utilities/theme.utilities";

@Injectable({
	providedIn: "root",
})
export class ThemeService implements OnDestroy {
	// Define prefix for clearer and more readable class names in scss files
	private readonly colorSchemePrefix: string = "color-scheme-";
	// Define key for localStorage
	private keyPrefersColor: string = "prefers-theme-color";
	private defaultTheme: string = THEME_LIGHT;
	private renderer!: Renderer2;
	colorScheme: BehaviorSubject<string> = new BehaviorSubject<string>(THEME_LIGHT);
	userPrefersColorScheme: BehaviorSubject<string> = new BehaviorSubject<string>(THEME_OS_DEFAULT);

	constructor(private readonly rendererFactory: RendererFactory2) {
		// Create new renderer from renderFactory, to make it possible to use renderer2 in a service
		this.renderer = rendererFactory.createRenderer(null, null);
	}

	ngOnDestroy(): void {
		this.colorScheme.unsubscribe();
		this.userPrefersColorScheme.unsubscribe();
	}

	load(): void {
		this.getColorScheme();
		// Set body class
		this.colorScheme.subscribe((scheme) => {
			const classList: DOMTokenList = document.body.classList;
			if (classList.contains(this.colorSchemePrefix + THEME_DARK) && scheme !== THEME_DARK) {
				this.renderer.removeClass(document.body, this.colorSchemePrefix + THEME_DARK);
			}
			if (classList.contains(this.colorSchemePrefix + THEME_LIGHT) && scheme !== THEME_LIGHT) {
				this.renderer.removeClass(document.body, this.colorSchemePrefix + THEME_LIGHT);
			}
			if (!classList.contains(this.colorSchemePrefix + THEME_LIGHT) || !classList.contains(this.colorSchemePrefix + THEME_DARK)) {
				this.renderer.addClass(document.body, this.colorSchemePrefix + (scheme === THEME_DARK ? THEME_DARK : THEME_LIGHT));
			}
		});
	}

	update(scheme: string): void {
		if (scheme === THEME_OS_DEFAULT) {
			localStorage.removeItem(this.keyPrefersColor);
			this.detectPrefersColorScheme();
		} else {
			this.setColorScheme(scheme);
		}
	}

	private detectPrefersColorScheme(): void {
		this.userPrefersColorScheme.next(THEME_OS_DEFAULT);
		// Detect if prefers-color-scheme is supported
		if (window.matchMedia("(prefers-color-scheme)").media !== "not all") {
			// Set colorScheme to Dark if prefers-color-scheme is dark. Otherwise, set it to Light.
			const scheme = window.matchMedia("(prefers-color-scheme: dark)").matches ? THEME_DARK : THEME_LIGHT;
			this.defaultTheme = scheme;
			this.colorScheme.next(scheme);
		} else {
			// If the browser does not support prefers-color-scheme, set the default to light.
			this.colorScheme.next(THEME_LIGHT);
		}
	}

	private setColorScheme(scheme: string): void {
		// Save prefers-color-scheme to localStorage
		localStorage.setItem(this.keyPrefersColor, scheme);
		this.colorScheme.next(scheme);
		this.userPrefersColorScheme.next(scheme);
	}

	private getColorScheme(): void {
		// Check if any prefers-color-scheme is stored in localStorage
		const localStorageColorScheme = localStorage.getItem(this.keyPrefersColor);
		if (localStorageColorScheme) {
			this.colorScheme.next(localStorageColorScheme);
			this.userPrefersColorScheme.next(localStorageColorScheme);
		} else {
			// If no prefers-color-scheme is stored in localStorage, try to detect OS default prefers-color-scheme
			this.detectPrefersColorScheme();
		}
	}
}
