
/**
 * Esempio#1: valgono le regole di scoping di Java. 
 * 
 */
public class InnerAnidate {

	private class Liv1 {
		private int x = 1;
		public Liv1() {
			System.out.println(x);
		}
		
		private class Liv2 {
			private int x = 2;
			public Liv2() {
				System.out.println(x);
				
			}
			
			private class Liv3 {
				private int x = 3;	
				
				public Liv3() {
					System.out.println(x);					
				}
				
			}
			
		}
	}
	
	public static void main(String args[]) {
		InnerAnidate outer = new InnerAnidate();
		InnerAnidate.Liv1 ref1 = outer.new Liv1();//Liv2, Liv3 non sono visibili fuori
		InnerAnidate.Liv1.Liv2 ref2 = ref1.new Liv2();//Liv3 non sono visibili fuori
		InnerAnidate.Liv1.Liv2.Liv3 ref3 = ref2.new Liv3();//Liv3 non sono visibili fuori			
	}
	
	
}

/**
 * Esempio#2: valgono le regole di scoping di Java. 
 * 
 */
class InnerAnidate1 {
	private int main = 1;
	
	private class Liv1 {
		private int x = 1;
		public Liv1() {
			System.out.println(x);
			System.out.println(main);
		}
		
		private class Liv2 {
			private int y = 2;
			public Liv2() {
				System.out.println(x);
				System.out.println(y);
				System.out.println(main);
				
			}
			
			private class Liv3 {
				private int z = 3;	
				
				public Liv3() {
					System.out.println(x);
					System.out.println(y);
					System.out.println(z);
					System.out.println(main);
				}				
			}
		}
	}
	
	public static void produciStampe(String args[]) {
		InnerAnidate1 outer = new InnerAnidate1();
		InnerAnidate1.Liv1 ref1 = outer.new Liv1();//Liv2, Liv3 non sono visibili fuori
		InnerAnidate1.Liv1.Liv2 ref2 = ref1.new Liv2();//Liv3 non sono visibili fuori
		InnerAnidate1.Liv1.Liv2.Liv3 ref3 = ref2.new Liv3();//Liv3 non sono visibili fuori	
	}
	
	
}
