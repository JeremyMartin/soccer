import { FormControl } from "@angular/forms";

export function notEmptyValidator(control: FormControl) {
	const isEmpty: boolean = (control.value || "").trim().length === 0;
	return !isEmpty ? null : { empty: true };
}

export function numberOnlyValidator(control: FormControl) {
	const val: any = control.value;
	if (!val || val === "") {
		return null;
	}
	const isValid: boolean = val.toString().match(/^[0-9]+$/);
	return isValid ? null : { invalidNumber: true };
}
