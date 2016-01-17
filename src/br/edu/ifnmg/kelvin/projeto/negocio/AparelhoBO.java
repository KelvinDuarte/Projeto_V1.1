/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.negocio;

import br.edu.ifnmg.kelvin.projeto.entidade.Aparelho;
import br.edu.ifnmg.kelvin.projeto.persistencia.AparelhoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author KELVIN
 */
public class AparelhoBO {
    
    public void cadastrarAparelho(Aparelho aparelho) throws SQLException{
        AparelhoDAO aparelhoDAO = new AparelhoDAO();
        aparelhoDAO.cadastrarAparelho(aparelho);
    }
    
    public void editarAparelho(Aparelho aparelho) throws SQLException{
        AparelhoDAO aparelhoDAO = new AparelhoDAO();
        aparelhoDAO.editarAparelho(aparelho);
    }
    
    public List<Aparelho> buscarTodos() throws SQLException{
        AparelhoDAO aparelhoDAO = new AparelhoDAO();
        return aparelhoDAO.buscarTodos();
    }
    
    public void excluirAparelho(int id_aparelho) throws SQLException{
        AparelhoDAO aparelhoDAO = new AparelhoDAO();
        aparelhoDAO.excluirAparelho(id_aparelho);
    }
}
