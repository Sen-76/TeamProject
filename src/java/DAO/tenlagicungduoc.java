/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Enitiy.UserExcel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Vector;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author ASUS
 */
public class tenlagicungduoc extends DAOExcel{
    public Vector<UserExcel> readExcel(String excelFilePath) {
        InputStream inputStream = null;
        Vector<UserExcel> listBooks = new Vector<>();
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
                UserExcel book = new UserExcel();
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
                            book.setGroupID((String) getCellValue(cell));
                            break;
                        case 1:
                            book.setUsername((String) getCellValue(cell) + "");
                            break;
                        case 2:
                            book.setRollNum((String) getCellValue(cell) + "");
                            break;
                        case 3:
                            book.setFullName((String) getCellValue(cell) + "");
                            break;
                        case 4:
                            book.setLeader((String) getCellValue(cell) + "");
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
        
    }
}
