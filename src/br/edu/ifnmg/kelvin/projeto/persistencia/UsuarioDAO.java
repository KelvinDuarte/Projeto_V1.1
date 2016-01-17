/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.persistencia;

import br.edu.ifnmg.kelvin.projeto.entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author KELVIN
 */
public class UsuarioDAO {
    
    private final String SQL_AUTENTICAR = "SELECT * FROM USUARIOS WHERE USUARIO = ? AND SENHA = ?";
    
    public Usuario autenticar(String login, String senha) throws SQLException{
    
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Usuario usuarioAutenticar = null;   
        try{
            conexao = BancoDeDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_AUTENTICAR); 
            comando.setString(1, login); 
            comando.setString(2, senha);
            resultado = comando.executeQuery();           
            while(resultado.next()){
                usuarioAutenticar = new Usuario();
                usuarioAutenticar.setUsuario(resultado.getString(1));
                usuarioAutenticar.setSenha(resultado.getString(2));
            }
        }catch(Exception e){
            if(conexao != null){
            conexao.rollback();           
            } 
        }finally{
            BancoDeDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return usuarioAutenticar;
    }
}
