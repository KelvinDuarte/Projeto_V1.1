/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.negocio;

import br.edu.ifnmg.kelvin.projeto.entidade.PersonalTrainer;
import br.edu.ifnmg.kelvin.projeto.persistencia.PersonalDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author KELVIN
 */
public class PersonalBO {
    
    public void cadastrarPersonal(PersonalTrainer personalTrainer) throws SQLException{
        PersonalDAO personalDAO = new PersonalDAO();
        personalDAO.cadastrarPersonal(personalTrainer);
    }
    
    public void editarPersonal(PersonalTrainer personalTrainer) throws SQLException{
        PersonalDAO personalDAO = new PersonalDAO();
        personalDAO.editarPersonal(personalTrainer);
    }
    
    public void excluirPersonal(int id_personal) throws SQLException{
        PersonalDAO personalDAO = new PersonalDAO();
        personalDAO.excluirPersonal(id_personal);
    }
    
    public List<PersonalTrainer> buscarTodos() throws SQLException{
        PersonalDAO personalDAO = new PersonalDAO();
        return personalDAO.buscarTodos();
    } 
    
    public List<PersonalTrainer> carregarTabelaPersonalPorNome(String nome) throws SQLException{
        PersonalDAO personalDAO = new PersonalDAO();
        return personalDAO.carregarTabelaPersonalPorNome(nome);
    }
    public List<PersonalTrainer> carregarTabelaPersonalPorCpf(String cpf) throws SQLException{
        PersonalDAO personalDAO = new PersonalDAO();
        return personalDAO.carregarTabelaPersonalPorCpf(cpf);
    }
    public List<PersonalTrainer> carregarTabelaPersonalPorNomeCpf(String nome, String cpf) throws SQLException{
        PersonalDAO personalDAO = new PersonalDAO();
        return personalDAO.carregarTabelaPersonalPorNomeCpf(nome,cpf);
    }    
}
