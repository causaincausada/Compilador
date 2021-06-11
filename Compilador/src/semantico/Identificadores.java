/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantico;

import OtrasClases.Token;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author carlo
 */
public class Identificadores {
    private HashMap<String, Integer> identificadores;
    private Set<String> identificadoresFunciones;
    private ArrayList<String> llamadasFuncion;
    private int tipo;
    
    public Identificadores() {
         identificadores = new HashMap<>();
         identificadoresFunciones = new HashSet<>();
         llamadasFuncion = new ArrayList<>();
    }
    
    public boolean addIdentificador(String i, int tipo){
        
        if(!identificadores.containsKey(i)){
            identificadores.put(i, tipo);
            return true;
        } else {
            error(1, i);
            return false;
        }
    }
    
    public boolean addIdentificadorFuncion(String i){
        if(!identificadores.containsKey(i)){
            identificadoresFunciones.add(i);
            identificadores.put(i, Token.FUNCION);
            return true;
        } else {
            error(1, i);
            return false;
        }
    }
    
    public void existeIdentificadorFuncion(String i){
        llamadasFuncion.add(i);
    }
    
    public boolean comprobarLlamadas(){
        boolean r = true;
        
        for(String i : llamadasFuncion){
            if(!identificadoresFunciones.contains(i)){
                error(3, i);
                return false;
            }
        }
        
        return r;
    }
    
    public boolean existeIdentificador(String i){
        if(identificadores.containsKey(i)){
            return true;
        } else {
            error(2, i);
            return false;
        }
    }
    
    public int getTipo(String i){
        return identificadores.get(i);
    }
    
    private void error(int e, String i) {
        switch (e) {
            case 1:
                System.out.println("No se puede volver a declarar con el mismo identificador: " + i);
                break;
            case 2:
                System.out.println("No fue declarado el identificador: " + i);
                break;
                
            case 3:
                System.out.println("No existe el identificador de una funcion: " + i);
                break;
            default:
                System.out.println("Error no considerado");
        }
    }
}
