/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexico;

import Lector.Lector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author carlo
 */
public class Lexico {

    public Lexico() {
    }

    public String token(Lector l) {
        String s = "";
        char c;
        c = l.read();
        
        if(((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')) || c == '_'){
            s += "" + c;
            
            //while()
        }
      
        
            
        return s;
    }
}
