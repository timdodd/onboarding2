import { Component, OnInit } from '@angular/core';
import {PhoneModel} from '../../models/phone.model';
import {Subscription} from 'rxjs';
import {ActivatedRoute, NavigationExtras, Router} from '@angular/router';
import {PhoneService} from '../../services/phone.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {UserPhoneVerificationModalComponent} from '../user-phone-verification-modal/user-phone-verification-modal.component';

@Component({
  selector: 'app-user-phone-list',
  templateUrl: './user-phone-list.component.html',
  styleUrls: ['./user-phone-list.component.css']
})
export class UserPhoneListComponent implements OnInit {

  phones: PhoneModel[] = [];
  userId: string;
  loadingSubscription = Subscription.EMPTY;

  constructor(private phoneService: PhoneService,
              private router: Router,
              private route: ActivatedRoute,
              private modalService: NgbModal) {
    this.route.queryParams.subscribe(params => {
      this.userId = params['userId'];
    });
  }

  ngOnInit() {
    this.loadingSubscription = this.phoneService.getAllPhones(this.userId).subscribe(phones => {
      this.phones = phones.filter(p => p.userId === this.userId);
    });
  }

  onCreate() {
    const navigationExtras: NavigationExtras = {
      queryParams: {
        'userId': this.userId
      }
    };
    this.router.navigate(['/createPhone'], navigationExtras);
  }

  onDelete(phoneId: string) {
    console.log(phoneId);
    this.phoneService.delete(this.userId, phoneId).subscribe(() => this.ngOnInit());
  }

  onEdit(userId: string, phoneId: string) {
    const navigationExtras: NavigationExtras = {
      queryParams: {
        'userId': userId,
        'phoneId': phoneId
      }
    };
    this.router.navigate(['/editPhone'], navigationExtras);
  }

  onVerify(phone: PhoneModel) {
    this.phoneService.sendVerify(phone.userId, phone).subscribe();
    const modalRef = this.modalService.open(UserPhoneVerificationModalComponent);
    modalRef.componentInstance.phone = phone;
  }

  onUser() {
    this.router.navigateByUrl('');
  }

}
