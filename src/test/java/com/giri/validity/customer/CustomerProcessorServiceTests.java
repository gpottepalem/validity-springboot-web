package com.giri.validity.customer;

import org.junit.Test;


import java.util.List;

import static org.junit.Assert.*;

/**
 * A Junit test case for {@link CustomerProcessorService}
 *
 * @author gpottepalem
 * Created on Feb 26, 2019
 */
public class CustomerProcessorServiceTests {

    CustomerProcessorService service = new CustomerProcessorService();

    @Test
    public void normalDataFileLoadedShouldHaveExpectedNumberOfCustomers() {
        try {
            assertEquals(service.loadCustomers("/normal.csv").size(), 106);
        } catch (Exception e) {
            assertThat(e, null);
        }
    }

    @Test
    public void advancedDataFileLoadedShouldHaveExpectedNumberOfCustomers() {
        try {
            assertEquals(service.loadCustomers("/advanced.csv").size(), 109);
        } catch (Exception e) {
            assertThat(e, null);
        }
    }

    @Test
    public void existingCustomerDataFileProcessedShouldResultWithNoExceptionAndExpectedCustomers() {
        CustomerMatchResults results = null;
        try {
            results = service.processCustomerData("/normal.csv");
        } catch (Exception e) {
            assertThat(e, null);
        }
        assertEquals(results.getGroupedCustomers().size() > 0, true);
        assertEquals(results.getPotentialDuplicateCustomers().size() > 0, true);
        assertEquals(results.getNonDuplicates().size() > 0, true);
    }

    @Test
    public void nonExistingCustomerDataFileProcessedShouldResultWithNoExceptionAndZeroCustomers() {
        CustomerMatchResults results = null;
        try {
            results = service.processCustomerData("/not-found.csv");
        } catch (Exception e) {
            assertThat(e, null);
        }
        assertEquals(results.getGroupedCustomers().size(), 0);
    }
}
