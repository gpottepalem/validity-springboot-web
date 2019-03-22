package com.giri.validity.customer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Provides implementation for loading and processing customer data.
 *
 * @author gpottepalem
 * Created on Feb 25, 2019
 */
@Service
public class CustomerProcessorService {
    Algorithm algorithm = new MetaPhoneAlgorithm();

    /**
     * Loads, sorts and returns customers from the given csv file
     *
     * @param customersDataFile process given file found in the classpath, if null defaults to /normal.csv
     * @return list of customers loaded, sorted by firstName, lastName, phone, email
     */
    public List<Customer> loadCustomers(String customersDataFile) throws IOException, URISyntaxException {
        if (customersDataFile == null) {
            customersDataFile = "/normal.csv";
        }
        InputStream is = this.getClass().getResourceAsStream(customersDataFile);
        if (is == null) {
            return null;
        }
        Reader reader = new BufferedReader(new InputStreamReader(is));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

        List<Customer> customers = new ArrayList<>();
        Customer customer = null;
        for(CSVRecord csvRecord : csvParser) {
            customer = new Customer();
            customer.setLoadedId(Integer.parseInt(csvRecord.get(0))); // glitch
            customer.setFirstName(csvRecord.get("first_name"));
            customer.setLastName(csvRecord.get("last_name"));
            customer.setCompany(csvRecord.get("company"));
            customer.setEmail(csvRecord.get("email"));
            customer.setAddress1(csvRecord.get("address1"));
            customer.setAddress2(csvRecord.get("address2"));
            customer.setZip(csvRecord.get("zip"));
            customer.setCity(csvRecord.get("city"));
            customer.setState(csvRecord.get("state_long"));
            customer.setStateCode(csvRecord.get("state"));
            customer.setPhone(csvRecord.get("phone"));

            customers.add(customer);
        }

        Collections.sort(customers);
        return customers;
    }

    /**
     * Processes customers loaded from the given file and returns results
     *
     * @param customersDataFile optional, defaulted to process normal.csv file found in the classpath
     * @return results
     */
    public CustomerMatchResults processCustomerData(String customersDataFile) throws IOException, URISyntaxException {
        if (customersDataFile == null) {
            customersDataFile = "/normal.csv";
        }
        List<Customer> customers = loadCustomers(customersDataFile);
        return algorithm.processCustomers(customers);
    }

}
