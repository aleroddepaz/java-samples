package org.arp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Holder;

import org.tempuri.ArrayOfPasoParada;
import org.tempuri.DinamicaSoap;
import org.tempuri.PasoParada;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    static final String INDEX_VIEW = "/WEB-INF/index.jsp";

    private final DinamicaSoap port;

    @Inject
    public HomeServlet(DinamicaSoap port) {
        this.port = port;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher(INDEX_VIEW).forward(req, res);
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
        req.getRequestDispatcher(INDEX_VIEW).forward(req, res);
    }

}
