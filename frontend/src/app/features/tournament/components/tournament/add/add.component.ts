import { AfterViewInit, Component, ElementRef, OnInit, ViewChild, ViewEncapsulation } from "@angular/core";
import { FormStep } from "../../../../../commons/form-step/model/form-step";
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from "@angular/forms";
import { notEmptyValidator } from "../../../../../validators/notEmptyValidator";
import { compareFn, generateArrayNumber, normalizeText } from "../../../../../commons/utils/fn-utilities";
import { Club } from "../../../../../models/club/club";
import { Nation } from "../../../../../models/nation/nation";
import { calculateNbPoolByNbTeams } from "../../../utilities/tournament.utilities";
import { Store } from "@ngrx/store";
import { ReferentialState } from "../../../../../store/referential/referential.state";
import { combineLatest, debounceTime, distinctUntilChanged, map, Observable, OperatorFunction } from "rxjs";
import { selectClubs, selectNations } from "../../../../../store/referential/referential.selectors";
import { LanguageService } from "../../../../../commons/language/service/language.service";
import { Language } from "../../../../../commons/language/model/language";
import { LANGUAGE_FR } from "../../../../../commons/language/utilities/language.utilities";
import { NgbTypeaheadSelectItemEvent } from "@ng-bootstrap/ng-bootstrap/typeahead/typeahead";
import { BreadcrumbItem } from "../../../../../commons/breadcrumb/model/breadcrumb-item";
import { INVALID, VALID } from "../../../../../commons/utils/css-utilities";
import { Team } from "../../../../../models/team/team";
import { Player } from "../../../../../models/player/player";

@Component({
	selector: "app-add",
	templateUrl: "./add.component.html",
	styleUrls: ["./add.component.scss"],
	encapsulation: ViewEncapsulation.None,
})
export class AddComponent implements OnInit, AfterViewInit {
	readonly LANGUAGE_FR = LANGUAGE_FR;
	isLoading: boolean = true;
	formSteps: Array<FormStep> = [{ label: "label.configuration" }, { label: "label.players" }, { label: "label.teams" }];
	currentFormStep: number = 1;
	groups: Array<number> = [1];
	form: FormGroup = this.fb.group({
		configuration: this.fb.group({
			nbPlayers: new FormControl(2, [Validators.required, Validators.min(2)]),
			typeMatch: new FormControl("1", [Validators.required]),
			nbGroups: new FormControl(null, [Validators.required]),
			typeTeam: new FormControl("club", [Validators.required]),
		}),
		players: this.fb.array([], [Validators.required, Validators.min(2)]),
		teams: this.fb.group({
			random: new FormControl(false),
			teams: this.fb.array([], [Validators.required, Validators.min(2)]),
		}),
	});
	players: FormArray = this.form.get("players") as FormArray;
	teams: FormArray = this.form.get("teams")?.get("teams") as FormArray;
	allClubs: Array<Club> = [];
	selectedClubs: Array<Club> = [];
	allNations: Array<Nation> = [];
	selectedNations: Array<Nation> = [];
	currentLanguage!: Language;
	@ViewChild("preferred")
	preferred!: ElementRef;
	breadcrumbItems: Array<BreadcrumbItem> = [
		{ name: "menu.home", action: { path: "/" } },
		{ name: "menu.tournament", action: { path: "/tournament" } },
		{ name: "label.breadcrumb.add" },
	];
	teamsClub: Array<Club> = [];
	teamsNation: Array<Nation> = [];
	isSubmit: boolean = false;

	constructor(
		private readonly fb: FormBuilder,
		private readonly storeReferential: Store<ReferentialState>,
		private readonly languageService: LanguageService
	) {
		languageService.currentLanguage.subscribe((language) => (this.currentLanguage = language));
	}

	ngOnInit(): void {
		combineLatest([this.storeReferential.select(selectNations), this.storeReferential.select(selectClubs)]).subscribe({
			next: ([nations, clubs]) => {
				if (nations && clubs) {
					this.allNations = nations;
					this.allClubs = clubs;
					this.isLoading = false;
					this.setAutoFocus();
				}
			},
		});
	}

	private applyPlayersAndTeams(nb: number) {
		for (let i = 0; i < nb; i++) {
			const controlPlayer: FormControl = new FormControl(null, [Validators.required, notEmptyValidator]);
			const controlTeam: FormGroup = new FormGroup({
				name: new FormControl(null),
				clubOrNation: new FormControl(null, [this.ValidatorClubOrNation()]),
			});
			controlPlayer.valueChanges.subscribe((value) => {
				controlTeam.get("name")?.setValue(value);
			});
			this.players.push(controlPlayer);
			this.teams.push(controlTeam);
		}
	}

