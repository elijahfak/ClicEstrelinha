/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import excecoes.CredenciaisInvalidasException;
import excecoes.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Adulto;
import modelo.Aluno;
import modelo.Funcionario;
import modelo.FuncionarioInterface;
import modelo.Turma;
import modelo.funcionarios.Auxiliar;
import modelo.funcionarios.Diretor;
import modelo.funcionarios.Educador;
import modelo.funcionarios.Secretario;
import visao.PopUps;

public class Controle {

    private boolean validaTodosCampos(int cargo, String nome, String senha, String cpf, String telefone, String email) {
        return nome.equals("")
                || (cargo == 1 || cargo == 2 ? senha.equals("") : false)
                //                || data.equals("")
                || cpf.equals("")
                //                || rg.equals("")
                || telefone.equals("")
                //                || telefonecomercial.equals("")
                || email.equals("") //                || endereco.equals("")
                ;
    }

    public boolean usuarioSessaoIsDiretor() {
        try {
            return ControleFuncionario.getInstance().getFuncionario_sessao().getCargo().equals("diretor");
        } catch (NullPointerException ex) {
            return false;
        }
    }

    private String gerarMatricula() {
        return (Calendar.getInstance().get(Calendar.YEAR) + ControleFuncionario.getInstance().getSequenceComplementoMat());
    }

    private boolean funcionarioExiste(int cpf) {
        return ControleFuncionario.getInstance().cpfExiste(cpf);
    }

    public String addFuncionario(String nome, String senha, String cpf, String telefone, String email, int sexo, int cargo, boolean ativo) throws PreenchimentoCamposException, FuncionarioJaCadastradoException, ParseException {
        boolean validaTodosCampos = this.validaTodosCampos(cargo, nome, senha, cpf, telefone, email);

        if (validaTodosCampos) {
            String campos_em_branco = this.getCamposEmBranco(nome, senha, cpf, telefone, email);
            throw new PreenchimentoCamposException("Preencha todos os campos para continuar\n\nOs seguintes campos apresentam-se em branco:\n" + campos_em_branco);
        }

        try {
            Integer.parseInt(cpf);
        } catch (Exception e) {
            throw new ParseException("CPF não pode conter letras", 0);
        }

        try {
            Integer.parseInt(telefone);
        } catch (Exception e) {
            throw new ParseException("Telefone não pode conter letras", 0);
        }

        boolean funcionarioExiste = funcionarioExiste(Integer.parseInt(cpf));
        if (funcionarioExiste) {
            throw new FuncionarioJaCadastradoException("Funcionario ja cadastrado");
        }

        String mat = this.gerarMatricula();

        Funcionario func = null;

        if (cargo == 1) {//diretor
            mat += "1";
            func = new Diretor(
                    senha, mat, Integer.parseInt(cpf), email, Integer.parseInt(telefone), nome, sexo, ativo
            );
        }

        if (cargo == 2) {//secretaria
            mat += "2";
            func = new Secretario(
                    senha, mat, Integer.parseInt(cpf), email, Integer.parseInt(telefone), nome, sexo, ativo
            );
        }

        if (cargo == 3) {//educador
            mat += "3";
            func = new Educador(
                    mat, Integer.parseInt(cpf), email, Integer.parseInt(telefone), nome, sexo, ativo
            );
        }

        if (cargo == 4) {//auxiliar
            mat += "4";
            func = new Auxiliar(
                    mat, Integer.parseInt(cpf), email, Integer.parseInt(telefone), nome, sexo, ativo
            );
        }

        ControleFuncionario.getInstance().addFuncionario(func);

        return mat;
    }

    public void editarFuncionario(FuncionarioInterface funcionario, String nome, String senha, String cpf, String telefone, String email, int sexo, int cargo, boolean ativo) throws PreenchimentoCamposException, ParseException {
        boolean validaTodosCampos = this.validaTodosCampos(cargo, nome, senha, cpf, telefone, email);

        if (validaTodosCampos) {
            String campos_em_branco = this.getCamposEmBranco(nome, senha, cpf, telefone, email);
            throw new PreenchimentoCamposException("Preencha todos os campos para continuar\n\nOs seguintes campos apresentam-se em branco:\n" + campos_em_branco);
        }

        if (!(cargo == 1 || cargo == 2)) {//diretor//secretaria
            senha = "";
        }

        funcionario.setNome(nome);
        funcionario.setSenha(senha);
        funcionario.setCpf(Integer.parseInt(cpf));
        funcionario.setTelefone(Integer.parseInt(telefone));
        funcionario.setEmail(email);
        funcionario.setSexo(sexo);
        funcionario.setAtivo(ativo);

        ControleFuncionario.getInstance().editarFuncionario(funcionario);
    }

