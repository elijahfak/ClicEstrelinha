/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import excecoes.FuncionarioNaoDisponivelException;
import excecoes.TurmaLotadaException;
import excecoes.TurmaNaoEncontradaException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Logger;
import modelo.Aluno;
import modelo.FuncionarioInterface;
import modelo.Turma;

/**
 *
 * @author Elizeu
 */
public class ControleTurma implements Serializable {

    private ArrayList<Turma> turmas;
    private ArrayList<Turma> historico_turmas;
    public static ControleTurma instancia;

    public static ControleTurma getInstance() {
        //return GerrenciadorLivrosHolder.INSTANCE;
        if (instancia == null) {
            ControleTurma gu = new ControleTurma();
            instancia = gu;
        }

        return instancia;
    }

    public static void setInstance(ControleTurma cf) {
        instancia = cf;
    }

    public ControleTurma() {
        this.turmas = new ArrayList<Turma>();
        this.historico_turmas = new ArrayList<Turma>();
    }

    private Turma regrasCrudTurma(Turma turmaedicao, String nome, int educador_cpf, int auxiliar_cpf, int tipo, String sala, int turno, String[][] alunos) throws FuncionarioNaoDisponivelException, TurmaLotadaException {
        Turma turma = new Turma();
        turma.setNome(nome);
        turma.setAno(Calendar.getInstance().get(Calendar.YEAR));
        turma.setTipo(tipo);
        turma.setSala(Integer.parseInt(sala));
        turma.setTurno(turno);

//        for (int i = 0; i < alunos.length; i++) {
//            for (int j = 0; j < alunos[i].length; j++) {
//                System.out.print(alunos[i][j].toString() + ", ");
//            }
//            System.out.println("");
//        }
        if (turmaedicao == null) {
            FuncionarioInterface educador = (FuncionarioInterface) ControleFuncionario.getInstance().buscar_funcionario_cpf(educador_cpf);
            boolean is_educador_disponivel = ControleTurma.getInstance().isFuncionarioDisponivel(educador, turno);
            if (is_educador_disponivel) {
                turma.setEducador(educador);
            } else {
                throw new FuncionarioNaoDisponivelException("Educador não disponivel para este turno");
            }

            FuncionarioInterface auxiliar = (FuncionarioInterface) ControleFuncionario.getInstance().buscar_funcionario_cpf(auxiliar_cpf);
            boolean is_auxiliar_disponivel = ControleTurma.getInstance().isFuncionarioDisponivel(auxiliar, turno);
            if (is_auxiliar_disponivel) {
                turma.setAuxiliar(auxiliar);
            } else {
                throw new FuncionarioNaoDisponivelException("Auxiliar não disponivel para este turno");
            }
        } else {
            FuncionarioInterface educador = (FuncionarioInterface) ControleFuncionario.getInstance().buscar_funcionario_cpf(educador_cpf);
            if (turmaedicao.getEducador().getCpf() == educador_cpf) {
                turma.setEducador(educador);
            } else {
                boolean is_educador_disponivel = ControleTurma.getInstance().isFuncionarioDisponivel(educador, turno);
                if (is_educador_disponivel) {
                    turma.setEducador(educador);
                } else {
                    throw new FuncionarioNaoDisponivelException("Educador não disponivel para este turno");
                }
            }

            FuncionarioInterface auxiliar = (FuncionarioInterface) ControleFuncionario.getInstance().buscar_funcionario_cpf(auxiliar_cpf);

//            System.out.println("CPF AUXILIAR DA TURMA: " + turmaedicao.getAuxiliar().getCpf() + ", CPF SELECIONADO: " + auxiliar_cpf);
            if (turmaedicao.getAuxiliar().getCpf() == auxiliar_cpf) {
                turma.setAuxiliar(auxiliar);
            } else {
                boolean is_auxiliar_disponivel = ControleTurma.getInstance().isFuncionarioDisponivel(auxiliar, turno);
                if (is_auxiliar_disponivel) {
                    turma.setAuxiliar(auxiliar);
                } else {
                    throw new FuncionarioNaoDisponivelException("Auxiliar não disponivel para este turno");
                }
            }
        }

        int qtd_aluno = alunos.length;
        String report = "";
        if (qtd_aluno <= 14) {
            ArrayList<Aluno> alunos_set = ControleAluno.getInstance().getAlunosList(alunos);
            for (Aluno aluno : alunos_set) {
                boolean aat = turma.associarAlunoTurma(aluno);
                if (!aat) {
                    report += "\n" + aluno.getNome();
                }
            }

            if (!report.equals("")) {
                throw new TurmaLotadaException("Ocorreram alguns erros ao tentar associar os seguintes alunos a turma:\n\n" + report + "\n\nVerifique se a quantidade de alunos atingiu a capacidade máxima ou se o(s) aluno(s) listados estão ativos no sistema");
            }

        } else {
            throw new TurmaLotadaException("Quantidade de alunos atingiu a capacidade máxima");
        }

        return turma;
    }

