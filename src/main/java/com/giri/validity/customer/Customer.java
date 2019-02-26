package com.giri.validity.customer;

/**
 * Customer domain object, holds details of a customer
 *
 * @author gpottepalem
 * Created on Feb 25, 2019
 */
public class Customer implements Comparable<Customer> {
    private int loadedId;
    private String firstName;
    private String lastName;
    private String company;
    private String email;
    private String address1;
    private String address2;
    private String zip;
    private String city;
    private String state;
    private String stateCode;
    private String phone;

    /** Computed phonetic phrase */
    private String phoneticPhrase;

    // getters and setters
    public int getLoadedId() {
        return loadedId;
    }

    public void setLoadedId(int loadedId) {
        this.loadedId = loadedId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneticPhrase() {
        return phoneticPhrase;
    }

    public void setPhoneticPhrase(String phoneticPhrase) {
        this.phoneticPhrase = phoneticPhrase;
    }

    /**
     * Returns full name, just lastName appended to firstName
     * @return
     */
    public String getName() {
        return firstName + lastName;
    }

    @Override
    public String toString() {
        return loadedId + "," + firstName + "," + lastName + "," + company + "," + email + "," +
                address1 + "," + address2 + "," + zip + "," + city + "," + state + "," + stateCode + "," + phone;
    }

    @Override
    public int compareTo(Customer o) {
        int result = firstName.compareTo(o.getFirstName());
        if (result != 0) {
            return result;
        }
        result = lastName.compareTo(o.getLastName());
        if (result != 0) {
            return result;
        }
        result = phone.compareTo(o.getPhone());
        if (result != 0) {
            return result;
        }
        return email.compareTo(o.getEmail());
    }
}
