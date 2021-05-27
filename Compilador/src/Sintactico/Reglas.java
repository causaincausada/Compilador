/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sintactico;

import OtrasClases.ListaTokens;
import OtrasClases.Token;

/**
 *
 * @author carlo
 */
public class Reglas {
    ListaTokens tokens;
    
    public Reglas(ListaTokens tokens) {
        this.tokens = tokens;
    }
    
    //<PROGRAMA>::= <BLOQUE> <aux0> .
    public boolean programa(){
        if(!bloque()) return  false;
        if(!aux0()) return false;
        Token t = tokens.getToken();
        if(t.getCadena().equals(".")){
            return true;
        }else{
            error(1);
            return false;
        }
    }
    
    //<TIPOS>::= entero
    //<TIPOS>::= decimal
    //<TIPOS>::= caracter 
    //<TIPOS>::= cadena
    private boolean tipos(){
        Token t = tokens.getToken();
        switch(t.getCadena()){
            case "entero":
                return true;
            case "decimal":
                return true;
            case "caracter":
                return true;
            case "cadena":
                return true;
            default:
                error(2);
                return false;
        }
    }
    
    //<DATO>::= <entero_dato>
    //<DATO>::= <decimal_dato>
    //<DATO>::= <caracter _dato>
    //<DATO>::= <cadena_dato>
    private boolean dato(){
        Token t = tokens.getToken();
        switch(t.getTipo()){
            case Token.ENTERO:
                return true;
            case Token.DECIMAL:
                return true;
            case Token.CARACTER:
                return true;
            case Token.CADENA:
                return true;
            default:
                error(3);
                return false;
        }
    }
    
    //<DECLARAR CONSTANTES>::= constante <TIPOS> <identificador> = <DATO> ;
    private boolean declararConstantes(){
        Token t = tokens.getToken();
        if(t.getCadena().equals("constante")){
            if(!tipos()) return false;
            t = tokens.getToken();
            if(t.getTipo() == Token.IDENTIFICADOR){
                ///Agregar a la lista de indetificadores y ver si no esta ocupado***********************
                t = tokens.getToken();
                if(t.getCadena().equals("=")){
                    if(!dato()) return false;
                    t = tokens.getToken();
                    if(t.getCadena().equals(";")){
                        return true;
                    }else{
                        error(7);
                        return false;
                    }
                }else{
                    error(6);
                    return false;
                }
            }else{
                error(5);
                return false;
            }
        }else{
            error(4);
            return false;
        }
    }
    
    //<DECLARAR VARIABLES>::= <TIPOS> <identificador> <aux1>
    private boolean declararVariables() {
        if (!tipos()) return false;
        Token t = tokens.getToken();
        if (t.getTipo() == Token.IDENTIFICADOR) {
            ///Agregar a la lista de indetificadores y ver si no esta ocupado***********************
            if (!aux1()) return false;
            return true;
        } else {
            error(5);
            return false;
        }
    }
    
    //<aux1>::= ;
    //<aux1>::= = <DATO>;
    private boolean aux1(){
        Token t = tokens.getToken();
        switch(t.getCadena()){
            case ";":
                return true;
            case "=":
                if(!dato()) return false;
                t = tokens.getToken();
                if(t.getCadena().equals(";")){
                    return true;
                } else {
                    error(7);
                    return false;
                }
            default:
                error(8);
                return false;
        }
    }
    
    //<FUNCION>::= funcion <identificador> <BLOQUE>
    private boolean funcion(){
        Token t = tokens.getToken();
        if(t.getCadena().equals("funcion")){
            t = tokens.getToken();
            if(t.getTipo() == Token.IDENTIFICADOR){
                ///Agregar a la lista de indetificadores y ver si no esta ocupado***********************
                if(!bloque()) return false;
                return true;
            }else{
                error(5);
                return false;
            }
        }else{
            error(9);
            return false;
        }
    }
    
    //<ASIGANACIÓN DE VALORES>::= <identificador> = <EXP ARITMETICA> ;
    private boolean asignacionDeValores() {
        Token t = tokens.getToken();
        if (t.getTipo() == Token.IDENTIFICADOR) {
            t = tokens.getToken();
            if(t.getCadena().equals("=")){
                    if(!expAritmetica()) return false;
                    t = tokens.getToken();
                    if(t.getCadena().equals(";")){
                        return true;
                    }else{
                        error(7);
                        return false;
                    }
                }else{
                    error(6);
                    return false;
                }
        } else {
            error(5);
            return false;
        }
    }
    
