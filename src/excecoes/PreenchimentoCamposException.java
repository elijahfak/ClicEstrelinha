/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excecoes;

/**
 *
 * @author Elizeu
 */
public class PreenchimentoCamposException extends Exception {

    /**
     * Creates a new instance of <code>PreenchimentoCamposException</code>
     * without detail message.
     */
    public PreenchimentoCamposException() {
    }

    /**
     * Constructs an instance of <code>PreenchimentoCamposException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public PreenchimentoCamposException(String msg) {
        super(msg);
    }
}
