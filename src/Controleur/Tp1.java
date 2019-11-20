/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Controleur.Heuristics.Dijkstra;
import Controleur.Heuristics.Heuristic;
import Modele.CaseImplementee;
import Modele.Direction;
import Modele.Labyrinthe;
import Vue.Menu;
import Vue.MenuGraphique;

import java.util.Stack;

/**
 *c'est le main
 * @author Léo
 */
public class Tp1 {
    private static String levelName="labyrinthe2";
    private static Menu myMenu = new Menu(levelName);
    
    //private static LabyrintheGraphique myMazeGraphique = new LabyrintheGraphique(myMenu.getMaze());

    private static MenuGraphique mainMenu = new MenuGraphique(levelName);

    public static void main(String[] args) {
        Tp1 controleur = new Tp1();
        controleur.mainMenuControleur();
        
    }

    /**
     *
     */
    private Tp1(){}

    /**
     * main menu for the controller
     */
    private void mainMenuControleur(){
        String choice;
        do {
            choice=myMenu.mainMenuVue();
            switch (choice) {
                case "z":// user
                    boucleDeJeu();
                    break;
                case "e":
                    artificialMenu();
                    break;
                case "r": // load another level
                    break;
                default:
                    Labyrinthe lab;
                    lab = new Labyrinthe(choice);
                    myMenu.setMaze(lab) ;
                    break;
            } // end of switch
        } while (!choice.equals("q"));
    }

    /**
     * the real boucle de jeu
     */
    private void boucleDeJeu(){
        myMenu.displayLab();
        boolean quit;
        do {
            quit = chooseMoveControleur();
        } while (!(myMenu.getMaze().win() || quit));
    }

    /**
     * a simple menu for AI
     */
    private void artificialMenu(){
        String choice;
        do {
            choice=myMenu.artificialMenuVue();
            switch (choice) {
                case "d":// dijkstra A*
                    myMenu.displayLab();
                    Search searchField = new Search(myMenu.getMaze());
                    Heuristic dijkstraHeur = new Dijkstra(searchField);
             //       System.out.println(dijkstraHeur);
             //       TimeUnit.SECONDS.sleep(1);
                    Stack<CaseImplementee> solution = searchField.Astar(dijkstraHeur);
                    searchField.displaySol(solution);
                    break;
                case "b":// bfs
                    break;
                case "f":
                    bruteforce();
                    break;
                default:
                    break;
            } // end of switch
            Labyrinthe lab =new Labyrinthe("labyrinthe");
            myMenu.setMaze(lab) ;
        } while (!choice.equals("q"));
    }

    /**
     *
     * @return
     * boolean to see if we can move
     */
    //l'utilisateur pourra ici choisir un mouvement parmi les 5 proposés
    private boolean chooseMoveControleur(){
        //un Scanner pour écire
        String choice;
        do {
            choice = myMenu.chooseMoveVue();
            try {
                switch (choice) {
                    case "z": // up
                        if(myMenu.getMaze().canMoveToCase(Direction.UP)) myMenu.getMaze().move(Direction.UP);
                        break;
                    case "q": // left
                        if(myMenu.getMaze().canMoveToCase(Direction.LEFT)) myMenu.getMaze().move(Direction.LEFT);
                        break;
                    case "s": // down
                        if(myMenu.getMaze().canMoveToCase(Direction.DOWN)) myMenu.getMaze().move(Direction.DOWN);
                        break;
                    case "d": // right
                        if(myMenu.getMaze().canMoveToCase(Direction.RIGHT)) myMenu.getMaze().move(Direction.RIGHT);
                        break;
                    case "r": // random
                        myMenu.getMaze().autoMove();
                        break;
                    case "x": // quitter
                        break;
                    default:
                        System.out.println("Please select a valid move\n");
                        myMenu.displayLab();
                        break;
                } // end of switch
            }catch (Labyrinthe.ImpossibleMoveException ignored){}

        } while (!choice.equals("x") && !myMenu.getMaze().win()); // tant qu'on n'a pas gagné ou envie de quitter le jeu
        if(myMenu.getMaze().win()) {
            System.out.println("Well played !! You win !!");
            myMenu.displayLab();
        }
        Labyrinthe lab =new Labyrinthe("labyrinthe");
        myMenu.setMaze(lab) ;
        return true;
    }

    /**
     * do the bruteforce
     */
    private void bruteforce(){
        String choice = "a";
        do {
            switch (choice) {
                case "x": // quit
                    break;
                default:
                    short boucle = 0;
                    while(!myMenu.getMaze().win() && boucle<10){
                        myMenu.getMaze().forceMove();
                        myMenu.displayLab();
                        try {
                            Thread.sleep(450);
                        } catch (InterruptedException ignored) {
                        }
                        boucle++;
                    }
                    choice = myMenu.continueBruteforce();
                    break;
            } // end of switch

        } while (!choice.equals("x") && !myMenu.getMaze().win());
    }


}
