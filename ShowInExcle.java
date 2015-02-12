package algorithms;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * Created by qt on 2015/2/11.
 */
public class ShowInExcle {
    public static void show(String path, double[] arr, double[] res) throws IOException {
        Workbook wb = new XSSFWorkbook();
        //创建Sheet并给名字(表示Excel的一个Sheet)
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("HSSF_Sheet_1");
        //Row表示一行Cell表示一列
        Row row1 = null;
        Row row2 = null;
        Cell cell1 = null;
        Cell cell2 = null;
        row1 = sheet.createRow(1);
        row2 = sheet.createRow(2);
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            cell1 = row1.createCell(i);
            cell2 = row2.createCell(i);
            cell1.setCellValue(arr[i]);
            cell2.setCellValue(res[i]);
        }
        OutputStream out = new FileOutputStream(new File(path));
        wb.write(out);
        out.close();
    }
    public static void changeXls(String path, double[] arr, double[] res) throws IOException, InvalidFormatException {
        InputStream inFs = new FileInputStream(path);
        Workbook wb = WorkbookFactory.create(inFs);
        Sheet sheet = wb.getSheetAt(0);
        int len = arr.length;
        Row row1 = null;
        Row row2 = null;
        Cell cell1 = null;
        Cell cell2 = null;
        row1 = sheet.getRow(1);
        row2 = sheet.getRow(2);
        for (int i = 0; i < len; i++) {
            cell1 = row1.getCell(i);
            cell1.setCellType(Cell.CELL_TYPE_NUMERIC);
            cell2 = row2.getCell(i);
            cell2.setCellType(Cell.CELL_TYPE_NUMERIC);
            try {
                cell1.setCellValue(arr[i]);
                cell2.setCellValue(res[i]);
            }
            catch (Exception e){
                e.printStackTrace();
                break;
            }
            /*cell1.setCellValue(arr[i]);
            cell2.setCellValue(res[i]);*/
        }
        FileOutputStream outputStream = new FileOutputStream(path);
        wb.write(outputStream);
        outputStream.close();
        wb.close();
    }
}
