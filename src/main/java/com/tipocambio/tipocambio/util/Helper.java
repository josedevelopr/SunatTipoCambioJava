package com.tipocambio.tipocambio.util;

import com.tipocambio.tipocambio.prueba.Pruebas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Helper {

    private static final Logger log = LoggerFactory.getLogger(Helper.class);

    public static Date sumarDiasAfecha(Date fecha, int dias) throws ParseException {
        Date resultado = null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_MONTH, dias);

        resultado = calendar.getTime();
        return resultado;
    }
}
