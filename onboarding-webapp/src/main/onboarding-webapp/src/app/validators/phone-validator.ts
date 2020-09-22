import { AbstractControl } from '@angular/forms';

export function phoneNumberValidator(control: AbstractControl): { [key: string]: any } | null {
  const valid = /\d{11}/.test(control.value);
  if (valid) {
    return null;
  } else {
    return {invalidNumber: { valid: false, value: control.value } };
  }

  // return valid
  //   ? null
  //   : { invalidNumber: { valid: false, value: control.value } };
}
