
package DAO.Issue;

import DAO.DAOExcel;
import static DAO.DAOExcel.*;
import Enitiy.IsseExcel;
import java.io.*;
import java.util.*;
import org.apache.poi.ss.usermodel.*;

public class IssueExcelDAO extends DAOExcel{
    public String RandomBullSh() {
        String result = "";
        Random rand = new Random();

        for (int i = 0; i < 6; i++) {
            int a = rand.nextInt(10);
            result = result + a;
        }
        return result;
    }
    public static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }
        return cellValue.toString();
    }
    public Vector<IsseExcel> readExcel1(String excelFilePath) {
        InputStream inputStream = null;
        Vector<IsseExcel> listBooks = new Vector<>();
        try {

            // Get file
            inputStream = new FileInputStream(new File(excelFilePath));
            // Get workbook
            Workbook workbook = getWorkbook(inputStream, excelFilePath);
            // Get sheet
            Sheet sheet = workbook.getSheetAt(0);
            // Get all rows
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                if (nextRow.getRowNum() == 0) {
                    // Ignore header
                    continue;
                }

                // Get all cells
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                // Read cells and set value for book object
                IsseExcel book = new IsseExcel();
                while (cellIterator.hasNext()) {
                    //Read cell
                    Cell cell = cellIterator.next();
                    Object cellValue = getCellValue(cell);
                    if (cellValue == null || cellValue.toString().isEmpty()) {
                        continue;
                    }
                    // Set value for book object
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            book.setAssignee_id((String) getCellValue(cell) + "");
                            break;
                        case 1:
                            book.setIssue_title((String) getCellValue(cell) + "");
                            break;
                        case 2:
                            book.setDescription((String) getCellValue(cell) + "");
                            break;
                        case 3:
                            book.setGitlab_url((String) getCellValue(cell) + "");
                            break;
                        case 4:
                            book.setCreated_at((String) getCellValue(cell) + "");
                            break;
                        case 5: 
                            book.setTeam_id("");
                            book.setDue_date((String) getCellValue(cell) + "");
                            break;
//                        case 6: 
//                            book.setTeam_id("");
//                            break;
                        case 6: 
                            book.setLabel((String) getCellValue(cell) + "");
                            break;
                        case 7: 
                            book.setStatus((String) getCellValue(cell) + "");
                            break;
                        default:
                            break;
                    }
                }
                listBooks.add(book);
            }
            workbook.close();
            inputStream.close();
            return listBooks;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    public static void main(String[] args) {
        IssueExcelDAO dao = new IssueExcelDAO();
        Vector<IsseExcel> ve = dao.readExcel1("Template_Issue.xlsx");
        System.out.println(ve);
    }
}
