/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexico;

import Lector.Lector;
import OtrasClases.Token;
import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class Lexico {

    private Lector l = new Lector();
    private ArrayList<String> simbolos;

    public Lexico(Lector l) {
        this.l = l;
        this.simbolos = new ArrayList();
        simbolos.add("constante");
        simbolos.add("entero");
        simbolos.add("decimal");
        simbolos.add("caracter");
        simbolos.add("cadena");
        simbolos.add("llamar");
        simbolos.add("inicio");
        simbolos.add("fin");
        simbolos.add("si");
        simbolos.add("entonces");
        simbolos.add("sino");
        simbolos.add("mientras");
        simbolos.add("hacer");
        simbolos.add("+");
        simbolos.add("-");
        simbolos.add("*");
        simbolos.add("/");
        simbolos.add("(");
        simbolos.add(")");
        simbolos.add(";");
        simbolos.add("funcion");
        simbolos.add(",");
        simbolos.add(".");
        simbolos.add("leer");
        simbolos.add("escribir");
    }

    public Token token() {
        int tipo = Token.ERROR;
        String s = "";
        char c;
        c = l.read();

        if (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')) || c == '_') {
            s += "" + c;
            c = l.read();
            while (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')) || ((c >= '1') && (c <= '9')) || c == '_') {
                s += "" + c;
                c = l.read();
            }
            l.regresarPuntero();
            
            if(isSimbolo(s)){
                tipo = Token.SIMBOLO;
            } else{
                tipo = Token.IDENTIFICADOR;
            }
            
            return new Token(s, tipo);
        } else if ((c >= '0') && (c <= '9')) {
            s += "" + c;
            c = l.read();
            while (((c >= '0') && (c <= '9')) || c == '.') {
                if (c == '.') {
                    char c2 = l.read();
                    l.regresarPuntero();
                    if (!((c2 >= '0') && (c2 <= '9'))) {
                        l.regresarPuntero();
                        return new Token(s, Token.ENTERO);
                    }

                    s += "" + c;
                    c = l.read();
                    while (((c >= '0') && (c <= '9'))) {
                        s += "" + c;
                        c = l.read();
                    }
                    l.regresarPuntero();
                    return new Token(s, Token.DECIMAL);
                } else {
                    s += "" + c;
                    c = l.read();
                }
            }
            l.regresarPuntero();
            return new Token(s, Token.ENTERO);
        } else if (c == ' ' || c == (char) 0) {
            return token();
        } else if (c == (char) 65535) {
            return new Token("Fin de archivo", Token.FIN);
        } else if (c == (char) 34) {
            s += "" + c;
            c = l.read();
            while (c != (char) 34) {
                s += "" + c;
                c = l.read();
            }
            s += "" + (char) 34;
            if(s.length() == 3){
                return new Token(s, Token.CARACTER);
            }
            return new Token(s, Token.CADENA);
        } else if (c == '=') {
            c = l.read();
            if (c == '=') {
                return new Token("==", Token.SIMBOLO);
            } else {
                l.regresarPuntero();
                return new Token("=", Token.SIMBOLO);
            }
        }else if (c == '!') {
            c = l.read();
            if (c == '=') {
                return new Token("!=", Token.SIMBOLO);
            } else {
                l.regresarPuntero();
                return new Token("!", Token.SIMBOLO);
            }
        } else if (c == '<') {
            c = l.read();
            if (c == '=') {
                return new Token("<=", Token.SIMBOLO);
            } else {
                l.regresarPuntero();
                return new Token("<", Token.SIMBOLO);
            }
        }else if (c == '>') {
            c = l.read();
            if (c == '=') {
                return new Token(">=", Token.SIMBOLO);
            } else {
                l.regresarPuntero();
                return new Token(">", Token.SIMBOLO);
            }
        }else if (c == '&') {
            c = l.read();
            if (c == '&') {
                return new Token("&&", Token.SIMBOLO);
            } else {
                l.regresarPuntero();
                return new Token("&", Token.ERROR);
            }
        }else if (c == '|') {
            c = l.read();
            if (c == '|') {
                return new Token("||", Token.SIMBOLO);
            } else {
                l.regresarPuntero();
                return new Token("|", Token.ERROR);
            }
        }else {
            s += c;
            
            if(isSimbolo(s)){
                tipo = Token.SIMBOLO;
            } else{
                tipo = Token.ERROR;
            }
            
            return new Token(s, tipo);
        }
    }
    
    private boolean isSimbolo(String s){
        return this.simbolos.contains(s);
    }
}
