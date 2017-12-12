/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

public class Adulto extends Pessoa implements Serializable {

    private int cpf;
    private String email;
    private int telefone;

    public Adulto(int cpf, String email,  int telefone,  String nome, int sexo, boolean ativo) {
        super(nome, sexo, ativo);
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }


    @Override
    public String toString() {
        return "Adulto{ cpf=" + cpf + ", email=" + email + ", telefone=" + telefone + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }


}
