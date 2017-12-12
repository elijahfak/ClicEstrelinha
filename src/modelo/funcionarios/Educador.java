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
public class Educador extends Funcionario {
    public Educador( String matricula,  int cpf, String email, int telefone, String nome, int sexo, boolean ativo) {
        super("", matricula,  cpf, email, telefone,  nome, sexo, ativo);

    }

    @Override
    public String getCargo() {
        return "educador";
    }
}
