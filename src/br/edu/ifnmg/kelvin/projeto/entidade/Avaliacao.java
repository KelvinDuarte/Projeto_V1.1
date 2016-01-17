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
public class Avaliacao {
    
    private int id_avaliacao;
    private double peso;
    private double altura;
    private double frequenciaCardiaca;
    private double pressaoArterial;
    private double torax;
    private double cintura;
    private double abdome;
    private double quadril;
    private double antebracoEsquerdo;
    private double antebracoDireito;
    private double bracoEsquerdo;
    private double bracoDireito;
    private double coxaEsquerda;
    private double coxaDireita;
    private int id_atleta;
    private int id_personal;
    private double panturrilhaEsquerda;
    private double panturrilhaDireita;
    private double subscapular;
    private double tricipital;
    private double peitoral;
    private double axilarMedia;
    private double supraIliaca;
    private double abdominal;

    public String getNomeAtleta() {
        return nomeAtleta;
    }

    public void setNomeAtleta(String nomeAtleta) {
        this.nomeAtleta = nomeAtleta;
    }

    public String getNomePersonal() {
        return nomePersonal;
    }

    public void setNomePersonal(String nomePersonal) {
        this.nomePersonal = nomePersonal;
    }
    private double coxa;
    private int flexoes;
    private int abdominais;
    private Date dataAvaliacao;
    private Date dataValidade;
    private String nomeAtleta;
    private String nomePersonal;
    public int getId_atleta() {
        return id_atleta;
    }

    public void setId_atleta(int id_atleta) {
        this.id_atleta = id_atleta;
    }

    public int getId_personal() {
        return id_personal;
    }

    public void setId_personal(int id_personal) {
        this.id_personal = id_personal;
    }
    
    public int getId_avaliacao() {
        return id_avaliacao;
    }

    public void setId_avaliacao(int id_avaliacao) {
        this.id_avaliacao = id_avaliacao;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    public void setFrequenciaCardiaca(double frequenciaCardiaca) {
        this.frequenciaCardiaca = frequenciaCardiaca;
    }

    public double getPressaoArterial() {
        return pressaoArterial;
    }

    public void setPressaoArterial(double pressaoArterial) {
        this.pressaoArterial = pressaoArterial;
    }

    public double getTorax() {
        return torax;
    }

    public void setTorax(double torax) {
        this.torax = torax;
    }

    public double getCintura() {
        return cintura;
    }

    public void setCintura(double cintura) {
        this.cintura = cintura;
    }

    public double getAbdome() {
        return abdome;
    }

    public void setAbdome(double abdome) {
        this.abdome = abdome;
    }

    public double getQuadril() {
        return quadril;
    }

    public void setQuadril(double quadril) {
        this.quadril = quadril;
    }

    public double getAntebracoEsquerdo() {
        return antebracoEsquerdo;
    }

    public void setAntebracoEsquerdo(double antebracoEsquerdo) {
        this.antebracoEsquerdo = antebracoEsquerdo;
    }

    public double getAntebracoDireito() {
        return antebracoDireito;
    }

    public void setAntebracoDireito(double antebracoDireito) {
        this.antebracoDireito = antebracoDireito;
    }

    public double getBracoEsquerdo() {
        return bracoEsquerdo;
    }

    public void setBracoEsquerdo(double bracoEsquerdo) {
        this.bracoEsquerdo = bracoEsquerdo;
    }

    public double getBracoDireito() {
        return bracoDireito;
    }

    public void setBracoDireito(double bracoDireito) {
        this.bracoDireito = bracoDireito;
    }

    public double getCoxaEsquerda() {
        return coxaEsquerda;
    }

    public void setCoxaEsquerda(double coxaEsquerda) {
        this.coxaEsquerda = coxaEsquerda;
    }

    public double getCoxaDireita() {
        return coxaDireita;
    }

    public void setCoxaDireita(double coxaDireita) {
        this.coxaDireita = coxaDireita;
    }

    public double getPanturrilhaEsquerda() {
        return panturrilhaEsquerda;
    }

    public void setPanturrilhaEsquerda(double panturrilhaEsquerda) {
        this.panturrilhaEsquerda = panturrilhaEsquerda;
    }

    public double getPanturrilhaDireita() {
        return panturrilhaDireita;
    }

    public void setPanturrilhaDireita(double panturrilhaDireita) {
        this.panturrilhaDireita = panturrilhaDireita;
    }

    public double getSubscapular() {
        return subscapular;
    }

    public void setSubscapular(double subscapular) {
        this.subscapular = subscapular;
    }

    public double getTricipital() {
        return tricipital;
    }

    public void setTricipital(double tricipital) {
        this.tricipital = tricipital;
    }

    public double getPeitoral() {
        return peitoral;
    }

    public void setPeitoral(double peitoral) {
        this.peitoral = peitoral;
    }

    public double getAxilarMedia() {
        return axilarMedia;
    }

    public void setAxilarMedia(double axilarMedia) {
        this.axilarMedia = axilarMedia;
    }

    public double getSupraIliaca() {
        return supraIliaca;
    }

    public void setSupraIliaca(double supraIliaca) {
        this.supraIliaca = supraIliaca;
    }

    public double getAbdominal() {
        return abdominal;
    }

    public void setAbdominal(double abdominal) {
        this.abdominal = abdominal;
    }

    public double getCoxa() {
        return coxa;
    }

    public void setCoxa(double coxa) {
        this.coxa = coxa;
    }

    public int getFlexoes() {
        return flexoes;
    }

    public void setFlexoes(int flexoes) {
        this.flexoes = flexoes;
    }

    public int getAbdominais() {
        return abdominais;
    }

    public void setAbdominais(int abdominais) {
        this.abdominais = abdominais;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    
}
