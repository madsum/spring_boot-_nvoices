package com.ma.home.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ma.home.dao.BuyerPartyDetailsDAO;
import com.ma.home.dao.DefinitionDetailsDAO;
import com.ma.home.dao.InvoiceDetailsDAO;
import com.ma.home.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class XmlPaserServiceImpl implements XmlPaserService {

	@Autowired
	private InvoiceDetailsDAO invoiceDetailsDAO;

	@Autowired
	private DefinitionDetailsDAO definitionDetailsDAO;

	@Autowired
	private BuyerPartyDetailsDAO buyerPartyDetailsDAO;

	private static final Logger logger = LoggerFactory.getLogger(XmlPaserServiceImpl.class);

	public XmlPaserServiceImpl(){

	}

	private boolean isBuyerExistInDatabase(BuyerPartyDetails byerDetails){
		// check does this buyer already in database
		List<BuyerPartyDetails> buyerList =(List<BuyerPartyDetails>) buyerPartyDetailsDAO.findAll();
		logger.info("DB buyer list size = "+buyerList.size());
		
		for(BuyerPartyDetails buyer : buyerList){
			if(buyer == null){
				return false;
			}		
			logger.info("BuyerPartyIdentifier from DB ="+buyer.getBuyid());
			if(buyer.getBuyid().compareToIgnoreCase(byerDetails.getBuyerPartyIdentifier()) == 0){
				// buyer found in DB 
				return true;
			}
		}
		return false;
	}

	private boolean isInvoiceExistInDatabase(InvoiceDetails invoice){
		// check does this buyer invoice in database
		List<InvoiceDetails> invoiceList = (List<InvoiceDetails>) invoiceDetailsDAO.findAll();
		logger.info("DB buyer list size = "+invoiceList.size());
		for(InvoiceDetails invo : invoiceList){
			if(invo == null){
				return false;
			}
			logger.info("BuyerPartyIdentifier from DB ="+invo.getInvoiceNumber());
			if(invo.getInvoiceNumber().compareToIgnoreCase(invoice.getInvoiceNumber()) == 0){
				// invoice found in DB 
				return true;
			}
		}
		return false;
	}	

	public boolean writeInDatabase(Finvoices finVoices){


		if( finVoices == null){
			return false;
		}


		for(Finvoice finvoice : finVoices.getFinvoices())
		{
			if( finvoice == null){
				return false;
			}

			BuyerPartyDetails byerDetails = finvoice.getBuyerPartyDetails();
			BuyerPostalAddressDetails postalAddrDetails = null;
			if(byerDetails != null)
			{
				postalAddrDetails = byerDetails.getBuyerPostalAddressDetails();
				if(postalAddrDetails == null)
				{
					return false;
				}
			}
			// setting each other table member to make one 2 one relationship
			byerDetails.setBuyerPostalAddressDetails(postalAddrDetails);
			postalAddrDetails.setBuyerPartyDetails(byerDetails);
			// write only new buyer
			if(!isBuyerExistInDatabase(byerDetails))
			{
				// this buyer is not in DB, so write it.
				buyerPartyDetailsDAO.save(byerDetails);
			}
			InvoiceDetails invoiceDetails = finvoice.getInvoiceDetails();

			// write new invoice only
			if(!isInvoiceExistInDatabase(invoiceDetails)){
				// this 
				if(invoiceDetails != null){
					invoiceDetails.setBuyerPartyDetails(buyerPartyDetailsDAO
									.getByBuyerPartyIdentifier(byerDetails.getBuyerPartyIdentifier()));
				}

				invoiceDetailsDAO.save(invoiceDetails);
				for(DefinitionDetails dd : invoiceDetails.getDefinitionDetails())
				{
					if(dd != null){
						dd.setInvoiceDetails(invoiceDetails);
						definitionDetailsDAO.save(dd);
					}
					else{
						return false;
					}
				}
			}
		}
		return true;
	}

	public Finvoices parseXmlFile(File xmlFile){
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Finvoices.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Finvoices fininvoices = (Finvoices) jaxbUnmarshaller.unmarshal(xmlFile);
			logger.info("Print: "+fininvoices);
			//getJsonData(fininvoices);
			return fininvoices;
		} catch (Exception e) {
			System.out.println("Unmarshaller STOP excption: " + e.getMessage());
			return null;
		}	
	}

	public Finvoices getFinvoicesoBbjectFromXml(File xmlFile){

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Finvoices.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Finvoices fininvoices = (Finvoices) jaxbUnmarshaller.unmarshal(xmlFile);
			//fillData(fininvoices);	
			String str = getJsonData(fininvoices);
			System.out.println(str);
			return fininvoices;
		} catch (Exception e) {
			System.out.println("Unmarshaller STOP excption: " + e.getMessage());
			return null;
		}
	}

/*
	public Finvoices parseXmlFile(String xmlFile) {
		return null;
	}*/

	public String getJsonData(Finvoices finvoices){
		ObjectMapper mapper = new ObjectMapper();

		try {

			// convert user object to json string,
			// mapper.writeValue(new File("test.json"), finvoices);
			System.out.println(mapper.writeValueAsString(finvoices));
			return mapper.writeValueAsString(finvoices);

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		catch(Exception ex){

		}

		return "";
	}	
}
