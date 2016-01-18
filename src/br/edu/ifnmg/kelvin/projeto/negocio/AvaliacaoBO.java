/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.negocio;

import br.edu.ifnmg.kelvin.projeto.entidade.Avaliacao;
import br.edu.ifnmg.kelvin.projeto.persistencia.AvaliacaoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author KELVIN
 */
public class AvaliacaoBO {

    public void cadastrarAvaliacao(Avaliacao avaliacao) throws SQLException{
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        avaliacaoDAO.cadastrarAvaliacao(avaliacao);
    }
    
    public void editarAvaliacao(Avaliacao avaliacao) throws SQLException, Exception{
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        avaliacaoDAO.editarAvaliacao(avaliacao);
    }
    
    public void excluirAvaliacao(int id_avaliacao) throws SQLException{
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        avaliacaoDAO.excluirAvaliacao(id_avaliacao);
    }
    
    public List<Avaliacao> buscarTodos() throws SQLException{
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        return avaliacaoDAO.buscarTodos();
    }
    
    public List<Avaliacao> carregarTabelaAvaliacaoPorAtleta(String atleta) throws SQLException{
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        return avaliacaoDAO.carregarTabelaAvaliacaoPorAtleta(atleta);
    }
    
    public List<Avaliacao> carregarTabelaAvaliacaoPorPersonal(String personal) throws SQLException{
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        return avaliacaoDAO.carregarTabelaAvaliacaoPorPersonal(personal);
    }
    
    public List<Avaliacao> carregarTabelaAvaliacaoPorAtletaPersonal(String atleta, String personal) throws SQLException{
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        return avaliacaoDAO.carregarTabelaAvaliacaoPorAtletaPersonal(atleta,personal);
    }    
    
}
