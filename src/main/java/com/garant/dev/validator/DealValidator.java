package com.garant.dev.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.garant.dev.model.Deal;
import com.garant.dev.model.User;
import com.garant.dev.service.DealService;
import com.garant.dev.service.UserService;

/**
 * Validator for {@link net.proselyte.springsecurityapp.model.Deal} class,
 * implements {@link Validator} interface.
 *
 * @author Anna Likhachova
 * @version 1.0
 */

@Component
public class DealValidator implements Validator{

	@Autowired
    private DealService dealService;
	
	@Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Deal.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Deal deal = (Deal) o;
     
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "Required");
        if (deal.getSubject().length() < 1 || deal.getSubject().length() > 50) {
            errors.rejectValue("subject", "Size.deals.subject");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "Required");
        if (deal.getQuantity().length() < 1 || deal.getQuantity().length() > 50) {
            errors.rejectValue("quantity", "Size.deals.quantity");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "complect", "Required");
        if (deal.getComplect().length() < 1 || deal.getComplect().length() > 50) {
            errors.rejectValue("complect", "Size.deals.complect");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "Required");
        if (deal.getWeight().length() < 1 || deal.getWeight().length() > 50) {
            errors.rejectValue("weight", "Size.deals.weight");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "additionaly", "Required");
        if (deal.getAdditionaly().length() < 1 || deal.getAdditionaly().length() > 200) {
            errors.rejectValue("additionaly", "Size.deals.additionaly");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sum", "Required");
        if (!String.valueOf(deal.getSum()).matches("/^\\d+$/")) {
            errors.rejectValue("sum", "Digit.deal.sum.regex");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "terms", "Required");
       
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "buyer", "Required");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "seller", "Required");
       
        
    }
}
