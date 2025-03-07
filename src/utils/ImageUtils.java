package utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageUtils {
	public static Image createImage(String path) {
		return new Image(ClassLoader.getSystemResource(path).toString());
	}
	
	public static ImageView createImageView(String path, double width, double height) {
		return createImageView(createImage(path), width, height);
	}
	
	public static ImageView createImageView(Image image, double width, double height) {
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		return imageView;
	}
}
