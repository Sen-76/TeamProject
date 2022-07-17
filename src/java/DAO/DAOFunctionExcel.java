/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ConnectDB.ConnectJDBC;
import static DAO.DAOExcel.getWorkbook;
import Enitiy.Class_s;
import Enitiy.FunctionExcel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Admin
 */
public class DAOFunctionExcel extends DAOExcel{
     
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
    public Vector<FunctionExcel> readExcel1(String excelFilePath) {
        InputStream inputStream = null;
        Vector<FunctionExcel> listBooks = new Vector<>();
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
                FunctionExcel book = new FunctionExcel();
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
                            book.setFunction_name((String) getCellValue(cell));
                            break;
                        case 1:
                            book.setAccess_roles((String) getCellValue(cell) + "");
                            break;
                        case 2:
                            book.setSetting_value((String) getCellValue(cell) + "");
                            break;
                        case 3:
                            book.setFullname((String) getCellValue(cell) + "");
                            break;
                        case 4:
                            book.setPriority((String) getCellValue(cell) + "");
                            break;
                        case 5:
                            book.setFeature_name((String) getCellValue(cell) + "");
                            break;
                        case 6:
                            book.setTeam_name((String) getCellValue(cell) + "");
                            break;
                        case 7:
                            book.setClass_code((String) getCellValue(cell) + "");
                            break;
                        case 8:
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
        DAOFunctionExcel dao = new DAOFunctionExcel();
        Vector<FunctionExcel> ve = dao.readExcel1("MOCK_DATA-1.xlsx");
        System.out.println(ve);
    }
}

