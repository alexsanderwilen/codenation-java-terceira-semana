package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {
    @Somar
    private BigDecimal totalSoma;

    @Subtrair
    private BigDecimal  totalSubitracao;

    public CalculadorDeClasses(){
        this.totalSoma = BigDecimal.ZERO;
        this.totalSubitracao = BigDecimal.ZERO;
    }

    @Override
    public BigDecimal somar(Object object) {
        Field[] fieldList = object.getClass().getDeclaredFields();

        for (Field field : fieldList) {
            if(field.isAnnotationPresent(Somar.class)){
                if(field.getType().getName().contains("BigDecimal")) {
                    field.setAccessible(true);
                    try {
                        Field f = object.getClass().getDeclaredField(field.getName());
                        f.setAccessible(true);
                        try {
                            totalSoma = totalSoma.add((BigDecimal) f.get(object));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return totalSoma;
    }

    @Override
    public BigDecimal subtrair(Object object) {
        Field[] fieldList = object.getClass().getDeclaredFields();

        for (Field field : fieldList) {
            if(field.isAnnotationPresent(Subtrair.class)){
                if(field.getType().getName().contains("BigDecimal")) {
                    totalSubitracao = totalSubitracao.add(BigDecimal.valueOf(1));
                }
            }
        }
        return totalSubitracao;
    }

    @Override
    public BigDecimal totalizar(Object object) {
        inicializaTotalizadores();
        somar(object);
        subtrair(object);
        return totalSoma.subtract(totalSubitracao);
    }

    private void inicializaTotalizadores(){
        totalSoma = BigDecimal.ZERO;
        totalSubitracao = BigDecimal.ZERO;
    }
}