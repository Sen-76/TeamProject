package DAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Enitiy.Class_s;
import Enitiy.User;
import Enitiy.UserExcel;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ASUS
 */
public class DAOExcel {

    public void CreateExcel(Vector<Class_s> v) {

        XSSFWorkbook wkb = new XSSFWorkbook();
        XSSFSheet sheet = wkb.createSheet("amen");
        XSSFRow row = null;
        Cell cell = null;
        row = sheet.createRow(0); // lùi 3 dòng

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("SubjectId");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("SubjectCode");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("SubjectName");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("DepartmentId");

        for (int i = 0; i < v.size(); i++) {
            Class_s get = v.get(i);
            row = sheet.createRow(1 + i);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(v.get(i).getId());

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(v.get(i).getStatus());

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(v.get(i).getClassCode());

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(v.get(i).getClassYear());

        }

        File f = new File("ameno.xlsx");
        try {
            FileOutputStream fis = new FileOutputStream(f);
            wkb.write(fis);
            fis.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void readExcel_theoCot(int Vrow, int Vcolume) {
        String result = null;
        Workbook wb = null;
        try {
            FileInputStream fis = new FileInputStream("FileHere/aaa.xlsx");
            wb = new XSSFWorkbook(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(Vrow);
        Cell cell = row.getCell(Vcolume);

        result = cell.getStringCellValue();
        System.out.println(result);
    }

    private static final String FILE_NAME = "FileHere/aaa.xlsx";

    public void ShowExcel() {
        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0); // xem sheet
            Iterator<Row> iterator = datatypeSheet.iterator();
            String result = "";
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellType() == CellType.STRING) {
                        // System.out.print(currentCell.getStringCellValue() + "|");
                        result += currentCell.getStringCellValue() + "|";
                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
                        //System.out.print(currentCell.getNumericCellValue() + "|");
                        result += currentCell.getNumericCellValue() + "|";
                    }

                }
                System.out.println(result);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

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

    // Get Workbook
    public static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Get cell value
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

    public String RandomBullSh() {
        String result = "";
        Random rand = new Random();

        for (int i = 0; i < 6; i++) {
            int a = rand.nextInt(10);
            result = result + a;
        }
        int resu = rand.nextInt(2);
        return (resu) + "";
    }

    public static int getRandomInteger(int maximum, int minimum) {
        return ((int) (Math.random() * (maximum - minimum))) + minimum;
    }

    public String randomArray() {

        Random rd = new Random(); // creating Random object
        int[] arr = {2,
            3,
            4,
            6,
            9,
            10,
            2036,
            2040,
            2048,
            2056,
            2065,
            2081,
            2091};

        int a = rd.nextInt(arr.length);

        return arr[a] + "";
    }

    public String randomSta() {

        Random rd = new Random(); // creating Random object
        int[] arr = {1, 2};

        int a = rd.nextInt(arr.length);

        return arr[a] + "";
    }

    public static void main(String[] args) {
        DAOExcel dao = new DAOExcel();
        DAOchangePass daoC = new DAOchangePass();
//        Vector<Class_s> v = daoC.ViewAllClassName("", "0");
//        dao.CreateExcel(v);
        Vector<UserExcel> v = dao.readExcel("MOCK_DATA.xlsx");
        for (UserExcel o : v) {
            System.out.println(o);
        }
    }

}
