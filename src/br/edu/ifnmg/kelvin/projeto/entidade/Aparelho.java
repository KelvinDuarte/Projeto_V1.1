/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.entidade;

/**
 *
 * @author KELVIN
 */
public class Aparelho {
    private int id_aparelho;
    private String nome;
    private int quantidade;
    private String tipo;
    private String categoria;

    public int getId_aparelho() {
        return id_aparelho;
    }

    public void setId_aparelho(int id_aparelho) {
        this.id_aparelho = id_aparelho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
    
}
