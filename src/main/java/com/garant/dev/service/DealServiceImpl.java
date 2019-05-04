package com.garant.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.garant.dev.dao.DealDao;
import com.garant.dev.model.Deal;
import com.garant.dev.model.User;

@Service("dealService")
@Transactional
public class DealServiceImpl implements DealService{

	@Autowired
	private DealDao dao;

	public Deal findById(int id) {
		return dao.findById(id);
	}

	public void saveDeal(Deal deal) {
		dao.save(deal);
	}

	public List<Deal> findAllDeals() {
		return dao.findAllDeals();
	}

	public void deleteDealById(int id) {
		dao.deleteById(id);
		
	}
	
	public void updateDeal(Deal deal) {
        Deal entity = dao.findById(deal.getId());
        if(entity!=null){
            entity.setSubject(deal.getSubject());
            entity.setQuantity(deal.getQuantity());
            entity.setSum(deal.getSum());
            entity.setWeight(deal.getWeight());
            entity.setComplect(deal.getComplect());
            entity.setTerms(deal.getTerms());
        }
    }

	public List<Deal> findAllDealsSeller(int id) {
		return dao.findAllDealsSeller(id);
	}
	
	public List<Deal> findAllDealsBuyer(int id) {
		return dao.findAllDealsBuyer(id);
	}
}
