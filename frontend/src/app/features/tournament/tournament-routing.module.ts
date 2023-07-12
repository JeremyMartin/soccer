import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AddComponent } from "./components/tournament/add/add.component";
import { ListComponent } from "./components/tournament/list/list.component";
import { ViewComponent } from "./components/tournament/view/view.component";

const routes: Routes = [
	{ path: "", component: ListComponent },
	{ path: "add", component: AddComponent },
	{ path: "consult/:id", component: ViewComponent },
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule],
})
export class TournamentRoutingModule {}
