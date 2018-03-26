package com.ma.home.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 
 * This class represents Finvoices XML node. This is root node.
 *  
 * @author Masum Islma
 *
 */
@XmlRootElement(name="Finvoices")
public class Finvoices {
	
	public Finvoices(){
		
	}
	
	/**
	* For annotation all subsequent properties name must be presented as same as in XML file. 
	*/
	
	@XmlElements({ @XmlElement(name = "Finvoice", type = Finvoice.class),
	@XmlElement(name = "Finvoices", type = Finvoices.class) })
	public List<Finvoice> finvoices ;	
	
	public List<Finvoice> getFinvoices() {
		return finvoices;
	}
	
	@Override
	public String toString() {
		return "Finvoices [finvoices=" + finvoices + "]";
	}
	
}