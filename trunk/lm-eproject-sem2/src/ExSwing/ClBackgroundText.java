/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExSwing;



import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JTextField;


/**
 *
 * @author usu
 */
public class ClBackgroundText extends JTextField {

    private Image bgImage;

    public ClBackgroundText(Image bgImage) {
        super();
        this.bgImage = bgImage;        
    }

    public Image getBackgroundImage() {
        return bgImage;
    }

    public void setBackgroundImage(Image bgImage) {
        this.bgImage = bgImage;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isOpaque()) {
            setOpaque(false);
        }

        if (getBackgroundImage() != null) {
            for (int i = 0; i <= getWidth(); i += getBackgroundImage().getWidth(null)) {
                for (int j = 0; j < getHeight(); j += getBackgroundImage().getHeight(null)) {
                    g.drawImage(getBackgroundImage(), i, j, null);
                }
            }
        }

        super.paintComponent(g);
    }
    
}

