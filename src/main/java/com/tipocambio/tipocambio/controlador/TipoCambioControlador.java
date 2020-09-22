package com.tipocambio.tipocambio.controlador;

import com.tipocambio.tipocambio.modelos.TipoCambio;
import com.tipocambio.tipocambio.servicio.TipoCambioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/tipo-cambio")
@CrossOrigin
public class TipoCambioControlador {
    private static final Logger log = LoggerFactory.getLogger(TipoCambioControlador.class);
    @Autowired
    private TipoCambioServicio servicio;

    @GetMapping("/por-dia")
    public @ResponseBody
    ResponseEntity obtenerTipoCambioHoy(final @RequestParam(name = "dia") int dia,
                                    final @RequestParam(name = "mes") int mes,
                                    final @RequestParam(name = "anio") int anio){
        try {
            return new ResponseEntity<TipoCambio>(servicio.obtenerTipoCambioPorDia(dia, mes, anio), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/por-mes")
    public @ResponseBody
    ResponseEntity obtenerTipoCambioHoy(final @RequestParam(name = "mes") int mes,
                                        final @RequestParam(name = "anio") int anio){
        try {
            return new ResponseEntity<List<TipoCambio>>(servicio.obtenerTipoCambioPorMes( mes, anio), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
