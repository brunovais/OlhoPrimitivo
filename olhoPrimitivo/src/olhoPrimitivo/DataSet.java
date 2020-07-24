/*
 * ****************************************
 * Robo especialista em cores
 * ****************************************
 *	Recupera e processa imagens do dataSet 
 * ****************************************
 * By Bruno Vais
 */
package olhoPrimitivo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;


public class DataSet {
	protected File dataSetPath;
	private String dataPath;
	private Double rgbMedia = 0d;
	private String[] images;
	private ArrayList<Double> dataSetValue = new ArrayList<Double>();
	private ArrayList<String> dataSetClass = new ArrayList<String>();
	
	public DataSet(String dataPath){
		this.setDataSetPath(new File(dataPath));
		this.dataPath = dataPath;
		this.getImage();
	}
	
	protected void getImage() {
		int x = 0;
		this.images = this.getDataSetPath().list();
		for(String imageName : this.images) {
			File fullPath = new File(this.dataPath + "\\" + imageName);
			try {
				
				BufferedImage image = ImageIO.read(fullPath);
				this.getRGB(image);
				this.dataSetClass.add(this.getClass(imageName));
				this.dataSetValue.add(this.rgbMedia);
				System.out.println(this.rgbMedia.toString());
				image = null;
				x++;
			} catch (IOException e) {
				System.out.println("arquivo não encontrado");
				e.printStackTrace();
			}
		}
	}
	
	public void getRGB(BufferedImage image) {
		for(int x = 0; x < image.getHeight(); x ++) {
			for(int y = 0; y < image.getWidth(); y ++) {
				this.rgbMedia = (((this.rgbMedia/10) + (image.getRGB(y, x)/10))* -1);
			}
		}
		this.rgbMedia = (Double) Math.floor(this.rgbMedia/300);
	}
	public String getClass(String imageName) {
		String[] imageClass = imageName.split("R3X");
		return imageClass[0];
	}
	

	public ArrayList<String> getDataSetClasses() {
		return this.dataSetClass;
	}
	
	public ArrayList<Double> getDataSetValue() {
		return this.dataSetValue;
	}
	
	public File getDataSetPath() {
		return dataSetPath;
	}

	public void setDataSetPath(File dataSetPath) {
		this.dataSetPath = dataSetPath;
	}
}
