package org.sid.metier.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.sid.dao.PersonneRepository;
import org.sid.entities.Personne;
import org.sid.metier.service.IExcelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExcelDataServiceImpl implements IExcelDataService{
	
	@Value("${app.upload.file:${user.home}}")
	public String EXCEL_FILE_PATH;

	@Autowired
	PersonneRepository personneRepository;

	Workbook workbook; 

	@Override
	public List<Personne> getExcelDataAsList() {
		
		List<String> list = new ArrayList<String>();

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// Create the Workbook
		try {
			workbook = WorkbookFactory.create(new File(EXCEL_FILE_PATH));
			
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}

		// Retrieving the number of sheets in the Workbook
		System.out.println("-------Workbook has '" + workbook.getNumberOfSheets() + "' Sheets-----");

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(0);

		// Getting number of columns in the Sheet
		int noOfColumns = sheet.getRow(0).getLastCellNum();
		System.out.println("-------Sheet has '"+noOfColumns+"' columns------");

		// Using for-each loop to iterate over the rows and columns
		for (Row row : sheet) {
			for (Cell cell : row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				list.add(cellValue);
			}
		}

		// filling excel data and creating list as List<Invoice>
		List<Personne> invList = createList(list, noOfColumns);

		// Closing the workbook
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return invList;
	}
	
	private List<Personne> createList(List<String> excelData, int noOfColumns) {

		ArrayList<Personne> invList = new ArrayList<Personne>();

		int i = noOfColumns;
		do {
			Personne inv = new Personne();
			
			System.out.println(" "+excelData.get(i)+", "+excelData.get(i + 1) + ", " +excelData.get(i + 2));

			inv.setPrenom(excelData.get(i));
			inv.setNom(excelData.get(i + 1));
			inv.setTelephone(excelData.get(i + 2));
			//inv.setVote(excelData.get(i + 3));

			invList.add(inv);
			i = i + (noOfColumns);

		} while (i < excelData.size());
		
		return invList;
	}

	@Override
	public int saveExcelData(List<Personne> invoices) {
		// TODO Auto-generated method stub
		invoices = personneRepository.saveAll(invoices);
		return invoices.size();
	}

}