    private boolean verificaPreenchimentoCamposCadastroResponsavel(String nome, String CPF, String telefone, String email) {
        return nome.equals("")
                || CPF.equals("")
                || telefone.equals("")
                || email.equals("");
    }

    public void cadastrarResponsavel(String nome, String CPF, String telefone, String email, int sexo) throws PreenchimentoCamposException {
        boolean vp = this.verificaPreenchimentoCamposCadastroResponsavel(nome, CPF, telefone, email);

        if (!vp) {
            int cpf_int = Integer.parseInt(CPF);
            boolean vcpf = ControleAdulto.getInstance().isCpfJaCadastrado(cpf_int);
            if (!vcpf) {
                ControleAdulto.getInstance().addResponsavel(nome, cpf_int, Integer.parseInt(telefone), email, sexo);
                PopUps.showMessage("Responsavel Cadastrado com sucesso");
            } else {
                PopUps.showMessage("Responsavel Ja Cadastrado");
            }
        } else {
            throw new PreenchimentoCamposException("Preencha todos os campos para prosseguir");
        }
    }

    public void editarResponsavel(Adulto adulto, String nome, String CPF, String telefone, String email, int sexo) throws PreenchimentoCamposException {
        boolean vp = this.verificaPreenchimentoCamposCadastroResponsavel(nome, CPF, telefone, email);

        if (vp) {
            throw new PreenchimentoCamposException("Preencha todos os campos para prosseguir");
        }

        adulto.setNome(nome);
        adulto.setCpf(Integer.parseInt(CPF));
        adulto.setTelefone(Integer.parseInt(telefone));
        adulto.setEmail(email);
        adulto.setSexo(sexo);

        ControleAdulto.getInstance().editarResponsavel(adulto);
        PopUps.showMessage("Responsavel Editado com sucesso");

    }

    private String getCamposEmBranco(String nome, String senha, String cpf, String telefone, String email) {
        String resp = "";
        if (nome.equals("")) {
            resp += "Nome\n";
        }

        if (senha.equals("")) {
            resp += "Senha\n";
        }

        if (cpf.equals("")) {
            resp += "CPF\n";
        }

        if (telefone.equals("")) {
            resp += "Telefone\n";
        }

        if (email.equals("")) {
            resp += "Email\n";
        }

        return resp;
    }

    public boolean autenticarUsuario(String login, String senha) throws CredenciaisInvalidasException, FuncionarioNaoEncontradoException, FuncionarioSemPermissaoException {
        return ControleFuncionario.getInstance().autenticarUsuario(login, senha);
    }

    public void logout() {
        ControleFuncionario.getInstance().setFuncionario_sessao(null);
    }

    public String[] getFuncrStringArray(int tipo) {
        return ControleFuncionario.getInstance().getFuncrStringArray(tipo);
    }

    public String[][] getFunc_matriz() {
        return ControleFuncionario.getInstance().getFunc_matriz();
    }

    public String[] getAdultoStringArray() {
        return ControleAdulto.getInstance().getAdultoStringArray();
    }

    public int getFuncionarioPorIndice(int tipo, int indice) {
        FuncionarioInterface func = ControleFuncionario.getInstance().buscar_funcionario_indice(tipo, indice);
        return func == null ? -1 : func.getCpf();
    }

    public int getFuncionarioIndicePorCpf(int tipo, int cpf) {
        return ControleFuncionario.getInstance().buscar_indice_funcionario(tipo, cpf);
    }

    public int getAdultoPorIndice(int indice) {
        Adulto adulto = ControleAdulto.getInstance().buscar_adulto_indice(indice);
        return adulto == null ? -1 : adulto.getCpf();
    }

    public String[] getAlunosStringArray() {
        return ControleAluno.getInstance().getAlunosStringArray();
    }

    private boolean verificaPreenchimentoCamposCadastroAluno(String nome, String nasc, String certidao) {
        return nome.equals("")
                || nasc.equals("")
                || certidao.equals("");
    }

    public void cadastrarAluno(String nome, int sexo, String nascDia, String nascMes, String nascAno, String certidao, boolean ativo, String[][] responsaveis, boolean paidesconhecido, boolean maedesconhecida) throws PreenchimentoCamposException, AdultoNaoEncontradoException, ParseException, CadastroResponsavelException {
        boolean vp = this.verificaPreenchimentoCamposCadastroAluno(nome, nascDia, certidao);
        if (!vp) {
            ControleAluno.getInstance().addAluno(nome, sexo, nascDia, nascMes, nascAno, certidao, ativo, responsaveis, paidesconhecido, maedesconhecida);
            PopUps.showMessage("Aluno Cadastrado com sucesso");
        } else {
            throw new PreenchimentoCamposException("Preencha todos os campos para prosseguir");
        }
    }

