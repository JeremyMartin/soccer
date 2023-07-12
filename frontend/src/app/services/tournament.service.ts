import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Tournament } from "../models/tournament/tournament";
import { environment } from "../../environments/environment";

@Injectable({
	providedIn: "root",
})
export class TournamentService {
	constructor(private readonly http: HttpClient) {}

	private pathTournament: string = `${environment.apiUrl}/tournament`;

	list(): Observable<Array<Tournament>> {
		return this.http.get<Array<Tournament>>(`${this.pathTournament}/list`);
	}

	add(tournament: Tournament): Observable<Tournament> {
		return this.http.post<Tournament>(`${this.pathTournament}/add`, tournament);
	}

	findById(id: string | number): Observable<Tournament> {
		return this.http.get<Tournament>(`${this.pathTournament}/${id}`);
	}

	deleteById(id: string | number): Observable<any> {
		return this.http.delete<any>(`${this.pathTournament}/${id}`);
	}
}
