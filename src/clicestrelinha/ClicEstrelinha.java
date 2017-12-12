/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clicestrelinha;

import controle.Controle;
import controle.Serializacao;
import excecoes.AdultoNaoEncontradoException;
import excecoes.CadastroResponsavelException;
import excecoes.FuncionarioJaCadastradoException;
import excecoes.PreenchimentoCamposException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import visao.CadastroFuncionarioForm;
import visao.Principal;

/**
 *
 * @author Elizeu
 */
public class ClicEstrelinha {

    public static void main(String args[]) {
        Controle controle = new Controle();

//        try {
//            controle.addFuncionario("Diretor 1", "senha", "1111111111", "123123", "123123", 1, 1, true);
//            controle.addFuncionario("Educador 1", "senha", "22222222", "123123", "123123", 1, 3, true);
//            controle.addFuncionario("Educador 2", "senha", "333333333", "123123", "123123", 1, 3, true);
//            controle.addFuncionario("Auxiliar 1", "senha", "44444444", "123123", "123123", 1, 4, true);
//            controle.addFuncionario("Auxiliar 2", "senha", "55555555", "123123", "123123", 1, 4, true);
//            controle.cadastrarResponsavel("Responsavel 1", "6666666", "1233333", "email@email.com", 0);
//
//            String responsaveis[][] = {
//                {"", "", "6666666", "0"}
//            };
//            controle.cadastrarAluno("Joaozinho", 0, "02", "02", "2012", "345345", true, responsaveis, false, false);
//            controle.cadastrarAluno("Mariazinha", 1, "02", "02", "2013", "345345", true, responsaveis, false, false);
//            controle.cadastrarAluno("Pedrinho", 0, "02", "02", "2013", "345345", true, responsaveis, false, false);
//            controle.cadastrarAluno("Marizinha", 1, "02", "02", "2014", "345345", true, responsaveis, false, false);
//            controle.cadastrarAluno("Zezinho", 0, "02", "02", "2014", "345345", true, responsaveis, false, false);
//
//        } catch (PreenchimentoCamposException | FuncionarioJaCadastradoException | CadastroResponsavelException | ParseException | AdultoNaoEncontradoException ex) {
//        }
//
        Serializacao.carrega_funcionarios();
        Serializacao.carrega_adultos();
        Serializacao.carrega_alunos();
        Serializacao.carrega_turmas();

        int funcs = controle.getFunc_matriz().length;

        if (funcs == 0) {
            try {
                controle.addFuncionario("Diretor 1", "123456", "1111111111", "123123", "123123", 1, 1, true);
            } catch (PreenchimentoCamposException | FuncionarioJaCadastradoException | ParseException ex) {
            }
        }

//
        new Principal().setVisible(true);
//                new CadastroFuncionarioForm().setVisible(true);
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//
//            }
//        });
    }

}
