package com.ma.home.service;

import com.ma.home.model.Finvoices;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseRreaderWritter {
	public  void setXmlFileName(String fileName);
	public boolean writeInDatabase(Finvoices finVoices);
	public String uploadXmlFile(String fileName, byte[] bytes);

}
