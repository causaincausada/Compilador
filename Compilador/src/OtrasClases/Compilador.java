/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OtrasClases;

import Lector.Lector;
import Lexico.Lexico;
import Sintactico.Reglas;

/**
 *
 * @author carlo
 */
public class Compilador {

    public static void compilar() {
        Lector l = new Lector();
        l.open();
        Lexico lex = new Lexico(l);
        
        ListaTokens tokens = new ListaTokens();
        if(tokens.calcularLista(lex, l)){
            //No hay errores en analisis lexico
            Reglas sintactico = new Reglas(tokens);
            if(sintactico.programa()){
                
            } else {
                error();
            }
        } else {
            error();
        }
        
        l.cerrar();
    }
    
    private static void error(){
        System.out.println("Compilacion detenida.");
    }
}
