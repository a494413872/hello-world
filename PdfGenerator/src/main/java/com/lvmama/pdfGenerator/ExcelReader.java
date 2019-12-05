package com.lvmama.pdfGenerator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    private final static String XlS=".xls";
    private final static String XlSX=".xlsx";
    /**
     * 判断Excel的版本,获取Workbook
     * @param in
     * @return

     */
    public static Workbook getWorkbok(InputStream in, File file) throws IOException {
        Workbook wb = null;
        if(file.getName().endsWith(XlS)){  //Excel 2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(XlSX)){  // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     * 判断文件是否是excel
     */
    public static void checkExcelVaild(File file) throws Exception{
        if(!file.exists()){
            throw new Exception("文件不存在");
        }
        if(!(file.isFile() && (file.getName().endsWith(XlS) || file.getName().endsWith(XlSX)))){
            throw new Exception("文件不是Excel");
        }
    }

    public static List<BaseData> readData(String excelPath) throws Exception {
        List<BaseData> datas = new ArrayList<BaseData>();
            // 同时支持Excel 2003、2007
            File excelFile = new File(Generate.excelPath); // 创建文件对象
            FileInputStream in = new FileInputStream(excelFile); // 文件流
            checkExcelVaild(excelFile);
            Workbook workbook = getWorkbok(in,excelFile);
            //Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel2003/2007/2010都是可以处理的

//            int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
            /**
             * 设置当前excel中sheet的下标：0开始
             */
            Sheet sheet = workbook.getSheetAt(0);   // 遍历第一个Sheet
            // 为跳过第一行目录设置count
            int count = 0;
            for (Row row : sheet) {
                try {
                    // 跳过第一行的目录
                    if(count < 1 ) {
                        count++;
                        continue;
                    }

                    //如果当前行没有数据，跳出循环
                    if(row.getCell(0).toString().equals("")){
                        break;
                    }
                    BaseData baseData = new BaseData();
                    Iterator<Cell> cells = row.cellIterator();
                    while (cells.hasNext()){
                        Cell cell = cells.next();
                        if(cell == null) {
                            throw new Exception("Cell is Empty at row "+row.getRowNum()+" column "+cell.getColumnIndex());
                        }
                        String value = getValue(cell);
                        switch (cell.getColumnIndex()){
                            case 0:
                                baseData.setColumnOne(value);break;
                            case 1:
                                baseData.setColumnTwo(value);break;
                            case 2:
                                baseData.setColumnThree(value);break;
                            case 3:
                                baseData.setColumnFour(value);break;
                            case 4:
                                baseData.setColumnFive(value);break;
                            default:
                                throw new Exception("Column number larger than agreement. please check.");

                        }

                    }
                    datas.add(baseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        return datas;
    }
    private static String getValue(Cell cell) throws Exception {
        String obj = null;
        switch (cell.getCellTypeEnum()) {
            case STRING:
                obj = cell.getStringCellValue();
                break;
            default:
                throw new Exception("Please set all column as String.");
        }
        return obj;
    }

}
