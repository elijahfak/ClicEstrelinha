/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import excecoes.AdultoNaoEncontradoException;
import excecoes.AlunoNaoEncontradoException;
import excecoes.CadastroResponsavelException;
import excecoes.PreenchimentoCamposException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Adulto;
import modelo.Aluno;
import modelo.Responsabilidade;
import modelo.Turma;

/**
 *
 * @author Elizeu
 */
public class ControleAluno implements Serializable {

    private HashMap<String, Aluno> alunos;
    private int sequence_aluno;
    public static ControleAluno instancia;

    public static ControleAluno getInstance() {
        //return GerrenciadorLivrosHolder.INSTANCE;
        if (instancia == null) {
            ControleAluno gu = new ControleAluno();
            instancia = gu;
        }

        return instancia;
    }

    public void escreveAlunosTeste() {
        Iterator it = this.getAlunos().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Aluno aluno = (Aluno) pair.getValue();

            System.out.println(aluno.getMatricula() + " - " + aluno.getNome() + " - " + aluno.getIdade31Marco());
        }
    }

    public static void setInstance(ControleAluno cf) {
        instancia = cf;
    }

    public ControleAluno() {
        this.alunos = new HashMap<String, Aluno>();
        this.sequence_aluno = 0;
    }

    public int setInstancesetInstance() {
        return sequence_aluno;
    }

    public String getSequenceComplementoMat() {
        String prefixo = "0000" + (this.sequence_aluno + 1);
        return prefixo.substring(prefixo.length() - 4);
    }

    private String gerarMatriculaAluno() {
        return Calendar.getInstance().get(Calendar.YEAR) + ControleAluno.getInstance().getSequenceComplementoMat();
    }

    private Aluno regrasCrudAluno(Aluno aluno, String nome, int sexo, String nascDia, String nascMes, String nascAno, String certidao, boolean ativo, String[][] responsaveis, boolean paidesconhecido, boolean maedesconhecida) throws PreenchimentoCamposException, CadastroResponsavelException, AdultoNaoEncontradoException, ParseException {
        Aluno alunovalida = this.regrasCrudAluno(nome, sexo, nascDia, nascMes, nascAno, certidao, ativo, responsaveis, paidesconhecido, maedesconhecida);
        alunovalida.setMatricula(aluno.getMatricula());
        return alunovalida;
    }

    private Aluno regrasCrudAluno(String nome, int sexo, String nascDia, String nascMes, String nascAno, String certidao, boolean ativo, String[][] responsaveis, boolean paidesconhecido, boolean maedesconhecida) throws PreenchimentoCamposException, CadastroResponsavelException, AdultoNaoEncontradoException, ParseException {
        try {
            Integer.parseInt(nascDia);
        } catch (Exception e) {
            throw new ParseException("Dia de Nascimento não pode conter letras", 0);
        }

        if (Integer.parseInt(nascDia) > 31) {
            throw new PreenchimentoCamposException("Campo \"Data de Nascimento\" com formato inválido");
        }

        try {
            Integer.parseInt(certidao);
        } catch (Exception e) {
            throw new ParseException("Certidão de Nascimento não pode conter letras", 0);
        }

        Date datanascimento;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            datanascimento = formatter.parse(nascDia + "/" + nascMes + "/" + nascAno);
//            System.out.println(datanascimento);
//            System.out.println(formatter.format(datanascimento));
        } catch (ParseException e) {
            throw new PreenchimentoCamposException("Campo \"Data de Nascimento\" com formato inválido..");
        }

        Date datamatricula = new Date();
        int numerocertidao = Integer.parseInt(certidao);
        int numeroresponsaveis = responsaveis.length;

        if (numeroresponsaveis < 1) {
            throw new CadastroResponsavelException("Não há responsáveis cadastrados. É necessário ao menos 1 responsável pai/mãe ou outro,no caso de falecimento");
        }

        ArrayList<Responsabilidade> responsaveis_set = new ArrayList<Responsabilidade>();
        Adulto pai = null;
        Adulto mae = null;
        for (int i = 0; i < numeroresponsaveis; i++) {
            int grau_parentesco = Integer.parseInt(responsaveis[i][3]);
            int cpf_it = Integer.parseInt(responsaveis[i][2]);

            Adulto ad = ControleAdulto.getInstance().buscar_adulto_cpf(cpf_it);

            if (grau_parentesco == 0) {//mae
                mae = ad;
            }

            if (grau_parentesco == 1) {//pai
                pai = ad;
            }

            Responsabilidade responsabilidade = new Responsabilidade(grau_parentesco, ad);
            responsaveis_set.add(responsabilidade);
        }

        String matricula_aluno = "";
        //Verificar se a propriedade "Mãe desconhecida/falecida" esta setada e se a propriedade "Pai desconhecido/falecido" esta setada
        if (paidesconhecido && maedesconhecida) {
            if (responsaveis_set.size() == 0) {
                throw new CadastroResponsavelException("Não há responsáveis cadastrados. É necessário ao menos 1 responsável selecionado.");
            } else {
                matricula_aluno = this.gerarMatriculaAluno();
            }
        } else {
            if ((mae == null && !maedesconhecida) && (pai == null && !paidesconhecido)) {
                throw new CadastroResponsavelException("Não há pais cadastrados. É necessário que ao menos 1 dos responsáveis seja a mãe ou o pai.");
            } else {
                matricula_aluno = this.gerarMatriculaAluno();
            }
        }

        Aluno aluno = new Aluno(
                nome, sexo, ativo, datamatricula, datanascimento, matricula_aluno, numerocertidao, paidesconhecido, maedesconhecida, responsaveis_set
        );

        return aluno;
    }

    public void addAluno(String nome, int sexo, String nascDia, String nascMes, String nascAno, String certidao, boolean ativo, String[][] responsaveis, boolean paidesconhecido, boolean maedesconhecida) throws PreenchimentoCamposException, CadastroResponsavelException, AdultoNaoEncontradoException, ParseException {
        Aluno aluno = this.regrasCrudAluno(nome, sexo, nascDia, nascMes, nascAno, certidao, ativo, responsaveis, paidesconhecido, maedesconhecida);
        this.getAlunos().put(aluno.getMatricula(), aluno);
        this.sequence_aluno++;
        Serializacao.getInstance().gravarAlunos(ControleAluno.getInstance());
    }

    public void editarAluno(Aluno aluno, String nome, int sexo, String nascDia, String nascMes, String nascAno, String certidao, boolean ativo, String[][] responsaveis, boolean paidesconhecido, boolean maedesconhecida) throws PreenchimentoCamposException, CadastroResponsavelException, AdultoNaoEncontradoException, ParseException {
        Aluno alunovalida = this.regrasCrudAluno(aluno, nome, sexo, nascDia, nascMes, nascAno, certidao, ativo, responsaveis, paidesconhecido, maedesconhecida);
        this.getAlunos().put(alunovalida.getMatricula(), alunovalida);

        if (!ativo) {
            System.out.println(alunovalida.getNome() + " Inativo");
            ArrayList<Turma> turmas = ControleTurma.getInstance().getTurmas();

            for (Turma turma : turmas) {
                if (turma.isAlunoEmTurma(alunovalida.getMatricula())) {
                    turma.desassociarAlunoTurma(aluno);
                    Serializacao.getInstance().gravarTurmas(ControleTurma.getInstance());
                    break;
                }
            }
        }

        Serializacao.getInstance().gravarAlunos(ControleAluno.getInstance());
    }

    public HashMap<String, Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(HashMap<String, Aluno> alunos) {
        this.alunos = alunos;
    }

    public ArrayList<Aluno> buscar_aluno() {
        ArrayList<Aluno> result_set = new ArrayList<Aluno>();

        Iterator it = ControleAluno.getInstance().getAlunos().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Aluno aluno = (Aluno) pair.getValue();
            // it.remove(); // avoids a ConcurrentModificationException
            result_set.add(aluno);
        }

        return result_set;
    }

    public String[][] buscar_aluno_matriz() {
        ArrayList<Aluno> alu = this.buscar_aluno();

        int qtd_alunos = alu.size();
        String[][] result_set = new String[this.getAlunos().size()][3];

        for (int i = 0; i < qtd_alunos; i++) {
            Aluno aluno = alu.get(i);
            result_set[i][0] = aluno.getMatricula();
            result_set[i][1] = aluno.getNome();
            result_set[i][2] = aluno.getIdade() + "";
        }

        return result_set;
    }

    public ArrayList<Aluno> getAlunosList(String[][] alunos) {
        ArrayList<Aluno> alunos_set = new ArrayList<>();
        for (int i = 0; i < alunos.length; i++) {
            String aluno_mat = alunos[i][0];
            Aluno al = this.alunos.get(aluno_mat);
            alunos_set.add(al);
        }

        return alunos_set;
    }

    public Aluno buscar_aluno_matricula(String matricula) throws AlunoNaoEncontradoException {

        if (!this.alunos.containsKey(matricula)) {
            throw new AlunoNaoEncontradoException("Aluno Não encontrado");
        }

        return this.alunos.get(matricula);
    }

    public String[] getAlunosStringArray() {
        ArrayList<Aluno> aluno_set = this.buscar_aluno();

        int list_size = aluno_set.size();
        String[] list_set = new String[list_size];

        for (int i = 0; i < list_size; i++) {
            list_set[i] = aluno_set.get(i).getNome();
        }
        return list_set;
    }

}
