package com.giri.validity.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.http.MediaType.*;

/**
 * A controller mapped to end-point: /customer.
 *
 * @author gpottepalem
 * Created on Feb 26, 2019
 */
@RestController
public class CustomerController {

    @Autowired
    CustomerProcessorService customerProcessorService;

    @RequestMapping(value = "/customer", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE, TEXT_HTML_VALUE})
    public Object list(ModelAndView modelAndView, @RequestParam(required = false, defaultValue = "html") String format) {
        CustomerMatchResults results = null;
        try {
            results = customerProcessorService.processCustomerData("/normal.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.getModel().put("potentialDuplicateCustomers", results.getPotentialDuplicateCustomers());
        modelAndView.getModel().put("nonDuplicateCustomers", results.getNonDuplicates());

        if (format.equals("html")) {
            modelAndView.setViewName("list");
            return modelAndView;
        } else {
            return modelAndView.getModel();
        }
    }

}
