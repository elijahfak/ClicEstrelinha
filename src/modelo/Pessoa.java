/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Elizeu
 */
public class Pessoa implements Serializable {

    private String nome;
    private int sexo;
    private boolean ativo;

    public Pessoa(String nome, int sexo, boolean ativo) {
        this.nome = nome;
        this.sexo = sexo;
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", sexo=" + sexo + ", ativo=" + ativo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

}
