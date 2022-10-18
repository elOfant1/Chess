package Chess;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageLoader {

	public ImageLoader() {
		// TODO Auto-generated constructor stub
	}

	public static BufferedImage loadImage(Piece p) {
		BufferedImage image = null;
		File file = new File(
				"sprites\\" + p.getType().toString() + "_" + p.getTeam().toString().toLowerCase() + ".png");
		try {
			image = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("King png");
		}
		return image;
	}

}
