import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http'
import { environment } from 'src/environments/environment.development';
import { Partner } from '../model/partner.model';

@Injectable({
  providedIn: 'root'
})
export class PartnerService {

  constructor(private http: HttpClient) { }

  getPartnerList(): Observable<Partner[]> {
    return this.http.get<Partner[]>(environment.APIURL + "partner")
  }

  getPartnerById(id: number): Observable<Partner> {
    return this.http.get<Partner>(environment.APIURL + "partner/" + id)
  }
}
