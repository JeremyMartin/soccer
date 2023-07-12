import { HttpErrorResponse } from "@angular/common/http";
import { AfterViewInit, Component, EventEmitter, Input, OnChanges, OnDestroy, Output, SimpleChanges } from "@angular/core";
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { Match } from "../../../../models/match/match";
import { MatchService } from "../../../../services/match.service";
import { numberOnlyValidator } from "../../../../validators/customValidators";
import { Language } from "../../../language/model/language";
import { LanguageService } from "../../../language/service/language.service";
import { LANGUAGE_FR } from "../../../language/utilities/language.utilities";
import { ToastService } from "../../../toast/service/toast.service";
import { HIDE_MODAL } from "../../utils/popup.utilities";

@Component({
	selector: "app-score[match][eventReload][eventCancel]",
	templateUrl: "./score.component.html",
})
export class ScoreComponent implements AfterViewInit, OnChanges, OnDestroy {
	readonly LANGUAGE_FR = LANGUAGE_FR;
	@Input()
	match: Match | undefined;
	@Output()
	eventReload: EventEmitter<any> = new EventEmitter<any>();
	@Output()
	eventCancel: EventEmitter<any> = new EventEmitter<any>();
	form: FormGroup = this.fb.group({
		homeGoals: new FormControl(null, [Validators.required, Validators.min(0), numberOnlyValidator]),
		awayGoals: new FormControl(null, [Validators.required, Validators.min(0), numberOnlyValidator]),
		penalty: new FormControl(false),
		homePenalty: new FormControl(null),
		awayPenalty: new FormControl(null),
		homeYellowCard: new FormControl(0, [Validators.required, Validators.min(0), numberOnlyValidator]),
		homeRedCard: new FormControl(0, [Validators.required, Validators.min(0), numberOnlyValidator]),
		awayYellowCard: new FormControl(0, [Validators.required, Validators.min(0), numberOnlyValidator]),
		awayRedCard: new FormControl(0, [Validators.required, Validators.min(0), numberOnlyValidator]),
	});
	currentMatch?: Match;
	isSubmit: boolean = false;
	currentLanguage!: Language;
	private modal: HTMLElement | null = null;

	constructor(
		private readonly fb: FormBuilder,
		private readonly languageService: LanguageService,
		private readonly matchService: MatchService,
		private readonly toastService: ToastService
	) {
		languageService.currentLanguage.subscribe((language) => (this.currentLanguage = language));
	}

	ngAfterViewInit(): void {
		this.modal = document.getElementById("editScore") as HTMLElement;
		if (this.modal) {
			this.modal.addEventListener(HIDE_MODAL, this.onHideModal);
		}
		this.form.get("homeGoals")?.valueChanges.subscribe((value) => {
			if (this.currentMatch) {
				this.currentMatch.homeGoals = value;
			}
		});
		this.form.get("awayGoals")?.valueChanges.subscribe((value) => {
			if (this.currentMatch) {
				this.currentMatch.awayGoals = value;
			}
		});
		this.form.get("penalty")?.valueChanges.subscribe((value) => {
			if (this.currentMatch) {
				this.currentMatch.penalty = value;
			}
			this.applyPenalty(value, "homePenalty");
			this.applyPenalty(value, "awayPenalty");
		});
		this.form.get("homePenalty")?.valueChanges.subscribe((value) => {
			if (this.currentMatch) {
				this.currentMatch.homePenalty = value;
			}
		});
		this.form.get("awayPenalty")?.valueChanges.subscribe((value) => {
			if (this.currentMatch) {
				this.currentMatch.awayPenalty = value;
			}
		});
		this.form.get("homeYellowCard")?.valueChanges.subscribe((value) => {
			if (this.currentMatch) {
				this.currentMatch.homeYellowCard = value;
			}
		});
		this.form.get("homeRedCard")?.valueChanges.subscribe((value) => {
			if (this.currentMatch) {
				this.currentMatch.homeRedCard = value;
			}
		});
		this.form.get("awayYellowCard")?.valueChanges.subscribe((value) => {
			if (this.currentMatch) {
				this.currentMatch.awayYellowCard = value;
			}
		});
		this.form.get("awayRedCard")?.valueChanges.subscribe((value) => {
			if (this.currentMatch) {
				this.currentMatch.awayRedCard = value;
			}
		});
	}

	ngOnChanges(changes: SimpleChanges): void {
		if (changes && changes["match"]) {
			if (this.match) {
				this.currentMatch = { ...this.match };
				const onPenalty: boolean = this.match.penalty || false;
				this.form.get("homeGoals")?.setValue(this.match.homeGoals);
				this.form.get("homeGoals")?.updateValueAndValidity();
				this.form.get("awayGoals")?.setValue(this.match.awayGoals);
				this.form.get("awayGoals")?.updateValueAndValidity();
				this.form.get("penalty")?.setValue(onPenalty);
				this.form.get("penalty")?.updateValueAndValidity();
				this.form.get("homePenalty")?.setValue(this.match.homePenalty);
				this.form.get("awayPenalty")?.setValue(this.match.awayPenalty);
				this.form.get("homeYellowCard")?.setValue(this.match.homeYellowCard || 0);
				this.form.get("homeRedCard")?.setValue(this.match.homeRedCard || 0);
				this.form.get("awayYellowCard")?.setValue(this.match.awayYellowCard || 0);
				this.form.get("awayRedCard")?.setValue(this.match.awayRedCard || 0);
				this.applyPenalty(onPenalty, "homePenalty");
				this.applyPenalty(onPenalty, "awayPenalty");
			}
		}
	}

	ngOnDestroy(): void {
		if (this.modal) {
			this.modal.removeEventListener(HIDE_MODAL, this.onHideModal);
		}
	}

	isDisable(): boolean {
		return this.isSubmit || this.form.invalid;
	}

	onClickUpdate(): void {
		if (this.currentMatch) {
			this.isSubmit = true;
			this.matchService.update(this.currentMatch).subscribe({
				next: (result: Match) => {
					if (result) {
						this.isSubmit = false;
						this.toastService.showSuccess("success.update.match");
						this.eventReload.emit();
						this.closeModal();
					} else {
						this.isSubmit = false;
						console.warn(result);
						this.toastService.showError("error.update.match");
					}
				},
				error: (err: HttpErrorResponse) => {
					this.isSubmit = false;
					console.error(err);
					this.toastService.showError("error.update.match");
				},
			});
		}
	}

	private onHideModal = () => {
		this.eventCancel.emit();
		setTimeout(() => {
			this.isSubmit = false;
			this.form.reset(
				{
					homeGoals: null,
					awayGoals: null,
					penalty: false,
					homePenalty: null,
					awayPenalty: null,
				},
				{
					emitEvent: true,
				}
			);
		}, 200);
	};

	private applyPenalty(checked: boolean, controlKey: string) {
		if (controlKey) {
			if (checked) {
				this.form.get(controlKey)?.setValidators([Validators.required, Validators.min(0), numberOnlyValidator]);
			} else {
				this.form.get(controlKey)?.setValidators(null);
				this.form.get(controlKey)?.setValue(null);
			}
			this.form.get(controlKey)?.updateValueAndValidity();
		}
	}

	private closeModal(): void {
		const btnClose: HTMLButtonElement | null = document.getElementById("btnEditScoreClose") as HTMLButtonElement;
		if (btnClose) {
			setTimeout(() => {
				btnClose.click();
			}, 200);
		}
	}
}
