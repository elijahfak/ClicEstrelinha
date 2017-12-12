/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.funcionarios;

import java.util.Date;
import modelo.Funcionario;

/**
 *
 * @author Elizeu
 */
public class Secretario extends Funcionario {

    public Secretario(String senha, String matricula, int cpf, String email, int telefone, String nome, int sexo, boolean ativo) {
        super(senha, matricula, cpf, email, telefone, nome, sexo, ativo);
    }

    @Override
    public String getCargo() {
        return "secretario";
    }
}
