/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.Labyrinthe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *	Cet objet est une fenêtre de menu. Il est possible de charger un 
 *	labyrinthe via un fichier .txt dont le nom est tapé dans un JTextField.
 *	De base labyrinthe.txt est chargé. Une image est également chargée.
 * 	En cliquant sur jouer, labyrintheGraphique est lancé.
 * @author Leo
 */
public class MenuGraphique {
 
    private JFrame fenetre = new JFrame("Super Labyrinthe");

    private JTextField jtf = new JTextField("labyrinthe2");

    private JLabel name = new JLabel("Super Labyrinthe");
    private JLabel label = new JLabel("Carte");

    private JButton bouton0 = new JButton("Jouer");
    private JButton bouton1 = new JButton("Charger");
    private JButton bouton2 = new JButton("Quitter");

    private JPanel mainPanel = new JPanel();

    private static LabyrintheGraphique myMazeGraphique;
    private static Labyrinthe lab;

    public MenuGraphique(String levelName){
        lab = new Labyrinthe(levelName);
        fenetre.setSize(600, 600);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        
        Font police2 = new Font("Arial", Font.BOLD, 30);
        Font police = new Font("Arial", Font.BOLD, 14);
        name.setFont(police2);
        jtf.setFont(police);
        jtf.setPreferredSize(new Dimension(150, 30));

        try {
            BufferedImage myPicture = ImageIO.read(getClass().getResourceAsStream("/ressources/lab.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            Box b0 = Box.createHorizontalBox();
            b0.add(name);

            Box b00 = Box.createHorizontalBox();
            b00.add(picLabel);
            Box b1 = Box.createHorizontalBox();
            b1.add(label);
            b1.add(Box.createRigidArea(new Dimension(5,0)));
            b1.add(jtf);
            b1.add(Box.createRigidArea(new Dimension(5,0)));
            b1.add(bouton1);

            Box b2 = Box.createHorizontalBox();
            b2.add(bouton0);

            Box b3 = Box.createVerticalBox();
            b3.add(Box.createRigidArea(new Dimension(0,15)));
            b3.add(b0);
            b3.add(Box.createRigidArea(new Dimension(0,30)));
            b3.add(b00);
            b3.add(Box.createRigidArea(new Dimension(0,30)));
            b3.add(b1);
            b3.add(Box.createRigidArea(new Dimension(0,15)));
            b3.add(b2);

            mainPanel.add(b3);
        }
        catch (IOException e){
            System.out.print("error loadin image " + getClass().getResourceAsStream("lab.png").toString());
            Box b0 = Box.createHorizontalBox();
            b0.add(name);

            Box b1 = Box.createHorizontalBox();
            b1.add(label);
            b1.add(Box.createRigidArea(new Dimension(5,0)));
            b1.add(jtf);
            b1.add(Box.createRigidArea(new Dimension(5,0)));
            b1.add(bouton1);

            Box b2 = Box.createHorizontalBox();
            b2.add(bouton0);

            Box b3 = Box.createVerticalBox();
            b3.add(Box.createRigidArea(new Dimension(0,15)));
            b3.add(b0);
            b3.add(Box.createRigidArea(new Dimension(0,30)));
            b3.add(b1);
            b3.add(Box.createRigidArea(new Dimension(0,15)));
            b3.add(b2);

            mainPanel.add(b3);
        }


        bouton0.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){

                myMazeGraphique = new LabyrintheGraphique(lab);
                fenetre.setVisible(false);
                
            }
        });
        
        bouton1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){

                lab = new Labyrinthe(jtf.getText());

            }
        });
        //
        fenetre.setContentPane(mainPanel);
        
        fenetre.setVisible(true);
    
    
    }
}