	private ValidatorClubOrNation(): ValidatorFn {
		return (control: AbstractControl): ValidationErrors | null => {
			const value = control.value;
			if (value && value.hasOwnProperty("id")) {
				return null;
			} else {
				return {
					clubOrNation: {
						valid: false,
					},
				};
			}
		};
	}

	private enabledTeams(enable: boolean): void {
		for (let i = 0; i < this.teams.controls.length; i++) {
			if (enable) {
				this.teams.controls[i].get("clubOrNation")?.disable({ onlySelf: true, emitEvent: false });
			} else {
				this.teams.controls[i].get("clubOrNation")?.enable({ onlySelf: true, emitEvent: false });
			}
		}
	}

	ngAfterViewInit(): void {
		this.applyPlayersAndTeams(2);
		this.form
			.get("configuration")
			?.get("nbPlayers")
			?.valueChanges.subscribe((value) => {
				this.form.get("configuration")?.get("nbGroups")?.setValue(null);
				this.form.get("configuration")?.get("nbGroups")?.updateValueAndValidity();
				this.groups = generateArrayNumber(1, calculateNbPoolByNbTeams(value) + 1);
				const size = this.players.length;
				if (size < value) {
					const diff: number = value - size;
					this.applyPlayersAndTeams(diff);
				} else if (size > value) {
					for (let i = size; i >= value; i--) {
						this.players.removeAt(i);
						this.teams.removeAt(i);
						if (this.selectedClubs.length > 0) {
							this.selectedClubs.splice(i, 1);
						}
						if (this.selectedNations.length > 0) {
							this.selectedNations.splice(i, 1);
						}
						if (this.teamsClub.length > 0) {
							this.teamsClub.splice(i, 1);
						}
						if (this.teamsNation.length > 0) {
							this.teamsNation.splice(i, 1);
						}
					}
				}
			});
		this.form
			.get("configuration")
			?.get("typeTeam")
			?.valueChanges.subscribe((value) => {
				if (value === "club") {
					this.selectedNations = [];
					this.teamsNation = [];
				} else {
					this.selectedClubs = [];
					this.teamsClub = [];
				}
				const nbPlayers = +this.form.get("configuration")?.get("nbPlayers")?.value | 2;
				for (let i = 0; i < nbPlayers; i++) {
					this.removeSelectedTeam(i);
				}
			});
		this.form
			.get("teams")
			?.get("random")
			?.valueChanges.subscribe((value) => this.enabledTeams(value as boolean));
	}

	isTypeTeam(type: string): boolean {
		return this.form?.get("configuration")?.get("typeTeam")?.value === type;
	}

	private setAutoFocus(): void {
		setTimeout(() => {
			switch (this.currentFormStep) {
				case 1:
					document.getElementById("nbPlayers")?.focus();
					break;
				case 2:
					document.getElementById("player0")?.focus();
					break;
				case 3:
					document.getElementById("random")?.focus();
					break;
				default:
				// do nothing
			}
		}, 500);
	}

	onClickPrevious(): void {
		if (this.currentFormStep > 1) {
			this.currentFormStep--;
			this.setAutoFocus();
		}
	}

	onClickNext(): void {
		if (this.currentFormStep + 1 <= this.formSteps.length) {
			this.currentFormStep++;
			this.setAutoFocus();
		}
	}

	isRandomTeam(): boolean {
		return this.form.get("teams")?.get("random")?.value || false;
	}

	isDisabledButtonGenerate(): boolean {
		if (this.isTypeTeam("club")) {
			for (let i = 0; i < this.teams.controls.length; i++) {
				if (!this.getFormControlAsClub(i)) {
					return false;
				}
			}
			return true;
		} else {
			for (let i = 0; i < this.teams.controls.length; i++) {
				if (!this.getFormControlAsNation(i)) {
					return false;
				}
			}
			return true;
		}
	}

	getStyleValidationFormControl(control: AbstractControl): string {
		if (control.dirty || control.touched) {
			return control.valid ? VALID : INVALID;
		}
		return "";
	}

	getStyleValidation(key: string): string {
		switch (key) {
			case "nbPlayers":
				return this.form.get("configuration")?.get("nbPlayers")?.valid ? VALID : INVALID;
			case "nbGroups":
				if (this.form.get("configuration")?.get("nbGroups")?.dirty || this.form.get("configuration")?.get("nbGroups")?.touched) {
					return this.form.get("configuration")?.get("nbGroups")?.valid ? VALID : INVALID;
				}
				return "";
			default:
				return "";
		}
	}

