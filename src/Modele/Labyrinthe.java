/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Controleur.Node;

import java.io.*;
import java.util.ArrayList;

/**
 *C'est la classe principale du projet. elle contient le labyrinthe et toutes ses informations
 * @author Fabien
 */
public class Labyrinthe {
    
    private static ArrayList<ArrayList<Case>> matrice = new ArrayList<>();
    private short tailleX;
    private short tailleY;
    private short posX;
    private short posY;
    private short departX;
    private short departY;
    private short arriveeX;
    private short arriveeY;

    /**
     *
     * @param dir
     * une Direction
     * @return
     *      if we can move to the case:true else false
     * @throws ImpossibleMoveException
     *      si on ne peut pas bouger
     */
    //imaginons que nous voulons modifier les positions du joueur à partir d'une direction donnée
    public boolean canMoveToCase(Direction dir) throws ImpossibleMoveException{
        switch (dir.toNumber()){
            case 0: // up
                if (posX>0 && matrice.get(posX-1).get(posY).canMoveToCase()) return true;
                else throw new ImpossibleMoveException();
            case 1: // down
                if (posX<tailleX-1 && matrice.get(posX+1).get(posY).canMoveToCase()) return true;
                else throw new ImpossibleMoveException();
            case 2: // left
                if (posY>0 && matrice.get(posX).get(posY-1).canMoveToCase()) return true;
                else throw new ImpossibleMoveException();
            case 3: // right
                if (posY<tailleY-1 && matrice.get(posX).get(posY+1).canMoveToCase()) return true;
                else throw new ImpossibleMoveException();
        }
        return false;
    }
    /**
     *
     * @param dir
     * une Direction
     * @return
     *      if we can move to the case:true else false
     */
    private boolean canMoveToCaseNoThrows(Direction dir){
        switch (dir.toNumber()){
            case 0: // up
                return posX > 0 && matrice.get(posX - 1).get(posY).canMoveToCase();
            case 1: // down
                return posX < tailleX - 1 && matrice.get(posX + 1).get(posY).canMoveToCase();
            case 2: // left
                return posY > 0 && matrice.get(posX).get(posY - 1).canMoveToCase();
            case 3: // right
                return posY < tailleY - 1 && matrice.get(posX).get(posY + 1).canMoveToCase();
        }
        return false;
    }

    /**
     *
     * @param dir
     * une Direction
     */
    public void move(Direction dir){
        switch (dir.toNumber()){
            case 0: // up
                 posX--;
                break;
            case 1: // down
                posX++;
                break;
            case 2: // left
                posY--;
                break;
            case 3: // right
                posY++;
                break;
        }
    }

    /**
     * for bruteforce
     */
    public void autoMove(){
        short random = (short) (Math.random()*4); //mettre le rand dès que possible
        if(canMoveToCaseNoThrows(Direction.toDir(random))) move(Direction.toDir(random));
    }

    /**
     * force to do a random move
     */
    public void forceMove(){
        short random = (short) (Math.random()*4); //mettre le rand dès que possible
        while(!canMoveToCaseNoThrows(Direction.toDir(random))) random = (short) (Math.random()*4);
        move(Direction.toDir(random));
    }

    /**
     *
     * @param node
     *  a node
     * @param dir
     * une Direction
     * @return
     *      un boolean
     */
    //imaginons que nous voulons modifier les positions du joueur à partir d'une direction donnée
    public boolean canMoveObjectToCase(Node node, Direction dir){
        switch (dir.toNumber()){
            case 0: // up
                if (node.getPosPlayerX()>0 && matrice.get(node.getPosPlayerX()-1).get(node.getPosPlayerY()).canMoveToCase()) return true;
                break;
            case 1: // down
                if (node.getPosPlayerX()<tailleX-1 && matrice.get(node.getPosPlayerX()+1).get(node.getPosPlayerY()).canMoveToCase()) return true;
                break;
            case 2: // left
                if (node.getPosPlayerY()>0 && matrice.get(node.getPosPlayerX()).get(node.getPosPlayerY()-1).canMoveToCase()) return true;
                break;
            case 3: // right
                if (node.getPosPlayerY()<tailleY-1 && matrice.get(node.getPosPlayerX()).get(node.getPosPlayerY()+1).canMoveToCase()) return true;
                break;
        }
        return false;
    }

    /**
     *
     * @return
     * true if we win
     */
    public boolean win(){
        return posX == arriveeX && posY == arriveeY;
    }

    /**
     * exeception for
     */
    //Exception
    public static class ImpossibleMoveException extends Exception{
        public ImpossibleMoveException(){
            System.out.println("Vous essayez de vous déplacer sur une case inaccessible!");
        }
    }

    /**
     * an exception for file
     */
    public static class FileFormatException extends Exception{
        public FileFormatException(){
            System.out.println("Fichier incorrect ou introuvable");
        }
    }

