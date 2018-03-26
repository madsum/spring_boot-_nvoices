package com.ma.home.service;

import com.ma.home.dao.BuyerPartyDetailsDAO;
import com.ma.home.dao.DefinitionDetailsDAO;
import com.ma.home.dao.InvoiceDetailsDAO;
import com.ma.home.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.List;
import java.util.regex.Pattern;

public class DatabaseRreaderWritterImpl implements  DatabaseRreaderWritter{

	
	@Autowired
	private InvoiceDetailsDAO invoiceDetailsDAO;

	@Autowired
	private DefinitionDetailsDAO definitionDetailsDAO;

	@Autowired
	private BuyerPartyDetailsDAO buyerPartyDetailsDAO;

	private static final Logger logger = LoggerFactory.getLogger(DatabaseRreaderWritterImpl.class);
	
	private String xmlFileName;
	private String absulateXmlFileName;

	public DatabaseRreaderWritterImpl(){
		
	}
	
	public String getAbsulateXmlFileName() {
		return absulateXmlFileName;
	}

	public void setAbsulateXmlFileName(String absulateXmlFileName) {
		this.absulateXmlFileName = absulateXmlFileName;
	}


	public String getXmlFileName() {
		return xmlFileName;
	}

	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public String uploadXmlFile(String fileName, byte[] bytes){
		xmlFileName = fileName.split(Pattern.quote("."))[0];

		try {	
			// Creating the directory to store file
			String rootPath = System.getProperty("catalina.home");
			if (rootPath==null || rootPath.isEmpty())
			{
				rootPath = "testng";
			}
			File dir = new File(rootPath + File.separator + "tmpFiles");
			if (!dir.exists())
				dir.mkdirs();
			absulateXmlFileName = dir.getAbsolutePath() + File.separator + fileName;
			File fh = new File(absulateXmlFileName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fh));
			stream.write(bytes);
			stream.close();
		} catch (FileNotFoundException e) {
			logger.info(e.getMessage());
			absulateXmlFileName = null;
		} catch (IOException e) {
			logger.info(e.getMessage());
			absulateXmlFileName = null;
		}
		return absulateXmlFileName;
	}
	
	private BuyerPartyDetails isBuyerExistInDatabase(BuyerPartyDetails byerDetails){
		// check does this buyer already in database
		List<BuyerPartyDetails> buyerList =  (List<BuyerPartyDetails>) buyerPartyDetailsDAO.findAll();
		logger.info("DB buyer list size = "+buyerList.size());

		for(BuyerPartyDetails buyer : buyerList){
			if(buyer == null){
				return null;
			}		
			logger.info("BuyerPartyIdentifier from DB ="+buyer.getBuyid());

			if(buyer.getBuyid() != null && byerDetails.getBuyerPartyIdentifier() != null){
				if(buyer.getBuyid().compareToIgnoreCase(byerDetails.getBuyerPartyIdentifier()) == 0){
					// buyer found in DB 
					return buyer;
				}
			}else if(buyer.getBuyerOrganisationName() != null && byerDetails.getBuyerOrganisationName() != null){
				// buyer id is not present. So check buyer name.
				if(buyer.getBuyerOrganisationName().compareToIgnoreCase(byerDetails.getBuyerOrganisationName()) == 0){
					// buyer found in DB 
					return buyer;
				}				
			}
		}
		return null;
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
			if(invo.getInvoiceNumber() != null && invoice.getInvoiceNumber() != null){
				if(invo.getInvoiceNumber().compareToIgnoreCase(invoice.getInvoiceNumber()) == 0){
					// invoice found in DB 
					return true;
				}
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
			byerDetails.setXmlFileName(xmlFileName);
			
			// check does BuyerPartyDetails already in database if exist returns existed DB item 
			//if not exist it return null. Then we write in DB.
			BuyerPartyDetails newByerDetails = isBuyerExistInDatabase(byerDetails);
			// write only new buyer
			if(newByerDetails == null)
			{
				newByerDetails = byerDetails;
				// this buyer is not in DB, so write it.
				buyerPartyDetailsDAO.save(newByerDetails);
			}
			InvoiceDetails invoiceDetails = finvoice.getInvoiceDetails();
			invoiceDetails.setXmlFileName(xmlFileName);
			// write new invoice only
			if(!isInvoiceExistInDatabase(invoiceDetails)){
				// this 
				if(invoiceDetails != null){
					invoiceDetails.setBuyerPartyDetails(newByerDetails);
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
}
