package com.example.api;

import com.opencsv.CSVReader;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * whydda 2019-03-18
 */
public class ExceluploadComp {
    //csv 파일업로드 처리
    public static List<String[]> getCsvFile(File file) throws FileNotFoundException, IOException {
        int colNameRowNum = 0;
        boolean mapType = false;
        List list = new ArrayList();
        CSVReader reader = new CSVReader(new FileReader(file));
        String[] s;
        String[] cols = null;
        int tmpNum = colNameRowNum-1;
        int rowCount = 0;
        s = reader.readNext();
        while (s != null) {
            if(rowCount == tmpNum){
                cols = s;
            }else if(rowCount > tmpNum){
                if(mapType){
                    HashMap row = new HashMap();
                    for(int i=0;i<cols.length;i++){
                        row.put(cols[i], s[i]);
                    }
                    list.add(row);
                }else{
                    list.add(s);
                }
            }
            rowCount++;
            s = reader.readNext();
        }

        return list;
    }

    //엑셀 파일업로드 xlsx
    public static List<String[]> getExcelInXlsx(File fileLocation) throws IOException {
        List<String[]> listStrArrays = new ArrayList<String[]>();

        FileInputStream fis = new FileInputStream(fileLocation);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int rowindex = 0;
        int columnindex = 0;
        //시트 수 (첫번째에만 존재하므로 0을 준다)
        //만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getPhysicalNumberOfRows();

        //각 셀의 읽어온다.

        for (rowindex = 0; rowindex < rows; rowindex++) {
            //행을읽는다
            XSSFRow row = sheet.getRow(rowindex);
            if (row != null) {
                //셀의 수
                int cells = row.getPhysicalNumberOfCells();
                listStrArrays.add(new String[cells]);
                for (columnindex = 0; columnindex <= cells; columnindex++) {
                    //셀값을 읽는다
                    XSSFCell cell = row.getCell(columnindex);
                    String value = "";
                    //셀이 빈값일경우를 위한 널체크
                    if (cell == null) {
                        continue;
                    } else {
                        //타입별로 내용 읽기
//                        switch (cell.getCellType()) {
//                            case XSSFCell.CELL_TYPE_FORMULA:
//                                value = cell.getCellFormula();
//                                break;
//                            case XSSFCell.CELL_TYPE_NUMERIC:
//                                value = cell.getNumericCellValue() + "";
//                                break;
//                            case XSSFCell.CELL_TYPE_STRING:
//                                value = cell.getStringCellValue() + "";
//                                break;
//                            case XSSFCell.CELL_TYPE_BLANK:
//                                value = cell.getBooleanCellValue() + "";
//                                break;
//                            case XSSFCell.CELL_TYPE_ERROR:
//                                value = cell.getErrorCellValue() + "";
//                                break;
//                        }
                    }
                    listStrArrays.get(rowindex)[columnindex] = value;
                }
            }
        }
        return listStrArrays;
    }

    //엑셀 파일업로드 xls
    public static List<String[]> getExcelInXls(File fileLocation) throws IOException{
        List<String[]> listStrArrays = new ArrayList<String[]>();

        FileInputStream fis = new FileInputStream(fileLocation);
        HSSFWorkbook workbook=new HSSFWorkbook(fis);
        int rowindex=0;
        int columnindex=0;
        //시트 수 (첫번째에만 존재하므로 0을 준다)
        //만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다

        HSSFSheet sheet=workbook.getSheetAt(0);
        //행의 수
        int rows=sheet.getPhysicalNumberOfRows();

        for(rowindex=1;rowindex<rows;rowindex++) {
            //행을 읽는다
            HSSFRow row = sheet.getRow(rowindex);
            if (row != null) {
                //셀의 수
                int cells = row.getPhysicalNumberOfCells();
                listStrArrays.add(new String[cells]);
                for (columnindex = 0; columnindex <= cells; columnindex++) {
                    //셀값을 읽는다
                    HSSFCell cell = row.getCell(columnindex);
                    String value = "";
                    //셀이 빈값일경우를 위한 널체크
                    if (cell == null) {
                        continue;
                    } else {
                        //타입별로 내용 읽기
//                        switch (cell.getCellType()) {
//                            case HSSFCellStylel.CELL_TYPE_FORMULA:
//                                value = cell.getCellFormula();
//                                break;
//                            case HSSFCell.CELL_TYPE_NUMERIC:
//                                value = cell.getNumericCellValue() + "";
//                                break;
//                            case HSSFCell.CELL_TYPE_STRING:
//                                value = cell.getStringCellValue() + "";
//                                break;
//                            case HSSFCell.CELL_TYPE_BLANK:
//                                value = cell.getBooleanCellValue() + "";
//                                break;
//                            case HSSFCell.CELL_TYPE_ERROR:
//                                value = cell.getErrorCellValue() + "";
//                                break;
//                        }
                    }
                    listStrArrays.get(rowindex)[columnindex] = value;
                }
            }
        }
        return listStrArrays;
    }
}
