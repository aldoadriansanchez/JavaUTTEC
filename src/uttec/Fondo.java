/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uttec;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author vicin
 */
public class Fondo extends javax.swing.JPanel {
ImageIcon imagen; 
    /**
     * Creates new form Fondo
     */
    public Fondo(String nombre) {
        initComponents();
          imagen=new ImageIcon(getClass().getResource(nombre));
        setSize(imagen.getIconWidth(),imagen.getIconHeight());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
protected void paintComponent (Graphics g){
        Dimension d=getSize();
        g.drawImage(imagen.getImage(), 0,0,d.width,d.height, this);
        setOpaque(false);
      super.paintComponent(g);
      
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
