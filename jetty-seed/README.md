# jetty-seed

This is an skeleton for a JavaEE webapp with the following technologies:

* Servlet 3.1 (Jetty 9.3)
* JUnit
* Selenium

Add the following Git pre-commit hook to run unit and integration tests:

	#!/bin/bash
	mvn -q verify
