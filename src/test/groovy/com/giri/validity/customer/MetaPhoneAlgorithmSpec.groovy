package com.giri.validity.customer

import spock.lang.Specification
import spock.lang.Subject

/**
 * Specification for {@link MetaPhoneAlgorithm}
 *
 * @author gpottepalem* Created on Feb 24, 2019
 */
class MetaPhoneAlgorithmSpec extends Specification{
    @Subject
    MetaPhoneAlgorithm algorithm = new MetaPhoneAlgorithm()

    void "Customer with null properties results with null phonetic phrase"() {
        when:
        Customer customer = new Customer()

        then: 'computed phoneticPhrase is null'
        !algorithm.computePhoneticPhrase(customer)
    }

    void "Customer with a firstName or lastName results with non null phonetic phrase"() {
        when:
        Customer customer = new Customer(firstName: 'cust1')

        then: 'computed phoneticPhrase is not null'
        algorithm.computePhoneticPhrase(customer)

        when:
        customer = new Customer(lastName: 'custLast1')

        then: 'computed phoneticPhrase is not null'
        algorithm.computePhoneticPhrase(customer)
    }

    void "Two customers with same phoneticPhrase are in one group"() {
        given:
        List<Customer> customers = [
                new Customer(firstName: 'cust1', lastName: 'custLast1'),
                new Customer(firstName: 'cust2', lastName: 'custLast2')
        ]

        when: 'Two customers with same phoneticPhrase are processed'
        def result = algorithm.processCustomers(customers)

        then: 'they get grouped into two'
        result.groupedCustomers.size() == 1

        and: 'each group contains exactly one customer'
        result.groupedCustomers.each { key, val ->
            val.size() > 0
        }
    }

    void "Three customers, two with same phoneticPhrase result in two different groups"() {
        given:
        List<Customer> customers = [
                new Customer(firstName: 'custOne', lastName: 'custLast1'),
                new Customer(firstName: 'custOn', lastName: 'custLast1'),
                new Customer(firstName: 'two', lastName: 'custLast2')
        ]

        when: 'Three customers, two with same phoneticPhrase are processed'
        def result = algorithm.processCustomers(customers)

        then: 'result contains two groups'
        result.groupedCustomers.size() == 2

        and: 'grouped customers contain 1 in one group and 2 in other'
        result.groupedCustomers.values()*.size().containsAll( [1,2] )
    }
}
