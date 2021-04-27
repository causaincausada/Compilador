/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OtrasClases;

/**
 *
 * @author carlo
 */
public class Token {
    public static final int IDENTIFICADOR = 0;
    public static final int SIMBOLO = 1;
    public static final int ENTERO = 2;
    public static final int DECIMAL = 3;
    public static final int CADENA = 4;
    public static final int CARACTER = 5;
    public static final int ERROR = 6;
    public static final int FIN = 7;
    
    private String cadena;
    private int Tipo;

    public Token(String cadena, int Tipo) {
        this.cadena = cadena;
        this.Tipo = Tipo;
    }

    public String getCadena() {
        return cadena;
    }

    public int getTipo() {
        return Tipo;
    }
    
    public String getTipoString() {
        String mensaje = "";
        
        switch (this.Tipo){
            case 0:
                mensaje = "Identifacador";
                break;
            case 1:
                mensaje = "Simbolo";
                break;
            case 2:
                mensaje = "Entero";
                break;
            case 3:
                mensaje = "Decimal";
                break;
            case 4:
                mensaje = "Cadena";
                break;
            case 5:
                mensaje = "Caracter";
                break;
            case 6:
                mensaje = "Error";
                break;
            case 7:
                mensaje = "Fin de achivo";
                break;
            default:
                mensaje = "Tipo no definido";
                break;
        }
        
        return mensaje;
    }
}
