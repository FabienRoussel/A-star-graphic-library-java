/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.CaseImplementee;
import Modele.Labyrinthe;

import java.util.Scanner;

/**
 *
 *	Cet objet est un menu console. Il est possible de charger un
 *	labyrinthe via un fichier .txt dont le nom est tapé .
 *	De base labyrinthe.txt est chargé. Une image est également chargée.
 * 	En cliquant sur jouer, labyrintheGraphique est lancé.
 * @author Léo
 */
public class Menu {

    private Labyrinthe myMaze ;

    public Menu(String fileName){
        myMaze = new Labyrinthe(fileName);
    }

    public String mainMenuVue(){
        String choice;
        Scanner scan = new Scanner(System.in);
        System.out.println("\nEnter a file name to load another Labyrinthe");
        System.out.println("Press z to play by yourself");
        System.out.println("Press e to activate our artificial intelligence");
        System.out.println("Press r to...");
        System.out.println("Press q to quit the game");
        choice = scan.nextLine();
        return choice;
    }

    public String artificialMenuVue(){
        String choice;
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPress d for an A* with Dijkstra heuristic");
        System.out.println("Press f for a simple bruteforce");
        System.out.println("Press q to quit the game");
        choice = scan.nextLine();
        return choice;
    }

    //Si on veut afficher la matice
    public void displayLab(){
        //double for classique pour afficher la matrice
        for(short lig = 0; lig < myMaze.getTailleX() ; lig++)
        {
            for(short col = 0; col < myMaze.getTailleY() ; col++){
                if (myMaze.getPosX()==lig &&  myMaze.getPosY()==col) System.out.print("P");
                else if(myMaze.getArriveeX()==lig &&  myMaze.getArriveeY()==col) System.out.print("A");
                else System.out.print(myMaze.getMatrice().get(lig).get(col)); //grace a la methode toString l'objet est converti en chaine de char :p
            }
            System.out.println("");
        }
        System.out.println();
    }


    //Si on veut afficher la matice
    public static void displayLabByAI(Labyrinthe myMaze, CaseImplementee caseImp){
        //double for classique pour afficher la matrice
        for(short lig = 0; lig < myMaze.getTailleX() ; lig++)
        {
            for(short col = 0; col < myMaze.getTailleY() ; col++){
                if (caseImp.getPosX()==lig &&  caseImp.getPosY()==col) System.out.print("P");
                else if(myMaze.getArriveeX()==lig &&  myMaze.getArriveeY()==col) System.out.print("A");
                else System.out.print(myMaze.getMatrice().get(lig).get(col)); //grace a la methode toString l'objet est converti en chaine de char :p
            }
            System.out.println("");
        }
        System.out.println("");
        //TimeUnit.SECONDS.sleep(1);
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //l'utilisateur pourra ici choisir un mouvement parmi les 5 proposés
    public String chooseMoveVue(){
        String choice;
        Scanner scan = new Scanner(System.in);
        //un menu classique
        System.out.print("\nPress z to go up,");
        System.out.println(" s to go down");
        System.out.print("Press q to go left,");
        System.out.println(" d to go right");
        System.out.println("Press r if you want to somewhere randomly");
        System.out.println("Press x to quit the game");
        displayLab();
        choice = scan.nextLine();
        return choice;
    }

    // si on veut continuer le bruteforce après dix coups aléatoire
    public String continueBruteforce(){
        String choice;
        Scanner scan = new Scanner(System.in);
        //un menu classique
        System.out.println("Press x to quit the game");
        System.out.print("\nPress another touch if you want to continue");
        choice = scan.nextLine();
        return choice;
    }

    public Labyrinthe getMaze(){
        return myMaze;
    }

    public void setMaze(Labyrinthe lab){
        myMaze = lab;
    }
}
