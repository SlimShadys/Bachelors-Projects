public class Main {

	public static void main(String[] args) {

		//Classe B
		B obj = new B();
		
		/*
		 * Dato che obj � di classe B, e B "extends" la classe A, allora stamper�
		 * il metodo contenuto in B.
		 */
		obj.print();
		
		/*--------------------------------------------------------*/
		
		//Classe A
		A obj1 = new A();
		
		/*
		 * Dato che obj1 � di classe A, allora stamper� il metodo contenuto in A.
		 */
		obj1.print();
		
	}

}
