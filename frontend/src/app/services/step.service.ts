import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { Step } from "../models/step/step";

@Injectable({
	providedIn: "root",
})
export class StepService {
	constructor(private readonly http: HttpClient) {}

	private pathStep: string = `${environment.apiUrl}/step`;

	list(): Observable<Array<Step>> {
		return this.http.get<Array<Step>>(this.pathStep);
	}
}
