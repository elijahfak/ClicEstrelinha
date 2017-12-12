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
public class TurmaNaoEncontradaException extends Exception{

    public TurmaNaoEncontradaException() {
    }

    public TurmaNaoEncontradaException(String msg) {
        super(msg);
    }
}