    /**
     * constructor
     */
    public Labyrinthe(){
        try {
            initFromFile("labyrinthe");
        } catch (FileFormatException ex) {
            System.out.println("Mauvais fichier");
        }
    }

    /**
     *
     * @param nameFile
     * name of the file to load
     * @throws FileFormatException
     * throws the error if there is one
     */
    private final void initFromFile(String nameFile) throws FileFormatException
    {
        InputStream inputStream = getClass().getResourceAsStream("/ressources/"+ nameFile +".txt");
        //Storing the content of the file into a BufferReader, then reading the BufferReader
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            //We define a line to read
            String line = br.readLine();
            do {
                String[] locationParts = line.split(" ");
               
                tailleX = Short.parseShort(locationParts[0].trim());
                tailleY = Short.parseShort(locationParts[1].trim());
                posX = Short.parseShort(locationParts[2].trim());
                posY = Short.parseShort(locationParts[3].trim());
                departX = posX;
                departY = posY;
                arriveeX= Short.parseShort(locationParts[4].trim());
                arriveeY = Short.parseShort(locationParts[5].trim());
                line = br.readLine();
            }while (line != null && (line.charAt(0) != 'X'&& line.charAt(0) != '_' && line.charAt(0) != '.'));
            
            for(short lig = 0; lig < tailleX ; lig++)
            {
                ArrayList<Case> matriceLigneCol = new ArrayList<>();
                for(short col=0 ;col < tailleY ;col++){
                    CaseImplementee uneCase = new CaseTrou(lig,col);
                    matriceLigneCol.add(uneCase);
                }
                matrice.add(lig,matriceLigneCol);
            }
            short rowNumber=0;
            while (line != null) {
                
                for (short col = 0; col < line.length(); col++) {
                    //Decoding the character and creating the corresponding BlockType
                    if(col>tailleY-1){
                        System.out.println("error size");
                        break;
                    }
                    switch (line.charAt(col)) {
                        case 'X':
                            CaseImplementee unMur = new CaseMur(rowNumber, col);
                            matrice.get(rowNumber).set(col, unMur);
                            break;
                        case '.':
                            CaseImplementee unRalentisseur = new CaseRalentisseur(rowNumber, col);
                            matrice.get(rowNumber).set(col, unRalentisseur);
                            break;
                        case '_'://nothing
                            break;
                        default:
                            break;
                    }
                }//Here we calculate the number of rows by incrementing it each time a line is read
                rowNumber++;
                line = br.readLine();
            }
        }
        catch (Exception ex){
            try {
                initFromFile("labyrinthe");
                throw new FileFormatException();
            } catch (FileFormatException ignored) {
            }
            //   System.exit(0);
        }            
    }
    
    public Labyrinthe(String nameFile)
    {
        try {
            initFromFile(nameFile);
        } catch (FileFormatException ex) {
            System.out.println("Mauvais fichier, chargement labyrinthe de base");

        }

    }
    
    
    
    //Pour tester la matrice
    //a enlever une fois le chargement implementé
    public Labyrinthe(boolean testing){
  /*      tailleX = 5;
        tailleY = 5;
        posX = 0;
        posY = 0;
        departX = 0;
        departY = 0;
        arriveeX = 4;
        arriveeY = 4;

        for(short lig = 0; lig < tailleX ; lig++)
        {
            ArrayList<Case> matriceLigneCol = new ArrayList<>();
            for(short col=0 ;col < tailleY ;col++){
                CaseImplementee uneCase = new CaseTrou(lig,col);
                matriceLigneCol.add(uneCase);
            }
            matrice.add(lig,matriceLigneCol);
        }
        CaseImplementee uneCase = new CaseMur((short)0, (short)1);
        matrice.get(0).set(1, uneCase); uneCase = new CaseMur((short)1, (short)1);
        matrice.get(1).set(1, uneCase); uneCase = new CaseMur((short)2, (short)1);
        matrice.get(2).set(1, uneCase); uneCase = new CaseMur((short)2, (short)2);
        matrice.get(2).set(2, uneCase); uneCase = new CaseMur((short)4, (short)2);
        matrice.get(4).set(2, uneCase);/***/
    //    uneCase = new CaseRalentisseur((short)3, (short)4); matrice.get(3).set(4, uneCase);


        tailleX = 15;
        tailleY = 35;
        posX = 0;
        posY = 0;
        departX = 0;
        departY = 0;
        arriveeX = 14;
        arriveeY = 18;
        for(short lig = 0; lig < tailleX ; lig++)
        {
            ArrayList<Case> matriceLigneCol = new ArrayList<>();
            for(short col=0 ;col < tailleY ;col++){
                CaseImplementee uneCase = new CaseTrou(lig,col);
                matriceLigneCol.add(uneCase);
            }
            matrice.add(lig,matriceLigneCol);
        }
        for(short lig = 4; lig < tailleX-1 ; lig+=6)
        {
            for(short col=0 ;col < tailleY-4 ;col++){
                CaseImplementee uneCase = new CaseMur(lig, col);
                matrice.get(lig).set(col, uneCase);
            }
        }
        for(short lig = 7; lig < tailleX-1 ; lig+=6)
        {
            for(short col=4 ;col < tailleY ;col++){
                CaseImplementee uneCase = new CaseMur(lig, col);
                matrice.get(lig).set(col, uneCase);
            }
        }
        for(short lig = 0; lig < 2 ; lig++)
        {
            for(short col=3 ;col < tailleY ;col+=6){
                CaseImplementee uneCase = new CaseMur(lig, col);
                matrice.get(lig).set(col, uneCase);
            }
        }
        for(short lig = 1; lig < 4 ; lig++)
        {
            for(short col=6 ;col < tailleY ;col+=6){
                CaseImplementee uneCase = new CaseMur(lig, col);
                matrice.get(lig).set(col, uneCase);
            }
        }
        for(short repet = 8; repet<tailleY-5 ; repet+=6)
        {
            for(short col=repet ;col < repet+3 ;col++){
                CaseImplementee uneCase = new CaseRalentisseur((short) 2, col);
                matrice.get(2).set(col, uneCase);
            }
        }

        CaseImplementee uneCase = new CaseMur((short)0, (short)1);
        matrice.get(0).set(1, uneCase); uneCase = new CaseMur((short)1, (short)1);
        matrice.get(1).set(1, uneCase); uneCase = new CaseMur((short)2, (short)1);
        matrice.get(2).set(1, uneCase); uneCase = new CaseMur((short)2, (short)2);
        matrice.get(2).set(2, uneCase); uneCase = new CaseMur((short)4, (short)2);
        matrice.get(4).set(2, uneCase);
        uneCase = new CaseRalentisseur((short)(tailleX-2), (short)(tailleY-1));
        matrice.get(tailleX-2).set(tailleY-1, uneCase);
        for(short col = (short) (tailleY-1); col >tailleY-16 ; col--){
            uneCase = new CaseRalentisseur((short) 2, col);
            matrice.get(tailleX-1).set(col, uneCase);
        }
        uneCase = new CaseRalentisseur((short)(tailleX-2), (short)(tailleY-1));
        matrice.get(tailleX-2).set(tailleY-1, uneCase);
    }

    //Constructeur surchargé avec un labyrinthe déjà créé
    public Labyrinthe(Labyrinthe laMatrice){
        ArrayList<Case> matriceLigneCol = new ArrayList<>();
        //on instancie tous nos élements a partir d'une matrice déjà crée
        tailleX = laMatrice.getTailleX();
        tailleY = laMatrice.getTailleY();
        posX = laMatrice.getPosX();
        posY = laMatrice.getPosY();
        departX = laMatrice.getDepartX();
        departY = laMatrice.getDepartY();
        arriveeX = laMatrice.getArriveeX();
        arriveeY = laMatrice.getArriveeY();

        for(short lig = 0; lig < tailleX ; lig++)
        {
            for(short col=0 ;col < tailleY ;col++){
                matriceLigneCol.add(laMatrice.getValueFromLigColMatrice(lig, col));
            }
            matrice.add(lig,matriceLigneCol);
        }
    }

    @Override
    public void finalize()
    {
        //matrice.removeAll();
    }
    
    //getter
    public short getPosX(){
        return posX;
    }
    public short getPosY(){
        return posY;
    }
    public short getTailleX(){
        return tailleX;
    }
    public short getTailleY(){return tailleY;}
    public short getDepartX(){
        return departX;
    }
    public short getDepartY(){
        return departY;
    }
    public short getArriveeX(){
        return arriveeX;
    }
    public short getArriveeY(){
        return arriveeY;
    }
    
    public ArrayList<ArrayList<Case>> getMatrice(){
        return matrice;
    }
    
    public Case getValueFromLigColMatrice(short ligne, short col){
        return matrice.get(ligne).get(col);
    }


    //setter
    public void setPosX(short x){
        posX = x;
    }
    public void setPosY(short y){
        posY = y;
    }
    public void setTailleX(short x){
        tailleX = x;
    }
    public void setTailleY(short y){
        tailleY = y;
    }
    public void setDepartX(short x){
        departX = x;
    }
    public void setDepartY(short y){
        departY = y;
    }
    public void setArriveeX(short x){
        arriveeX = x;
    }
    public void setArriveeY(short y){
        arriveeY = y;
    }
    
    public void setMatrice(ArrayList<ArrayList<Case>> mat){
        matrice = mat;
    }
    public void setValueForLigColMatrice(short lig, short col, Case myCase){ matrice.get(lig).set(col, myCase); }
/**/

}


