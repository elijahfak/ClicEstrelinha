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
public class CredenciaisInvalidasException extends Exception {

    public CredenciaisInvalidasException() {
    }

    public CredenciaisInvalidasException(String msg) {
        super(msg);
    }
}
