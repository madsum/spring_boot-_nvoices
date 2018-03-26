package com.ma.home.dao;

import com.ma.home.model.InvoiceDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvoiceDetailsDAO extends CrudRepository<InvoiceDetails, Long> {
/*	public List<InvoiceDetails> list();
	public InvoiceDetails get(int id);
	public void saveOrUpdate(InvoiceDetails user);
	public void delete(int id);
	// gets an InvoiceDetails by given buyer databse id
	public List<InvoiceDetails> getByBuyerId(int id);*/
}