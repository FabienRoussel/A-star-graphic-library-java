package Vue;


//On fait les imports
import Controleur.Heuristics.Dijkstra;
import Controleur.Heuristics.Heuristic;
import Controleur.Search;
import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

/**
 * Created by Fabien on 03/05/2017
 *  @author Fabien
 * labyrinthe en mode graphique dans une console
 */
public class LabyrintheGraphique extends JFrame implements ActionListener{
    //déclaration du labyrinthe
    private Labyrinthe myMaze;

    //Jpanel qui contiendra la grille
    private JPanel drawingPanel = new JPanel();

    //Les boutons pour se déplacer, acceder au menu et aux intelligence artificielle
    private JButton menu = new JButton("Menu");
    private JButton random = new JButton("Aléatoire");
    private JButton intelligent = new JButton("Intelligent");
    private JButton haut = new JButton("Haut");
    private JButton bas = new JButton("Bas");
    private JButton gauche = new JButton("Gauche");
    private JButton droite = new JButton("Droite");

    //Constructeur par défaut
    public LabyrintheGraphique(){
        this(new Labyrinthe());
    }

    //Constructeur surchargé

    /**
     *
     * @param lab de type Labyrinthe
     */
    public LabyrintheGraphique(Labyrinthe lab) {
        this.myMaze = lab;
        //On initailise le labyrinthe

        //on prépare les actions listener
        setupActionListeners();

        //on regroupe les boutons de déplcacements
        Box box0 = Box.createHorizontalBox();
        box0.add(haut);
        Box box1 = Box.createHorizontalBox();
        box1.add(gauche);
        box1.add(droite);
        Box box2 = Box.createHorizontalBox();
        box2.add(bas);

        //On crée la box qui englobera les boutons de déplacement
        Box buttonsPanel = Box.createVerticalBox();
        buttonsPanel.add(box0);
        buttonsPanel.add(box1);
        buttonsPanel.add(box2);
        buttonsPanel.revalidate();
/*
        buttonsPanel.setLayout(new BorderLayout());        buttonsPanel.add(haut, BorderLayout.NORTH);        buttonsPanel.add(bas, BorderLayout.SOUTH);        buttonsPanel.add(gauche, BorderLayout.WEST);        buttonsPanel.add(droite, BorderLayout.EAST);        buttonsPanel.revalidate();
*/
        //on regroupe les boutons de solutions et du menu
        Box solving0 = Box.createHorizontalBox();
        solving0.add(menu);
        Box solving1 = Box.createHorizontalBox();
        solving1.add(random);
        solving1.add(intelligent);
        //on les englobe dans la meme "div"
        Box solvingPanel = Box.createVerticalBox();
        solvingPanel.add(solving0);
        solvingPanel.add(solving1);
        solvingPanel.revalidate();

        //On ajoute les boutons dans le panel principal
        this.getContentPane().add(buttonsPanel, BorderLayout.WEST);
        this.getContentPane().add(solvingPanel, BorderLayout.EAST);

        drawingPanel.setLayout(new GridLayout(myMaze.getTailleY(), myMaze.getTailleX()));

        //we put the grid
        updateGrid();

        //we prepare the JFrame
        this.getContentPane().add(drawingPanel, BorderLayout.NORTH);
        this.setTitle("Labyrinthe Solver");
        //this.setSize((int)(getToolkit().getScreenSize().getWidth()/2),(int) (getToolkit().getScreenSize().getHeight()/2));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
/*
    public LabyrintheGraphique(Labyrinthe lab){
        myMaze=lab;

        this.setTitle("Animation");
        this.setSize((int)(getToolkit().getScreenSize().getWidth()/2),(int) (getToolkit().getScreenSize().getHeight()/2));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }*/

    /**
     * update the matrix of the Labyerinthe
     */
    private void updateGrid() {
        //On prepare le drawningPanel
        drawingPanel.removeAll();
        drawingPanel.setLayout(new GridLayout(myMaze.getTailleX(), myMaze.getTailleY()));
        JPanel element;

        //Double for selon la taille de la mtrice
        //en ligne d'abord
        for (short x = 0; x < myMaze.getTailleX(); x++) {
            //en colonne ensuite
            for (short y = 0; y < myMaze.getTailleY(); y++) {
                //we prepare the jpanel
                element = new JPanel();
                //we redimension it
                element.setPreferredSize(new Dimension(30, 20));
                //if on est a la pose player alors on met le player
                if (x == myMaze.getPosX() && y == myMaze.getPosY()) {
                    element.setBackground(new Color(0, 150, 250));
                //else if on rencontre un mur
                } else if (myMaze.getValueFromLigColMatrice(x, y) instanceof CaseMur) {
                    element.setBackground(Color.BLACK);
                //si c'est l'arrivée
                } else if (x == myMaze.getArriveeX() && y == myMaze.getArriveeY()) {
                    element.setBackground(new Color(138, 246, 194));
                 // si c'est une case ralentisseur (voir CaseRalentisseur (Bonus))
                } else if (myMaze.getValueFromLigColMatrice(x, y) instanceof CaseRalentisseur) {
                    element.setBackground(new Color(200, 200, 200));
                }
                //on ajoute le panel
                drawingPanel.add(element);
            }
        }
        //on validate le tavleau de panel et on l'affiche
        drawingPanel.validate();
        drawingPanel.repaint();
    }

    /**
     * to call the action
     */
    public void setupActionListeners() {
        //if we want to go up
        haut.addActionListener(e -> {
            try {
                // if we can move
                if(myMaze.canMoveToCase(Direction.UP)) myMaze.move(Direction.UP);
                updateGrid();
            } catch (Labyrinthe.ImpossibleMoveException e1) {
                //e1.printStackTrace();
            }
        });
        //si on va en bas
        bas.addActionListener(e -> {
            try {
                // if we can move
                if(myMaze.canMoveToCase(Direction.DOWN)) myMaze.move(Direction.DOWN);
                updateGrid();
            } catch (Labyrinthe.ImpossibleMoveException e1) {
                //e1.printStackTrace();
            }
        });

        //si on va a gauche
        gauche.addActionListener(e -> {
            try {
                // if we can move
                if(myMaze.canMoveToCase(Direction.LEFT)) myMaze.move(Direction.LEFT);
                updateGrid();
            } catch (Labyrinthe.ImpossibleMoveException e1) {
                //e1.printStackTrace();
            }

        });

        droite.addActionListener(e -> {
            try {
                // if we can move
                if(myMaze.canMoveToCase(Direction.RIGHT)) myMaze.move(Direction.RIGHT);
                updateGrid();
            } catch (Labyrinthe.ImpossibleMoveException e1) {
                //e1.printStackTrace();
            }
        });
        //do a random will do not have win
        random.addActionListener(e -> {
            Timer timer = new Timer(100, e12 -> {
                //force a random move that is possible
                myMaze.forceMove();
                updateGrid();
                //if win then stop
                if (myMaze.win()) {
                    ((Timer) e12.getSource()).stop();
                }
            });
            timer.start();
        });
        //button for the menu
        menu.addActionListener(e -> {
            new MenuGraphique("labyrinthe");
            this.setVisible(false);
        });
        //Astar
        //A pretty cool version of an artificial intelligence
        intelligent.addActionListener(e -> {
            ///on déclare l'heuristique et la classe de l'ASTAR
            Search searchField = new Search(myMaze);
            Heuristic dijkstraHeur = new Dijkstra(searchField);
            Stack<CaseImplementee> solution = searchField.Astar(dijkstraHeur);
            //On déroule la solution
            Timer timer = new Timer(250, e13 -> {
                CaseImplementee posPlayer = solution.pop();
                if (!solution.isEmpty()) {
                    myMaze.setPosX(posPlayer.getPosX());
                    myMaze.setPosY(posPlayer.getPosY());
                    updateGrid();
                } else {
                    myMaze.setPosX(posPlayer.getPosX());
                    myMaze.setPosY(posPlayer.getPosY());
                    updateGrid();
                    ((Timer) e13.getSource()).stop();
                }
            });
            timer.start();
        });
    }

    /**
     *
     * @param e
     *      when event occured
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
