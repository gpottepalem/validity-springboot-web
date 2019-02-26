package com.giri.validity.customer;

import java.util.List;

/**
 * Algorithm interface, defines interface for all supported algorithms
 *
 * @author gpottepalem
 * Created on Feb 25, 2019
 */
public interface Algorithm {
    /**
     * Computes, sets and returns phonetic phrase for a given customer.
     * Default implementation, simply uses {@link Customer#firstName} and {@link Customer#lastName} as phoneticPhrase
     * @param customer
     * @return phonetic phrase
     */
    default String computePhoneticPhrase(Customer customer) {
        customer.setPhoneticPhrase(customer.getFirstName() + customer.getLastName());
        return customer.getPhoneticPhrase();
    }

    /**
     * Given a list of customers, it processes and populates results
     * @param customers list of customers
     * @return the result
     */
    CustomerMatchResults processCustomers(List<Customer> customers);
}
