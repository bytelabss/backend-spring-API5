package fatec.bytelabss.dataViz.sparkExample.utils;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;

@AllArgsConstructor
public class SheetNameExtractor {
    private String filePath;

    public LinkedList<String> getSheetNames() throws IOException {
        LinkedList<String> sheetNames = new LinkedList<String>();

        FileInputStream file = new FileInputStream(new File(this.filePath));

        Workbook workbook = WorkbookFactory.create(file);

        System.out.println("List of sheets in the Excel file:");
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            System.out.println("Sheet " + (i + 1) + ": " + workbook.getSheetName(i));
            sheetNames.add(workbook.getSheetName(i));
        }

        workbook.close();
        file.close();

        return sheetNames;
    }
}
