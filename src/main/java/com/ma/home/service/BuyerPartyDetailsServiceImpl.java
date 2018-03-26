package com.ma.home.service;

import com.ma.home.dao.BuyerPartyDetailsDAO;
import com.ma.home.exception.BuyerNotFound;
import com.ma.home.model.BuyerPartyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BuyerPartyDetailsServiceImpl{


	@Autowired
	private BuyerPartyDetailsDAO buyerPartyDetailsDAO2;

	public BuyerPartyDetailsServiceImpl() {

	}

//	public BuyerPartyDetailsServiceImpl(SessionFactory sessionFactory) {
		//this.sessionFactory = sessionFactory;
//	}

	@Transactional
	public List<BuyerPartyDetails> list() {
		@SuppressWarnings("unchecked")

		List<BuyerPartyDetails> listUser = (List<BuyerPartyDetails>) buyerPartyDetailsDAO2.findAll();// sessionFactory.getCurrentSession()
		//.createCriteria(BuyerPartyDetails.class)
		//.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listUser;
	}

	@Transactional
	public void saveOrUpdate(BuyerPartyDetails buyerPartyDetails) {
		buyerPartyDetailsDAO2.save(buyerPartyDetails);
		//sessionFactory.getCurrentSession().saveOrUpdate(buyerPartyDetails);
	}

	@Transactional(rollbackFor=BuyerNotFound.class)
	public BuyerPartyDetails delete(int id) {
		BuyerPartyDetails buyerToDelete = buyerPartyDetailsDAO2.findById(Integer.toUnsignedLong(id))
				.orElseThrow(() -> new BuyerNotFound());
		buyerPartyDetailsDAO2.delete(buyerToDelete);
		//BuyerPartyDetails buyerToDelete = this.get(id);
		//sessionFactory.getCurrentSession().delete(buyerToDelete);
		return buyerToDelete;
	}
/*
	@Transactional(rollbackFor=BuyerNotFound.class)
	public BuyerPartyDetails get(int id) {
		String hql = "from BuyerPartyDetails where BuyerPartyDetails_id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);


		@SuppressWarnings("unchecked")
		List<BuyerPartyDetails> listUser = (List<BuyerPartyDetails>) query.list();

		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		return null;
	}
*/
	@Transactional(rollbackFor=BuyerNotFound.class)
	public BuyerPartyDetails getByBuyerPartyIdentifier(String buyer_id)
	{
		BuyerPartyDetails buyerPartyDetails = buyerPartyDetailsDAO2.findById(Long.parseLong(buyer_id))
				.orElseThrow(() -> new BuyerNotFound());
		return  buyerPartyDetails;
		/*
		Query query = sessionFactory.getCurrentSession().createQuery("from BuyerPartyDetails as buyer where buyer.buyid =?");
		query.setString(0,buyer_id); 

		@SuppressWarnings("unchecked")
		List<BuyerPartyDetails> listUser = (List<BuyerPartyDetails>) query.list();

		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		return null;
		*/
	}

	@Transactional(rollbackFor=BuyerNotFound.class)
	public List<BuyerPartyDetails> listByfileName(String fileName){
		List<BuyerPartyDetails> buyerList = buyerPartyDetailsDAO2.listByfileName(fileName);
		if (buyerList != null && !buyerList.isEmpty()) {
			return buyerList;
		}
		return null;
		/*
		Query query = sessionFactory.getCurrentSession().createQuery("from BuyerPartyDetails as buyer where buyer.xmlFileName =?");
		query.setString(0,fileName); 

		@SuppressWarnings("unchecked")
		List<BuyerPartyDetails> buyerList = (List<BuyerPartyDetails>) query.list();

		if (buyerList != null && !buyerList.isEmpty()) {
			return buyerList;
		}
		return null;
		*/
	}

	@Transactional(rollbackFor=BuyerNotFound.class)
	public BuyerPartyDetails getByBuyerPartyByName(String name){

		BuyerPartyDetails buyerPartyDetails = buyerPartyDetailsDAO2.getByBuyerPartyByName(name);
		return  buyerPartyDetails;
		/*
		Query query = sessionFactory.getCurrentSession().createQuery("from BuyerPartyDetails as buyer where buyer.buyerOrganisationName =?");
		query.setString(0,name); 		
		@SuppressWarnings("unchecked")
		List<BuyerPartyDetails> buyerList = (List<BuyerPartyDetails>) query.list();

		if (buyerList != null && !buyerList.isEmpty()) {
			return buyerList.get(0);
		}
		*/
		//return null;
	}

}