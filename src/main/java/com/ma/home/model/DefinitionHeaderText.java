package com.ma.home.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * This class represents DefinitionHeaderText XML node.
 * 
 * @author Masum Islam
 *
 */
public class DefinitionHeaderText {
	
	public DefinitionHeaderText(){
		
	}	
	
	/**
	* For annotation all subsequent properties name must be presented as same as in XML file. 
	*/
	
	// It holds DefinitionHeaderText node's content
	private String content;
	// It holds DefinitionHeaderText node's attribute
	private String DefinitionCode;

	public String getDefinitionCode() {
		return DefinitionCode;
	}
	
	@XmlAttribute(name="DefinitionCode")
	public void setDefinitionCode(String DefinitionCode) {
		this.DefinitionCode = DefinitionCode;
		System.out.println("DefinitionCode: "+DefinitionCode);
	}
	
	
	public String getContent() {
		return content;
	}
	
	@XmlValue
	public void setContent(String con) {
		content = con;
	}	
		
	@Override
	public String toString() {
		return "DefinitionHeaderText [content=" + content + ", DefinitionCode="
				+ DefinitionCode + "]";
	}	
}