    public void addTurma(String nome, int cpf_educador, int cpf_auxiliar, int tipo, String sala, int turno, String[][] alunos) throws FuncionarioNaoDisponivelException, TurmaLotadaException {
        Turma turma = this.regrasCrudTurma(null, nome, cpf_educador, cpf_auxiliar, tipo, sala, turno, alunos);
        this.turmas.add(turma);
        Serializacao.getInstance().gravarTurmas(ControleTurma.getInstance());
    }

    public void editarTurma(Turma turmaedicao, String nome, int cpf_educador, int cpf_auxiliar, int tipo, String sala, int turno, String[][] alunos) throws FuncionarioNaoDisponivelException, TurmaLotadaException {
        Turma turma = this.regrasCrudTurma(turmaedicao, nome, cpf_educador, cpf_auxiliar, tipo, sala, turno, alunos);

        int i = 0;
        for (Turma t : this.turmas) {
            if (t.getSala() == turmaedicao.getSala()
                    && t.getTurno() == turmaedicao.getTurno()) {
                this.turmas.remove(i);
                break;
            }
        }

        this.turmas.add(turma);

        Serializacao.getInstance().gravarTurmas(ControleTurma.getInstance());
    }

    public String[][] getTurma_matriz() {
        int list_size = this.turmas.size();
        String[][] list_set = new String[list_size][5];

        for (int i = 0; i < list_size; i++) {
            list_set[i][0] = this.turmas.get(i).getNome();
            list_set[i][1] = String.valueOf(this.turmas.get(i).getAno());
            list_set[i][2] = String.valueOf(this.turmas.get(i).getTipo());
            list_set[i][3] = String.valueOf(this.turmas.get(i).getTurno());
            list_set[i][4] = String.valueOf(this.turmas.get(i).getSala());
        }
        return list_set;
    }

    public boolean isFuncionarioDisponivel(FuncionarioInterface func, int turno) {
        boolean disponibilidade = true;
        int list_size = this.turmas.size();

        for (int i = 0; i < list_size; i++) {
            Turma turma = this.turmas.get(i);
            if ((turma.getEducador().getCpf() == func.getCpf() && turma.getTurno() == turno)
                    || (turma.getAuxiliar().getCpf() == func.getCpf() && turma.getTurno() == turno)) {
                disponibilidade = false;
                break;
            }
        }
        return disponibilidade;
    }

    public Turma buscar_turma(int turno, int sala) throws TurmaNaoEncontradaException {
        Turma encontrada = null;

        int list_size = this.turmas.size();

        for (int i = 0; i < list_size; i++) {
            Turma turma = this.turmas.get(i);
            if (turma.getTurno() == turno && turma.getSala() == sala) {
                encontrada = turma;
                break;
            }
        }

        if (encontrada == null) {
            throw new TurmaNaoEncontradaException("Turma não encontrada");
        }
        return encontrada;
    }

    public Turma buscar_turma_inativa(int turno, int sala) throws TurmaNaoEncontradaException {
        Turma encontrada = null;

        int list_size = this.historico_turmas.size();

        for (int i = 0; i < list_size; i++) {
            Turma turma = this.historico_turmas.get(i);
            if (turma.getTurno() == turno && turma.getSala() == sala) {
                encontrada = turma;
                break;
            }
        }

        if (encontrada == null) {
            throw new TurmaNaoEncontradaException("Turma não encontrada");
        }
        return encontrada;
    }

    public void excluirTurma(int turno, int sala) {
        int list_size = this.turmas.size();
        for (int i = 0; i < list_size; i++) {
            Turma turma = this.turmas.get(i);
            if (turma.getTurno() == turno && turma.getSala() == sala) {
                this.historico_turmas.add(turma);
                this.turmas.remove(i);
                Serializacao.getInstance().gravarTurmas(ControleTurma.getInstance());
                break;
            }
        }
    }

    public String[][] getRelatorio_turma_matriz() {
        int turmas_size = this.turmas.size();
        int historico_turmas_size = this.historico_turmas.size();
        int list_size = turmas_size + historico_turmas_size;

        String[][] list_set = new String[list_size][6];
        for (int i = 0; i < turmas_size; i++) {
            //"Nome", "Ano", "Situacao", "Vagas disponíveis"
            list_set[i][0] = this.turmas.get(i).getNome();
            list_set[i][1] = String.valueOf(this.turmas.get(i).getAno());
            list_set[i][2] = "ativo";
            list_set[i][3] = String.valueOf(14 - this.turmas.get(i).getAlunos().size());
            list_set[i][4] = String.valueOf(this.turmas.get(i).getSala());
            list_set[i][5] = String.valueOf(this.turmas.get(i).getTurno());
        }

        for (int j = 0; j < historico_turmas_size; j++) {
            list_set[turmas_size + j][0] = this.historico_turmas.get(j).getNome();
            list_set[turmas_size + j][1] = String.valueOf(this.historico_turmas.get(j).getAno());
            list_set[turmas_size + j][2] = "inativo";
            list_set[turmas_size + j][3] = String.valueOf("--");
            list_set[turmas_size + j][4] = String.valueOf(this.historico_turmas.get(j).getSala());
            list_set[turmas_size + j][5] = String.valueOf(this.historico_turmas.get(j).getTurno());
        }

        return list_set;
    }

    public ArrayList<Turma> getTurmas() {
        return turmas;
    }
    
    
    
}
