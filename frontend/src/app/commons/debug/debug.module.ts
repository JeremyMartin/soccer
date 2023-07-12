import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { DebugComponent } from "./component/debug.component";

@NgModule({
	declarations: [DebugComponent],
	imports: [CommonModule],
	exports: [DebugComponent],
})
export class DebugModule {}
