/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.persistencia;

import br.edu.ifnmg.kelvin.projeto.entidade.PersonalTrainer;
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
public class PersonalDAO {
    
    private final String SQL_INSERT = "INSERT INTO PERSONAL (NOME, APELIDO, SEXO, DATA_NASCIMENTO, DATA_ADMISSAO, FUNCAO, TELEFONE, CPF, RG, ORGAO_EXPEDIDOR, DATA_EXPEDICAO, EMAIL, ESTADO_CIVIL, SALARIO, ENDERECO, NUMERO, BAIRRO, CIDADE, UF, CEP, NACIONALIDADE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  
    private final String SQL_UPDATE = "UPDATE PERSONAL SET NOME = ?, APELIDO = ?, FUNCAO = ?, DATA_NASCIMENTO = ?, DATA_ADMISSAO = ?, SEXO = ?, TELEFONE = ?, CPF = ?, RG = ?, ORGAO_EXPEDIDOR = ?, DATA_EXPEDICAO = ?, EMAIL = ?, ESTADO_CIVIL = ?, SALARIO = ?, ENDERECO = ?, NUMERO = ?, BAIRRO = ?, CIDADE = ?, UF = ?, CEP = ?, NACIONALIDADE = ?";
    private final String SQL_DELETE = "DELETE FROM PERSONAL WHERE ID_PERSONAL = ?";
    private final String SQL_BUSCAR_TODOS = "SELECT * FROM PERSONAL";
    private final String SQL_BUSCAR_NOME = "SELECT * FROM PERSONAL WHERE NOME = ?";
    private final String SQL_BUSCAR_CPF = "SELECT * FROM PERSONAL WHERE CPF = ?";
    private final String SQL_BUSCAR_NOME_CPF = "SELECT * FROM PERSONAL WHERE NOME = ? AND CPF = ?";
    
    
    public void cadastrarPersonal(PersonalTrainer personalTrainer) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        conexao = BancoDeDadosUtil.getConnection();
        comando = conexao.prepareStatement(SQL_INSERT);
        try{
            comando.setString(1, personalTrainer.getNome());
            comando.setString(2, personalTrainer.getApelido());
            String sexo = String.valueOf(personalTrainer.getSexo());
            comando.setString(3, sexo);           
            java.sql.Date dataNascimento = new java.sql.Date(personalTrainer.getDataNascimento().getTime());
            comando.setDate(4, dataNascimento);           
            java.sql.Date dataAdmissao = new java.sql.Date(personalTrainer.getDaAdmissao().getTime());
            comando.setDate(5, dataAdmissao);
            comando.setString(6, personalTrainer.getFuncao());
            comando.setString(7, personalTrainer.getTelefone());
            comando.setString(8, personalTrainer.getCpf());
            comando.setString(9, personalTrainer.getRg());
            comando.setString(10, personalTrainer.getOrgaoExpedidor());           
            java.sql.Date dataExpedicao = new java.sql.Date(personalTrainer.getDataExpedicao().getTime());
            comando.setDate(11, dataExpedicao);
            comando.setString(12, personalTrainer.getEmail());
            comando.setString(13, personalTrainer.getEstadoCivil());
            comando.setDouble(14, personalTrainer.getSalario());
            comando.setString(15, personalTrainer.getEndereco());
            comando.setInt(16, personalTrainer.getNumero());
            comando.setString(17, personalTrainer.getBairro());
            comando.setString(18, personalTrainer.getCidade());
            comando.setString(19, personalTrainer.getUf());
            comando.setString(20, personalTrainer.getCep());
            comando.setString(21, personalTrainer.getNacionalidade());
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
    
    public void editarPersonal(PersonalTrainer personalTrainer) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        conexao = BancoDeDadosUtil.getConnection();
        comando = conexao.prepareStatement(SQL_UPDATE); 
        try{
            comando.setString(1, personalTrainer.getNome());
            comando.setString(2, personalTrainer.getApelido());
            comando.setString(3, personalTrainer.getFuncao());
            java.sql.Date dataNascimento = new java.sql.Date(personalTrainer.getDataNascimento().getTime());
            comando.setDate(4, dataNascimento);
            java.sql.Date dataAdmissao = new java.sql.Date(personalTrainer.getDaAdmissao().getTime());
            comando.setDate(5, dataAdmissao);
            String sexo = String.valueOf(personalTrainer.getSexo());
            comando.setString(6, sexo);   
            comando.setString(7, personalTrainer.getTelefone());
            comando.setString(8, personalTrainer.getCpf());
            comando.setString(9, personalTrainer.getUf());
            comando.setString(10, personalTrainer.getOrgaoExpedidor());
            java.sql.Date dataExpedicao = new java.sql.Date(personalTrainer.getDataExpedicao().getTime());            
            comando.setDate(11, dataExpedicao);
            comando.setString(12, personalTrainer.getEmail());
            comando.setString(13, personalTrainer.getEstadoCivil());
            comando.setDouble(14, personalTrainer.getSalario());
            comando.setString(15, personalTrainer.getEndereco());
            comando.setInt(16, personalTrainer.getNumero());
            comando.setString(17, personalTrainer.getBairro());
            comando.setString(18, personalTrainer.getCidade());
            comando.setString(19, personalTrainer.getUf());
            comando.setString(20, personalTrainer.getCep());
            comando.setString(21, personalTrainer.getNacionalidade());
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
    
    public void excluirPersonal(int id_personal) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_DELETE);
            comando.setInt(1, id_personal);
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

    public List<PersonalTrainer> carregarTabelaPersonalPorNome(String nome) throws SQLException{
        List<PersonalTrainer> listaPersonals = new ArrayList<>();
        PersonalTrainer personal = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;           
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_NOME);         
            comando.setString(1, nome);
            resultado = comando.executeQuery();
            while(resultado.next()){
                personal = new PersonalTrainer();        
                personal.setId_personal(resultado.getInt(1));
                personal.setNome(resultado.getString(2));
                personal.setApelido(resultado.getString(3));        
                String sexo = resultado.getString(4);        
                personal.setSexo(sexo.charAt(0));        
                java.sql.Date dataNascimento = resultado.getDate(5);        
                personal.setDataNascimento(new Date(dataNascimento.getTime()));        
                java.sql.Date dataAdmissao = resultado.getDate(6);        
                personal.setDaAdmissao(new Date(dataAdmissao.getTime()));
                personal.setFuncao(resultado.getString(7));
                personal.setTelefone(resultado.getString(8));
                personal.setCpf(resultado.getString(9));
                personal.setRg(resultado.getString(10));
                personal.setOrgaoExpedidor(resultado.getString(11));        
                java.sql.Date dataExpedicao = resultado.getDate(12);       
                personal.setDataExpedicao(new Date(dataExpedicao.getTime()));
                personal.setEmail(resultado.getString(13));
                personal.setEstadoCivil(resultado.getString(14));       
                double salario = Double.parseDouble(resultado.getString(15));        
                personal.setSalario(salario);
                personal.setEndereco(resultado.getString(16));        
                int numero = Integer.parseInt(resultado.getString(17));        
                personal.setNumero(numero);
                personal.setBairro(resultado.getString(18));
                personal.setCidade(resultado.getString(19));
                personal.setUf(resultado.getString(20));
                personal.setCep(resultado.getString(21));
                personal.setNacionalidade(resultado.getString(22));
                listaPersonals.add(personal);
            }
        }catch(Exception e){
            if(conexao != null){
                conexao.rollback();
            }
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
            return listaPersonals;
    }
    
