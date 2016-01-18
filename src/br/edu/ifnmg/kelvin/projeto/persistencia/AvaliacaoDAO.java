/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.persistencia;

import br.edu.ifnmg.kelvin.projeto.entidade.Avaliacao;
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
public class AvaliacaoDAO {

    private static final String SQL_INSERT = "INSERT INTO AVALIACAO(PESO, ALTURA, FREQUENCIA_CARDIACA, PRESSAO_ARTERIAL, TORAX, CINTURA, ABDOME, QUADRIL, ANTEBRACO_ESQUERDO, ANTEBRACO_DIREITO, BRACO_ESQUERDO, BRACO_DIREITO, COXA_ESQUERDA, COXA_DIREITA, NOME_ATLETA, NOME_PERSONAL, PANTURRILHA_ESQUERDA, PANTURRILHA_DIREITA, SUBSCAPULAR, TRICIPITAL, PEITORAL, AXILARMEDIA, SUPRAILIACA, ABDOMINAL, COXA, FLEXOES, ABDOMINAIS, DATA_AVALIACAO, DATA_VALIDADE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE AVALIACAO SET PESO = ?, ALTURA = ?, FREQUENCIA_CARDIACA = ?, PRESSAO_ARTERIAL = ?, TORAX = ?, CINTURA = ?, ABDOME = ?, QUADRIL = ?, ANTEBRACO_ESQUERDO = ?, ANTEBRACO_DIREITO = ?, BRACO_ESQUERDO = ?, BRACO_DIREITO = ?, COXA_ESQUERDA = ?, COXA_DIREITA = ?, NOME_ATLETA = ?, NOME_PERSONAL = ?, PANTURRILHA_ESQUERDA = ?, PANTURRILHA_DIREITA = ?, SUBSCAPULAR = ?, TRICIPITAL = ?, PEITORAL = ?, AXILARMEDIA = ?, SUPRAILIACA = ?, ABDOMINAL = ?, COXA = ?, FLEXOES = ?, ABDOMINAIS = ?, DATA_AVALIACAO = ?, DATA_VALIDADE = ?";
    private static final String SQL_DELETE = "DELETE FROM AVALIACAO WHERE ID_AVALIACAO = ?";
    private static final String SQL_BUSCAR_TODOS = "SELECT * FROM AVALIACAO";
    private static final String SQL_BUSCAR_ATLETA = "SELECT * FROM AVALIACAO WHERE NOME_ATLETA = ?";
    private static final String SQL_BUSCAR_PERSONAL = "SELECT * FROM AVALIACAO WHERE NOME_ATLETA = ?";
    private static final String SQL_BUSCAR_ATLETA_PERSONAL = "SELECT * FROM AVALIACAO WHERE NOME_ATLETA = ? AND NOME_PERSONAL = ?";
    public void cadastrarAvaliacao(Avaliacao avaliacao) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        conexao = BancoDeDadosUtil.getConnection();
        comando = conexao.prepareStatement(SQL_INSERT);
        try{
            comando.setDouble(1, avaliacao.getPeso());
            comando.setDouble(2, avaliacao.getAltura());
            comando.setDouble(3, avaliacao.getFrequenciaCardiaca());
            comando.setDouble(4, avaliacao.getPressaoArterial());
            comando.setDouble(5, avaliacao.getTorax());
            comando.setDouble(6, avaliacao.getCintura());
            comando.setDouble(7, avaliacao.getAbdome());
            comando.setDouble(8, avaliacao.getQuadril());
            comando.setDouble(9, avaliacao.getAntebracoEsquerdo());
            comando.setDouble(10, avaliacao.getAntebracoDireito());
            comando.setDouble(11, avaliacao.getBracoEsquerdo());
            comando.setDouble(12, avaliacao.getBracoDireito());
            comando.setDouble(13, avaliacao.getCoxaEsquerda());
            comando.setDouble(14, avaliacao.getCoxaDireita());
            comando.setString(15, avaliacao.getNomeAtleta());
            comando.setString(16, avaliacao.getNomePersonal());
            comando.setDouble(17, avaliacao.getPanturrilhaEsquerda());
            comando.setDouble(18, avaliacao.getPanturrilhaDireita());
            comando.setDouble(19, avaliacao.getSubscapular());
            comando.setDouble(20, avaliacao.getTricipital());
            comando.setDouble(21, avaliacao.getPeitoral());
            comando.setDouble(22, avaliacao.getAxilarMedia());
            comando.setDouble(23, avaliacao.getSupraIliaca());
            comando.setDouble(24, avaliacao.getAbdominal());
            comando.setDouble(25, avaliacao.getCoxa());
            comando.setDouble(26, avaliacao.getFlexoes());
            comando.setDouble(27, avaliacao.getAbdominais());        
            java.sql.Date dataAvaliacao = new java.sql.Date(avaliacao.getDataAvaliacao().getTime()); 
            comando.setDate(28, dataAvaliacao);           
            java.sql.Date dataValidade = new java.sql.Date(avaliacao.getDataValidade().getTime());
            comando.setDate(29, dataValidade);
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
    
    public void editarAvaliacao(Avaliacao avaliacao) throws Exception{
       Connection conexao = null;
        PreparedStatement comando = null;
        conexao = BancoDeDadosUtil.getConnection();
        comando = conexao.prepareStatement(SQL_UPDATE);
        try{
            comando.setDouble(1, avaliacao.getPeso());
            comando.setDouble(2, avaliacao.getAltura());
            comando.setDouble(3, avaliacao.getFrequenciaCardiaca());
            comando.setDouble(4, avaliacao.getPressaoArterial());
            comando.setDouble(5, avaliacao.getTorax());
            comando.setDouble(6, avaliacao.getCintura());
            comando.setDouble(7, avaliacao.getAbdome());
            comando.setDouble(8, avaliacao.getQuadril());
            comando.setDouble(9, avaliacao.getAntebracoEsquerdo());
            comando.setDouble(10, avaliacao.getAntebracoDireito());
            comando.setDouble(11, avaliacao.getBracoEsquerdo());
            comando.setDouble(12, avaliacao.getBracoDireito());
            comando.setDouble(13, avaliacao.getCoxaEsquerda());
            comando.setDouble(14, avaliacao.getCoxaDireita());
            comando.setString(15, avaliacao.getNomeAtleta());
            comando.setString(16, avaliacao.getNomePersonal());
            comando.setDouble(17, avaliacao.getPanturrilhaEsquerda());
            comando.setDouble(18, avaliacao.getPanturrilhaDireita());
            comando.setDouble(19, avaliacao.getSubscapular());
            comando.setDouble(20, avaliacao.getTricipital());
            comando.setDouble(21, avaliacao.getPeitoral());
            comando.setDouble(22, avaliacao.getAxilarMedia());
            comando.setDouble(23, avaliacao.getSupraIliaca());
            comando.setDouble(24, avaliacao.getAbdominal());
            comando.setDouble(25, avaliacao.getCoxa());
            comando.setDouble(26, avaliacao.getFlexoes());
            comando.setDouble(27, avaliacao.getAbdominais());           
            java.sql.Date dataAvaliacao = new java.sql.Date(avaliacao.getDataAvaliacao().getTime()); 
            comando.setDate(28, dataAvaliacao);            
            java.sql.Date dataValidade = new java.sql.Date(avaliacao.getDataValidade().getTime());
            comando.setDate(29, dataValidade);
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
    
    public void excluirAvaliacao(int id_avaliacao) throws SQLException{
       Connection conexao = null;
        PreparedStatement comando = null;
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_DELETE);
            comando.setInt(1, id_avaliacao);
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

    public List<Avaliacao> carregarTabelaAvaliacaoPorAtleta(String atleta) throws SQLException{
        List<Avaliacao> listaAvaliacao = new ArrayList<>();
        Avaliacao avaliacao = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;           
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_ATLETA);           
            comando.setString(1, atleta);
            resultado = comando.executeQuery();
            while(resultado.next()){
                avaliacao = new Avaliacao();        
                avaliacao.setId_avaliacao(resultado.getInt(1));
                avaliacao.setPeso(resultado.getDouble(2));
                avaliacao.setAltura(resultado.getDouble(3));
                avaliacao.setFrequenciaCardiaca(resultado.getDouble(4));
                avaliacao.setPressaoArterial(resultado.getDouble(5));
                avaliacao.setTorax(resultado.getDouble(6));
                avaliacao.setCintura(resultado.getDouble(7));
                avaliacao.setAbdome(resultado.getDouble(8));
                avaliacao.setQuadril(resultado.getDouble(9));
                avaliacao.setAntebracoEsquerdo(resultado.getDouble(10));
                avaliacao.setAntebracoDireito(resultado.getDouble(11));
                avaliacao.setBracoEsquerdo(resultado.getDouble(12));
                avaliacao.setBracoDireito(resultado.getDouble(13));
                avaliacao.setCoxaEsquerda(resultado.getDouble(14));
                avaliacao.setCoxaDireita(resultado.getDouble(15));
                avaliacao.setNomeAtleta(resultado.getString(16));
                avaliacao.setNomePersonal(resultado.getString(17));
                avaliacao.setPanturrilhaEsquerda(resultado.getDouble(18));
                avaliacao.setPanturrilhaDireita(resultado.getDouble(19));
                avaliacao.setSubscapular(resultado.getDouble(20));
                avaliacao.setTricipital(resultado.getDouble(21));
                avaliacao.setPeitoral(resultado.getDouble(22));
                avaliacao.setAxilarMedia(resultado.getDouble(23));
                avaliacao.setSupraIliaca(resultado.getDouble(24));
                avaliacao.setAbdominal(resultado.getDouble(25));
                avaliacao.setCoxa(resultado.getDouble(26));
                avaliacao.setFlexoes(resultado.getInt(27));
                avaliacao.setAbdominais(resultado.getInt(28));
                java.sql.Date dataAvaliacao = resultado.getDate(29);
                avaliacao.setDataAvaliacao(dataAvaliacao);
                java.sql.Date dataValidade = resultado.getDate(30);
                avaliacao.setDataValidade(dataAvaliacao);
                listaAvaliacao.add(avaliacao);
            }
        }catch(Exception e){
            if(conexao != null){
                conexao.rollback();
            }
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
            return listaAvaliacao;
    }
    
    public List<Avaliacao> carregarTabelaAvaliacaoPorPersonal(String personal) throws SQLException{
        List<Avaliacao> listaAvaliacao = new ArrayList<>();
        Avaliacao avaliacao = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;           
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_PERSONAL);           
            comando.setString(1, personal);
            resultado = comando.executeQuery();
            while(resultado.next()){
                avaliacao = new Avaliacao();        
                avaliacao.setId_avaliacao(resultado.getInt(1));
                avaliacao.setPeso(resultado.getDouble(2));
                avaliacao.setAltura(resultado.getDouble(3));
                avaliacao.setFrequenciaCardiaca(resultado.getDouble(4));
                avaliacao.setPressaoArterial(resultado.getDouble(5));
                avaliacao.setTorax(resultado.getDouble(6));
                avaliacao.setCintura(resultado.getDouble(7));
                avaliacao.setAbdome(resultado.getDouble(8));
                avaliacao.setQuadril(resultado.getDouble(9));
                avaliacao.setAntebracoEsquerdo(resultado.getDouble(10));
                avaliacao.setAntebracoDireito(resultado.getDouble(11));
                avaliacao.setBracoEsquerdo(resultado.getDouble(12));
                avaliacao.setBracoDireito(resultado.getDouble(13));
                avaliacao.setCoxaEsquerda(resultado.getDouble(14));
                avaliacao.setCoxaDireita(resultado.getDouble(15));
                avaliacao.setNomeAtleta(resultado.getString(16));
                avaliacao.setNomePersonal(resultado.getString(17));
                avaliacao.setPanturrilhaEsquerda(resultado.getDouble(18));
                avaliacao.setPanturrilhaDireita(resultado.getDouble(19));
                avaliacao.setSubscapular(resultado.getDouble(20));
                avaliacao.setTricipital(resultado.getDouble(21));
                avaliacao.setPeitoral(resultado.getDouble(22));
                avaliacao.setAxilarMedia(resultado.getDouble(23));
                avaliacao.setSupraIliaca(resultado.getDouble(24));
                avaliacao.setAbdominal(resultado.getDouble(25));
                avaliacao.setCoxa(resultado.getDouble(26));
                avaliacao.setFlexoes(resultado.getInt(27));
                avaliacao.setAbdominais(resultado.getInt(28));
                java.sql.Date dataAvaliacao = resultado.getDate(29);
                avaliacao.setDataAvaliacao(dataAvaliacao);
                java.sql.Date dataValidade = resultado.getDate(30);
                avaliacao.setDataValidade(dataAvaliacao);
                listaAvaliacao.add(avaliacao);
            }
        }catch(Exception e){
            if(conexao != null){
                conexao.rollback();
            }
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
            return listaAvaliacao;
    }

    public List<Avaliacao> carregarTabelaAvaliacaoPorAtletaPersonal(String atleta, String personal) throws SQLException{
        List<Avaliacao> listaAvaliacao = new ArrayList<>();
        Avaliacao avaliacao = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;           
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_ATLETA_PERSONAL);           
            comando.setString(1, atleta);
            comando.setString(2, personal);
            resultado = comando.executeQuery();
            while(resultado.next()){
                avaliacao = new Avaliacao();        
                avaliacao.setId_avaliacao(resultado.getInt(1));
                avaliacao.setPeso(resultado.getDouble(2));
                avaliacao.setAltura(resultado.getDouble(3));
                avaliacao.setFrequenciaCardiaca(resultado.getDouble(4));
                avaliacao.setPressaoArterial(resultado.getDouble(5));
                avaliacao.setTorax(resultado.getDouble(6));
                avaliacao.setCintura(resultado.getDouble(7));
                avaliacao.setAbdome(resultado.getDouble(8));
                avaliacao.setQuadril(resultado.getDouble(9));
                avaliacao.setAntebracoEsquerdo(resultado.getDouble(10));
                avaliacao.setAntebracoDireito(resultado.getDouble(11));
                avaliacao.setBracoEsquerdo(resultado.getDouble(12));
                avaliacao.setBracoDireito(resultado.getDouble(13));
                avaliacao.setCoxaEsquerda(resultado.getDouble(14));
                avaliacao.setCoxaDireita(resultado.getDouble(15));
                avaliacao.setNomeAtleta(resultado.getString(16));
                avaliacao.setNomePersonal(resultado.getString(17));
                avaliacao.setPanturrilhaEsquerda(resultado.getDouble(18));
                avaliacao.setPanturrilhaDireita(resultado.getDouble(19));
                avaliacao.setSubscapular(resultado.getDouble(20));
                avaliacao.setTricipital(resultado.getDouble(21));
                avaliacao.setPeitoral(resultado.getDouble(22));
                avaliacao.setAxilarMedia(resultado.getDouble(23));
                avaliacao.setSupraIliaca(resultado.getDouble(24));
                avaliacao.setAbdominal(resultado.getDouble(25));
                avaliacao.setCoxa(resultado.getDouble(26));
                avaliacao.setFlexoes(resultado.getInt(27));
                avaliacao.setAbdominais(resultado.getInt(28));
                java.sql.Date dataAvaliacao = resultado.getDate(29);
                avaliacao.setDataAvaliacao(dataAvaliacao);
                java.sql.Date dataValidade = resultado.getDate(30);
                avaliacao.setDataValidade(dataAvaliacao);
                listaAvaliacao.add(avaliacao);
            }
        }catch(Exception e){
            if(conexao != null){
                conexao.rollback();
            }
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
            return listaAvaliacao;
    }    
    
