Jetty session clustering
========================

This is an example of Jetty 9.3 with session clustering enabled via JDBC.
First of all, download [Apache Derby][1] and start the network server on port 1527:

	$ java -jar derbyrun.jar server start

Then start an nginx server with the following configuration:

	events {
	    worker_connections  1024;
	}
	http {
	    upstream cluster1 {
	        ip_hash;
	        server localhost:8080;
	        server localhost:8081;
	        server localhost:8082;
	    }
	    server {
	        listen 80;
	        location / {
	            proxy_pass http://cluster1;
	            proxy_connect_timeout 10;
	        }
	    }
	}

Finally, run three Jetty instances:

	$ mvn jetty:run -Djetty.port=8080
	$ mvn jetty:run -Djetty.port=8081
	$ mvn jetty:run -Djetty.port=8082

The web page will be available at <http://localhost/>.
To check that the sessions are correctly persisted, mark the server assigned by the IP hash as `down`.
The next request should send you to another instance that keeps the same session attributes.
See the [official documentation][2] for further details.

  [1]: http://db.apache.org/derby/derby_downloads.html
  [2]: http://www.eclipse.org/jetty/documentation/current/session-clustering-jdbc.html