	isDisabledButtonNextOrSubmit(): boolean {
		let isValid: boolean = false;
		switch (this.currentFormStep) {
			case 1:
				isValid = (this.form.get("configuration")?.valid || false) && this.isCompleted();
				break;
			case 2:
				isValid = this.form.get("players")?.valid || false;
				break;
			case 3:
				isValid = (this.form.get("teams")?.valid || false) && this.isDisabledButtonGenerate();
				break;
			default:
			// nothing
		}
		return !isValid;
	}

	searchClub: OperatorFunction<string, Array<Club>> = (text$: Observable<string>) => {
		return text$.pipe(
			debounceTime(200),
			distinctUntilChanged(),
			map((term) => {
				if (term.trim() === "") return [];
				const clubs = this.allClubs
					.filter((club) => !this.selectedClubs.includes(club))
					.filter(
						(club) =>
							normalizeText(club.name.toLowerCase()).includes(normalizeText(term.toLowerCase())) ||
							normalizeText(club.shortName.toLowerCase()).includes(normalizeText(term.toLowerCase()))
					)
					.sort((a, b) => compareFn(a.name, b.name));
				if (clubs.length > 8) {
					return clubs.slice(0, 8);
				}
				return clubs;
			})
		);
	};

	searchNation: OperatorFunction<string, Array<Nation>> = (text$: Observable<string>) => {
		return text$.pipe(
			debounceTime(200),
			distinctUntilChanged(),
			map((term) => {
				if (term.trim() === "") return [];
				const nations = this.allNations
					.filter((nation) => !this.selectedNations.includes(nation))
					.filter((nation) =>
						this.currentLanguage === LANGUAGE_FR
							? normalizeText(nation.nameFr.toLowerCase()).includes(normalizeText(term.toLowerCase()))
							: normalizeText(nation.nameEn.toLowerCase()).includes(normalizeText(term.toLowerCase()))
					)
					.sort((a, b) => (this.currentLanguage === LANGUAGE_FR ? compareFn(a.nameFr, b.nameFr) : compareFn(a.nameEn, b.nameEn)));
				if (nations.length > 8) {
					return nations.slice(0, 8);
				}
				return nations;
			})
		);
	};

	selectItemClub(event: NgbTypeaheadSelectItemEvent): void {
		event.preventDefault();
		this.selectedClubs.push(event.item);
		this.preferred.nativeElement.value = "";
	}

	selectItemNation(event: NgbTypeaheadSelectItemEvent): void {
		event.preventDefault();
		this.selectedNations.push(event.item);
		this.preferred.nativeElement.value = "";
	}

	preventKeyDownEnter(event: Event) {
		event.preventDefault();
	}

	sortClubByName(sortByName: boolean = true): Array<Club> {
		return this.selectedClubs.sort((a, b) => (sortByName ? compareFn(a.name, b.name) : compareFn(a.shortName, b.shortName)));
	}

	sortNationssByName(): Array<Nation> {
		return this.selectedNations.sort((a, b) =>
			this.currentLanguage === LANGUAGE_FR ? compareFn(a.nameFr, b.nameFr) : compareFn(a.nameEn, b.nameEn)
		);
	}

	removeSelectedItem(item: Club | Nation): void {
		if (this.isTypeTeam("club")) {
			this.selectedClubs.splice(this.selectedClubs.indexOf(item as Club), 1);
		} else {
			this.selectedNations.splice(this.selectedNations.indexOf(item as Nation), 1);
		}
		setTimeout(() => {
			this.preferred.nativeElement.focus();
		}, 100);
	}

	getFormControlAsClub(indexFormControl: number): Club | undefined {
		return (this.teams.controls[indexFormControl].get("clubOrNation")?.value as Club) || undefined;
	}

	getFormControlAsNation(indexFormControl: number): Nation | undefined {
		return (this.teams.controls[indexFormControl].get("clubOrNation")?.value as Nation) || undefined;
	}

	removeSelectedTeam(indexFormControl: number): void {
		if (indexFormControl !== null && indexFormControl !== undefined) {
			const clubOrNation: Club | Nation = this.teams.controls[indexFormControl].get("clubOrNation")?.value;
			if (clubOrNation) {
				if (this.isTypeTeam("club")) {
					this.teamsClub.splice(this.teamsClub.indexOf(clubOrNation as Club), 1);
				} else {
					this.teamsNation.splice(this.teamsNation.indexOf(clubOrNation as Nation), 1);
				}
				this.teams.controls[indexFormControl].get("clubOrNation")?.setValue(null);
				this.teams.controls[indexFormControl].get("clubOrNation")?.updateValueAndValidity();
				if (this.teams.controls[indexFormControl].get("clubOrNation")?.enabled) {
					setTimeout(() => document.getElementById(`chosenTeam${indexFormControl}`)?.focus(), 100);
				}
			}
		}
	}

