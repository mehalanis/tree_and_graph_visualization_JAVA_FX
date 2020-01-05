/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphe.Bellman;

import Graphe.graphe.Graphe;
import Graphe.graphe.MatriceAdjacence;
import Graphe.graphe.Sommet;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.util.ArrayList;
import java.util.Collections;
import javafx.util.Pair;




/**
 *
 * @author Abdou
 */
public class bellman {
  public  final int infini = 99999;
  public ArrayList<Sommet> list_sommet=new ArrayList<Sommet>();
  private ArrayList<Sommet> list_sommet2=new ArrayList<Sommet>();// arrray list list_sommet perd ces éléments donc on utilise la 2eme list_sommet qui vas etre stable
  private MatriceAdjacence matrice;
  private int[][] M;// variable pour tester la matrice au cours  de l'implementation +++++ utilisation d'une matrice adj défini 
  //private ArrayList<Sommet>listSom;// list de som A=1 , B=2 ...etc teste au cours d impl
  

  public ArrayList<Sommet> getList_sommet() {
        return list_sommet;
    }

    public MatriceAdjacence getMatrice() {
        return matrice;
    }

    public void setList_sommet(ArrayList<Sommet> list_sommet) {
        this.list_sommet = list_sommet;
    }

    public void setMatrice(MatriceAdjacence matrice) {
        this.matrice = matrice;
    }
  public bellman(Graphe g )                     // constructeur à prtir de graphe
    {     
        matrice=g.MatriceAdjacence();
        M=matrice.getMatric();
        for(int i=0;i<M.length;i++){
            M[i][i]=infini;
        }
        list_sommet=g.getList_sommet();
    }
  
    public bellman()                          // constructeur pour tester l'implémentation :) 
        {
            int[][] a={                         // matrice de G2 serie de td 
                         {infini,2,10,infini},
                         {infini,infini,7,3},
                         {infini,infini,infini,-6},
                         {infini,infini,infini,infini},
            
                      };
            
            //String b[]={"A","B","C","D"};// Sommet A = 1 ere ligne en matAdj  ,B=2 2eme ligneen matAdjc ...etc 
            //listSom=b;
            M=a;
           
            list_sommet.add(new Sommet("1"));//Sommet A =1
            
            list_sommet.add(new Sommet("2"));//Sommet B =2
            list_sommet.add(new Sommet("3"));//C
            list_sommet.add(new Sommet("4"));//Ds
             list_sommet2.add(new Sommet("1"));//Sommet A =1
            
            list_sommet2.add(new Sommet("2"));//Sommet B =2
            list_sommet2.add(new Sommet("3"));//C
            list_sommet2.add(new Sommet("4"));
            
        }
    
   
    public ArrayList<Pair<Sommet,Integer>> ListPredecesseur(Sommet a)
            
