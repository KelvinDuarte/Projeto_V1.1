/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.negocio;

import br.edu.ifnmg.kelvin.projeto.entidade.Atleta;
import br.edu.ifnmg.kelvin.projeto.persistencia.AtletaDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author KELVIN
 */
public class AtletaBO {
    
    public void cadastrarAtleta(Atleta atleta) throws SQLException{
        AtletaDAO atletaDAO = new AtletaDAO();
        atletaDAO.cadastrarAtleta(atleta);
    }
    
    public void editarAtleta(Atleta atleta) throws SQLException{
        AtletaDAO atletaDAO = new AtletaDAO();
        atletaDAO.editarAtleta(atleta);
    }
    
    public void excluirAtleta(int id_atleta) throws SQLException{
        AtletaDAO atletaDAO = new AtletaDAO();
        atletaDAO.excluirAtleta(id_atleta);
    }
    
    public List<Atleta> buscarTodos() throws SQLException{
        AtletaDAO atletaDAO = new AtletaDAO();
        return atletaDAO.buscarTodos();
    }
    
    public List<Atleta> carregarTabelaAtletaPorNome(String nome) throws SQLException{
        AtletaDAO atletaDAO = new AtletaDAO();  
        return atletaDAO.carregarTabelaAtletaPorNome(nome);
    }

    public List<Atleta> carregarTabelaAtletaPorCpf(String cpf) throws SQLException{
        AtletaDAO atletaDAO = new AtletaDAO();
        return atletaDAO.carregarTabelaAtletaPorCpf(cpf);
    }   
    
    public List<Atleta> carregarTabelaAtletaPorNomeCpf(String nome, String cpf) throws SQLException{
        AtletaDAO atletaDAO = new AtletaDAO();
        return atletaDAO.carregarTabelaAtletaPorNomeCpf(nome,cpf);
    }    
}
