/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import ABR_AVL.NoeudAvl;
import static java.lang.Integer.max;

/**
 *
 * @author mounir
 */
public class TreeAVL {
    NoeudAvl root=null;
    
    public TreeAVL(int x){
     root=new NoeudAvl(x);
    }
    public void insert(int x){
        root=insert(root,x);
    }
    

    private NoeudAvl insert(NoeudAvl n, int x) {
        if(n==null){
            n= new NoeudAvl(x); 
        }else{
            if(x<n.value){
                n.fg=insert(n.fg,x); 
            }else if(x>n.value) {
                n.fd=insert(n.fd,x);
            }
        }
        n.balence=(calculeProfondeur(n.fg)-calculeProfondeur(n.fd));
        n=rotation(n);
        n.profendeur=max(calculeProfondeur(n.fg),calculeProfondeur(n.fd))+1;
        return n;
    }
    
    
    private int calculeProfondeur(NoeudAvl n){
        return (n==null) ? -1 : n.profendeur;
    }
    private NoeudAvl rotation(NoeudAvl R){
        
         if (R!=null){
             if((R.balence==-2)&&(R.fd.balence==-1)){
                 NoeudAvl P=R.fd;
                 R.fd=P.fg;
                 P.fg=R;
                 R.profendeur=max(calculeProfondeur(R.fg),calculeProfondeur(R.fd))+1;
                 P.profendeur=max(calculeProfondeur(P.fg),calculeProfondeur(P.fd))+1;
                 R.balence=(calculeProfondeur(R.fg)-calculeProfondeur(R.fd));
                 P.balence=(calculeProfondeur(P.fg)-calculeProfondeur(P.fd));
                 return P;
                 
             }else if((R.balence==-2)&&(R.fd.balence==1)){
                 NoeudAvl P=R.fd;
                 NoeudAvl Q=P.fg;
                 R.fd=Q.fg;
                 P.fg=Q.fd;
                 Q.fd=P;
                 Q.fg=R;
                 R.profendeur=max(calculeProfondeur(R.fg),calculeProfondeur(R.fd))+1;
                 P.profendeur=max(calculeProfondeur(P.fg),calculeProfondeur(P.fd))+1;
                 Q.profendeur=max(calculeProfondeur(Q.fg),calculeProfondeur(Q.fd))+1;
                 R.balence=(calculeProfondeur(R.fg)-calculeProfondeur(R.fd));
                 P.balence=(calculeProfondeur(P.fg)-calculeProfondeur(P.fd));
                 Q.balence=(calculeProfondeur(Q.fg)-calculeProfondeur(Q.fd));
                 return Q;
                 
                 
                 
             }else if((R.balence==2)&&(R.fg.balence==1)){
                 NoeudAvl P=R.fg;
                 R.fg=P.fd;
                 P.fd=R;
                 R.profendeur=max(calculeProfondeur(R.fg),calculeProfondeur(R.fd))+1;
                 P.profendeur=max(calculeProfondeur(P.fg),calculeProfondeur(P.fd))+1;
                 R.balence=(calculeProfondeur(R.fg)-calculeProfondeur(R.fd));
                 P.balence=(calculeProfondeur(P.fg)-calculeProfondeur(P.fd));
                 return P;
                 
             }else if((R.balence==2)&&(R.fg.balence==-1)){
                 NoeudAvl P=R.fg;
                 NoeudAvl Q=P.fd;
                 R.fg=Q.fd;
                 P.fd=Q.fg;
                 Q.fd=R;
                 Q.fg=P;
                 R.profendeur=max(calculeProfondeur(R.fg),calculeProfondeur(R.fd))+1;
                 P.profendeur=max(calculeProfondeur(P.fg),calculeProfondeur(P.fd))+1;
                 Q.profendeur=max(calculeProfondeur(Q.fg),calculeProfondeur(Q.fd))+1;
                 R.balence=(calculeProfondeur(R.fg)-calculeProfondeur(R.fd));
                 P.balence=(calculeProfondeur(P.fg)-calculeProfondeur(P.fd));
                 Q.balence=(calculeProfondeur(Q.fg)-calculeProfondeur(Q.fd));
                 return Q;
             }
             
         }
         return R;
        
        
    }
    public void delete(int x){
        root=delete(root,x);
    }
    private NoeudAvl delete(NoeudAvl n,int x){
        if(n!=null){
            if(x<n.value){
                n.fg=delete(n.fg,x);
            }else if(x>n.value){
                n.fd=delete(n.fd,x);
            }else{
                if((n.fg==null)||(n.fd==null)){
                if(n.fg==null) n=n.fd;else n=n.fg;
                
                return n;
            }
               
                //si on a chaisit le min prédéssusseur:
              NoeudAvl t1= minVal(n.fd); 
              n.value=t1.value;
              n.fd=delete(n.fd,t1.value);
               // si on a choisit le max succésseur :
              /*NoeudAvl t2=maxVal(n.fg);
              n.value=t2.value;
              n.fg=delete(n.fg,t2.value);*/
            }
        n.balence=(calculeProfondeur(n.fg)-calculeProfondeur(n.fd));
        n.profendeur=max(calculeProfondeur(n.fg),calculeProfondeur(n.fd))+1;
        n=rotation(n);
        return n;    
        }else{
        return n;    
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    public void affich(){
        affichage(root);
    }
    private void affichage(NoeudAvl n){
        if(n==null){
        }else{
        affichage(n.fg);
        if(n.fg!=null){
            System.out.println("\n fils gauche = "+n.fg.value);
        }
        System.out.println("value = "+n.value+"\n profendeur = "+n.profendeur+"\n balence = "+n.balence);
        if(n.fd!=null){
            System.out.println(" fils droit = "+n.fd.value);
        }
        affichage(n.fd);
        }
        
    }

    private NoeudAvl minVal(NoeudAvl n) {
        return (n.fg==null) ? n : minVal(n.fg);
    }

    private NoeudAvl maxVal(NoeudAvl n) {
        return (n.fd==null) ? n : minVal(n.fd);
    }
    
}
