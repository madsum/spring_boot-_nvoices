package com.ma.home.service;

import com.ma.home.model.Finvoices;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public interface XmlPaserService {
	//public Finvoices parseXmlFile(String xmlFile);
	public Finvoices parseXmlFile(File xmlFile);
	public String getJsonData(Finvoices finvoices);
}