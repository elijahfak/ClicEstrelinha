/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Elizeu
 */
public class Funcionario extends Adulto implements Serializable, FuncionarioInterface {

    private String matricula;
    private String senha;

    public Funcionario(String senha, String matricula, int cpf, String email, int telefone,  String nome, int sexo, boolean ativo) {
        super(cpf, email,  telefone, nome, sexo, ativo);
        this.matricula = matricula;
        this.senha = senha;
    }

    @Override
    public String getMatricula() {
        return matricula;
    }

    @Override
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String getCargo() {
        return "";
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "{Mat: " + matricula + " - Senha: " + senha + "} ";
//        return "Funcionario{" + "data_admissao=" + data_admissao + ", data_demissao=" + data_demissao + ", matricula=" + matricula
//               // + "celular=" + celular + ", cpf=" + cpf + ", email=" + email + ", endereco=" + endereco + ", rg=" + rg + ", telefone=" + telefone + ", telefone2=" + telefone2
////                + "nome=" + nome + ", sexo=" + sexo + ", ativo=" + ativo
//                + '}';
    }
}