    {    ArrayList<Pair<Sommet,Integer>> result=new  ArrayList<Pair<Sommet,Integer>>();
        int pos=Integer.valueOf(a.getNom())-1;
        //System.out.println("M.len"+M.length);
        for(int i=0 ; i<M.length;i++)
            {//System.out.println("pos"+pos);
                if(M[i][pos]!=infini)
                {
                    result.add(new Pair(new Sommet(String.valueOf(i+1)),M[i][pos]));
                    //System.out.println("String.valueOf(i)"+String.valueOf(i));
                   // System.out.println("i"+i);
                }
                
            }
        return result;
    }
     public ArrayList<ArrayList<String>> PlusCC(Sommet Origine , Sommet cible )// pcc de l'origine vers un sommet 
        { // init 
            ArrayList<ArrayList<String>> LesEtapes=new ArrayList<ArrayList<String>>(); // une array list qui va contenir les étapes de l'algo bellman
            ArrayList<String> UneLigne=new ArrayList<String>();// cette ligne vas contenir la dernier ligne de l'array LesEtapes pour marquer les sommets
            ArrayList<Pair<Sommet,Sommet>>chemin=new ArrayList<Pair<Sommet,Sommet>>();//elle va représenter list des arcs pour le PCC 
            ArrayList<Sommet>PCCList=new  ArrayList<Sommet>();// array list de chemin  , elle vas contenir le chemin de l'origine à la cible en ordre 
           
             LesEtapes.add(new ArrayList<String>());
             LesEtapes.get(0).add("0");
              for(int i=1 ;i<list_sommet.size();i++)
              {
                  LesEtapes.get(0).add("inf");
              }
              UneLigne=LesEtapes.get(0);
              
            int pos=Integer.valueOf(Origine.getNom())-1;// pos en matrice adj (ligne )
            // int pos2=list_sommet.indexOf(Origine);
            String SommetCible=cible.getNom();
            for(int i =0;i<M.length;i++) // pour vérifier que le sommet Origine n'a aucun prédécesseur 
                {
                    if(M[i][pos]!=infini)
                    {
                    return new ArrayList<ArrayList<String>>();// au lieu de retourner null  on retourne une  arraylist vide d'ou il n'existe aucun chemin de l'origine à la cible  
                    }
                    
                }
            //debut
            ArrayList<Pair<Sommet ,Integer > > S=new ArrayList<Pair<Sommet,Integer >>(); // couple (sommet , poids) 
            
            
            S.add(new Pair(Origine,0));
            ArrayList<Sommet> R=new ArrayList<Sommet>(); // contient les  sommets non marqués 
            R=list_sommet;
            R.remove(Origine);
             ArrayList<Pair<Sommet,Integer>> predeceseur;
            boolean found=false;
            int PosSommetFound=-1;
            Sommet SPChoisi=null;
            int j=0;
            // for(int j=0;j<R.size();j++)
              while (j<R.size())  
              { int min=infini;
                    if(!found)  {   //System.out.println("hola");
                                    predeceseur=ListPredecesseur(R.get(j));
                                   // System.out.println("its"+predeceseur.get(0).getKey().getNom()+"hoo");
                                    for(int i=0;i<predeceseur.size();i++)
                                    {
                                        if(R.contains(predeceseur.get(i).getKey()))   // assurer que tous les predcs sont marqués sinn on marque pas le sommet 
                                            {
                                            found=false;
                                              //  System.out.println("Found");
                                             break;
                                            }
                                        else{
                                            found=true;
                                            //System.out.println("not Found");
                                            }
                                        if (i==predeceseur.size()-1)
                                        {                                  //fin de vérification de marquage de tous les préds de ce sommet donc on le marque et on ajoute son poids dans S ^^
                                           // System.out.println("fin devérif");
                                                                           // on choisit le min entre les poids des sommets
                                            for(int k=0 ;k<S.size();k++)
                                                {   
                                                    for(int indexPre =0;indexPre<predeceseur.size();indexPre++)
                                                            {  //System.out.println("choisir le min ");
                                                                //String S1=S.get(k).getKey().getNom();
                                                              //  Sommet S2=predeceseur.get(indexPre).getKey();
                                                                /*Pair<Sommet,Integer> P2=predeceseur.get(indexPre);
                                                                Sommet S2=P2.getKey();
                                                                String SS2=S2.getNom();*/
                                                                
                                                                
                                                           if(S.get(k).getKey().getNom().equals(predeceseur.get(indexPre).getKey().getNom()))
                                                              //if(S1.equals(SS2))  
                                                                {
                                                                 int poidsFinal =S.get(k).getValue()+predeceseur.get(indexPre).getValue();
                                                                // System.out.println("poids final");
                                                                 if(min>poidsFinal)
                                                                            {
                                                                                SPChoisi=S.get(k).getKey(); // on garde le sommet qui a le min pour l'ajouter en chemin
                                                                                min=poidsFinal;
                                                                            }
                                                                }
                                                            }
                                                }
                                            // System.out.println("poids final"+min);
                                            S.add(new Pair(R.get(j),min));
                                            int PosInTab=-1;
                                            for(int ii=0 ;ii<list_sommet2.size();ii++)
                                                { 
                                                    if (list_sommet2.get(ii).getNom().equals(R.get(j).getNom()))
                                                    {PosInTab=ii;
                                                        }
                                                }
                                           ArrayList<String> UneLigne2=new ArrayList<String>();
                                           for (int jj=0;jj<UneLigne.size();jj++)
                                            {
                                                UneLigne2.add(UneLigne.get(jj));
                                            }
                                           UneLigne=UneLigne2;
                                          
                                            UneLigne2.set(PosInTab,String.valueOf(min) );
                                            
                                          
                                           LesEtapes.add(UneLigne2);
                                            
                                           //LesEtapes.set(LesEtapes.size()-1, UneLigne);
                                           
                                            // on peut pas supprimer le sommet marquer de R dans cette itération donc je le supriime dans la prochaine itération ^^
                                            chemin.add(new Pair(SPChoisi,R.get(j)));
                             
                              
                                           
                                            break;
                                        }
                                        
                                        
                                    }
                                   j++;
                                }
                    else
                        { 
                                 try{         if(R.get(j-1).getNom().equals(SommetCible))  // si le sommet mmarqué est notre cible on arrete 
                                          { R.remove(j-1); // suppr de sommet choisi   
                                           
                                            break;}
                                          else{
                                           R.remove(j-1); // suppr de sommet choisi 
                                           
                                            j=0;
                                                    }
                                 found=false;
                                 }catch(Exception e){}
                            
                            
                            
                            
                        }
                }
           //sommet trouver d'ou on va retourner le pcc depuis l'origine à la cible
          if (!chemin.isEmpty()){
              
              
              
          
          
           return LesEtapes;
          }else{
        
        return new  ArrayList<ArrayList<String>>() ;
        }}
     public ArrayList<ArrayList<String>> PlusCC(Sommet Origine )// pcc de l'origine vers un sommet 
        { // init 
         Sommet  cible=list_sommet.get(list_sommet.size()-1);
            ArrayList<ArrayList<String>> LesEtapes=new ArrayList<ArrayList<String>>(); // une array list qui va contenir les étapes de l'algo bellman
            ArrayList<String> UneLigne=new ArrayList<String>();// cette ligne vas contenir la dernier ligne de l'array LesEtapes pour marquer les sommets
            ArrayList<Pair<Sommet,Sommet>>chemin=new ArrayList<Pair<Sommet,Sommet>>();//elle va représenter list des arcs pour le PCC 
            ArrayList<Sommet>PCCList=new  ArrayList<Sommet>();// array list de chemin  , elle vas contenir le chemin de l'origine à la cible en ordre 
           
             LesEtapes.add(new ArrayList<String>());
             LesEtapes.get(0).add("0");
              for(int i=1 ;i<list_sommet.size();i++)
              {
                  LesEtapes.get(0).add("inf");
              }
              UneLigne=LesEtapes.get(0);
              
            int pos=Integer.valueOf(Origine.getNom())-1;// pos en matrice adj (ligne )
            // int pos2=list_sommet.indexOf(Origine);
            String SommetCible=cible.getNom();
            for(int i =0;i<M.length;i++) // pour vérifier que le sommet Origine n'a aucun prédécesseur 
                {
                    if(M[i][pos]!=infini)
                    {
                    return new ArrayList<ArrayList<String>>();// au lieu de retourner null  on retourne une  arraylist vide d'ou il n'existe aucun chemin de l'origine à la cible  
                    }
                    
                }
            //debut
            ArrayList<Pair<Sommet ,Integer > > S=new ArrayList<Pair<Sommet,Integer >>(); // couple (sommet , poids) 
            
            
            S.add(new Pair(Origine,0));
            ArrayList<Sommet> R=new ArrayList<Sommet>(); // contient les  sommets non marqués 
            R=list_sommet;
            R.remove(Origine);
             ArrayList<Pair<Sommet,Integer>> predeceseur;
            boolean found=false;
            int PosSommetFound=-1;
            Sommet SPChoisi=null;
            int j=0;
            // for(int j=0;j<R.size();j++)
              while (j<R.size())  
              { int min=infini;
                    if(!found)  {   //System.out.println("hola");
                                    predeceseur=ListPredecesseur(R.get(j));
                                   // System.out.println("its"+predeceseur.get(0).getKey().getNom()+"hoo");
                                    for(int i=0;i<predeceseur.size();i++)
                                    {
                                        if(R.contains(predeceseur.get(i).getKey()))   // assurer que tous les predcs sont marqués sinn on marque pas le sommet 
                                            {
                                            found=false;
                                              //  System.out.println("Found");
                                             break;
                                            }
                                        else{
                                            found=true;
                                            //System.out.println("not Found");
                                            }
                                        if (i==predeceseur.size()-1)
                                        {                                  //fin de vérification de marquage de tous les préds de ce sommet donc on le marque et on ajoute son poids dans S ^^
                                           // System.out.println("fin devérif");
                                                                           // on choisit le min entre les poids des sommets
                                            for(int k=0 ;k<S.size();k++)
                                                {   
                                                    for(int indexPre =0;indexPre<predeceseur.size();indexPre++)
                                                            {  //System.out.println("choisir le min ");
                                                                //String S1=S.get(k).getKey().getNom();
                                                              //  Sommet S2=predeceseur.get(indexPre).getKey();
                                                                /*Pair<Sommet,Integer> P2=predeceseur.get(indexPre);
                                                                Sommet S2=P2.getKey();
                                                                String SS2=S2.getNom();*/
                                                                
                                                                
                                                           if(S.get(k).getKey().getNom().equals(predeceseur.get(indexPre).getKey().getNom()))
                                                              //if(S1.equals(SS2))  
                                                                {
                                                                 int poidsFinal =S.get(k).getValue()+predeceseur.get(indexPre).getValue();
                                                                // System.out.println("poids final");
                                                                 if(min>poidsFinal)
                                                                            {
                                                                                SPChoisi=S.get(k).getKey(); // on garde le sommet qui a le min pour l'ajouter en chemin
                                                                                min=poidsFinal;
                                                                            }
                                                                }
                                                            }
                                                }
                                            // System.out.println("poids final"+min);
                                            S.add(new Pair(R.get(j),min));
                                            int PosInTab=-1;
                                            for(int ii=0 ;ii<list_sommet2.size();ii++)
                                                { 
                                                    if (list_sommet2.get(ii).getNom().equals(R.get(j).getNom()))
                                                    {PosInTab=ii;
                                                        }
                                                }
                                           ArrayList<String> UneLigne2=new ArrayList<String>();
                                           for (int jj=0;jj<UneLigne.size();jj++)
                                            {
                                                UneLigne2.add(UneLigne.get(jj));
                                            }
                                           UneLigne=UneLigne2;
                                          
                                            UneLigne2.set(PosInTab,String.valueOf(min) );
                                            
                                          
                                           LesEtapes.add(UneLigne2);
                                            
                                           //LesEtapes.set(LesEtapes.size()-1, UneLigne);
                                           
                                            // on peut pas supprimer le sommet marquer de R dans cette itération donc je le supriime dans la prochaine itération ^^
                                            chemin.add(new Pair(SPChoisi,R.get(j)));
                             
                              
                                           
                                            break;
                                        }
                                        
                                        
                                    }
                                   j++;
                                }
                    else
                        { 
                                 try{       
                                          
                                           R.remove(j-1); // suppr de sommet choisi 
                                           
                                            j=0;
                                                    
                                 found=false;
                                 }catch(Exception e){}
                            
                            
                            
                            
                        }
                }
           //sommet trouver d'ou on va retourner le pcc depuis l'origine à la cible
          if (!chemin.isEmpty()){
              
              
              
          
          
           return LesEtapes;
          }else{
        
        return new  ArrayList<ArrayList<String>>() ;
        }}
    public static void main (String []args)
    {
    bellman bel=new bellman();
    
    

   //ArrayList<ArrayList<String>>myResult=bel.PlusCC(bel.list_sommet.get(0), bel.list_sommet.get(3));  // deux sommet  
    ArrayList<ArrayList<String>>myResult=bel.PlusCC(bel.list_sommet.get(0)); // vers l'orgine à tous les sommets
    
   for(ArrayList<String> tab:myResult)
    {
        for(String ligne : tab)
            {
                System.out.print(" "+ligne+" | ");
            }
        System.out.println("");
    }
    
    // on peut rencontrer des erreurs si le plus cours chemin n'erxiste pas ou bien l'origne a un ou plusiers prédecesseur :)
    
    }
    }
