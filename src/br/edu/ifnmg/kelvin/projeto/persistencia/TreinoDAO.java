/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.persistencia;

import br.edu.ifnmg.kelvin.projeto.entidade.Treino;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KELVIN
 */
public class TreinoDAO {
    
    private static final String SQL_INSERT = "INSERT INTO TREINOS (TIPO, CATEGORIA, APARELHO1, APARELHO2, APARELHO3, APARELHO4, APARELHO5, APARELHO6, APARELHO7) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE TREINOS SET TIPO = ?, CATEGORIA = ?, APARELHO1 = ?, APARELHO2 = ?, APARELHO3 = ?, APARELHO4 = ?, APARELHO5 = ?, APARELHO6 = ?, APARELHO7 = ?";
    private static final String SQL_DELETE = "DELETE FROM TREINOS WHERE ID_TREINO = ?";
    private static final String SQL_BUSCAR_TODOS = "SELECT * FROM TREINOS";
    
    public void cadastrarTreino(Treino treino) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        conexao = BancoDeDadosUtil.getConnection();
        comando = conexao.prepareStatement(SQL_INSERT);
        try{
            comando.setString(1, treino.getTipo());
            comando.setString(2, treino.getCategoria());
            comando.setString(3, treino.getAparelho01());
            comando.setString(4, treino.getAparelho02());
            comando.setString(5, treino.getAparelho03());
            comando.setString(6, treino.getAparelho04());
            comando.setString(7, treino.getAparelho05());
            comando.setString(8, treino.getAparelho06());
            comando.setString(9, treino.getAparelho07());
            comando.execute();
            conexao.commit();
        }catch(Exception e){
            if(conexao != null){
                conexao.rollback();
            }
            throw e;
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando);
        }        
    }
    
    public void editarTreino(Treino treino) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        conexao = BancoDeDadosUtil.getConnection();
        comando = conexao.prepareStatement(SQL_UPDATE);
        try{
            comando.setString(1, treino.getTipo());
            comando.setString(2, treino.getCategoria());
            comando.setString(3, treino.getAparelho01());
            comando.setString(4, treino.getAparelho02());
            comando.setString(5, treino.getAparelho03());
            comando.setString(6, treino.getAparelho04());
            comando.setString(7, treino.getAparelho05());
            comando.setString(8, treino.getAparelho06());
            comando.setString(9, treino.getAparelho07());
            comando.execute();
            conexao.commit();
        }catch(Exception e){
            if(conexao != null){
                conexao.rollback();
            }
            throw e;
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando);
        }        
    }
    
    public void excluirTreino(int id_treino) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        conexao = BancoDeDadosUtil.getConnection();
        comando = conexao.prepareStatement(SQL_DELETE);    
        try{
            comando.setInt(1, id_treino);
            comando.execute();
            conexao.commit();
        }catch(Exception e){
            if(conexao != null){
                conexao.rollback();
            }
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando);
        }         
    }
    
    public List<Treino> buscarTodos() throws SQLException{    
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;      
        List<Treino> listaTreinos = new ArrayList<>();      
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_TODOS);
            resultado = comando.executeQuery();
            while(resultado.next()){
                Treino treino = this.extrairLinhaResultado(resultado);
                listaTreinos.add(treino);
            }
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
            return listaTreinos;
    } 

    private Treino extrairLinhaResultado(ResultSet resultado) throws SQLException {
        Treino treino = new Treino();     
        treino.setId_treino(resultado.getInt(1));
        treino.setTipo(resultado.getString(2));
        treino.setCategoria(resultado.getString(3));
        treino.setAparelho01(resultado.getString(4));
        treino.setAparelho02(resultado.getString(5));
        treino.setAparelho03(resultado.getString(6));
        treino.setAparelho04(resultado.getString(7));
        treino.setAparelho05(resultado.getString(8));
        treino.setAparelho06(resultado.getString(9));
        treino.setAparelho07(resultado.getString(10));     
        return treino;
    }
}
