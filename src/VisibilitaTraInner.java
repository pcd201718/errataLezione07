

/**
 * Errata#1: Visibilita' tra Inner di istanza: Prima costruisco un oggetto del tipo (i.e., Inner1, Inner2),
 * anche se i campi dichiarati "private" sono accessibili direttamente usando il loro riferimento.
 * Morale: questo comportamento e diverso da quanto annunciato a lezione dove gli oggetti interni possono 
 * "accedere ai campi solo tramite i metodi propri definite nelle classi".
 */
public class VisibilitaTraInner {
	
	private class Inner1 {//start Inner1
		
		private int x=1;
		
		public int getX() {return x;}
		
		public void accediAlIstanzaInner2() { 
			Inner2 i2 = new Inner2();
			i2.y = x; 
			System.out.println("Campo y di i2 (istanza): " + i2.y);
		}		
	} //end Inner1
	
	public class Inner2 {//start Inner2
	
		private int y = 2;
		
		public int getY() {return y;}
		
		public void accediAlIstanzaInner1() { 
			Inner1 i1 = new Inner1(); 
			i1.x = y; 
			System.out.println("Campo x di i1 (istanza): " + i1.x);
		}		
	} //end Inner2
	
	public static void main(String args[]) {
		VisibilitaTraInner outer = new VisibilitaTraInner();
		VisibilitaTraInner.Inner2 inner2 = outer.new Inner2();
		System.out.println(inner2.getY());
		VisibilitaTraInner.Inner1 inner1 = outer.new Inner1();
		System.out.println(inner1.getX());
	}
}


/**
 * Gli esempi sotto evidenziati mostrano alcuni casi particolari con classi interne (static e d'istanza) che contengono dei campi statici. 
 * 
 * Esempio#1 - campo dati statico e classe interna di istanza: dichiarata una classe Inner1 con un campo dati marcato static e final. Il Mondo (main), Outer 
 * (accediA_XDiInner1), Inner2(costrutore) possono accedere al riferimento a Inner1.x (static e final). 
 * Morale: posso dichiarare campi dati statici in una classe interna di istanza "se e solo se" marcati come final. La raggione e che marcando il campo finale 
 * si puo' dare un interpretazione classica del significato del campo statico come COMUNE a tutte le instanze di Inner rachiuse nella istanze di Outer.
 * Se si omete il marcatore 'final' per il campo dati statico il significato di "x" sarebbe ambiguo:
 * 	(1) x e' un campo dati statico e comune a tutte le istanza di inner rachiuse in un istanza di outer oppure
 *  (2) x e' un campo dati statico comune a tutte le istanza di Inner - diverse Outer hanno una coppia propria!
 * 
 */

//Errata#2:  
class Esempio1 {
	
	public void accediA_XDiInner1() {
		System.out.println(Inner1.x);
	}
		
	private class Inner1 { 
		/**
		 * Sia static che final, se no il codice non compila. Se si omette il final il compilatore produce un errore del tipo:
		 * "The field x cannot be declared static in a non-static inner type, unless initialized with a 
		 * constant expression"
		 */
		private static final int x=1;
	} 
	
	private class Inner2 {

		public Inner2() {System.out.println(Inner1.x);}
	}	
	
	public static void main(String args[]) {
		System.out.println(Esempio1.Inner1.x);
	}
}


/**
 * Esempio#2 - campi dati statici e classe interna statica (non di istanza).
 * Avendo visto Esempio#1, qui a differenza si puo' ommetere il macratore final. Il significato di x e fermo!
 * */
//Errata#2:  
class AltroEsempio {
	
	static class Inner1 { public static int x=1;} /** static sufficiente */
	
	class Inner2 {
		public int get() {return Inner1.x;}
	}	
}