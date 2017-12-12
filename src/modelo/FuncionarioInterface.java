/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Elizeu
 */
public interface FuncionarioInterface {

    public String getNome();

    public String getMatricula();

    public void setMatricula(String matricula);

    public String getCargo();

    public int getCpf();

    public String getSenha();

    public void setSenha(String senha);

    public int getSexo();

    public int getTelefone();

    public String getEmail();

    public boolean isAtivo();

    public void setNome(String nome);

    public void setCpf(int cpf);

    public void setTelefone(int parseInt);

    public void setEmail(String email);

    public void setSexo(int sexo);

    public void setAtivo(boolean ativo);

}
