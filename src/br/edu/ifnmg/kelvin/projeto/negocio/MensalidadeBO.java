/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.negocio;

import br.edu.ifnmg.kelvin.projeto.entidade.Mensalidade;
import br.edu.ifnmg.kelvin.projeto.persistencia.MensalidadeDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author KELVIN
 */
public class MensalidadeBO {
    
    public void cadastrarMensalidade(Mensalidade mensalidade) throws SQLException{
        MensalidadeDAO mensalidadeDAO = new MensalidadeDAO();
        mensalidadeDAO.cadastrarMensalidade(mensalidade);
    }
    
    public void editarMensalidade(Mensalidade mensalidade) throws SQLException{
        MensalidadeDAO mensalidadeDAO = new MensalidadeDAO();
        mensalidadeDAO.editarMensalidade(mensalidade);
    }
    
    public List<Mensalidade> buscarTodos() throws SQLException{
        MensalidadeDAO mensalidadeDAO = new MensalidadeDAO();
        return mensalidadeDAO.buscarTodos(); 
    }
    
    public void excluirMensalidade(int id_mensalidade) throws SQLException{
        MensalidadeDAO mensalidadeDAO = new MensalidadeDAO(); 
        mensalidadeDAO.excluirMensalidade(id_mensalidade);
    }    
    
    public List<Mensalidade> pesquisarPorNome(String nome) throws SQLException{
        MensalidadeDAO mensalidadeDAO = new MensalidadeDAO();
       return mensalidadeDAO.pesquisarPorNome(nome);
    }
    
    public List<Mensalidade> pesquisarPorData(int mes) throws SQLException{
        MensalidadeDAO mensalidadeDAO = new MensalidadeDAO();
       return mensalidadeDAO.pesquisarPorData(mes);
    }  
    
    public List<Mensalidade> pesquisarPorNomeData(String nome,int mes) throws SQLException{
        MensalidadeDAO mensalidadeDAO = new MensalidadeDAO();
       return mensalidadeDAO.pesquisarPorNomeData(nome,mes);
    } 
}
