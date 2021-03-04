/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lector;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author carlo
 */
public class Lector {

    JFileChooser file;
    File documento;
    FileReader archivos;

    public Lector() {
        this.file = null;
        this.documento = null;
        this.archivos = null;
    }

    public void open() {
        file = new JFileChooser();
        file.setCurrentDirectory(new File("."));
        file.showOpenDialog(file);
        //abrimos el archivo seleccionado
        documento = file.getSelectedFile();
        
        try {
            if (documento != null) {
                archivos = new FileReader(documento);
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
            //archivos.mark(1);
        } catch (IOException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    

}
