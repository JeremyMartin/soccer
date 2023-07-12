import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { Match } from "../models/match/match";

@Injectable({
	providedIn: "root",
})
export class MatchService {
	constructor(private readonly http: HttpClient) {}

	private pathMatch: string = `${environment.apiUrl}/match`;

	update(match: Match): Observable<Match> {
		return this.http.put<Match>(`${this.pathMatch}`, match);
	}
}
