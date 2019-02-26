# Overview
[![Build Status](https://travis-ci.org/gpottepalem/validity-springboot-web.svg?branch=master)](https://travis-ci.org/gpottepalem/validity-springboot-web)

This is a simple Spring Boot 2.1.3 web application with one controller (`CustomerController`) with one action (`list`) 
which loads a csv file with customer data, processes it and lists potential duplicates and non-duplicates found.

This is the same Grails app https://github.com/gpottepalem/validity-grails-web written in Java with Spring Boot for 
comparing efforts and learning curve.

## Installation
Nothing really is needed.

## Running the app
Simply run the following command from the main project directory:   
`./gradlew bootRun`

## Test the end-point
**End-point: `customer`**  
List customers data processed (html): `curl http://localhost:8080/customer`  
List customers data processed (json): `curl http://localhost:8080/customer?format=json`  
List customers data processed (xml) : `curl http://localhost:8080/customer?format=xml`  

## Tests
### Running
Simply run the following command from the main project directory to run all unit/integration/functional tests:   
`./gradlew clean test`
### Test Reports
Simply run the following command from the main project directory or point your browser to the following
`build/reports/...` directory:  
`open ./build/reports/tests/test/index.html`  