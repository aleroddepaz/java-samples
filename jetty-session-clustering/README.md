Jetty session clustering
========================

This is an example of Jetty 9.3 with session clustering enabled via JDBC.
First of all, start an nginx server with the following configuration:

	events {
	    worker_connections  1024;
	}
	http {
	    upstream cluster1 {
	        server localhost:8080;
	        server localhost:8081;
	        server localhost:8082;
	    }
	    server {
	        listen 80;
	        location / {
	            proxy_pass http://cluster1;
	        }
	    }
	}

Then start the three Jetty instances:

	$ mvn jetty:run -Djetty.port=8080
	$ mvn jetty:run -Djetty.port=8081
	$ mvn jetty:run -Djetty.port=8082

The web page will be available at <http://localhost/>.
