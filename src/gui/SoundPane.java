package gui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sound.SoundManager;
import utils.ImageUtils;

public class SoundPane {	
	private static final Image FILL_IMAGE = ImageUtils.createImage("subelement_image/volume/volume-color.png");
	private static final Image EMPTY_IMAGE = ImageUtils.createImage("subelement_image/volume/volume-black.png");
	
	private static int volumePercentage = 50;
	private static VBox audioLevel = new VBox(2);
	
	public static HBox build(Stage primaryStage) {		
		HBox root = new HBox();
		root.setPrefSize(1440, 800);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(40);
		
		ImageView downArrowView = createArrow("down");
		ImageView upArrowView = createArrow("up");
		
		audioLevel.setPrefSize(90, 90);
		audioLevel.setAlignment(Pos.CENTER);
		audioLevel.setSpacing(0);
		
		updateAudioLevel();
		
		root.getChildren().add(downArrowView);
		root.getChildren().add(audioLevel);
		root.getChildren().add(upArrowView);
			
		return root;
	}
	
	private static void updateAudioLevel() {
		audioLevel.getChildren().clear();
		
		int fillDimension = calculateFillDimension();
		int emptyDimension = 360 - fillDimension;
		
		PixelReader pixelFillReader = FILL_IMAGE.getPixelReader();
        WritableImage croppedFillImage = new WritableImage(pixelFillReader, 0, emptyDimension, 360, fillDimension);

        ImageView croppedFillView = new ImageView(croppedFillImage);
        croppedFillView.setFitHeight(fillDimension / 4);
        croppedFillView.setFitWidth(90);
        
        PixelReader pixelEmptyReader = EMPTY_IMAGE.getPixelReader();
        WritableImage croppedEmptyImage = new WritableImage(pixelEmptyReader, 0, 0, 360, emptyDimension);

        ImageView croppedEmptyView = new ImageView(croppedEmptyImage);
        croppedEmptyView.setFitHeight(emptyDimension / 4);
        croppedEmptyView.setFitWidth(90);
        
        audioLevel.getChildren().addAll(croppedEmptyView, croppedFillView);
	}
	
	private static ImageView createArrow(String path) {
		ImageView arrowView = ImageUtils.createImageView("subelement_image/volume/volume-" + path + ".png", 80, 80);
		arrowView.setOnMouseClicked(event -> {
			if (path == "down")
				setVolumePercentage(volumePercentage - 10);
			else if (path == "up")
				setVolumePercentage(volumePercentage + 10);
		});
		return arrowView;
	}
	
	private static int calculateFillDimension() {
		return (int) ((360 * volumePercentage) / 100);
	}

	public static int getVolumePercentage() {
		return volumePercentage;
	}

	public static void setVolumePercentage(int volumePercentage) {
		volumePercentage = volumePercentage < 1 ? 1 : volumePercentage;
		volumePercentage = volumePercentage > 99 ? 99 : volumePercentage;
		
		SoundManager.setMusicVolume(volumePercentage);
		
		SoundPane.volumePercentage = volumePercentage;
		updateAudioLevel();
	}
}
