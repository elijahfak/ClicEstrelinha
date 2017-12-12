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
public class Responsabilidade implements Serializable{

    int grau_parentesco;
    Adulto responsavel;
    public static String[] GRAU_PARENTESCO_NOME = {
        "Mãe", "Pai", "Avô (pai)",
        "Avô (pai)", "Avô (mãe)", "Avó (pai)", "Avó (mãe)",
        "Tio (pai)", "Tio (mãe)", "Tia (pai)", "Tia (mãe)",
        "Responsável legal", "outro"
    };

    public Responsabilidade(int grau_parentesco, Adulto responsavel) {
        this.grau_parentesco = grau_parentesco;
        this.responsavel = responsavel;
    }

    public int getGrau_parentesco() {
        return grau_parentesco;
    }

    public void setGrau_parentesco(int grau_parentesco) {
        this.grau_parentesco = grau_parentesco;
    }

    public Adulto getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Adulto responsavel) {
        this.responsavel = responsavel;
    }

    public String getGrau_parentescoString(int i) {
        return Responsabilidade.GRAU_PARENTESCO_NOME[i];
    }

}
