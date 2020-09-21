package com.tipocambio.tipocambio.servicio;

import com.tipocambio.tipocambio.modelos.TipoCambio;
import com.tipocambio.tipocambio.prueba.Pruebas;
import com.tipocambio.tipocambio.repositorio.TipoCambioRepositorio;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TipoCambioServicio implements TipoCambioRepositorio {

    private static final Logger log = LoggerFactory.getLogger(TipoCambioRepositorio.class);

    @Override
    public Map<Integer, Map<String, Double>> obtenerDatos(int mes, int anio) {
        Map<Integer, Map<String, Double>> resultado = new HashMap<>();

        try {
            //Document doc = Jsoup.connect("https://e-consulta.sunat.gob.pe/cl-at-ittipcam/tcS01Alias").get();
            String url = String.format("https://e-consulta.sunat.gob.pe/cl-at-ittipcam/tcS01Alias?mes=%1$d&anho=%2$d",mes,anio);
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
            resultado = mapTipCambio;

        } catch(Exception e){
            log.error(e.toString());
            resultado = new HashMap<>();
        }

        return resultado;
    }

    @Override
    public TipoCambio obtenerTipoCambioPorDia(int dia, int mes, int anio) {
        return null;
    }


}
