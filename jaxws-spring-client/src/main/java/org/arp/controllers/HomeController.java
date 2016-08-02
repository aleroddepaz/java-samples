package org.arp.controllers;

import java.util.Date;
import java.util.List;

import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.tempuri.ArrayOfPasoParada;
import org.tempuri.DinamicaSoap;
import org.tempuri.PasoParada;

@Controller
@RequestMapping("/")
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    static final String HOME_VIEW = "home";

    private final DinamicaSoap port;

    public HomeController(DinamicaSoap port) {
        this.port = port;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get() {
        ModelAndView model = new ModelAndView(HOME_VIEW);
        model.addObject("now", new Date());
        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView post(@RequestParam(name = "linea") String linea, @RequestParam(name = "parada") String parada) {
        ModelAndView model = new ModelAndView(HOME_VIEW);
        model.addObject("now", new Date());
        model.addObject("linea", linea);
        model.addObject("parada", parada);

        try {
            Holder<Integer> status = new Holder<>();
            Holder<ArrayOfPasoParada> resultados = new Holder<>();
            port.getPasoParada(linea, parada, status, resultados);
            List<PasoParada> pasosParada = resultados.value.getPasoParada();
            model.addObject("pasosParada", pasosParada);
        } catch (RemoteAccessException e) {
            LOGGER.error("Error al llamar al servicio 'getPasoParada'", e);
            model.addObject("error", e.getLocalizedMessage());
        }
        return model;
    }

}
