package excecoes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elizeu
 */
public class FuncionarioJaCadastradoException extends Exception {

    /**
     * Creates a new instance of <code>FuncionarioJaCadastradoException</code>
     * without detail message.
     */
    public FuncionarioJaCadastradoException() {
    }

    /**
     * Constructs an instance of <code>FuncionarioJaCadastradoException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public FuncionarioJaCadastradoException(String msg) {
        super(msg);
    }
}
