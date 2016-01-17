/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.persistencia;

import br.edu.ifnmg.kelvin.projeto.entidade.Aparelho;
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
public class AparelhoDAO {
    
    private final String SQL_INSERT = "INSERT INTO APARELHOS (NOME, QUANTIDADE, TIPO, CATEGORIA) VALUES(?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE APARELHOS SET NOME = ?, QUANTIDADE = ?, TIPO = ?, CATEGORIA = ? WHERE ID_APARELHO = ?";
    private final String SQL_BUSCAR_TODOS = "SELECT * FROM APARELHOS";
    private final String SQL_DELETE = "DELETE FROM APARELHOS WHERE ID_APARELHO = ?";
    
    public void cadastrarAparelho(Aparelho aparelho) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;  
        conexao = BancoDeDadosUtil.getConnection(); 
        comando = conexao.prepareStatement(SQL_INSERT);
        try{
            comando.setString(1, aparelho.getNome());
            comando.setInt(2, aparelho.getQuantidade());
            comando.setString(3, aparelho.getTipo());
            comando.setString(4, aparelho.getCategoria());
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
    
    public void editarAparelho(Aparelho aparelho) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        conexao = BancoDeDadosUtil.getConnection();
        comando = conexao.prepareStatement(SQL_UPDATE); 
        try{
            comando.setString(1, aparelho.getNome());
            comando.setInt(2, aparelho.getQuantidade());
            comando.setString(3, aparelho.getTipo());
            comando.setString(4, aparelho.getCategoria());
            comando.setInt(5, aparelho.getId_aparelho());
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
    
    public void excluirAparelho(int id_aparelho) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        conexao = BancoDeDadosUtil.getConnection();
        comando = conexao.prepareStatement(SQL_DELETE);
        try{
            comando.setInt(1, id_aparelho);
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
    public List<Aparelho> buscarTodos() throws SQLException{  
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;      
        List<Aparelho> listaAparelhos = new ArrayList<>();      
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_TODOS);
            resultado = comando.executeQuery();
            while(resultado.next()){
                Aparelho aparelho = this.extrairLinhaResultado(resultado);
                listaAparelhos.add(aparelho);
            }
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
            return listaAparelhos;
    }
    
    private Aparelho extrairLinhaResultado(ResultSet resultado) throws SQLException{
        Aparelho aparelho = new Aparelho();
        aparelho.setId_aparelho(resultado.getInt(1));
        aparelho.setNome(resultado.getString(2));
        aparelho.setQuantidade(resultado.getInt(3));
        aparelho.setTipo(resultado.getString(4));
        aparelho.setCategoria(resultado.getString(5));
        return aparelho;
    }
}
