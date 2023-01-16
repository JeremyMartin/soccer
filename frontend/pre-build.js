const path = require("path");
const colors = require("colors/safe");
const fs = require("fs");

console.log(colors.cyan("Running pre-build tasks"));

let appVersion = null;

const allFileContents = fs.readFileSync(path.join(__dirname, "version.txt"), "utf-8");
allFileContents.split(/\r?\n/).forEach((line) => {
	if (line.length > 0) {
		appVersion = line;
	}
});

if (appVersion === null) {
	console.log(colors.red("Error appVersion not defined"));
	process.exit(1);
}
console.log(colors.white("AppVersion : ") + colors.yellow(appVersion));
const versionFilePath = path.join(__dirname + "/src/environments/version.ts");
const rowToWrite = `export const appVersion = '${appVersion}';`;

fs.writeFileSync(versionFilePath, rowToWrite, { flag: "w" });

console.log(colors.green("Done") + "\n");
