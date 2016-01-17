/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.negocio;

import br.edu.ifnmg.kelvin.projeto.entidade.Usuario;
import br.edu.ifnmg.kelvin.projeto.persistencia.UsuarioDAO;
import java.sql.SQLException;

/**
 *
 * @author KELVIN
 */
public class UsuarioBO {
    
    public Usuario autenticar(String login, String senha) throws SQLException{
        Usuario usuarioExistente = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioExistente = usuarioDAO.autenticar(login, senha);
        if(usuarioExistente != null){
            return usuarioExistente;
        }
        return usuarioExistente;
    }
    
}
