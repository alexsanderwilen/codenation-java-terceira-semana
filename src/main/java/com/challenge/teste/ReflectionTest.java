// 
// Decompiled by Procyon v0.5.36
// 

package com.challenge.teste;


import java.util.stream.Stream;

import java.math.BigDecimal;

public class ReflectionTest
{
    private static final String METHOD_SOMAR = "somar";
    private static final String METHOD_SUBTRAIR = "subtrair";
    private static final String METHOD_TOTALIZAR = "totalizar";
    private static final String CALCULADOR_DE_CLASSES_PATH = "com.challenge.desafio.CalculadorDeClasses";
    private static final String CALCULAVEL_PATH = "com.challenge.interfaces.Calculavel";

    public void whenAdd() {
        final Salario salario = new Salario();
        salario.setInss(BigDecimal.valueOf(5L));
        salario.setPlanoSaude(BigDecimal.valueOf(10L));
        salario.setValeRefeicao(BigDecimal.valueOf(15L));
        salario.setSalarioBruto(BigDecimal.valueOf(25L));
        final BigDecimal result = this.execute("somar", salario);
        System.out.println("soma: result" + result);
        System.out.println("Esperava: " + 40);


        //Assert.assertThat((Object)result, Matchers.equalTo((Object)new BigDecimal(40)));
    }

    public void whenAddWithouAnnotation() {
        final AnotherSalario salario = new AnotherSalario();
        salario.setInss(BigDecimal.valueOf(5L));
        salario.setPlanoSaude(BigDecimal.valueOf(10L));
        salario.setValeRefeicao(BigDecimal.valueOf(15L));
        salario.setSalarioBruto(BigDecimal.valueOf(25L));
        final BigDecimal result = this.execute("somar", salario);
        //Assert.assertThat((Object)result, Matchers.equalTo((Object)new BigDecimal(0)));
    }
    

    public void whenSubtract() {
        final Salario salario = new Salario();
        salario.setInss(BigDecimal.valueOf(5L));
        salario.setPlanoSaude(BigDecimal.valueOf(10L));
        salario.setValeRefeicao(BigDecimal.valueOf(15L));
        salario.setSalarioBruto(BigDecimal.valueOf(25L));
        final BigDecimal result = this.execute("subtrair", salario);
        //Assert.assertThat((Object)result, Matchers.equalTo((Object)new BigDecimal(15)));
    }
    

    public void whenSubtractAllWithouAnnotation() {
        final AnotherSalario salario = new AnotherSalario();
        salario.setInss(BigDecimal.valueOf(5L));
        salario.setPlanoSaude(BigDecimal.valueOf(10L));
        salario.setValeRefeicao(BigDecimal.valueOf(15L));
        salario.setSalarioBruto(BigDecimal.valueOf(25L));
        final BigDecimal result = this.execute("subtrair", salario);
        //Assert.assertThat((Object)result, Matchers.equalTo((Object)new BigDecimal(0)));
    }
    
    private BigDecimal execute(final String method, final Object object) {
        try {
            final Object obj = this.getCalculadorClassesClass().newInstance();
            return (BigDecimal)obj.getClass().getMethod(method, Object.class).invoke(obj, object);
        }
        catch (Exception e) {
            new RuntimeException(e);
            return null;
        }
    }
    

    public void whenResult() {
        final Salario salario = new Salario();
        salario.setInss(BigDecimal.valueOf(5L));
        salario.setPlanoSaude(BigDecimal.valueOf(10L));
        salario.setValeRefeicao(BigDecimal.valueOf(15L));
        salario.setSalarioBruto(BigDecimal.valueOf(25L));
        final BigDecimal result = this.execute("totalizar", salario);
        //Assert.assertThat((Object)result, Matchers.equalTo((Object)new BigDecimal(25)));
    }

    public void whenResultWithoutAnnotation() {
        final AnotherSalario salario = new AnotherSalario();
        salario.setInss(BigDecimal.valueOf(5L));
        salario.setPlanoSaude(BigDecimal.valueOf(10L));
        salario.setValeRefeicao(BigDecimal.valueOf(15L));
        salario.setSalarioBruto(BigDecimal.valueOf(25L));
        final BigDecimal result = this.execute("totalizar", salario);
        //Assert.assertThat((Object)result, Matchers.equalTo((Object)new BigDecimal(0)));
    }

    public void shouldImplementsCalculavel() {
        final boolean isSameClass = Stream.of(this.getCalculadorClassesClass().getInterfaces()).anyMatch(i -> i.equals(this.getCalculavelClass()));
       // Assert.assertThat((Object)isSameClass, Matchers.is((Object)true));
    }
    

    public void shouldHaveAddMethod() {
        final boolean temSoma = Stream.of(this.getCalculavelClass().getMethods()).anyMatch(m -> m.getName().equals("somar"));
      //  Assert.assertThat((Object)temSoma, Matchers.is((Object)true));
    }
    
    private Class<?> getCalculavelClass() {
        try {
            return Class.forName("com.challenge.interfaces.Calculavel");
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    private Class<?> getCalculadorClassesClass() {
        try {
            return Class.forName("com.challenge.desafio.CalculadorDeClasses");
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    

    public void shouldHaveSubtractMethod() {
        final boolean temSoma = Stream.of(this.getCalculavelClass().getMethods()).anyMatch(m -> m.getName().equals("subtrair"));
       // Assert.assertThat((Object)temSoma, Matchers.is((Object)true));
    }

    public void shouldHaveResultMethod() {
        final boolean temSoma = Stream.of(this.getCalculavelClass().getMethods()).anyMatch(m -> m.getName().equals("totalizar"));
      //  Assert.assertThat((Object)temSoma, Matchers.is((Object)true));
    }
}
