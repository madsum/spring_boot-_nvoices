package com.ma.home.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * 
 * This class represents Finvoice XML node.
 * 
 * @author Mausum Islam
 *
 */
public class Finvoice{
	
	public Finvoice(){
		
	}
	
	/**
	* For annotation all subsequent properties name must be presented as same as in XML file. 
	*/	
	
	@XmlElement	
	private String SellerOrganisationUnitNumber;
    @XmlElement	
    private String InvoiceRecipientLanguageCode;	
    @XmlElements({ @XmlElement(name = "SellerPartyDetails", type = SellerPartyDetails.class),
	@XmlElement(name = "finvoice", type = Finvoice.class) })
	private SellerPartyDetails sellerPartyDetails;	
	@XmlElements({ @XmlElement(name = "BuyerPartyDetails", type = BuyerPartyDetails.class),
	@XmlElement(name = "finvoice", type = Finvoice.class) })	
	private BuyerPartyDetails buyerPartyDetails;  
	@XmlElements({ @XmlElement(name = "InvoiceDetails", type = InvoiceDetails.class),
	@XmlElement(name = "finvoice", type = Finvoice.class) })	
	private InvoiceDetails invoiceDetails;	
    
   
	/**
    * All properties setter and getter 
    */
	public SellerPartyDetails getSellerPartyDetails() {
		return sellerPartyDetails;
	}
	
	public BuyerPartyDetails getBuyerPartyDetails() {
		return buyerPartyDetails;
	}
	
	public InvoiceDetails getInvoiceDetails() {
		return invoiceDetails;
	}	

    public String getSellerOrganisationUnitNumber ()
    {
        return SellerOrganisationUnitNumber;
    }

    public void setSellerOrganisationUnitNumber (String SellerOrganisationUnitNumber)
    {
        this.SellerOrganisationUnitNumber = SellerOrganisationUnitNumber;
    }

    public String getInvoiceRecipientLanguageCode ()
    {
        return InvoiceRecipientLanguageCode;
    }

    public void setInvoiceRecipientLanguageCode (String InvoiceRecipientLanguageCode)
    {
        this.InvoiceRecipientLanguageCode = InvoiceRecipientLanguageCode;
    } 
    
	@Override
	public String toString() {
		return "Finvoice [SellerOrganisationUnitNumber="
				+ SellerOrganisationUnitNumber
				+ ", InvoiceRecipientLanguageCode="
				+ InvoiceRecipientLanguageCode + ", sellerPartyDetails="
				+ sellerPartyDetails + ", buyerPartyDetails="
				+ buyerPartyDetails + ", invoiceDetails=" + invoiceDetails
				+ "]";
	}
    
}