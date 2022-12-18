package fr.ewaux.scrapping.service.file.impl;

import static fr.ewaux.commons.utilities.utils.FileHelper.createDirectoryIfNotExists;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ewaux.commons.utilities.exception.FileException;
import fr.ewaux.scrapping.model.external.club.Club;
import fr.ewaux.scrapping.model.external.league.League;
import fr.ewaux.scrapping.model.external.nation.Nation;
import fr.ewaux.scrapping.service.file.FileService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "fileService")
public class FileServiceImpl implements FileService {

	String directory;
	ObjectMapper objectMapper = new ObjectMapper();
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

	public FileServiceImpl(@Value("${path.download.directory:./download}") final String directory) {
		super();
		this.directory = directory;
	}

	@Override
	public void writeFile(String pathname, String filename, Object body) {
		String directory = this.directory + File.separator + pathname;
		createDirectoryIfNotExists(directory);
		try {
			Path path = Paths.get(directory, filename);
			if (log.isDebugEnabled()) {
				log.debug("write " + path);
			}
			this.objectMapper.writeValue(path.toFile(), body);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new FileException("Can't write file", filename, directory);
		}
	}

	@Override
	public void writeFile(final String pathname, final String filename, final byte[] bytes) {
		String directory = this.directory + File.separator + pathname;
		createDirectoryIfNotExists(directory);
		try {
			Path path = Paths.get(directory, filename);
			if (log.isDebugEnabled()) {
				log.debug("write " + path);
			}
			Files.write(path, bytes);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new FileException("Can't write file", filename, directory);
		}
	}

	@Override
	public void writeXls(final Class<?>... targets) {
		String directory = this.directory + File.separator + "excel";
		createDirectoryIfNotExists(directory);
		String suffix = "_" + this.simpleDateFormat.format(new Date()) + ".xlsx";
		String path = directory + File.separator + "export" + suffix;
		XSSFWorkbook workbook = null;
		FileOutputStream fileOutputStream = null;
		try {
			workbook = new XSSFWorkbook();
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			XSSFFont fontHeader = workbook.createFont();
			fontHeader.setFontName("Arial");
			fontHeader.setFontHeightInPoints((short) 14);
			fontHeader.setColor(HSSFColorPredefined.WHITE.getIndex());
			fontHeader.setBold(Boolean.TRUE);
			headerStyle.setFont(fontHeader);
			headerStyle.setAlignment(HorizontalAlignment.CENTER);
			headerStyle.setWrapText(Boolean.FALSE);
			CellStyle bodyStyle = workbook.createCellStyle();
			XSSFFont fontBody = workbook.createFont();
			fontBody.setFontName("Arial");
			fontBody.setFontHeightInPoints((short) 12);
			bodyStyle.setFont(fontBody);
			bodyStyle.setWrapText(Boolean.FALSE);
			for (Class<?> target : targets) {
				Set<?> body = this.readFile(target);
				if (CollectionUtils.isNotEmpty(body)) {
					this.writeSheet(workbook, headerStyle, bodyStyle, target.getSimpleName(), body);
				}
			}
			this.autoSizeColumns(workbook);
			fileOutputStream = new FileOutputStream(path);
			workbook.write(fileOutputStream);
		} catch (Exception ex) {
			throw new FileException("Can't create file", "export" + suffix, directory);
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (Exception ex) {
					log.warn("Can't close workbook", ex);
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (Exception ex) {
					log.warn("Can't close fileOutputStream", ex);
				}
			}
		}
	}

	private void writeSheet(final XSSFWorkbook workbook, final CellStyle headerStyle, final CellStyle bodyStyle, final String targetName,
		final Set<?> body) {
		Sheet sheet = null;
		if (targetName.equals(Nation.class.getSimpleName())) {
			sheet = workbook.createSheet("nations");
		}
		if (targetName.equals(Club.class.getSimpleName())) {
			sheet = workbook.createSheet("clubs");
		}
		if (targetName.equals(League.class.getSimpleName())) {
			sheet = workbook.createSheet("leagues");
		}
		if (sheet != null) {
			this.createHeaders(sheet, headerStyle);
			this.writeBody(sheet, bodyStyle, targetName, body);
		}
	}

	private void createHeaders(final Sheet sheet, final CellStyle headerStyle) {
		Row header = sheet.createRow(0);
		Cell cellId = header.createCell(0);
		cellId.setCellValue("ID");
		cellId.setCellStyle(headerStyle);
		Cell cellNameEn = header.createCell(1);
		cellNameEn.setCellValue("NAME EN");
		cellNameEn.setCellStyle(headerStyle);
		Cell cellNameFr = header.createCell(2);
		cellNameFr.setCellValue("NAME FR");
		cellNameFr.setCellStyle(headerStyle);
	}

	@SuppressWarnings("unchecked")
	private void writeBody(final Sheet sheet, final CellStyle bodyStyle, final String targetName, final Set<?> body) {
		if (CollectionUtils.isNotEmpty(body)) {
			if (targetName.equals(Nation.class.getSimpleName())) {
				int i = 1;
				Set<Nation> nations = (Set<Nation>) body;
				for (Nation nation : nations) {
					Row row = sheet.createRow(i);
					this.writeRow(row, bodyStyle, nation.getId(), nation.getName());
					i++;
				}
			}
			if (targetName.equals(Club.class.getSimpleName())) {
				int i = 1;
				Set<Club> clubs = (Set<Club>) body;
				for (Club club : clubs) {
					Row row = sheet.createRow(i);
					this.writeRow(row, bodyStyle, club.getId(), club.getName());
					i++;
				}
			}
		}
	}

	private void writeRow(final Row row, final CellStyle bodyStyle, final int id, final String nameEn) {
		Cell cellId = row.createCell(0);
		cellId.setCellStyle(bodyStyle);
		cellId.setCellValue(id);
		cellId.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
		Cell cellNameEn = row.createCell(1);
		cellNameEn.setCellStyle(bodyStyle);
		cellNameEn.setCellValue(nameEn);
		Cell cellNameFr = row.createCell(2);
		cellNameFr.setCellStyle(bodyStyle);
		cellNameFr.setCellValue("");
	}

	private void autoSizeColumns(final XSSFWorkbook workbook) {
		int numberOfSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = workbook.getSheetAt(i);
			if (sheet.getPhysicalNumberOfRows() > 0) {
				Row row = sheet.getRow(sheet.getFirstRowNum());
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					int columnIndex = cell.getColumnIndex();
					sheet.autoSizeColumn(columnIndex);
				}
			}
		}
	}

	private Set<?> readFile(final Class<?> target) throws IOException {
		String targetName = target.getSimpleName();
		if (targetName.equals(Nation.class.getSimpleName())) {
			return this.objectMapper.readValue(Paths.get(this.directory, "list", "nations.json").toFile(), new TypeReference<Set<Nation>>() {
			});
		}
		if (targetName.equals(Club.class.getSimpleName())) {
			return this.objectMapper.readValue(Paths.get(this.directory, "list", "clubs.json").toFile(), new TypeReference<Set<Club>>() {
			});
		}
		if (targetName.equals(League.class.getSimpleName())) {
			return this.objectMapper.readValue(Paths.get(this.directory, "list", "leagues.json").toFile(), new TypeReference<Set<League>>() {
			});
		}
		return null;
	}
}
