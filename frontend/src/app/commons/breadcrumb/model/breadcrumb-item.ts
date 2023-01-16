import { BreadcrumbAction } from "./breadcrumb-action";

export interface BreadcrumbItem {
	id?: string;
	name: string;
	action?: BreadcrumbAction;
}
