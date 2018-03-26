package com.ma.home.dao;

import com.ma.home.model.BuyerPartyDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface CustomBuyerPartyDeatilsDAO {
        @Query("SELECT b FROM BuyerPartyDetails buyer WHERE LOWER(buyer.xmlFileName) = fileName")
        public List<BuyerPartyDetails> listByfileName(String fileName);

        @Query("SELECT b FROM BuyerPartyDetails buyer WHERE LOWER(buyer.buyerOrganisationName) = name")
        public BuyerPartyDetails getByBuyerPartyByName(String name);

    }
