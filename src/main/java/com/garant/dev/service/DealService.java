package com.garant.dev.service;

import java.util.List;

import com.garant.dev.model.Deal;
import com.garant.dev.model.User;

public interface DealService {
 
    Deal findById(int id);
     
    void saveDeal(Deal deal);
     
    void deleteDealById(int id);
    
    void updateDeal(Deal deal);
 
    List<Deal> findAllDeals(); 
    
    List<Deal> findAllDealsBuyer(int id);
    
    List<Deal> findAllDealsSeller(int id);
     
}
