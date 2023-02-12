import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http'
import { environment } from 'src/environments/environment.development';
import { partner } from '../model/partner.model';

@Injectable({
  providedIn: 'root'
})
export class PartnerService {

  constructor(private http: HttpClient) { }

  getPartnerList(): Observable<partner[]> {
    return this.http.get<partner[]>(environment.APIURL + "partner")
  }

  getPartnerById(id: number): Observable<partner> {
    return this.http.get<partner>(environment.APIURL + "partner/" + id)
  }
}
