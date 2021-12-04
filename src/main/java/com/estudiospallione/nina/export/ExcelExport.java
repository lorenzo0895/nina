package com.estudiospallione.nina.export;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.estudiospallione.nina.entities.Fecha;

public class ExcelExport {

	public static void exportExcel() {
		try {
			Workbook workbook = new XSSFWorkbook();
			Sheet sh = workbook.createSheet("Reporte");
			String[] columnHeading = {"idCaja", "Fecha", "Cliente", "Importe"};
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 12);
			headerFont.setColor(IndexedColors.BLACK.index);
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFont(headerFont);
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
			Row headerRow = sh.createRow(0);
			for (int i = 0; i < columnHeading.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columnHeading[i]);
				cell.setCellStyle(headerStyle);
			}
			ArrayList<Fecha> a = createData();
			CreationHelper creationHelper = workbook.getCreationHelper();
			CellStyle dateStyle = workbook.createCellStyle();
			dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
			int rowNum = 1;
			for(Fecha i : a) {
				Row row = sh.createRow(rowNum++);
				row.createCell(0).setCellValue(i.getFecha());
				row.createCell(1).setCellValue(i.getSobrante());
				row.createCell(2).setCellValue(i.getSobrante());
				
			}
			for (int i=0;i<columnHeading.length;i++) {
				sh.autoSizeColumn(i);
			}
			Sheet sh2 = workbook.createSheet("Second");
			FileOutputStream fileOut = new FileOutputStream("/Users/Lorenzo/Desktop/Reporte.xlsx");
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Fecha> createData() {
		ArrayList<Fecha> a = new ArrayList();
		a.add(new Fecha(new GregorianCalendar(), 500.5f, false));
		a.add(new Fecha(new GregorianCalendar(), 500.5f, false));
		a.add(new Fecha(new GregorianCalendar(), 500.5f, false));
		a.add(new Fecha(new GregorianCalendar(), 500.5f, false));
		a.add(new Fecha(new GregorianCalendar(), 500.5f, false));
		a.add(new Fecha(new GregorianCalendar(), 500.5f, false));
		a.add(new Fecha(new GregorianCalendar(), 500.5f, false));
		a.add(new Fecha(new GregorianCalendar(), 500.5f, false));
		return a;
	}

}
