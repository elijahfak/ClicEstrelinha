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
public class Auxiliar extends Funcionario {
//
//    private boolean turno_trabalho1;
//    private boolean turno_trabalho2;

    public Auxiliar( String matricula, int cpf, String email,  int telefone, String nome, int sexo, boolean ativo) {
        super("", matricula, cpf, email,telefone,  nome, sexo, ativo);
//        this.turno_trabalho1 = false;
//        this.turno_trabalho2 = false;
    }

    @Override
    public String getCargo() {
        return "auxiliar";
    }

}
