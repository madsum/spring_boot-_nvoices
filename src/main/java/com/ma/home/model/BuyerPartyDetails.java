package com.ma.home.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "buyerPartyDetails")
public class BuyerPartyDetails {
    /**
     * For annotation all subsequent properties name must be presented as same as in XML file.
     */

    // for buyerPartyDetails table primary key
    int buyerPartyDetailsId;

    // Xml node and buyerPartyDetails.BuyerOrganisationName column
    @XmlElement
    private String BuyerOrganisationName;

    // Xml node and buyerPartyDetails.BuyerPartyIdentifier column
    @XmlElement
    //@Transient
    private String BuyerPartyIdentifier;
    // it is for database column name. Otherwise it conflicts with xml annotation
    private String buyid;

    @Column(name = "buyerPartyIdentifier")
    public String getBuyid() {
        return buyid;
    }

    public void setBuyid(String buyid) {
        this.buyid = buyid;
    }


    // Xml node and buyerPostalAddressDetails table
    // Set provides annotation for xml parser.
    // Get provides annotation for Database framework (Entity).
    private BuyerPostalAddressDetails buyerPostalAddressDetails;


    // Xml node and invoiceDetails table
    private Set<InvoiceDetails> invoiceDetails = new HashSet<InvoiceDetails>(0);
    // must annotate in getter method for database
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buyerPartyDetails")
    public Set<InvoiceDetails> getInvoiceDetails() {
        return invoiceDetails;
    }

    @XmlElements({ @XmlElement(name = "buyerPostalAddressDetails", type = BuyerPartyDetails.class),
            @XmlElement(name = "buyerPostalAddressDetails", type = BuyerPartyDetails.class) })
    public void setInvoiceDetails(Set<InvoiceDetails> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    //@Column(name = "XmlFileName")
    String xmlFileName;


    @Column(name = "XmlFileName")
    public String getXmlFileName() {
        return xmlFileName;
    }

    public void setXmlFileName(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    /**
     * All properties setter and getter
     */

    // must annotate in getter method for database
    // buyerPartyDetails.BuyerPartyDetails_id table column.
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "buyerPartyDetails_id", unique = true, nullable = false)
    public int getBuyerPartyDetails_id() {
        return buyerPartyDetailsId;
    }


    public void setBuyerPartyDetails_id(int buyerPartyDetails_id) {
        buyerPartyDetailsId = buyerPartyDetails_id;
    }

    // must annotate in getter method for database
    // buyerPartyDetails table has one to one relation with buyerPostalAddressDetails table
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "buyerPartyDetails", cascade = CascadeType.ALL)
    public BuyerPostalAddressDetails getBuyerPostalAddressDetails() {
        return buyerPostalAddressDetails;
    }

    @XmlElements({ @XmlElement(name = "BuyerPostalAddressDetails", type = BuyerPostalAddressDetails.class),
            @XmlElement(name = "BuyerPartyDetails", type = BuyerPartyDetails.class) })
    public void setBuyerPostalAddressDetails(
            BuyerPostalAddressDetails buyerPostalAddressDetails) {
        this.buyerPostalAddressDetails = buyerPostalAddressDetails;
    }

    // must annotate in getter method for database
    // buyerPartyDetails.BuyerOrganisationName column
    @Column(name = "buyerOrganisationName")
    public String getBuyerOrganisationName ()
    {
        return BuyerOrganisationName;
    }

    public void setBuyerOrganisationName (String BuyerOrganisationName)
    {
        this.BuyerOrganisationName = BuyerOrganisationName;
    }

    // must annotate in getter method for database
    // buyerPartyDetails.BuyerPartyIdentifier column
    //@Column(name = "BuyerPartyIdentifier")
    //@Transient
    public String getBuyerPartyIdentifier ()
    {
        return BuyerPartyIdentifier;
    }

    //@Transient
    @XmlElement(name = "BuyerPartyIdentifier")
    public void setBuyerPartyIdentifier (String BuyerPartyIdentifier)
    {
        this.BuyerPartyIdentifier = BuyerPartyIdentifier;
        buyid = BuyerPartyIdentifier;
    }


    @Override
    public String toString() {
        return "BuyerPartyDetails [buyerPostalAddressDetails="
                + buyerPostalAddressDetails + ", BuyerOrganisationName="
                + BuyerOrganisationName + ", BuyerPartyIdentifier="
                + BuyerPartyIdentifier + "]";
    }
}