     public List<PersonalTrainer> carregarTabelaPersonalPorCpf(String cpf) throws SQLException{
        List<PersonalTrainer> listaPersonals = new ArrayList<>();
        PersonalTrainer personal = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;           
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_CPF);         
            comando.setString(1, cpf);
            resultado = comando.executeQuery();
            while(resultado.next()){
                personal = new PersonalTrainer();        
                personal.setId_personal(resultado.getInt(1));
                personal.setNome(resultado.getString(2));
                personal.setApelido(resultado.getString(3));        
                String sexo = resultado.getString(4);        
                personal.setSexo(sexo.charAt(0));        
                java.sql.Date dataNascimento = resultado.getDate(5);        
                personal.setDataNascimento(new Date(dataNascimento.getTime()));        
                java.sql.Date dataAdmissao = resultado.getDate(6);        
                personal.setDaAdmissao(new Date(dataAdmissao.getTime()));
                personal.setFuncao(resultado.getString(7));
                personal.setTelefone(resultado.getString(8));
                personal.setCpf(resultado.getString(9));
                personal.setRg(resultado.getString(10));
                personal.setOrgaoExpedidor(resultado.getString(11));        
                java.sql.Date dataExpedicao = resultado.getDate(12);       
                personal.setDataExpedicao(new Date(dataExpedicao.getTime()));
                personal.setEmail(resultado.getString(13));
                personal.setEstadoCivil(resultado.getString(14));       
                double salario = Double.parseDouble(resultado.getString(15));        
                personal.setSalario(salario);
                personal.setEndereco(resultado.getString(16));        
                int numero = Integer.parseInt(resultado.getString(17));        
                personal.setNumero(numero);
                personal.setBairro(resultado.getString(18));
                personal.setCidade(resultado.getString(19));
                personal.setUf(resultado.getString(20));
                personal.setCep(resultado.getString(21));
                personal.setNacionalidade(resultado.getString(22));
                listaPersonals.add(personal);
            }
        }catch(Exception e){
            if(conexao != null){
                conexao.rollback();
            }
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
            return listaPersonals;
    }

    public List<PersonalTrainer> carregarTabelaPersonalPorNomeCpf(String nome, String cpf) throws SQLException{
        List<PersonalTrainer> listaPersonals = new ArrayList<>();
        PersonalTrainer personal = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;           
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_NOME_CPF);         
            comando.setString(1, nome);
            comando.setString(2, cpf);
            resultado = comando.executeQuery();
            while(resultado.next()){
                personal = new PersonalTrainer();        
                personal.setId_personal(resultado.getInt(1));
                personal.setNome(resultado.getString(2));
                personal.setApelido(resultado.getString(3));        
                String sexo = resultado.getString(4);        
                personal.setSexo(sexo.charAt(0));        
                java.sql.Date dataNascimento = resultado.getDate(5);        
                personal.setDataNascimento(new Date(dataNascimento.getTime()));        
                java.sql.Date dataAdmissao = resultado.getDate(6);        
                personal.setDaAdmissao(new Date(dataAdmissao.getTime()));
                personal.setFuncao(resultado.getString(7));
                personal.setTelefone(resultado.getString(8));
                personal.setCpf(resultado.getString(9));
                personal.setRg(resultado.getString(10));
                personal.setOrgaoExpedidor(resultado.getString(11));        
                java.sql.Date dataExpedicao = resultado.getDate(12);       
                personal.setDataExpedicao(new Date(dataExpedicao.getTime()));
                personal.setEmail(resultado.getString(13));
                personal.setEstadoCivil(resultado.getString(14));       
                double salario = Double.parseDouble(resultado.getString(15));        
                personal.setSalario(salario);
                personal.setEndereco(resultado.getString(16));        
                int numero = Integer.parseInt(resultado.getString(17));        
                personal.setNumero(numero);
                personal.setBairro(resultado.getString(18));
                personal.setCidade(resultado.getString(19));
                personal.setUf(resultado.getString(20));
                personal.setCep(resultado.getString(21));
                personal.setNacionalidade(resultado.getString(22));
                listaPersonals.add(personal);
            }
        }catch(Exception e){
            if(conexao != null){
                conexao.rollback();
            }
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
            return listaPersonals;
    }     
    
    public List<PersonalTrainer> buscarTodos() throws SQLException{    
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;      
        List<PersonalTrainer> listaPersonal = new ArrayList<>();      
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_TODOS);
            resultado = comando.executeQuery();
            while(resultado.next()){
                PersonalTrainer personal = this.extrairLinhaResultado(resultado);
                listaPersonal.add(personal);
            }
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
            return listaPersonal;
    }
    
    private PersonalTrainer extrairLinhaResultado(ResultSet resultado) throws SQLException{
        PersonalTrainer personal = new PersonalTrainer();        
        personal.setId_personal(resultado.getInt(1));
        personal.setNome(resultado.getString(2));
        personal.setApelido(resultado.getString(3));        
        String sexo = resultado.getString(4);        
        personal.setSexo(sexo.charAt(0));        
        java.sql.Date dataNascimento = resultado.getDate(5);        
        personal.setDataNascimento(new Date(dataNascimento.getTime()));        
        java.sql.Date dataAdmissao = resultado.getDate(6);        
        personal.setDaAdmissao(new Date(dataAdmissao.getTime()));
        personal.setFuncao(resultado.getString(7));
        personal.setTelefone(resultado.getString(8));
        personal.setCpf(resultado.getString(9));
        personal.setRg(resultado.getString(10));
        personal.setOrgaoExpedidor(resultado.getString(11));        
        java.sql.Date dataExpedicao = resultado.getDate(12);       
        personal.setDataExpedicao(new Date(dataExpedicao.getTime()));
        personal.setEmail(resultado.getString(13));
        personal.setEstadoCivil(resultado.getString(14));       
        double salario = Double.parseDouble(resultado.getString(15));        
        personal.setSalario(salario);
        personal.setEndereco(resultado.getString(16));        
        int numero = Integer.parseInt(resultado.getString(17));        
        personal.setNumero(numero);
        personal.setBairro(resultado.getString(18));
        personal.setCidade(resultado.getString(19));
        personal.setUf(resultado.getString(20));
        personal.setCep(resultado.getString(21));
        personal.setNacionalidade(resultado.getString(22));
        return personal;
    }
}
