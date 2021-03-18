/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Lector.Lector;
import Lexico.Lexico;
import OtrasClases.Token;

/**
 *
 * @author carlo
 */
public class Test {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Lector l = new Lector();
        l.open();
        Lexico lex = new Lexico(l);
        
        while(!l.finalArchivo()){
           Token t = lex.token(); 
           System.out.println( t.getCadena() + "                    " + t.getTipoString());
        }
        
        l.cerrar();
    }
}
