package org.arp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Holder;

import org.tempuri.ArrayOfPasoParada;
import org.tempuri.Dinamica;
import org.tempuri.DinamicaSoap;
import org.tempuri.PasoParada;

@WebServlet(urlPatterns = "/", loadOnStartup = 1)
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final Dinamica service = new Dinamica();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final String linea = req.getParameter("linea");
        final String parada = req.getParameter("parada");
        log("Consultando estimaciones de la línea '" + linea + "' por la parada '" + parada + "'");

        Holder<Integer> status = new Holder<>();
        Holder<ArrayOfPasoParada> result = new Holder<>();
        DinamicaSoap port = service.getDinamicaSoap();
        port.getPasoParada(linea, parada, status, result);
        log("Status: " + status.value);

        List<PasoParada> pasosParada = result.value.getPasoParada();
        req.setAttribute("linea", linea);
        req.setAttribute("parada", parada);
        req.setAttribute("pasosParada", pasosParada);
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, res);
    }

}
