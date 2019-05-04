package com.garant.dev.dao;

import java.util.List;

import com.garant.dev.model.Deal;

public interface DealDao {
	
	Deal findById(int id);
     
    void save(Deal deal);
     
    void deleteById(int id);
     
    List<Deal> findAllDeals();
    
    List<Deal> findAllDealsBuyer(int id);
    
    List<Deal> findAllDealsSeller(int id);

}
