package com.giri.validity.customer

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

/**
 * Specification for {@link CustomerProcessorService}.
 *
 * Equivalent to {@link CustomerProcessorServiceTests}. Just to demonstrate how readable a test specification is and how
 * powerful & simple it is writing specification for data-driven tests.
 *
 * @author gpottepalem
 * Created on Feb 24, 2019
 */
class CustomerProcessorServiceSpec extends Specification {

    @Subject
    CustomerProcessorService service = new CustomerProcessorService()

    @Unroll
    void "Existing customer data file:#testFile contains #expectedRecords records"() {
        expect:
        service.loadCustomers(testFile).size() == expectedRecords

        where:
        testFile        || expectedRecords
        '/normal.csv'   || 106
        '/advanced.csv' || 109
    }

    void "When customer data file is processed, results get populated with data"() {
        when: 'customer data file is processed'
        CustomerMatchResults results = service.processCustomerData('/normal.csv')

        then: 'no exception occurs'
        noExceptionThrown()

        and: 'results contains customers'
        results.groupedCustomers.size() > 1
    }

    void "Non-existing customer data file should result in no customers in the result"() {
        when: 'non existing file is taken for processing'
        CustomerMatchResults results = service.processCustomerData('/not-found.csv')

        then: 'no exception occurs'
        noExceptionThrown()

        and: 'result should have 0 customers'
        results.groupedCustomers.size() == 0
    }
}
