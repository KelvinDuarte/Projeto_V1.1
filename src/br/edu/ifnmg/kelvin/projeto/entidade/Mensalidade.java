/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.entidade;

import java.util.Date;

/**
 *
 * @author KELVIN
 */
public class Mensalidade {
    
    private int Id_mensalidade;
    private String nome;
    private Date dataEmissao;
    private Date dataVencimento;
    private double valorAPagar;
    private String status;
    
    public int getId_mensalidade() {
        return Id_mensalidade;
    }

    public void setId_mensalidade(int Id_mensalidade) {
        this.Id_mensalidade = Id_mensalidade;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    } 
    
    public double getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(double valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
}
