package olhoPrimitivo;

import java.util.Scanner;

public class Olho {
	public static void main(String[] args) {
		String s;
		Scanner input = new Scanner(System.in);
		System.out.println("digite o endereco da imagem");
		s = input.next();
		DataSet ds = new DataSet("C:\\Users\\bruno\\Desktop\\OlhoPrimitivo\\dataSet");
		InputImage ii = new InputImage(s);
		KNN knn = new KNN(ds, ii);
		System.out.println("digite o valor de k");
		s = input.next();
		knn.classify(Integer.parseInt(s));
		
	}
}
