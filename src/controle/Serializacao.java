/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Elizeu
 */
public class Serializacao {

    private static final String LOC = "";
    private static final String FUNC_ARQUIVO = "funcionarios.ser";
    private static final String ADUL_ARQUIVO = "adultos.ser";
    private static final String ALUN_ARQUIVO = "alunos.ser";
    private static final String TURM_ARQUIVO = "turmas.ser";

    private static void inicializa(String arquivo) {

    }

    public void gravarAdultos(ControleAdulto adultos) {
        Serializacao.salva(adultos, ADUL_ARQUIVO);
    }

    public void gravarFuncionarios(ControleFuncionario funcionarios) {
        Serializacao.salva(funcionarios, FUNC_ARQUIVO);
    }

    public void gravarAlunos(ControleAluno alunos) {
        Serializacao.salva(alunos, ALUN_ARQUIVO);
    }

    public void gravarTurmas(ControleTurma turmas) {
        Serializacao.salva(turmas, TURM_ARQUIVO);
    }

    public static void carrega_funcionarios() {
        ControleFuncionario gl = (ControleFuncionario) Serializacao.getInstance().carrega(FUNC_ARQUIVO);

        if (gl == null) {
            ControleFuncionario.getInstance();
        }

//        gl == null ? ControleFuncionario.getInstance() : gl;
        ControleFuncionario.setInstance(gl);
    }

    public static void carrega_adultos() {
        ControleAdulto gl = (ControleAdulto) Serializacao.getInstance().carrega(ADUL_ARQUIVO);
        if (gl == null) {
            ControleAdulto.getInstance();
        }

        ControleAdulto.setInstance(gl);
    }

    public static void carrega_alunos() {
        ControleAluno gl = (ControleAluno) Serializacao.getInstance().carrega(ALUN_ARQUIVO);
        if (gl == null) {
            ControleAluno.getInstance();
        }

        ControleAluno.setInstance(gl);
    }

    public static void carrega_turmas() {
        ControleTurma gl = (ControleTurma) Serializacao.getInstance().carrega(TURM_ARQUIVO);
        if (gl == null) {
            ControleTurma.getInstance();
        }

        ControleTurma.setInstance(gl);
    }

    private Serializacao() {
    }

    public static void salva(Object objeto, String arquivo) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(LOC + arquivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(objeto);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            //Alerta.mostraString("Diretorio invalido.\n" + e.getMessage());
            System.out.println("Diretorio invalido.\n" + e.getMessage());
        } catch (IOException e) {
            //Alerta.mostraString("Não foi possivel salvar o arquivo..\n" + e.getMessage());
            System.out.println("Não foi possivel salvar o arquivo..\n" + e.getMessage());

            e.printStackTrace();
        } catch (Exception e) {
            //Alerta.mostraString("Ocorreu um erro inesperado.\n" + e.getMessage());
            System.out.println("Ocorreu um erro inesperado.\n" + e.getMessage());
        }
    }

    //Carrega os arquivos de inicializacao
    public static Object carrega(String arquivo) {
        Object objeto = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(LOC + arquivo);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objeto = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            inicializa(arquivo);
        } catch (Exception e) {
            System.out.println("Erro ->> " + e.getMessage());
        } finally {
            return objeto;
        }
    }

    public static Serializacao getInstance() {
        return SerializacaoHolder.INSTANCE;
    }

    private static class SerializacaoHolder {

        private static final Serializacao INSTANCE = new Serializacao();
    }
}
