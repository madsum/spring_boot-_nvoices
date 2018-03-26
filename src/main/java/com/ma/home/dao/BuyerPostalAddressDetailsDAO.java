package com.ma.home.dao;

import com.ma.home.model.BuyerPostalAddressDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerPostalAddressDetailsDAO extends CrudRepository<BuyerPostalAddressDetails, Long> {

}