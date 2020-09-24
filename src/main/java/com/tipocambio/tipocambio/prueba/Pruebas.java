package com.tipocambio.tipocambio.prueba;

import com.tipocambio.tipocambio.modelos.TipoCambio;
import com.tipocambio.tipocambio.servicio.TipoCambioServicio;
import com.tipocambio.tipocambio.util.Helper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Pruebas {

    private static final Logger log = LoggerFactory.getLogger(Pruebas.class);
    public static void main(String[] args) {
        TipoCambioServicio servicio = new TipoCambioServicio();
       /* try {
            //Document doc = Jsoup.connect("https://e-consulta.sunat.gob.pe/cl-at-ittipcam/tcS01Alias").get();
            String url = String.format("https://e-consulta.sunat.gob.pe/cl-at-ittipcam/tcS01Alias?mes=%1$d&anho=%2$d",9,2020);
            Document doc = Jsoup.connect(url).get();

            log.info( doc.title());

            Map<Integer, Map<String, Double>> mapTipCambio = new HashMap<>();

            Elements newsHeadlines = doc.select("table");
            Elements tipcamTable = newsHeadlines.get(1).select("tr");
            int numFila = 0;
            for(Element tr: tipcamTable) {
                if(numFila > 0) {
                    int numColumna = 0;
                    int diaTipCam = 0;
                    Map<String, Double> mapValTipoCambio = new HashMap<>();
                    for(Element td : tr.select("td")) {

                        if(numColumna == 0) {
                            diaTipCam = Integer.parseInt(td.select("strong").html().toString());
                        }

                        if(numColumna == 1 || numColumna == 2) {
                            double valTipCam = Double.parseDouble(td.html().toString());
                            String desTipCam = numColumna == 1 ? "Compra" : "Venta" ;
                            mapValTipoCambio.put(desTipCam,valTipCam);
                        }

                        // log.info(td.html());
                        numColumna++;

                        if(numColumna == 3) {
                            mapTipCambio.put(diaTipCam, mapValTipoCambio);
                            numColumna = 0;
                            mapValTipoCambio = new HashMap<>();
                        }
                    }
                }
                numFila++;
            }

            log.info(mapTipCambio.toString());

        } catch(Exception e){
            log.error(e.toString());
        }*/
/*

        TipoCambio resultado = new TipoCambio();

        try {
            int dia = 1;
            int mes = 9;
            int anio = 2020;
            Map<Integer, Map<String, Double>> mapTipoCambio = servicio.obtenerDatos(mes, anio);

            double precioCompra = mapTipoCambio.get(dia).get("Compra");
            double precioVenta = mapTipoCambio.get(dia).get("Venta");

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaTC = formatter.parse(dia+"/"+mes+"/"+anio);
            resultado.setPrecioCompra(precioCompra);
            resultado.setPrecioVenta(precioVenta);
            resultado.setFecha(fechaTC);
            resultado.setPrecioPromedio((precioCompra+precioVenta)/2);
            log.info(mapTipoCambio.toString());
        } catch(Exception e) {
            log.error(e.toString());
        }*/
/*
        List lstTipoCambioSetiembre = servicio.obtenerDatos(9,2020);
        log.info(lstTipoCambioSetiembre.toString());*/
    try {
        TipoCambio tipoCambioHoy = servicio.obtenerTipoCambioPorDia(7,9,2020);
        log.info("\nTipo de cambio para hoy :"+tipoCambioHoy.toString());
    } catch (Exception e) {
        log.error(e.toString());
    }
/*
        List lstTipoCambio = servicio.obtenerTipoCambioPorMes(9,2020);
        lstTipoCambio.forEach(
                tc -> log.info(tc.toString())
        );

       /* String oldDate = "23/09/2020";
        System.out.println("Date before Addition: "+oldDate);
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaTC = formatter.parse(oldDate);
            Date newDate = Helper.sumarDiasAfecha(fechaTC, -1);
            //Displaying the new Date after addition of Days
            System.out.println("Date after Addition: "+formatter.format(newDate));
        }catch(Exception e){
            e.printStackTrace();
        }*/
    }
}
