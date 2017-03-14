package org.arp.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.ws.BindingProvider;

import org.tempuri.Dinamica;
import org.tempuri.DinamicaSoap;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class DinamicaModule extends AbstractModule {

    @Provides
    DinamicaSoap getDinamicaSoapClient(@Named("dinamica.endpoint") String endpoint) {
        DinamicaSoap port = new Dinamica().getDinamicaSoap();
        BindingProvider bp = (BindingProvider) port;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        return port;
    }

    @Override
    protected void configure() {
        String configLocation = System.getProperty("configLocation", "conf/app.properties");
        Properties prop = new Properties();
        try (InputStream is = new FileInputStream(configLocation)) {
            prop.load(is);
            Names.bindProperties(binder(), prop);
        } catch (IOException e) {
            this.addError(e);
        }
    }

}
