/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.excecao;

/**
 *
 * @author KELVIN
 */
public class LoginSenhaInvalidoException extends AcademiaException{
    
    public LoginSenhaInvalidoException(String mensagem) {
        super("Login ou Senha Inválidos.");
    }
    
}
