package utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageUtils {
	public static ImageView createImageView(String path, double width, double height) {
		Image image = new Image(ClassLoader.getSystemResource(path).toString());
		return createImageView(image, width, height);
	}
	
	public static ImageView createImageView(Image image, double width, double height) {
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		return imageView;
	}
}