    public void editarAluno(Aluno aluno, String nome, int sexo, String nascDia, String nascMes, String nascAno, String certidao, boolean ativo, String[][] responsaveis, boolean paidesconhecido, boolean maedesconhecida) throws PreenchimentoCamposException, AdultoNaoEncontradoException, ParseException, CadastroResponsavelException {
        boolean vp = this.verificaPreenchimentoCamposCadastroAluno(nome, nascDia, certidao);
        if (!vp) {
            ControleAluno.getInstance().editarAluno(aluno, nome, sexo, nascDia, nascMes, nascAno, certidao, ativo, responsaveis, paidesconhecido, maedesconhecida);
            PopUps.showMessage("Aluno Cadastrado com sucesso");
        } else {
            throw new PreenchimentoCamposException("Preencha todos os campos para prosseguir");
        }

    }

    public String[][] getAluno_Matriz() {
        return ControleAluno.getInstance().buscar_aluno_matriz();
    }

    private boolean verificaPreenchimentoCamposCadastroTurma(String nome, String sala) {
        return !nome.equals("")
                || !sala.equals("");
    }

    public void cadastrarTurma(String nome, int indice_educador, int indice_auxiliar, int tipo, String sala, int turno, String[][] alunos) throws PreenchimentoCamposException, FuncionarioNaoDisponivelException, TurmaLotadaException {
        boolean is_diretor = this.usuarioSessaoIsDiretor();
        if (is_diretor) {
            boolean vp = this.verificaPreenchimentoCamposCadastroTurma(nome, sala);

            if (vp) {
                int educador_cpf = this.getFuncionarioPorIndice(3, indice_educador);
                int auxiliar_cpf = this.getFuncionarioPorIndice(4, indice_auxiliar);

                ControleTurma.getInstance().addTurma(nome, educador_cpf, auxiliar_cpf, tipo, sala, turno, alunos);
            } else {
                throw new PreenchimentoCamposException("É necessário preencher todos os campos para continuar");
            }

        } else {
            PopUps.showMessage("Área restrita a diretores");
        }

    }

    public void editarTurma(Turma turma, String nome, int indice_educador, int indice_auxiliar, int tipo, String sala, int turno, String[][] alunos) throws PreenchimentoCamposException, FuncionarioNaoDisponivelException, TurmaLotadaException {
        if (!this.usuarioSessaoIsDiretor()) {
            PopUps.showMessage("Área restrita a diretores");
            return;
        }

        boolean vp = this.verificaPreenchimentoCamposCadastroTurma(nome, sala);

        if (!vp) {
            throw new PreenchimentoCamposException("É necessário preencher todos os campos para continuar");
        }

        int educador_cpf = this.getFuncionarioPorIndice(3, indice_educador);
        int auxiliar_cpf = this.getFuncionarioPorIndice(4, indice_auxiliar);

        System.out.println(educador_cpf + " -- " + auxiliar_cpf);

        ControleTurma.getInstance().editarTurma(turma, nome, educador_cpf, auxiliar_cpf, tipo, sala, turno, alunos);
    }

    public Aluno buscar_aluno_matricula(String matricula) throws AlunoNaoEncontradoException {
        return ControleAluno.getInstance().buscar_aluno_matricula(matricula);
    }

    public FuncionarioInterface buscar_funcionario_cpf(int cpf) throws FuncionarioNaoEncontradoException {
        return ControleFuncionario.getInstance().buscar_funcionario_cpf(cpf);
    }

    public String[][] getAdulto_Matriz() {
        return ControleAdulto.getInstance().getAdulto_Matriz();
    }

    public Adulto buscar_adulto_cpf(int cpf) throws AdultoNaoEncontradoException {
        return ControleAdulto.getInstance().buscar_adulto_cpf(cpf);
    }

    public String[][] getTurma_matriz() {
        return ControleTurma.getInstance().getTurma_matriz();
    }

    public Turma buscar_turma(int turno, int sala) throws TurmaNaoEncontradaException {
        return ControleTurma.getInstance().buscar_turma(turno, sala);
    }

    public Turma buscar_turma_inativa(int turno, int sala) throws TurmaNaoEncontradaException {
        return ControleTurma.getInstance().buscar_turma_inativa(turno, sala);
    }

    public void excluir_turma(int turno, int sala) {
        ControleTurma.getInstance().excluirTurma(turno, sala);
    }

    public String[][] getRelatorio_turma_matriz() {
        return ControleTurma.getInstance().getRelatorio_turma_matriz();
    }

    public String[] getAnosPermitidosIdadeAluno() {
        String[] anos = new String[4];

        Calendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);

        int j = 0;
        for (int i = year - 6; i < year - 2; i++) {
            anos[j++] = Integer.toString(i);
        }

        return anos;
    }
}
