package com.tipocambio.tipocambio.repositorio;

import com.tipocambio.tipocambio.modelos.TipoCambio;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface TipoCambioRepositorio {
    public List<TipoCambio> obtenerDatos(int mes, int anio);
    public TipoCambio obtenerTipoCambioPorDia(int dia, int mes, int anio) throws ParseException;
    public List<TipoCambio> obtenerTipoCambioPorMes(int mes, int anio);
}
