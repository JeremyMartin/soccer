import { AfterViewChecked, Directive, ElementRef, Input, Renderer2 } from "@angular/core";
import { NavigationEnd, Router } from "@angular/router";

@Directive({
	selector: "[active-menu][path]",
})
export class MenuActiveDirective implements AfterViewChecked {
	private currentPath: string;

	@Input()
	path: string | undefined;

	constructor(private readonly router: Router, private readonly elementRef: ElementRef, private renderer: Renderer2) {
		this.currentPath = window.location.pathname;
		this.router.events.subscribe((event: any) => {
			if (event instanceof NavigationEnd) {
				this.makeCurrentPath(this.router.url);
			}
		});
	}

	ngAfterViewChecked(): void {
		if (this.path && this.currentPath === this.path) {
			this.elementRef.nativeElement.classList.add("active");
			this.renderer.setAttribute(this.elementRef.nativeElement, "aria-current", "page");
		} else {
			this.elementRef.nativeElement.classList.remove("active");
			this.renderer.removeAttribute(this.elementRef.nativeElement, "aria-current");
		}
	}

	private makeCurrentPath(pathUrl: string): void {
		if (pathUrl.includes("tournament")) {
			this.currentPath = "/tournament";
		} else if (pathUrl.includes("parameter")) {
			this.currentPath = "/parameter";
		} else {
			this.currentPath = "/";
		}
	}
}