	searchPreferredClub: OperatorFunction<string, Array<Club>> = (text$: Observable<string>) => {
		return text$.pipe(
			debounceTime(200),
			distinctUntilChanged(),
			map((term) => {
				if (term.trim() === "") return [];
				const clubs = this.selectedClubs
					.filter((club) => !this.teamsClub.includes(club))
					.filter(
						(club) =>
							normalizeText(club.name.toLowerCase()).includes(normalizeText(term.toLowerCase())) ||
							normalizeText(club.shortName.toLowerCase()).includes(normalizeText(term.toLowerCase()))
					)
					.sort((a, b) => compareFn(a.name, b.name));
				if (clubs.length > 8) {
					return clubs.slice(0, 8);
				}
				return clubs;
			})
		);
	};

	searchPreferredNation: OperatorFunction<string, Array<Nation>> = (text$: Observable<string>) => {
		return text$.pipe(
			debounceTime(200),
			distinctUntilChanged(),
			map((term) => {
				if (term.trim() === "") return [];
				const nations = this.selectedNations
					.filter((nation) => !this.teamsNation.includes(nation))
					.filter((nation) =>
						this.currentLanguage === LANGUAGE_FR
							? normalizeText(nation.nameFr.toLowerCase()).includes(normalizeText(term.toLowerCase()))
							: normalizeText(nation.nameEn.toLowerCase()).includes(normalizeText(term.toLowerCase()))
					)
					.sort((a, b) => (this.currentLanguage === LANGUAGE_FR ? compareFn(a.nameFr, b.nameFr) : compareFn(a.nameEn, b.nameEn)));
				if (nations.length > 8) {
					return nations.slice(0, 8);
				}
				return nations;
			})
		);
	};

	selectPreferredClub(event: NgbTypeaheadSelectItemEvent): void {
		this.teamsClub.push(event.item);
	}

	selectPreferredNation(event: NgbTypeaheadSelectItemEvent): void {
		this.teamsNation.push(event.item);
	}

	randomClubOrNation(): void {
		if (this.isTypeTeam("club")) {
			function randomClub(array: Array<Club>): Club {
				return array[Math.floor(Math.random() * array.length)];
			}

			let clubs: Array<Club> = this.selectedClubs.filter((club) => !this.teamsClub.includes(club));
			for (let i = 0; i < this.teams.controls.length; i++) {
				if (!this.teams.controls[i].get("clubOrNation")?.value) {
					const random: Club = randomClub(clubs);
					this.teamsClub.push(random);
					clubs = clubs.filter((club) => club.id !== random.id);
					this.teams.controls[i].get("clubOrNation")?.setValue(random);
					this.teams.controls[i].get("clubOrNation")?.updateValueAndValidity();
				}
			}
		} else {
			function randomNation(array: Array<Nation>): Nation {
				return array[Math.floor(Math.random() * array.length)];
			}

			let nations: Array<Nation> = this.selectedNations.filter((nation) => !this.teamsNation.includes(nation));
			for (let i = 0; i < this.teams.controls.length; i++) {
				if (!this.teams.controls[i].get("clubOrNation")?.value) {
					const random: Nation = randomNation(nations);
					this.teamsNation.push(random);
					nations = nations.filter((nation) => nation.id !== random.id);
					this.teams.controls[i].get("clubOrNation")?.setValue(random);
					this.teams.controls[i].get("clubOrNation")?.updateValueAndValidity();
				}
			}
		}
	}

	isCompleted(): boolean {
		const nbPlayers: number = +this.form.get("configuration")?.get("nbPlayers")?.value || 2;
		let isCompleted: boolean = false;
		if (this.isTypeTeam("club")) {
			isCompleted = this.selectedClubs.length === nbPlayers;
		}
		if (this.isTypeTeam("national")) {
			isCompleted = this.selectedNations.length === nbPlayers;
		}
		return isCompleted;
	}

	submitForm(): void {
		this.isSubmit = true;
		this.form.disable({ onlySelf: true, emitEvent: false });
		document.querySelectorAll(".badge>.btn-close")?.forEach((btn) => {
			btn.setAttribute("disabled", "");
		});
		const teams: Array<Team> = [];
		const isClub: boolean = this.isTypeTeam("club");
		for (let i = 0; i < this.teams.controls.length; i++) {
			const player: Player = { name: this.teams.controls[i]?.get("name")?.value };
			if (isClub) {
				teams.push({ player: player, club: this.getFormControlAsClub(i) });
			} else {
				teams.push({ player: player, nation: this.getFormControlAsNation(i) });
			}
		}
		console.log(teams);
	}
}
