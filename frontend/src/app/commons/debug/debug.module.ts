import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { DebugComponent } from "./component/debug.component";

@NgModule({
	declarations: [DebugComponent],
	imports: [CommonModule],
	exports: [DebugComponent],
})
export class DebugModule {}
