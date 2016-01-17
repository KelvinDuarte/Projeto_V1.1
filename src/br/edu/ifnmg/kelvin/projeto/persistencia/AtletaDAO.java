/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.persistencia;

import br.edu.ifnmg.kelvin.projeto.entidade.Atleta;
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
public class AtletaDAO {
    
    private final static String SQL_INSERT = "INSERT INTO ATLETAS (NOME, APELIDO, NOMEPAI, NOMEMAE, DATA_NASCIMENTO, DATA_INICIO, SEXO, TELEFONE, CPF, RG, ORGAO_EXPEDIDOR, DATA_EXPEDICAO, EMAIL, ESTADO_CIVIL, ENDERECO, BAIRRO, NUMERO, CIDADE, UF, CEP, NACIONALIDADE, ID_PERSONAL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";  
    private final static String SQL_UPDATE = "UPDATE ATLETAS SET NOME = ?, APELIDO = ?, NOMEPAI = ?, NOMEMAE = ?, DATA_NASCIMENTO = ?, DATA_INICIO = ?, SEXO = ?, TELEFONE = ?, CPF = ?, RG = ?, ORGAO_EXPEDIDOR = ?, DATA_EXPEDICAO = ?, EMAIL = ?, ESTADO_CIVIL = ?, ENDERECO = ?, BAIRRO = ?, NUMERO = ?, CIDADE = ?, UF = ?, CEP = ?, NACIONALIDADE = ?, ID_PERSONAL = ?";
    private final static String SQL_DELETE = "DELETE FROM ATLETAS WHERE ID_ATLETA = ?";
    private final static String SQL_BUSCAR_TODOS = "SELECT * FROM ATLETAS";
    
    public void cadastrarAtleta(Atleta atleta) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        conexao = BancoDeDadosUtil.getConnection();
        comando = conexao.prepareStatement(SQL_INSERT);
        try
        {    
            comando.setString(1, atleta.getNome());
            comando.setString(2, atleta.getApelido());
            comando.setString(3, atleta.getNomePai());
            comando.setString(4, atleta.getNomeMae());
            java.sql.Date dataNascimento = new java.sql.Date(atleta.getDataNascimento().getTime());
            comando.setDate(5, dataNascimento);
            java.sql.Date dataInicio = new java.sql.Date(atleta.getDataInicio().getTime());
            comando.setDate(6, dataInicio);
            String sexo = String.valueOf(atleta.getSexo());
            comando.setString(7, sexo);
            comando.setString(8, atleta.getTelefone());
            comando.setString(9, atleta.getCpf());
            comando.setString(10, atleta.getRg());
            comando.setString(11, atleta.getOrgaoExpedidor());
            java.sql.Date dataExpedicao = new java.sql.Date(atleta.getDataExpedicao().getTime());
            comando.setDate(12, dataExpedicao);
            comando.setString(13, atleta.getEmail());
            comando.setString(14, atleta.getEstadoCivil());
            comando.setString(15, atleta.getEndereco());
            comando.setString(16, atleta.getBairro());
            comando.setInt(17, atleta.getNumero());
            comando.setString(18, atleta.getCidade());
            comando.setString(19, atleta.getUf());
            comando.setString(20, atleta.getCep());
            comando.setString(21, atleta.getNacionalidade());
            comando.setInt(22, atleta.getId_personal());
            comando.execute();
            conexao.commit();
        }catch(Exception e)          
        {
           if(conexao != null){
               conexao.rollback();
           } 
           throw e;
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando);
        }
    }
    
    public void editarAtleta(Atleta atleta) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        conexao = BancoDeDadosUtil.getConnection();
        comando = conexao.prepareStatement(SQL_UPDATE);
        try
        {    
            comando.setString(1, atleta.getNome());
            comando.setString(2, atleta.getApelido());
            comando.setString(3, atleta.getNomePai());
            comando.setString(4, atleta.getNomeMae());
            java.sql.Date dataNascimento = new java.sql.Date(atleta.getDataNascimento().getTime());
            comando.setDate(5, dataNascimento);
            java.sql.Date dataInicio = new java.sql.Date(atleta.getDataInicio().getTime());
            comando.setDate(6, dataInicio);
            String sexo = String.valueOf(atleta.getSexo());
            comando.setString(7, sexo);
            comando.setString(8, atleta.getTelefone());
            comando.setString(9, atleta.getCpf());
            comando.setString(10, atleta.getRg());
            comando.setString(11, atleta.getOrgaoExpedidor());
            java.sql.Date dataExpedicao = new java.sql.Date(atleta.getDataExpedicao().getTime());
            comando.setDate(12, dataExpedicao);
            comando.setString(13, atleta.getEmail());
            comando.setString(14, atleta.getEstadoCivil());
            comando.setString(15, atleta.getEndereco());
            comando.setString(16, atleta.getBairro());
            comando.setInt(17, atleta.getNumero());
            comando.setString(18, atleta.getCidade());
            comando.setString(19, atleta.getUf());
            comando.setString(20, atleta.getCep());
            comando.setString(21, atleta.getNacionalidade());
            comando.setInt(22, atleta.getId_personal());
            comando.execute();
            conexao.commit();
        }catch(Exception e)          
        {
           if(conexao != null){
               conexao.rollback();
           } 
           throw e;
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando);
        }        
    }
    
    public void excluirAtleta(int id_atleta) throws SQLException{
       Connection conexao = null;
        PreparedStatement comando = null;
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_DELETE);
            comando.setInt(1, id_atleta);
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
    public List<Atleta> buscarTodos() throws SQLException{
     
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;      
        List<Atleta> listaAtletas = new ArrayList<>();      
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_TODOS);
            resultado = comando.executeQuery();
            while(resultado.next()){
                Atleta atleta = this.extrairLinhaResultado(resultado);
                listaAtletas.add(atleta);
            }
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
            return listaAtletas;
    }
    
    private Atleta extrairLinhaResultado(ResultSet resultado) throws SQLException{
        Atleta atleta = new Atleta();
        
        atleta.setId_atleta(resultado.getInt(1));
        atleta.setNome(resultado.getString(2));
        atleta.setNomePai(resultado.getString(3));
        atleta.setNomeMae(resultado.getString(4));
        atleta.setApelido(resultado.getString(5));  
        java.sql.Date dataNascimento = resultado.getDate(6);
        atleta.setDataNascimento(new Date(dataNascimento.getTime()));       
        java.sql.Date dataInicio = resultado.getDate(7);
        atleta.setDataInicio(dataInicio);
        String sexo = resultado.getString(8);        
        atleta.setSexo(sexo.charAt(0));
        atleta.setTelefone(resultado.getString(9));
        atleta.setCpf(resultado.getString(10));
        atleta.setRg(resultado.getString(11));
        atleta.setOrgaoExpedidor(resultado.getString(12));      
        java.sql.Date dataExpedicao = resultado.getDate(13);
        atleta.setDataExpedicao(dataExpedicao);
        atleta.setEmail(resultado.getString(14));
        atleta.setEstadoCivil(resultado.getString(15));
        atleta.setEndereco(resultado.getString(16));
        atleta.setBairro(resultado.getString(17));
        atleta.setNumero(resultado.getInt(18));
        atleta.setCidade(resultado.getString(19));
        atleta.setUf(resultado.getString(20));
        atleta.setCep(resultado.getString(21));
        atleta.setNacionalidade(resultado.getString(22));
        atleta.setId_personal(resultado.getInt(23));
        return atleta;
    }               
}