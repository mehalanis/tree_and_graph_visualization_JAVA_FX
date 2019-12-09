public class amr {
	
	// creation de notre structure de noeud
	static class Noeud{
		int [] data;		//tableau des valeurs.
		Noeud [] children;	//tableau des noeuds de fils.
		
		int size;		    //taille de noeud qui doit être < ordre-1 .
		boolean leaf;		//true si feuille.
		
		//le constructeur.. 
		public Noeud(int t){ //t est l'ordre de notre AMR
			this.data = new int[t-1];
			this.children = new Noeud[t];
			
			this.leaf = true;
		}
	}
	
	
	// pour le test 
	static Noeud root;		//la racine de notre amr 
	static int t = 5;		//l'ordre de l'amr a recupuré de notre interface
	
	
	// creation de fonction de l'ajout dans le même noeud 
	private static void insertData(Noeud node,int data){ // en paramètre on a le noeud et la valeur a ajouté
		int index = node.size;
		for(int i=node.size-1;i>-1;i--){ // on lis les valeur par odres décroissant
			if(data<=node.data[i]){
				node.data[i+1] = node.data[i];
				index = i;
			}
			else{
				break;
			}
		}
		node.data[index] = data;
		node.size++;
	}

	// fonction recursive pour l'ajout dans d'autre niveau)
			public static void recu(Noeud Noeud,int data) {
			for(int i=0;i<=Noeud.size-1;i++) {
				if (data<=Noeud.data[i]) {
					if (Noeud.leaf==true && Noeud.size<t-1) {insertData(Noeud,data); return;} //feuille non pleine j'insert
					if (Noeud.children[i]==null && Noeud.size>=t-1) { Noeud node = new Noeud(t); insertData(node,data); Noeud.children[i]=node;Noeud.leaf=false;return;} // feuille pleine, je crée un nvx noeud 
					if (Noeud.leaf==false) {recu(Noeud.children[i], data);} // je refais le traitement pour les prédecesseur de root.data[i]
				return;
				}
				
								
			}
			int i = Noeud.size-1;
			if (data>Noeud.data[i]) {
				if (Noeud.leaf==true && Noeud.size<t-1) {insertData(Noeud,data); return;} //feuille non pleine j'insert
				if (Noeud.children[i+1]==null && Noeud.size>=t-1) { Noeud node = new Noeud(t); insertData(node,data); Noeud.children[i+1]=node;Noeud.leaf=false;return;} // feuille pleine, je crée un nvx noeud 
				if (Noeud.leaf==false) {recu(Noeud.children[i+1], data);} // je refais le traitement pour les successeur de root.data[i]
				return;
			}
			
			}
	
	
	// insertion 
	public static void insertion(int data){
		
		if(root==null){ //amr vide
			Noeud node = new Noeud(t);
			insertData(node,data);
			root = node;
			return;}
		
		recu (root,data); //amr non vide, recherche et insertion 
		
	} //fin insertion 
		
	
	
	
	
	//HOPE IT WORK... AMR ORDRE 5 DE NOTRE SERIE D'EXO
	
	
	//Fonction pour affichage sur console
	public static void display(Noeud node, int level){
		if(node==null){
			return;
		}
		System.out.print("Level : " + level + " " + "Data : ");
		for(int i=0;i<node.size;i++){
			System.out.print(node.data[i] + " ");
		}
		System.out.println();
		if(node.leaf){
			return;
		}
		for(int i=0;i<node.size+1;i++){
			display(node.children[i],level+1);
		}
	}
	public static void main(String [] args){
		insertion(25);
		insertion(60);
		insertion(35);
		insertion(10);
		insertion(5);
		insertion(20);
		insertion(65);
		insertion(45);
		insertion(70);
		insertion(40);
		insertion(50);
		insertion(55);
		insertion(30);
		insertion(15);
		insertion(22);
		insertion(62);
		insertion(64);
		insertion(4);
		insertion(8);
		
		display(root,1);
	}
	

}
