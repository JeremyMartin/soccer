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

	list(withClubs: boolean = false): Observable<Array<Nation>> {
		return this.http.get<Array<Nation>>(`${environment.apiUrl}/nation/list?full=${withClubs}`);
	}
}
