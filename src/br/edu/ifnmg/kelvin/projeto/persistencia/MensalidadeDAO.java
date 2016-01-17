/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.persistencia;

import br.edu.ifnmg.kelvin.projeto.entidade.Mensalidade;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KELVIN
 */
public class MensalidadeDAO {
    
    private static final String SQL_INSERT = "INSERT INTO MENSALIDADES(NOME, DATA_EMISSAO, DATA_VENCIMENTO, VALOR_A_PAGAR, STATUS) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE MENSALIDADES SET NOME = ?, DATA_EMISSAO = ?, DATA_VENCIMENTO = ?, VALOR_A_PAGAR = ?, STATUS = ?";
    private static final String SQL_DELETE = "DELETE FROM MENSALIDADES WHERE ID_MENSALIDADE = ?";
    private static final String SQL_BUSCAR_TODOS = "SELECT * FROM MENSALIDADES";
    private static final String SQL_BUSCAR_NOME = "SELECT * FROM MENSALIDADES WHERE NOME = ?";
    private static final String SQL_BUSCAR_DATA = "SELECT * FROM MENSALIDADES WHERE MONTH(DATA_VENCIMENTO) = ?";
    private static final String SQL_BUSCAR_NOME_DATA = "SELECT * FROM MENSALIDADES WHERE NOME = ? AND MONTH(DATA_VENCIMENTO) = ?";    
    public void cadastrarMensalidade(Mensalidade mensalidade) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        conexao = BancoDeDadosUtil.getConnection();
        comando = conexao.prepareStatement(SQL_INSERT);
        try{
            comando.setString(1, mensalidade.getNome());
            
            Date dataEmissao = new Date(mensalidade.getDataEmissao().getTime());
            comando.setDate(2, dataEmissao);
            
            Date dataVencimento = new Date(mensalidade.getDataVencimento().getTime());
            comando.setDate(3, dataVencimento);
            
            String valorAPagar = Double.toString(mensalidade.getValorAPagar());
            comando.setString(4, valorAPagar);
            comando.setString(5, mensalidade.getStatus());
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
    
    public void editarMensalidade(Mensalidade mensalidade) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        conexao = BancoDeDadosUtil.getConnection();
        comando = conexao.prepareStatement(SQL_UPDATE);
        try{
            comando.setString(1, mensalidade.getNome());
            java.sql.Date dataEmissao = new java.sql.Date(mensalidade.getDataEmissao().getTime());
            comando.setDate(2, dataEmissao);
            
            java.sql.Date dataVencimento = new java.sql.Date(mensalidade.getDataVencimento().getTime());
            comando.setDate(3, dataVencimento);
            String valorAPagar = Double.toString(mensalidade.getValorAPagar());
            comando.setString(4, valorAPagar);
            comando.setString(5, mensalidade.getStatus()); 
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
    
    public void excluirMensalidade(int id_mensalidade) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        conexao = BancoDeDadosUtil.getConnection();
        comando = conexao.prepareStatement(SQL_DELETE);    
        try{
            comando.setInt(1, id_mensalidade);
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
    
    public List<Mensalidade> pesquisarPorNome(String nome) throws SQLException{
        List<Mensalidade> listaMensalidade = new ArrayList<>();
        Mensalidade mensalidade = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;           
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_NOME);
            
            comando.setString(1, nome);
            resultado = comando.executeQuery();
            while(resultado.next()){
                mensalidade = new Mensalidade();
                mensalidade.setId_mensalidade(resultado.getInt(1));
                mensalidade.setNome(resultado.getString(2));
                mensalidade.setDataEmissao(resultado.getDate(3));
                mensalidade.setDataVencimento(resultado.getDate(4));
                mensalidade.setValorAPagar(resultado.getDouble(5));
                mensalidade.setStatus(resultado.getString(6)); 
                listaMensalidade.add(mensalidade);
            }
        }catch(Exception e){
            if(conexao != null){
                conexao.rollback();
            }
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
            return listaMensalidade;
    }
    
    public List<Mensalidade> pesquisarPorData(int mes) throws SQLException{
        List<Mensalidade> listaMensalidade = new ArrayList<>();
        Mensalidade mensalidade = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;           
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_DATA);
            comando.setInt(1, mes);
            resultado = comando.executeQuery();
            while(resultado.next()){
                mensalidade = new Mensalidade();
                mensalidade.setId_mensalidade(resultado.getInt(1));
                mensalidade.setNome(resultado.getString(2));
                mensalidade.setDataEmissao(resultado.getDate(3));
                mensalidade.setDataVencimento(resultado.getDate(4));
                mensalidade.setValorAPagar(resultado.getDouble(5));
                mensalidade.setStatus(resultado.getString(6)); 
                listaMensalidade.add(mensalidade);
            }
        }catch(Exception e){
            if(conexao != null){
                conexao.rollback();
            }
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
            return listaMensalidade;
    }    

    public List<Mensalidade> pesquisarPorNomeData(String nome, int mes) throws SQLException{
        List<Mensalidade> listaMensalidade = new ArrayList<>();
        Mensalidade mensalidade = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;           
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_NOME_DATA);
            comando.setString(1,nome);
            comando.setInt(2, mes);
            resultado = comando.executeQuery();
            while(resultado.next()){
                mensalidade = new Mensalidade();
                mensalidade.setId_mensalidade(resultado.getInt(1));
                mensalidade.setNome(resultado.getString(2));
                mensalidade.setDataEmissao(resultado.getDate(3));
                mensalidade.setDataVencimento(resultado.getDate(4));
                mensalidade.setValorAPagar(resultado.getDouble(5));
                mensalidade.setStatus(resultado.getString(6)); 
                listaMensalidade.add(mensalidade);
            }
        }catch(Exception e){
            if(conexao != null){
                conexao.rollback();
            }
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
            return listaMensalidade;
    } 

    public List<Mensalidade> buscarTodos() throws SQLException{    
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;      
        List<Mensalidade> listaMensalidades = new ArrayList<>();      
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_TODOS);
            resultado = comando.executeQuery();
            while(resultado.next()){
                Mensalidade mensalidade = this.extrairLinhaResultado(resultado);
                listaMensalidades.add(mensalidade);
            }
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
            return listaMensalidades;
    } 

    private Mensalidade extrairLinhaResultado(ResultSet resultado) throws SQLException {
        Mensalidade mensalidade = new Mensalidade();     
        mensalidade.setId_mensalidade(resultado.getInt(1));
        mensalidade.setNome(resultado.getString(2));
        mensalidade.setDataEmissao(resultado.getDate(3));
        mensalidade.setDataVencimento(resultado.getDate(4));
        mensalidade.setValorAPagar(resultado.getDouble(5));
        mensalidade.setStatus(resultado.getString(6));        
        return mensalidade;
    }    
    
}
