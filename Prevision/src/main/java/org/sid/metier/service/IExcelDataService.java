package org.sid.metier.service;

import java.util.List;

import org.sid.entities.Personne;

public interface IExcelDataService {
	
	List<Personne> getExcelDataAsList();
	
	int saveExcelData(List<Personne> invoices);

}
