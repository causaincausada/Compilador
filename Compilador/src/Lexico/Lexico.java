/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexico;

import Lector.Lector;

/**
 *
 * @author carlo
 */
public class Lexico {
    private Lector l = new Lector();
    
    public Lexico(Lector l) {
        this.l = l;
    }

    public String token() {
        String s = "";
        char c;
        c = l.read();
        
        if(((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')) || c == '_'){
            s += "" + c;
            c = l.read();
            while(((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')) || ((c >= '1') && (c <= '9')) || c == '_'){
                s += "" + c;
                c = l.read();
            }
            l.regresarPuntero();
            return s;
        } else if((c >= '1') && (c <= '9')){
            s += "" + c;
            c = l.read();
            while(((c >= '1') && (c <= '9')) || c == '.'){
                if(c == '.'){
                    s += "" + c;
                    c = l.read();
                    while(((c >= '1') && (c <= '9'))){
                        s += "" + c;
                        c = l.read();
                    }              
                    l.regresarPuntero();
                    return s;
                }else{
                    s += "" + c;
                    c = l.read();
                }
            }
            l.regresarPuntero();
            return s;
        } else {
            s += "" + c;
        }
        
        return s;
    }
}
