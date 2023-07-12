import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Nation } from "../models/nation/nation";
import { environment } from "../../environments/environment";

@Injectable({
	providedIn: "root",
})
export class NationService {
	constructor(private readonly http: HttpClient) {}

	private pathNation: string = `${environment.apiUrl}/nation`;

	list(withClubs: boolean = false): Observable<Array<Nation>> {
		return this.http.get<Array<Nation>>(`${this.pathNation}/list?full=${withClubs}`);
	}
}
