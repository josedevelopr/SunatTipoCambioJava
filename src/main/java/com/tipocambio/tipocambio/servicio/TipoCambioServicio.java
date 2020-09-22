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
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TipoCambioServicio implements TipoCambioRepositorio {

    private static final Logger log = LoggerFactory.getLogger(TipoCambioRepositorio.class);

    @Override
    public List<TipoCambio> obtenerDatos(int mes, int anio) {
        List<TipoCambio> resultado = new ArrayList<>();

        try {
            //Document doc = Jsoup.connect("https://e-consulta.sunat.gob.pe/cl-at-ittipcam/tcS01Alias").get();
            String url = String.format("https://e-consulta.sunat.gob.pe/cl-at-ittipcam/tcS01Alias?mes=%1$d&anho=%2$d",mes,anio);
            Document doc = Jsoup.connect(url).get();
            log.info( doc.title());

            List<TipoCambio> lstTipoCambio = new ArrayList<>();

            Elements newsHeadlines = doc.select("table");
            Elements tipcamTable = newsHeadlines.get(1).select("tr");
            int numFila = 0;
            for(Element tr: tipcamTable) {
                if(numFila > 0) {
                    int numColumna = 0;
                    int diaTipCam = 0;
                    double tipoCompra = 0;
                    double tipoVenta = 0;
                    TipoCambio tipoCambio = new TipoCambio();
                    for(Element td : tr.select("td")) {

                        if(numColumna == 0) {
                            diaTipCam = Integer.parseInt(td.select("strong").html().toString());
                        }

                        if(numColumna == 1 || numColumna == 2) {
                            if (numColumna == 1)
                                tipoCompra = Double.parseDouble(td.html().toString());
                            else
                                tipoVenta = Double.parseDouble(td.html().toString());
                        }

                        // log.info(td.html());
                        numColumna++;

                        if(numColumna == 3) {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaTC = formatter.parse(diaTipCam+"/"+mes+"/"+anio);

                            tipoCambio.setFecha(fechaTC);
                            tipoCambio.setPrecioCompra(tipoCompra);
                            tipoCambio.setPrecioVenta(tipoVenta);
                            tipoCambio.setPrecioPromedio((tipoCompra+tipoVenta)/2);

                            tipoCompra = 0;
                            tipoVenta = 0;
                            diaTipCam = 0;
                            numColumna = 0;

                            lstTipoCambio.add(tipoCambio);
                            tipoCambio = new TipoCambio();
                        }
                    }
                }
                numFila++;
            }

            log.info(lstTipoCambio.toString());
            resultado = lstTipoCambio;

        } catch(Exception e){
            log.error(e.toString());
            resultado = new ArrayList<>();
        }

        return resultado;
    }

    @Override
    public TipoCambio obtenerTipoCambioPorDia(int dia, int mes, int anio) throws ParseException {
        List<TipoCambio> lstTipoCambio = obtenerDatos(mes, anio);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaTC = formatter.parse(dia+"/"+mes+"/"+anio);
        TipoCambio resultado = lstTipoCambio
                                    .stream()
                                    .filter(tc -> tc.getFecha().equals(fechaTC))
                                    .findFirst()
                                    .get();
        return resultado;
    }

    @Override
    public List<TipoCambio> obtenerTipoCambioPorMes(int mes, int anio) {
        List<TipoCambio> resultado = new ArrayList<>();
        List<TipoCambio> lstTipoCambio = obtenerDatos(mes, anio);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        resultado = lstTipoCambio
                        .stream()
                        .sorted(Comparator.comparing(TipoCambio::getFecha))
                        .collect(Collectors.toList());

        return resultado;
    }


}
