/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lector;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author carlo
 */
public class Lector {

    private JFileChooser file;
    private File documento;
    private RandomAccessFile archivos;
    private boolean fin;

    public Lector() {
        this.file = null;
        this.documento = null;
        this.archivos = null;
        this.fin = false;
    }

    public void open() {
        file = new JFileChooser();
        file.setCurrentDirectory(new File("."));
        file.showOpenDialog(file);
        //abrimos el archivo seleccionado
        documento = file.getSelectedFile();

        try {
            if (documento != null) {
                archivos = new RandomAccessFile(documento.getPath(), "r");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
        }
    }

    public char read() {
        char c = 0;
        try {
            c = (char) archivos.read();
            checkFinal();
        } catch (IOException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Para cambiar los saltos de linea en espacios
        if(c == (char)13){
            read();
            checkFinal();
            return (char)0;
        }
        
        if(c == (char)10){
            return (char)0;
        }
        
        if(c == (char)9){
            return (char)0;
        }
        
        return c;
    }

    public void regresarPuntero() {
        try {
            archivos.seek(archivos.getFilePointer() - 1);
        } catch (IOException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean finalArchivo(){
        return fin;
    }
    
    private void checkFinal(){
        try {
            if(archivos.getFilePointer() >= (archivos.length()-1)){
                this.fin = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrar(){
        try {
            archivos.close();
        } catch (IOException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
