import {Component, Input} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {PhoneModel} from '../../models/phone.model';
import {PhoneService} from '../../services/phone.service';

@Component({
  selector: 'app-user-phone-verification-modal',
  templateUrl: './user-phone-verification-modal.component.html',
  styleUrls: ['./user-phone-verification-modal.component.css']
})

export class UserPhoneVerificationModalComponent {
  @Input() phone: PhoneModel;
  code: string;

  constructor(public activeModal: NgbActiveModal,
              private phoneService: PhoneService) {}

  checkVerify(phone: PhoneModel, code: string) {
    this.phoneService.checkVerify(phone.userId, phone, code).subscribe(() => this.activeModal.close());
    window.location.reload();
  }

}
