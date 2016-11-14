package com.qualicom.rr.marshall.impl;

import com.qualicom.rr.marshall.MarshallAdapter;
import com.qualicom.rr.model.Report;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

/**
 * Created by x110277 on 11/14/2016.
 */
@Component("xlsMarshallAdapter")
public class XLSMarshallAdapter implements MarshallAdapter {

    private static short TITLE_ROW = 0;

    private static short HEADER_ROW = 3;

    private static short DATA_ROW_START = 4;

    @Override
    public byte[] generateReports(Report... reports) throws Exception {
        if (reports == null || reports.length < 1)
            throw new Exception("One or more reports are supported for XLS marshalling.");

        Workbook workbook = new XSSFWorkbook();
        CellStyle titleCellStyle = createTitleCellStyle(workbook);
        CellStyle headerCellStyle = createHeaderCellStyle(workbook);
        CellStyle dataCellStyle = createDataCellStyle(workbook);


        for (Report report : reports) {
            Sheet sheet = workbook.createSheet(report.getName());

            Row rowtitle = sheet.createRow(TITLE_ROW);
            rowtitle.createCell(0).setCellValue(report.getTitle());
            rowtitle.getCell(0).setCellStyle(titleCellStyle);

            Row rowhead = sheet.createRow(HEADER_ROW);
            rowhead.setHeight((short)384); //1.5 characters * 256 each
            for (int i=0; i<report.getHeader().size(); i++) {
                rowhead.createCell(i).setCellValue(report.getHeader().get(i));
                rowhead.getCell(i).setCellStyle(headerCellStyle);
                sheet.setColumnWidth(i,3850); //15 chars * 256 each
            }

            for (int i=0; i<report.getData().size(); i++) {
                Row row = sheet.createRow(DATA_ROW_START + i);
                for (int j=0; j<report.getData().get(i).size(); j++) {
                    row.createCell(j).setCellValue(report.getData().get(i).get(j));
                    row.getCell(j).setCellStyle(dataCellStyle);
                }
            }
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        workbook.close();
        return byteArrayOutputStream.toByteArray();
    }

    private CellStyle createTitleCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(createTitleFont(workbook));
        return cellStyle;
    }

    private Font createTitleFont(Workbook workbook) {
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short)14);
        return font;
    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(createHeaderFont(workbook));
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.FINE_DOTS);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBottomBorderColor((short)0);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setLeftBorderColor((short)0);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setRightBorderColor((short)0);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setTopBorderColor((short)0);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    private Font createHeaderFont(Workbook workbook) {
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short)10);
        return font;
    }

    private CellStyle createDataCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(createDataFont(workbook));
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBottomBorderColor((short)0);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setLeftBorderColor((short)0);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setRightBorderColor((short)0);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setTopBorderColor((short)0);
        return cellStyle;
    }

    private Font createDataFont(Workbook workbook) {
        Font font = workbook.createFont();
        font.setBold(false);
        font.setFontHeightInPoints((short)10);
        return font;
    }

    @Override
    public boolean canHandleMultipleReports() {
        return true;
    }

    @Override
    public String getMimeType() {
        return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    }

    @Override
    public String getFileExtension() {
        return "xlsx";
    }
}
