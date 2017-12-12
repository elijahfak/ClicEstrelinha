/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Elizeu
 */
public class Aluno extends Pessoa implements Serializable {

    private Date data_matricula;
    private Date data_nascimento;
    private String matricula;
    private int numero_certidao;
    private boolean paidesconhecido;
    private boolean maedesconhecida;
    private ArrayList<Responsabilidade> responsavel;

    public Aluno(String nome, int sexo, boolean ativo, Date data_matricula, Date data_nascimento, String matricula, int numero_certidao, boolean paidesconhecido, boolean maedesconhecida, ArrayList<Responsabilidade> responsavel) {
        super(nome, sexo, ativo);
        this.data_matricula = data_matricula;
        this.data_nascimento = data_nascimento;
        this.matricula = matricula;
        this.numero_certidao = numero_certidao;
        this.paidesconhecido = paidesconhecido;
        this.maedesconhecida = maedesconhecida;
        this.responsavel = responsavel;
    }

    public Date getData_matricula() {
        return data_matricula;
    }

     public Date getData_nascimento() {
        return data_nascimento;
    }
    
    public void setData_matricula(Date data_matricula) {
        this.data_matricula = data_matricula;
    }

    public int getIdade() {
//        Date nascimento = new Date(1989, 6, 25);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this.data_nascimento);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        LocalDate birthDate = LocalDate.of(year, month, day);
        LocalDate currentDate = LocalDate.now();

        int age;
//        if ((birthDate != null) && (currentDate != null)) {
        age = Period.between(birthDate, currentDate).getYears();
//        }
        return age;
    }

    public int getIdade31Marco() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this.data_nascimento);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        LocalDate birthDate = LocalDate.of(year, month, day);

        Calendar calendart1_mar = new GregorianCalendar();
        calendar.setTime(new Date());
        LocalDate currentDate = LocalDate.of(calendart1_mar.get(Calendar.YEAR), 03, 31);

        int age;
        age = Period.between(birthDate, currentDate).getYears();
        System.out.println("Idade em 31 de Março: " + age);

        return age;
    }
    
    

    public void seData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public ArrayList<Responsabilidade> getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(ArrayList<Responsabilidade> responsavel) {
        this.responsavel = responsavel;
    }

    public int getNumero_certidao() {
        return numero_certidao;
    }

    public void setNumero_certidao(int numero_certidao) {
        this.numero_certidao = numero_certidao;
    }

    public boolean isPaiDesconhecido() {
        return paidesconhecido;
    }

    public void setPaiDesconhecido(boolean paidesconhecido) {
        this.paidesconhecido = paidesconhecido;
    }

    public boolean isMaeDesconhecida() {
        return maedesconhecida;
    }

    public void setMaeDesconhecida(boolean maedesconhecida) {
        this.maedesconhecida = maedesconhecida;
    }

   
}
