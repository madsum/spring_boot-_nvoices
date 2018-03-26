package com.ma.home.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "buyerPostalAddressDetails")
public class BuyerPostalAddressDetails {

    int buyerPartyDetailsId;

    @XmlElement
    private String BuyerTownName;

    @XmlElement
    private String BuyerPostCodeIdentifier;

    @XmlElement
    private String BuyerStreetName;

    private BuyerPartyDetails buyerPartyDetails;


    // must annotate in getter method for database
    // buyerPostalAddressDetails table has one to one relation with buyerPartyDetails table.
    // buyerPostalAddressDetails.BuyerPartyDetails_id table column.
    @GenericGenerator(name = "generator", strategy = "foreign",
    parameters = @Parameter(name = "property", value = "buyerPartyDetails"))
    @Id
    @GeneratedValue(generator = "generator")
    public int getBuyerPartyDetails_id() {
        return buyerPartyDetailsId;
    }

    public void setBuyerPartyDetails_id(int BuyerPartyDetails_id) {
        BuyerPartyDetails_id = BuyerPartyDetails_id;
    }

    public String getBuyerTownName() {
        return BuyerTownName;
    }

    public void setBuyerTownName(String buyerTownName) {
        BuyerTownName = buyerTownName;
    }

    public String getBuyerPostCodeIdentifier() {
        return BuyerPostCodeIdentifier;
    }

    public void setBuyerPostCodeIdentifier(String buyerPostCodeIdentifier) {
        BuyerPostCodeIdentifier = buyerPostCodeIdentifier;
    }

    public String getBuyerStreetName() {
        return BuyerStreetName;
    }

    public void setBuyerStreetName(String buyerStreetName) {
        BuyerStreetName = buyerStreetName;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public BuyerPartyDetails getBuyerPartyDetails() {
        return buyerPartyDetails;
    }

    public void setBuyerPartyDetails(BuyerPartyDetails buyerPartyDetails) {
        this.buyerPartyDetails = buyerPartyDetails;
    }

}
