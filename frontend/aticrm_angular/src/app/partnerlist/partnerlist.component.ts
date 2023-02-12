import { Component, OnInit } from '@angular/core';
import { partner } from '../model/partner.model';
import { PartnerService } from '../services/partner.service';

@Component({
  selector: 'app-partnerlist',
  templateUrl: './partnerlist.component.html',
  styleUrls: ['./partnerlist.component.scss']
})
export class PartnerlistComponent implements OnInit {
  items: partner[] = [];

  constructor(private ps: PartnerService) {

  }

  ngOnInit(): void {
    this.ps.getPartnerList().subscribe(plist => this.items = plist)
  }

  addClick() {
    console.log("Add new partner");
  }
}