    ///<LLAMAR FUNCION>::= llamar <identificador>;
    private boolean llamarFuncion() {
        Token t = tokens.getToken();
        if (t.getCadena().equals("llamar")) {
            t = tokens.getToken();
            if (t.getTipo() == Token.IDENTIFICADOR) {
                t = tokens.getToken();
                if (t.getCadena().equals(";")) {
                    return true;
                } else {
                    error(7);
                    return false;
                }

            } else {
                error(5);
                return false;
            }
        } else {
            error(10);
            return false;
        }
    }
    
    //<LEER>::= leer <identificador>;
    private boolean leer() {
        Token t = tokens.getToken();
        if (t.getCadena().equals("leer")) {
            t = tokens.getToken();
            if (t.getTipo() == Token.IDENTIFICADOR) {
                t = tokens.getToken();
                if (t.getCadena().equals(";")) {
                    return true;
                } else {
                    error(7);
                    return false;
                }

            } else {
                error(5);
                return false;
            }
        } else {
            error(11);
            return false;
        }
    }
    
    //<ESCRIBIR>::= escribir <identificador>;
    private boolean escribir() {
        Token t = tokens.getToken();
        if (t.getCadena().equals("escribir")) {
            t = tokens.getToken();
            if (t.getTipo() == Token.IDENTIFICADOR) {
                t = tokens.getToken();
                if (t.getCadena().equals(";")) {
                    return true;
                } else {
                    error(7);
                    return false;
                }

            } else {
                error(5);
                return false;
            }
        } else {
            error(12);
            return false;
        }
    }
    
    //<BLOQUE>::= inicio <aux2> fin
    private boolean bloque() {
        Token t = tokens.getToken();
        if (t.getCadena().equals("inicio")) {
            if(!aux2()) return false;
            t = tokens.getToken();
            if(t.getCadena().equals("fin")){
                return true;
            } else {
                error(14);
                return false;
            }
        } else {
            error(13);
            return false;
        }
    }
    
    //<IF>::= si ( <condición>) entonces <BLOQUE> <aux3>
    private boolean _if(){
        Token t = tokens.getToken();
        if(t.getCadena().equals("si")){
            t = tokens.getToken();
            if(t.getCadena().equals("(")){
                if(!condicion()) return false;
                t = tokens.getToken();
                if(t.getCadena().equals(")")){
                    t = tokens.getToken();
                    if(t.getCadena().equals("entonces")){
                        if(!bloque()) return false;
                        if(!aux3()) return false;
                        return true;
                    } else {
                        error(18);
                        return false;
                    }
                }else{
                    error(17);
                    return false;
                }
            } else {
                error(16);
                return false;
            }
        } else {
            error(15);
            return false;
        }
    }
    
    //<WHILE>::= mientras ( <CONDICION>) hacer <BLOQUE>
    private boolean _while(){
        Token t = tokens.getToken();
        if(t.getCadena().equals("mientras")){
            t = tokens.getToken();
            if(t.getCadena().equals("(")){
                if(!condicion()) return false;
                t = tokens.getToken();
                if(t.getCadena().equals(")")){
                    t = tokens.getToken();
                    if(t.getCadena().equals("hacer")){
                        if(!bloque()) return false;
                        return true;
                    } else {
                        error(20);
                        return false;
                    }
                }else{
                    error(17);
                    return false;
                }
            } else {
                error(16);
                return false;
            }
        } else {
            error(19);
            return false;
        }
    }
    
    //<O_RELACIONAL>::= ==
    //<O_RELACIONAL>::= !=
    //<O_RELACIONAL>::= <
    //<O_RELACIONAL>::= <=
    //<O_RELACIONAL>::= >
    //<O_RELACIONAL>::= >=
    //<O_RELACIONAL>::= &&
    //<O_RELACIONAL>::= ||
    private boolean o_relacional(){
        Token t = tokens.getToken();
        switch(t.getCadena()){
            case "==":
                return true;
            case "!=":
                return true;
            case "<":
                return true;
            case "<=":
                return true;
            case ">":
                return true;
            case ">=":
                return true;
            case "&&":
                return true;
            case "||":
                return true;                
            default:
                error(21);
                return false;
        }
    }
    
    //<CONDICION>::= <aux4> <O_RELACIONAL><aux4><aux5>
    private boolean condicion(){
        if(!aux4()) return false;
        if(!o_relacional()) return false;
        if(!aux4()) return false;
        if(!aux5()) return false;
        return true;
    }
    
