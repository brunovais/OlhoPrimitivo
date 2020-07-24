/*
 * ****************************************
 * Robo especialista em cores
 * ****************************************
 *	INPUT DA IMAGEM A CLASSIFICAR 
 * ****************************************
 * By Bruno Vais
 */
package olhoPrimitivo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class InputImage{
	private BufferedImage image;
	private Long imageRGB = 0l;
	private File dataPath;
	public InputImage(String dataPath) {
		this.dataPath = new File(dataPath);
		this.getImage();
	}
	
	private void getImage() {
		try {
			this.image = ImageIO.read(this.dataPath);
		} catch (IOException e) {
			System.out.println("arquivo não encontrado");
			e.printStackTrace();
		}
		this.getRGB();
	}
	
	public void getRGB() {
		for(int x = 0; x < this.image.getHeight(); x ++) {
			for(int y = 0; y < this.image.getWidth(); y ++) {
				this.imageRGB = (((this.imageRGB/10) + (image.getRGB(y, x)/10))* -1);
			}
		}
		this.imageRGB = (long) Math.floor(this.imageRGB/300);
	}
	
	
	public Long getInputRGB() {
		return this.imageRGB;
	}
}
