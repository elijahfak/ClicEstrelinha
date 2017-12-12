package modelo;

import java.io.Serializable;
import java.util.HashMap;

public class Turma implements Serializable {

    private HashMap<String, Aluno> alunos;
    private int ano;
    private FuncionarioInterface auxiliar;
    private FuncionarioInterface educador;
    private int limite_idade;
    private String nome;
    private int sala;
    private int tipo;
    private int turno;

    public Turma(HashMap<String, Aluno> alunos, int ano, FuncionarioInterface auxiliar, FuncionarioInterface educador, String nome, int sala, int tipo, int turno) {
        this.alunos = alunos;
        this.ano = ano;
        this.auxiliar = auxiliar;
        this.educador = educador;
        this.nome = nome;
        this.sala = sala;
        this.turno = turno;
        this.setTipo(tipo);
    }

    public Turma() {
        this.alunos = new HashMap<String, Aluno>();
    }

    public boolean associarAlunoTurma(Aluno aluno) {
        if (this.alunos == null) {
            this.alunos = new HashMap<String, Aluno>();
        }

        if (this.alunos.size() < 14) {
            if (aluno.getIdade31Marco() == this.limite_idade && aluno.isAtivo()) {
                this.alunos.put(aluno.getMatricula(), aluno);
                return true;
            } else if (!aluno.isAtivo()) {
                System.out.println("Aluno Inativo. Aluno: " + aluno.getNome());
            } else {
                System.out.println("Idade não compativel. Aluno: " + aluno.getIdade31Marco() + ", Turma: " + this.limite_idade);
            }
        }
        return false;

    }

    public int getAno() {
        return ano;
    }

    public FuncionarioInterface getAuxiliar() {
        return auxiliar;
    }

    public FuncionarioInterface getEducador() {
        return educador;
    }

    public String getNome() {
        return nome;
    }
    
    public int getSala() {
        return sala;
    }
    
    public int getTipo() {
        return tipo;
    }
    
        public boolean isAlunoEmTurma(String matricula) {
        return this.alunos.containsKey(matricula);
    }

        public void setAno(int ano) {
        this.ano = ano;
    }

    public void setAuxiliar(FuncionarioInterface auxiliar) {
        this.auxiliar = auxiliar;
    }

    public void setEducador(FuncionarioInterface educador) {
        this.educador = educador;
    }

     public void setLimite_idade(int limite_idade) {
        this.limite_idade = limite_idade;
    }
    
      public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }
    
    
    
    
    
    
    
    
    
    
    public void desassociarAlunoTurma(Aluno aluno) {
        if (this.alunos.containsKey(aluno.getMatricula())) {
            this.alunos.remove(aluno.getMatricula());
        }
    }


    public HashMap<String, Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(HashMap<String, Aluno> alunos) {
        this.alunos = alunos;
    }


    public int getLimite_idade() {
        return limite_idade;
    }

   

    

  

    

    public void setTipo(int tipo) {
        this.tipo = tipo;

        if (tipo == 0) {
            this.limite_idade = 3;
        }

        if (tipo == 1) {
            this.limite_idade = 4;
        }

        if (tipo == 2) {
            this.limite_idade = 5;
        }
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
}