    //<O_ARITMETICO>::= +
    //<O_ARITMETICO>::= -
    //<O_ARITMETICO>::= *
    //<O_ARITMETICO>::= /
    private boolean o_aritmetico(){
        Token t = tokens.getToken();
        switch(t.getCadena()){
            case "+":
                return true;
            case "-":
                return true;
            case "*":
                return true;
            case "/":
                return true;                
            default:
                error(22);
                return false;
        }
    }
    
    //<EXP ARITMETICA>::= <aux4><aux6> 
    private boolean expAritmetica(){
        if(!aux4()) return false;
        if(!aux6()) return false;
        return true;
    }
    
    //<aux0>::= ε
    //<aux0>::= <FUNCION> <aux0>
    //Follows(aux0) = {'.'}
    private boolean aux0(){
        Token t = tokens.getToken();
        if(!t.getCadena().equals(".")){
            tokens.regresarPuntero();
            if(!funcion()) return false;
            if(!aux0()) return false;
            return true;
        } else {
            tokens.regresarPuntero();
            return true;
        }
    }
    
    //<aux2>::= ε
    //<aux2>::= <ACCIONES> <aux2>
    //Follows(aux2) = {“fin”}
    private boolean aux2(){
        Token t = tokens.getToken();
        if(!t.getCadena().equals("fin")){
            tokens.regresarPuntero();
            if(!acciones()) return false;
            if(!aux2()) return false;
            return true;
        } else {
            tokens.regresarPuntero();
            return true;
        }
    }
    
    //<aux3>::= ε
    //<aux3>::= sino <BLOQUE>
    //Follows(aux3) = {“<identificador>” + “llamar” + “leer” + “escribir” + “si”
    //                  + “mientras” + “entero” + “decimal” + “caracter” + “cadena” 
    //                  + “constante” + “fin”}
    private boolean aux3(){
        Token t = tokens.getToken();
        String st = t.getCadena();
        if(!((st.equals("llamar"))||(st.equals("leer"))||(st.equals("escribir"))||
                (st.equals("si"))||(st.equals("mientras"))||(st.equals("entero"))||
                (st.equals("decimal"))||(st.equals("caracter"))||(st.equals("cadena"))||
                (st.equals("constante"))||(st.equals("fin"))||(t.getTipo()==Token.IDENTIFICADOR))){
            if(t.getCadena().equals("sino")){
                if(!bloque()) return false;
                return true;
            } else {
                error(23);
                return false;
            }
        } else {
            tokens.regresarPuntero();
            return true;
        }
    }
    
    //<aux4>::= <identificador>
    //<aux4>::= <DATO>
    //<aux4>::= (!<CONDICION>)
    private boolean aux4(){
        Token t = tokens.getToken();
        if(t.getTipo() == Token.IDENTIFICADOR){
            return true;
        } else if(t.getCadena().equals("(")) {
            t = tokens.getToken();
            if(t.getCadena().equals("!")){
                if(!condicion()) return false;
                t = tokens.getToken();
                if(t.getCadena().equals(")")){
                    return true;
                }else{
                    error(17);
                    return false;
                }
            } else {
                error(25);
                return false;
            }
            
        } else if((t.getTipo() == Token.ENTERO)||(t.getTipo() == Token.DECIMAL)||(t.getTipo() == Token.CARACTER)||(t.getTipo() == Token.CADENA)){
            tokens.regresarPuntero();
            if(!dato()) return false;
            return true;
        } else{
            error(24);
            return false;
        }
    }
    
    //<aux5>::= <O_RELACIONAL><aux4> <O_RELACIONAL> <aux4> <aux5>
    //<aux5>::= ε 
    //Follows(aux5) = {“)”}
    private boolean aux5(){
        Token t = tokens.getToken();
        if(!t.getCadena().equals(")")){
            tokens.regresarPuntero();
            if(!o_relacional())return false;
            if(!aux4())return false;
            if(!o_relacional())return false;
            if(!aux4())return false;
            if(!aux5())return false;
            return true;
        } else {
            tokens.regresarPuntero();
            return true;
        }
    }
    
    //<aux6>::= <O_ARITMETICO><aux4><aux6>
    //<aux6>::= ε
    //Follows(aux6) = {“;”}
    private boolean aux6(){
        Token t = tokens.getToken();
        if(!t.getCadena().equals(";")){
            tokens.regresarPuntero();
            if(!o_aritmetico()) return false;
            if(!aux4()) return false;
            if(!aux6()) return false;
            return true;
        } else {
            tokens.regresarPuntero();
            return true;
        }
    }
    
