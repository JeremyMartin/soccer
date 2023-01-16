import { Tournament } from "../../../models/tournament/tournament";

export function randomDate(start: Date): Date {
	const now: Date = new Date();
	return new Date(start.getTime() + Math.random() * (now.getTime() - start.getTime()));
}

export function randomText(length: number): string {
	let result = "";
	const characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	const charactersLength = characters.length;
	for (let i = 0; i < length; i++) {
		result += characters.charAt(Math.floor(Math.random() * charactersLength));
	}
	return result;
}

export function generateTournaments(): Array<Tournament> {
	const date: Date = new Date(2001, 0, 1);
	const tournaments: Array<Tournament> = [];
	for (let i = 1; i < 31; i++) {
		const name = "Tournament-" + i + "-" + randomText(7) + " " + randomText(10);
		tournaments.push({ id: i, date: randomDate(date), name: name });
	}
	const name = "Tournament-" + 31 + "-" + randomText(7) + " " + randomText(10);
	tournaments.push({ id: 31, name: name });
	return tournaments;
}
