/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.negocio;

import br.edu.ifnmg.kelvin.projeto.entidade.Treino;
import br.edu.ifnmg.kelvin.projeto.persistencia.TreinoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author KELVIN
 */
public class TreinoBO {
    
    public void cadastrarTreino(Treino treino) throws SQLException{
       TreinoDAO treinoDAO = new TreinoDAO();
       treinoDAO.cadastrarTreino(treino);
    }
    
    public void editarTreino(Treino treino) throws SQLException{
        TreinoDAO treinoDAO = new TreinoDAO();
        treinoDAO.editarTreino(treino);
    }
    
    public void excluirTreino(int id_treino) throws SQLException{
        TreinoDAO treinoDAO = new TreinoDAO();
        treinoDAO.excluirTreino(id_treino);
    }
    
    public List<Treino> buscarTodos() throws SQLException{
        TreinoDAO treinoDAO = new TreinoDAO();
        return treinoDAO.buscarTodos();
    }
    
    public List<Treino> carregarTabelaTreinoPorTipo(String tipo) throws SQLException{
        TreinoDAO treinoDAO = new TreinoDAO();
        return treinoDAO.carregarTabelaTreinoPorTipo(tipo);     
    }
    
    public List<Treino> carregarTabelaTreinoPorCategoria(String categoria) throws SQLException{
        TreinoDAO treinoDAO = new TreinoDAO();
        return treinoDAO.carregarTabelaTreinoPorCategoria(categoria);     
    }
    
    public List<Treino> carregarTabelaTreinoPorTipoCategoria(String tipo, String categoria) throws SQLException{
        TreinoDAO treinoDAO = new TreinoDAO();
        return treinoDAO.carregarTabelaTreinoPorTipoCategoria(tipo,categoria);     
    }    
}
