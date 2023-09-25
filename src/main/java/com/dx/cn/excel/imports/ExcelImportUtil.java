package com.dx.cn.excel.imports;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

/**
 * @Author w y y t c f j
 * @Description ExcelCellStyleReader
 * @Date 2023/9/23
 *
 *
 */
public class ExcelImportUtil {


    private static String filePath = "/Users/dengxu/Desktop/reptile/import.xlsx";

    /**
     * STRING：表示单元格中的内容为字符串类型。
     * NUMERIC：表示单元格中的内容为数字类型（包括整数和浮点数）。
     * BOOLEAN：表示单元格中的内容为布尔值类型。
     * FORMULA：表示单元格中的内容为公式类型。
     * BLANK：表示单元格为空白的。
     * ERROR：表示单元格中的内容为错误类型。
     * UNKNOWN：表示单元格类型未知。
     */
    public static void CellDataInfo(Cell cell){
        // 根据需要处理单元格数据
        CellType cellType = cell.getCellType();
        if (cellType == CellType.STRING) {
            System.out.print(cell.getStringCellValue() + "\t");
        } else if (cellType == CellType.NUMERIC) {
            System.out.print(cell.getNumericCellValue() + "\t");
        } else if (cellType == CellType.BOOLEAN) {
            System.out.print(cell.getBooleanCellValue() + "\t");
        } else if (cellType == CellType.FORMULA) {
            // 处理公式类型的单元格
        } else if (cellType == CellType.BLANK) {
            // 处理空白单元格
        } else if (cellType == CellType.ERROR) {
            // 处理错误类型的单元格
        } else {
            // 处理未知类型的单元格
        }
    }
    public static void CellStyleInfo(Cell cell,Workbook workbook){
        // 获取单元格样式
        CellStyle cellStyle = cell.getCellStyle();

        // 获取背景颜色
        Color bgColor = cellStyle.getFillForegroundColorColor();
        if (bgColor instanceof XSSFColor) {
            System.out.println("背景颜色：" + ((XSSFColor) bgColor).getARGBHex());
        }

        // 获取字体样式
        Font font = workbook.getFontAt(cellStyle.getFontIndex());
        System.out.println("字体名称：" + font.getFontName());
        System.out.println("字体大小：" + font.getFontHeightInPoints());

        // 读取其他样式属性，如加粗、斜体、下划线等
        boolean isBold = font.getBold();
        //斜体
        boolean isItalic = font.getItalic();
        //下划线
        byte underline = font.getUnderline();//1是 0否


        System.out.println("是否加粗：" + isBold);
        System.out.println("是否斜体：" + isItalic);
        System.out.println("下划线类型：" + underline);
    }


    public static void main(String[] args) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    CellDataInfo(cell);
                    CellStyleInfo(cell,workbook);
                    System.out.println("---------");
                }
            }
            workbook.close();
            excelFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
