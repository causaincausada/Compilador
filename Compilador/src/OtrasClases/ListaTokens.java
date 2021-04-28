/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OtrasClases;

import Lector.Lector;
import Lexico.Lexico;
import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class ListaTokens {
    private ArrayList<Token> aTokens;
    private int cont;
    
    public ListaTokens() {
        aTokens = new ArrayList();
        cont = 0;
    }
    
    public boolean calcularLista(Lexico lex, Lector l){
        while(!l.finalArchivo()){
           Token t = lex.token(); 
           //System.out.println( t.getCadena() + "                    " + t.getTipoString());
           if(t.getTipo() == Token.ERROR){
               System.out.println("Error en el token: " + t.getCadena());
               return false;
           }
           
           aTokens.add(t);
        }
        
        return true;
    }
    
    public Token getToken(){
        cont++;
        return aTokens.get(cont-1);
    }
    
    public void regresarPuntero(){
        cont--;
    }
}
