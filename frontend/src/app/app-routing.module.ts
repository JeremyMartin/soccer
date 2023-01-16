import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

const routes: Routes = [
	{ path: "", loadChildren: () => import("./features/home/home.module").then((m) => m.HomeModule) },
	{ path: "error", loadChildren: () => import("./features/error/error.module").then((m) => m.ErrorModule) },
	{ path: "tournament", loadChildren: () => import("./features/tournament/tournament.module").then((m) => m.TournamentModule) },
	{ path: "parameter", loadChildren: () => import("./features/parameter/parameter.module").then((m) => m.ParameterModule) },
	{ path: "**", redirectTo: "/error/404" },
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule],
})
export class AppRoutingModule {}
