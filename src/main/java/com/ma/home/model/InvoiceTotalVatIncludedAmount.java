package com.ma.home.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * This class InvoiceTotalVatIncludedAmount InvoiceDetails XML node.
 * 
 * @author Masum Islam
 *
 */
public class InvoiceTotalVatIncludedAmount {
	
	
	public InvoiceTotalVatIncludedAmount(){
		
	}
	
	/**
	* For annotation all subsequent properties name must be presented as same as in XML file. 
	*/
	
	@XmlValue
    private String content;
    private String amountCurrencyIdentifier;	
	
	/**
     * All properties setter and getter 
     */
	public String getAmountCurrencyIdentifier() {
		return amountCurrencyIdentifier;
	}
	
	@XmlAttribute(name="AmountCurrencyIdentifier")
	public void setAmountCurrencyIdentifier(String amountCurrencyIdentifier) {
		this.amountCurrencyIdentifier = amountCurrencyIdentifier;
	}
	
	public String getContent() {
		return content;
	}
	
	@Override
	public String toString() {
		return "InvoiceTotalVatIncludedAmount [content=" + content
				+ ", amountCurrencyIdentifier=" + amountCurrencyIdentifier
				+ "]";
	}	
			
}