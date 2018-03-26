package com.ma.home.model;

import javax.xml.bind.annotation.XmlElement;


/**
 * 
 * This class SellerPartyDetails InvoiceDetails XML node.
 * 
 * @author Masum Islam
 *
 */
public class SellerPartyDetails {
	

	public SellerPartyDetails(){
		
	}
	
	/**
	* For annotation all subsequent properties name must be presented as same as in XML file. 
	*/	
		
	@XmlElement
    private String SellerPartyIdentifier;
	@XmlElement
    private String SellerOrganisationName;
	

    /**
    * All properties setter and getter 
    */
    public String getSellerPartyIdentifier ()
    {
        return SellerPartyIdentifier;
    }

    public void setSellerPartyIdentifier (String SellerPartyIdentifier)
    {
        this.SellerPartyIdentifier = SellerPartyIdentifier;
    }

    public String getSellerOrganisationName ()
    {
        return SellerOrganisationName;
    }
    

    public void setSellerOrganisationName (String SellerOrganisationName)
    {
        this.SellerOrganisationName = SellerOrganisationName;
    }
    
	@Override
	public String toString() {
		return "SellerPartyDetails [SellerPartyIdentifier="
				+ SellerPartyIdentifier + ", SellerOrganisationName="
				+ SellerOrganisationName + "]";
	}
        
}