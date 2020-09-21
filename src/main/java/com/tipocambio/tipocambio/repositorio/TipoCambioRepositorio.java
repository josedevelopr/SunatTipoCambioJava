package com.tipocambio.tipocambio.repositorio;

import com.tipocambio.tipocambio.modelos.TipoCambio;

import java.util.List;
import java.util.Map;

public interface TipoCambioRepositorio {
    public Map<Integer, Map<String, Double>> obtenerDatos(int mes, int anio);
    public TipoCambio obtenerTipoCambioPorDia(int dia, int mes, int anio);
    public List<TipoCambio> obtenerTipoCambioPorMes(int mes, int anio);
}
