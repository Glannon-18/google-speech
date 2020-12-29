package com.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;


public class BackGroundPanel extends JPanel{
	Image im;
	public BackGroundPanel(Image im){
		this.im=im;
		this.setOpaque(false);
		
	}
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
