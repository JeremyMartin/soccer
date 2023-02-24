import { FormControl } from "@angular/forms";

export function notEmptyValidator(control: FormControl) {
	const isEmpty: boolean = (control.value || "").trim().length === 0;
	return !isEmpty ? null : { empty: true };
}