    //<ACCIONES>::= <ASIGNACION DE VALORES>
    //<ACCIONES>::= <LLAMAR FUNCION>
    //<ACCIONES>::= <LEER>
    //<ACCIONES>::= <ESCRIBIR>
    //<ACCIONES>::= <IF>
    //<ACCIONES>::= <WHILE>
    //<ACCIONES>::= <DECLARAR VARIABLES>
    //<ACCIONES>::= <DECLARAR CONSTANTES>
    private boolean acciones(){
        Token t = tokens.getToken();
        if(t.getTipo() == Token.IDENTIFICADOR){
            tokens.regresarPuntero();
            if(!asignacionDeValores()) return false;
            return true;
        }else {
            switch(t.getCadena()){
                case "llamar":
                    tokens.regresarPuntero();
                    if(!llamarFuncion()) return false;
                    return true;
                    
                case "leer":
                    tokens.regresarPuntero();
                    if(!leer()) return false;
                    return true;
                    
                case "escribir":
                    tokens.regresarPuntero();
                    if(!escribir()) return false;
                    return true;
                
                case "si":
                    tokens.regresarPuntero();
                    if(!_if()) return false;
                    return true;
                    
                case "mientras":
                    tokens.regresarPuntero();
                    if(!_while()) return false;
                    return true;
                    
                case "entero":
                    tokens.regresarPuntero();
                    if(!declararVariables()) return false;
                    return true;
                    
                case "decimal":
                    tokens.regresarPuntero();
                    if(!declararVariables()) return false;
                    return true;
                    
                case "caracter":
                    tokens.regresarPuntero();
                    if(!declararVariables()) return false;
                    return true;
                    
                case "cadena":
                    tokens.regresarPuntero();
                    if(!declararVariables()) return false;
                    return true;
                    
                case "constante":
                    tokens.regresarPuntero();;
                    if(!declararConstantes()) return false;
                    return true;
                    
                default:
                    error(26);
                    return false;
                    
            }
        }
        
    }
    
    private void error(int e){
//        tokens.regresarPuntero();
//        Token t = tokens.getToken();
//        System.out.println(t.getCadena());
        switch(e){
            case 1: 
                System.out.println("Se esperaba un '.'");
                break;
            case 2:
                System.out.println("Se esperaba un 'entero' o 'decimal' o 'caracter' o 'cadena'");
                break;
            case 3:
                System.out.println("Se esperaba un dato de tipo entero o dato de tipo decimal o dato de tipo caracter o dato de tipo cadena");
                break;
            case 4:
                System.out.println("Se esperaba un 'constante'");
                break;
            case 5:
                System.out.println("Se esperaba un identificador");
                break;                
            case 6:
                System.out.println("Se esperaba un '='");
                break;                
            case 7:
                System.out.println("Se esperaba un ';'");
                break;            
            case 8:
                System.out.println("Se esperaba un ';' o '='");
                break;               
            case 9:
                System.out.println("Se esperaba un 'funcion'");
                break;
            case 10:
                System.out.println("Se esperaba un 'llamar'");
                break;
                
            case 11:
                System.out.println("Se esperaba un 'leer'");
                break;
                
            case 12:
                System.out.println("Se esperaba un 'escribir'");
                break;
            
            case 13:
                System.out.println("Se esperaba un 'inicio'");
                break;
                
            case 14:
                System.out.println("Se esperaba un 'fin'");
                break;
            
            case 15:
                System.out.println("Se esperaba un 'si'");
                break;
                
            case 16:
                System.out.println("Se esperaba un '('");
                break;
            
            case 17:
                System.out.println("Se esperaba un ')'");
                break;
                
            case 18:
                System.out.println("Se esperaba un 'entonces'");
                break;
            
            case 19:
                System.out.println("Se esperaba un 'mientras'");
                break;
            
            case 20:
                System.out.println("Se esperaba un 'hacer'");
                break;
                
            case 21:
                System.out.println("Se esperaba un '==' o '!=' o '<' o '<=' o '>' o '>=' o '&&' o '||'");
                break;
            
            case 22:
                System.out.println("Se esperaba un '+' o '-' o '*' o '/'");
                break;
            
            case 23:
                System.out.println("Se esperaba un 'sino'");
                break;
            
            case 24:
                System.out.println("Se esperaba un dato de tipo entero o dato de tipo decimal o dato de tipo caracter o dato de tipo cadena o un identificador o un '('");
                break;
                
            case 25:
                System.out.println("Se esperaba un '!'");
                break;
                
            case 26:
                System.out.println("Se esperaba un 'llamar' o 'leer' o 'escribir' o 'si' o 'mientras' o 'entero' o 'decimal' o 'caracter' o 'cadena' o 'constante' o un identificador");
                break;
                
            default:
                System.out.println("Error no considerado");
        }
    }
}
