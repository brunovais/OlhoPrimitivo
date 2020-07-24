/*
 * ****************************************
 * Robo especialista em cores
 * ****************************************
 *	algoritmo de classificação KNN 
 * ****************************************
 * By Bruno Vais
 */
package olhoPrimitivo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class KNN {
	private DataSet dataSet;
	private InputImage imageInputed;
	private ArrayList<Double> distances = new ArrayList<Double>();	
	private ArrayList<String> classes = new ArrayList<String>();
	private ArrayList<String> classesType = new ArrayList<String>();	
	
	public KNN(DataSet ds, InputImage ii) {
		this.dataSet = ds;
		this.imageInputed = ii;
		this.classes = ds.getDataSetClasses();
		this.classesType = (ArrayList) this.classes.stream().distinct().collect(Collectors.toList());
		this.calcDestances();
		this.personalSort();
		
	}
	
	public void classify(int k) {
		Integer count = 0;
		if(k > this.classes.size()) {
			System.out.println("O K É MAIOR QUE O DATASET");
			System.exit(0);
		}
		
		ArrayList<String> finalClasses = new ArrayList<String>();
		ArrayList<String> finalClassesGamb = new ArrayList<String>();
		ArrayList<Integer> classesCount = new ArrayList<Integer>();
		for(int x = 0; x < k; x++) {
			finalClasses.add(x, this.classes.get(x));
		}
		for(int x = 0; x < this.classesType.size(); x++) {
			count = 0;
			for(int y = 0; y < finalClasses.size(); y++) {
				if(this.classesType.get(x).equals(finalClasses.get(y))) {
					count++;
				}
			}
			classesCount.add(count);
			finalClassesGamb.add(this.classesType.get(x));
		}
		System.out.println("------- Resultado --------");
		Integer per;
		for(int x = 0; x < classesCount.size(); x++) {
			per = (classesCount.get(x)*100)/k;
			System.out.println(finalClassesGamb.get(x) + ": " + per.toString() + "%");
		}
	}
	
	private void calcDestances() {
		Double aux;
		for(int x = 0; x < this.dataSet.getDataSetValue().size(); x++) {
			aux = this.dataSet.getDataSetValue().get(x) - this.imageInputed.getInputRGB();
			this.distances.add(Math.sqrt(Math.pow((aux), 2)));
		}
	}
	
	private void personalSort() {
		Double numAux = 0d;
		String classAux = "";
		for(int x = 0; x < this.distances.size(); x++) {
			for(int y = 0; y < this.distances.size(); y++) {
				if (x != y && this.distances.get(x) < this.distances.get(y)) {
					numAux = this.distances.get(y);
					classAux = this.dataSet.getDataSetClasses().get(y);
					this.distances.set(y, this.distances.get(x));
					this.classes.set(y, this.dataSet.getDataSetClasses().get(x));
					this.distances.set(x, numAux);
					this.classes.set(x, classAux);
					numAux = 0d;
					classAux = "";
					y = 0;
				}
			}
		}
	}

	public void ver(String var) {
		System.out.println(var);
	}
	public void ver(Double var) {
		System.out.println(var.toString());
	}
	public void aVer(ArrayList<String> var) {
		for(int x = 0; x < var.size(); x++) {
			System.out.println(var.get(x).toString());
		}
	}
}