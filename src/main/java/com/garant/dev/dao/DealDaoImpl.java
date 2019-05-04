package com.garant.dev.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.garant.dev.model.Deal;
import com.garant.dev.model.User;

@Repository("dealDao")
public class DealDaoImpl extends AbstractDao<Integer, Deal> implements DealDao{

static final Logger logger = LoggerFactory.getLogger(DealDaoImpl.class);
    
    public Deal findById(int id) {
        Deal deal = getByKey(id);
        return deal;
    }
    
    @SuppressWarnings("unchecked")
    public List<Deal> findAllDeals() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Deal> deals = (List<Deal>) criteria.list();
        return deals;
    }
    
    @SuppressWarnings("unchecked")
    public List<Deal> findAllDealsSeller(int sellerid) {
    		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
    		criteria.add(Restrictions.eq("seller.id", sellerid));
        List<Deal> deals = (List<Deal>) criteria.list();
        return deals;
    }
    
    @SuppressWarnings("unchecked")
    public List<Deal> findAllDealsBuyer(int buyerid) {
    		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
    	   	criteria.add(Restrictions.eq("buyer.id", buyerid));
        List<Deal> deals = (List<Deal>) criteria.list();
        return deals;
    }
 
    public void save(Deal deal) {
        persist(deal);
    }
 
    public void deleteById(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Deal deal = (Deal)crit.uniqueResult();
        delete(deal);
    }
    
}
