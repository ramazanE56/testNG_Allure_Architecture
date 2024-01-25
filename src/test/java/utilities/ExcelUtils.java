package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtils {
    public static void logToExcel(String testName, String status, String description) {
        try {
            FileInputStream file = new FileInputStream("src/test/java/Logs/logFile.xlsx");
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheet("TestLog");

            int rowCount = sheet.getPhysicalNumberOfRows();
            // En alt satıra yazmak için rowCount'u kullan

            Row row = sheet.createRow(rowCount);

            Cell cell = row.createCell(0);
            cell.setCellValue(getCurrentTime());

            cell = row.createCell(1);
            cell.setCellValue(testName);

            cell = row.createCell(2);
            cell.setCellValue(status);

            cell = row.createCell(3);
            cell.setCellValue(description);

            file.close();

            FileOutputStream outFile = new FileOutputStream("src/test/java/Logs/logFile.xlsx");
            workbook.write(outFile);
            outFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
