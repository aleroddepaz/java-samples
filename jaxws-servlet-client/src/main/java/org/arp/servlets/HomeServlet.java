package org.arp.servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tempuri.ArrayOfPasoParada;
import org.tempuri.Dinamica;
import org.tempuri.DinamicaSoap;
import org.tempuri.PasoParada;

@WebServlet(urlPatterns = "/", loadOnStartup = 1)
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeServlet.class);

    private final DinamicaSoap port;

    private Properties loadProperties() {
        String configLocation = System.getProperty("configLocation");
        Properties prop = new Properties();
        try(InputStream is = new FileInputStream(configLocation)) {
            prop.load(is);
        } catch(IOException e) {
            LOGGER.error("No se encontró el fichero de configuración", e);
        }
        return prop;
    }

    public HomeServlet() {
        Dinamica service = new Dinamica();
        Properties prop = loadProperties();
        
        port = service.getDinamicaSoap();
        BindingProvider bp = (BindingProvider) port;
        String endpoint = prop.getProperty("dinamica.endpoint");
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final String linea = req.getParameter("linea");
        final String parada = req.getParameter("parada");
        req.setAttribute("linea", linea);
        req.setAttribute("parada", parada);
        try {
            Holder<Integer> status = new Holder<>();
            Holder<ArrayOfPasoParada> result = new Holder<>();
            port.getPasoParada(linea, parada, status, result);
            List<PasoParada> pasosParada = result.value.getPasoParada();
            req.setAttribute("pasosParada", pasosParada);
        } catch(RuntimeException e) {
            log("Error al llamar al servicio 'getPasoParada'", e);
            req.setAttribute("error", e.getLocalizedMessage());
        }
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, res);
    }

}
