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
            System.out.println("Programa compilo exitosamente");
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
    
    private boolean bloque() {
        return false;
    }
    
    private boolean aux0(){
        return false;
    }
    
    private void error(int e){
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
        }
    }
}
