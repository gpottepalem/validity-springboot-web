package com.giri.validity.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Holds results of processed customer records
 *
 * @author gpottepalem
 * Created on Feb 25, 2019
 */
public class CustomerMatchResults {

    /**
     * Map of grouped {@link Customer}s with {@link Customer#phoneticPhrase} key, and list of customers with the same
     * {@link Customer#phoneticPhrase} as value
     * */
    private Map<String, List<Customer>> groupedCustomers = new HashMap<>();

    // Getters and setters
    public Map<String, List<Customer>> getGroupedCustomers() {
        return groupedCustomers;
    }

    public void setGroupedCustomers(Map<String, List<Customer>> groupedCustomers) {
        this.groupedCustomers = groupedCustomers;
    }

    /**
     * Returns all potential duplicate customers found, returns only the first of each pair of potential duplicates
     * @return list of potential duplicate customers
     */
    public List<Customer> getPotentialDuplicateCustomers() {
        return groupedCustomers.entrySet()
                        .stream()
                        .filter(e-> e.getValue().size() > 1)
                        .map(e-> e.getValue().get(0))
                        .collect(Collectors.toList());
    }

    /**
     * Returns all non-duplicate customers found
     * @return list of non-duplicate customers
     */
    public List<Customer> getNonDuplicates() {
        return groupedCustomers.entrySet()
                .stream()
                .filter(e-> e.getValue().size() == 1)
                .map(e-> e.getValue().get(0))
                .collect(Collectors.toList());
    }
}
