package com.tipocambio.tipocambio.prueba;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Pruebas {

    private static final Logger log = LoggerFactory.getLogger(Pruebas.class);
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://e-consulta.sunat.gob.pe/cl-at-ittipcam/tcS01Alias").get();
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
        }

    }
}
