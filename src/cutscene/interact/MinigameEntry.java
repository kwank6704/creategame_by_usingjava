package cutscene.interact;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import player.Player;
import javafx.scene.control.ButtonBar;

public class MinigameEntry extends InteractableObject {
	Player player = Player.getInstance();

	public MinigameEntry(double x, double y, int gameState, String name) {
		super(x, y, gameState, name);
	}

	@Override
	public void interact() {
		Platform.runLater(() -> {
			if (player.getGameState() == 1) {
				iScaleRoom();
			} else if (player.getGameState() == 4) {
				elevator();
			} else if (player.getGameState() == 6) {
				lanintania();
			} else if (player.getGameState() == 7) {
				computerCenter();
			} else if (player.getGameState() == 9) {
				database();
			} else if (player.getGameState() == 11) {
				guildRoom();
			} else if (player.getGameState() == 13) {
				floor4();
			 }
		});
	}

	private void iScaleRoom() {
		Platform.runLater(() -> {
			ButtonType winButton = new ButtonType("🏆 Win", ButtonBar.ButtonData.YES);
			ButtonType loseButton = new ButtonType("💀 Lose", ButtonBar.ButtonData.NO);

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Did you succeed in the minigame?", winButton,
					loseButton);
			alert.setTitle("🎮 Minigame Result");
			alert.setHeaderText("✨ Your fate is in your hands! ✨");

			alert.getDialogPane().lookupButton(winButton)
					.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
			alert.getDialogPane().lookupButton(loseButton)
					.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

			alert.showAndWait().ifPresent(response -> {
				if (response == winButton) {
					System.out.println("🎉 Congratulations! You won the minigame! 🎉");
					player.setGameState(player.getGameState() + 1);
				} else if (response == loseButton) {
					System.out.println("😢 You lost the minigame. Better luck next time!");
				}
			});
		});
	}

	private void elevator() {
		Platform.runLater(() -> {
			ButtonType winButton = new ButtonType("🏆 Win", ButtonBar.ButtonData.YES);
			ButtonType loseButton = new ButtonType("💀 Lose", ButtonBar.ButtonData.NO);

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Did you succeed in the minigame?", winButton,
					loseButton);
			alert.setTitle("🎮 Minigame Result");
			alert.setHeaderText("✨ Your fate is in your hands! ✨");

			alert.getDialogPane().lookupButton(winButton)
					.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
			alert.getDialogPane().lookupButton(loseButton)
					.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

			alert.showAndWait().ifPresent(response -> {
				if (response == winButton) {
					System.out.println("🎉 Congratulations! You won the minigame! 🎉");
					player.setGameState(player.getGameState() + 1);
				} else if (response == loseButton) {
					System.out.println("😢 You lost the minigame. Better luck next time!");
				}
			});
		});
	}

	private void lanintania() {
		Platform.runLater(() -> {
			ButtonType winButton = new ButtonType("🏆 Win", ButtonBar.ButtonData.YES);
			ButtonType loseButton = new ButtonType("💀 Lose", ButtonBar.ButtonData.NO);

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Did you succeed in helping him?", winButton,
					loseButton);
			alert.setTitle("🎮 Minigame Result");
			alert.setHeaderText("✨ Your fate is in your hands! ✨");

			alert.getDialogPane().lookupButton(winButton)
					.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
			alert.getDialogPane().lookupButton(loseButton)
					.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

			alert.showAndWait().ifPresent(response -> {
				if (response == winButton) {
					System.out.println("🎉 Congratulations! You won the minigame! 🎉");
					player.setGameState(player.getGameState() + 1);
				} else if (response == loseButton) {
					System.out.println("😢 You lost the minigame. Better luck next time!");
				}
			});
		});
	}

	private void computerCenter() {
		if (player.getHasGoToComCenter())
			return;
		
		Platform.runLater(() -> {
			ButtonType winButton = new ButtonType("🏆 Win", ButtonBar.ButtonData.YES);
			ButtonType loseButton = new ButtonType("💀 Lose", ButtonBar.ButtonData.NO);

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Did you succeed in playing game?", winButton,
					loseButton);
			alert.setTitle("🎮 Minigame Result");
			alert.setHeaderText("✨ Your fate is in your hands! ✨");

			alert.getDialogPane().lookupButton(winButton)
					.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
			alert.getDialogPane().lookupButton(loseButton)
					.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

			alert.showAndWait().ifPresent(response -> {
				if (response == winButton) {
					System.out.println("🎉 Congratulations! You won the minigame! 🎉");
					player.setHasGoToComCenter(true);
				} else if (response == loseButton) {
					System.out.println("😢 You lost the minigame. Better luck next time!");
				}
			});
		});
	}

	private void database() {
		if (player.getHasGoToDatabase())
			return;
		
		Platform.runLater(() -> {
			ButtonType winButton = new ButtonType("🏆 Win", ButtonBar.ButtonData.YES);
			ButtonType loseButton = new ButtonType("💀 Lose", ButtonBar.ButtonData.NO);

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Did you succeed in playing game?", winButton,
					loseButton);
			alert.setTitle("🎮 Minigame Result");
			alert.setHeaderText("✨ Your fate is in your hands! ✨");

			alert.getDialogPane().lookupButton(winButton)
					.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
			alert.getDialogPane().lookupButton(loseButton)
					.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

			alert.showAndWait().ifPresent(response -> {
				if (response == winButton) {
					System.out.println("🎉 Congratulations! You won the minigame! 🎉");
					player.setGameState(player.getGameState() + 1);

					player.setHasGoToDatabase(true);
				} else if (response == loseButton) {
					System.out.println("😢 You lost the minigame. Better luck next time!");
				}
			});
		});
	}
	
	private void guildRoom() {
		Platform.runLater(() -> {
			ButtonType winButton = new ButtonType("🏆 Win", ButtonBar.ButtonData.YES);
	        ButtonType loseButton = new ButtonType("💀 Lose", ButtonBar.ButtonData.NO);
	     
	
	        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Guild System", winButton, loseButton);
	        alert.setTitle("🎮 Minigame Result");
	        alert.setHeaderText("✨ Your fate is in your hands! ✨");
	        
	        alert.getDialogPane().lookupButton(winButton).setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;"); 
	        alert.getDialogPane().lookupButton(loseButton).setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
	
	        alert.showAndWait().ifPresent(response -> {
	            if (response == winButton) {
	                System.out.println("🎉 yeah 🎉");
	                player.setGameState(player.getGameState()+1);
	                
	                //for check cheated
	                player.setHasGoToDatabase(true);
	            } else if (response == loseButton) {
	                System.out.println("😢 yeah too !");
	                player.setGameState(player.getGameState()+1);
	            }
	        });
		});
		
	}
	
	private void floor4() {
		Platform.runLater(() -> {
			ButtonType winButton = new ButtonType("🏆 Win", ButtonBar.ButtonData.YES);
	        ButtonType loseButton = new ButtonType("💀 Lose", ButtonBar.ButtonData.NO);
	     
	
	        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Did you succeed in playing game?", winButton, loseButton);
	        alert.setTitle("🎮 Minigame Result");
	        alert.setHeaderText("✨ Your fate is in your hands! ✨");
	        
	        alert.getDialogPane().lookupButton(winButton).setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;"); 
	        alert.getDialogPane().lookupButton(loseButton).setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
	
	        alert.showAndWait().ifPresent(response -> {
	            if (response == winButton) {
	                System.out.println("🎉 Congratulations! You won the minigame! 🎉");
	                player.setGameState(player.getGameState()+1);
	                
	                //for check cheated
	                player.setHasGoToDatabase(true);
	            } else if (response == loseButton) {
	                System.out.println("😢 You lost the minigame. Better luck next time!");
	            }
	        });
		});
		
	}

	@Override
	public boolean gameStateCondition(int playerGameState) {
		System.out.println("check game state" + playerGameState);
		return this.getGameState() == playerGameState;
	}

}