    public List<Avaliacao> buscarTodos() throws SQLException{     
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;      
        List<Avaliacao> listaAvaliacoes = new ArrayList<>();      
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_TODOS);
            resultado = comando.executeQuery();
            while(resultado.next()){
                Avaliacao avaliacao = this.extrairLinhaResultado(resultado);
                listaAvaliacoes.add(avaliacao);
            }
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
            return listaAvaliacoes;
    }
    
    public Avaliacao extrairLinhaResultado(ResultSet resultado) throws SQLException{
        Avaliacao avaliacao = new Avaliacao();        
        avaliacao.setId_avaliacao(resultado.getInt(1));
        avaliacao.setPeso(resultado.getDouble(2));
        avaliacao.setAltura(resultado.getDouble(3));
        avaliacao.setFrequenciaCardiaca(resultado.getDouble(4));
        avaliacao.setPressaoArterial(resultado.getDouble(5));
        avaliacao.setTorax(resultado.getDouble(6));
        avaliacao.setCintura(resultado.getDouble(7));
        avaliacao.setAbdome(resultado.getDouble(8));
        avaliacao.setQuadril(resultado.getDouble(9));
        avaliacao.setAntebracoEsquerdo(resultado.getDouble(10));
        avaliacao.setAntebracoDireito(resultado.getDouble(11));
        avaliacao.setBracoEsquerdo(resultado.getDouble(12));
        avaliacao.setBracoDireito(resultado.getDouble(13));
        avaliacao.setCoxaEsquerda(resultado.getDouble(14));
        avaliacao.setCoxaDireita(resultado.getDouble(15));
        avaliacao.setNomeAtleta(resultado.getString(16));
        avaliacao.setNomePersonal(resultado.getString(17));
        avaliacao.setPanturrilhaEsquerda(resultado.getDouble(18));
        avaliacao.setPanturrilhaDireita(resultado.getDouble(19));
        avaliacao.setSubscapular(resultado.getDouble(20));
        avaliacao.setTricipital(resultado.getDouble(21));
        avaliacao.setPeitoral(resultado.getDouble(22));
        avaliacao.setAxilarMedia(resultado.getDouble(23));
        avaliacao.setSupraIliaca(resultado.getDouble(24));
        avaliacao.setAbdominal(resultado.getDouble(25));
        avaliacao.setCoxa(resultado.getDouble(26));
        avaliacao.setFlexoes(resultado.getInt(27));
        avaliacao.setAbdominais(resultado.getInt(28));
        java.sql.Date dataAvaliacao = resultado.getDate(29);
        avaliacao.setDataAvaliacao(dataAvaliacao);
        java.sql.Date dataValidade = resultado.getDate(30);
        avaliacao.setDataValidade(dataAvaliacao);        
        return avaliacao;   
    }    
}
