import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { Club } from "../models/club/club";

@Injectable({
	providedIn: "root",
})
export class ClubService {
	constructor(private readonly http: HttpClient) {}

	private pathClub: string = `${environment.apiUrl}/club`;

	list(withNation: boolean = false): Observable<Array<Club>> {
		return this.http.get<Array<Club>>(`${this.pathClub}/list?full=${withNation}`);
	}
}
