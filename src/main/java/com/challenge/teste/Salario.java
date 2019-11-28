// 
// Decompiled by Procyon v0.5.36
// 

package com.challenge.teste;

import com.challenge.annotation.Subtrair;
import com.challenge.annotation.Somar;
import java.math.BigDecimal;

public class Salario
{
    @Somar
    private BigDecimal salarioBruto;
    @Subtrair
    private BigDecimal inss;
    @Subtrair
    private BigDecimal planoSaude;
    @Somar
    private BigDecimal valeRefeicao;
    
    public BigDecimal getSalarioBruto() {
        return this.salarioBruto;
    }
    
    public void setSalarioBruto(final BigDecimal salarioBruto) {
        this.salarioBruto = salarioBruto;
    }
    
    public BigDecimal getInss() {
        return this.inss;
    }
    
    public void setInss(final BigDecimal inss) {
        this.inss = inss;
    }
    
    public BigDecimal getPlanoSaude() {
        return this.planoSaude;
    }
    
    public void setPlanoSaude(final BigDecimal planoSaude) {
        this.planoSaude = planoSaude;
    }
    
    public BigDecimal getValeRefeicao() {
        return this.valeRefeicao;
    }
    
    public void setValeRefeicao(final BigDecimal valeRefeicao) {
        this.valeRefeicao = valeRefeicao;
    }
}
