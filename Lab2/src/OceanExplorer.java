import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class OceanExplorer extends Application {
    boolean[][] islandMap;
    final int dimensions = 10; // We are creating 10X10 maps
    final int islandCount = 10;
    final int scale = 50; // Scale everything by 50.
    Pane root;
    OceanMap oceanMap;
    javafx.scene.image.Image shipImage;
    ImageView shipImageView;
    Scene scene;
    Ship ship;
    javafx.scene.image.Image islandImage;
    ImageView islandImageView;

    public static void main(String[] args) {
        launch(args);
    }

    private void drawMap() {
        for (int x = 0; x < dimensions; x++) {
            for (int y = 0; y < dimensions; y++) {
                javafx.scene.shape.Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);
                rect.setStroke(javafx.scene.paint.Color.BLACK); // We want the black outline
                rect.setFill(javafx.scene.paint.Color.PALETURQUOISE); // I like this color better than BLUE
                this.root.getChildren().add(rect); // Add to the node tree in the pane
            }
        }
    }

    private void startSailing() {
        this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent keyEvent) {
                // System.out.println("going "+keyEvent.getCode());
                switch (keyEvent.getCode()) {
                    case RIGHT:
                        ship.goEast();
                        break;
                    case LEFT:
                        ship.goWest();
                        break;
                    case UP:
                        ship.goNorth();
                        break;
                    case DOWN:
                        ship.goSouth();
                        break;
                    default:
                        break;
                }
                // Don’t forget to add the ImageView to the Pane!

                shipImageView.setX(ship.getShipLocation().x * scale);
                shipImageView.setY(ship.getShipLocation().y * scale);

            }
        });
    }

    private void loadIslandsImage() {
        // Load image. It takes 5 arguments:
        // (1) file name, width, height, maintain ratio (T/F), smoothing (T/F)
        this.islandImage = new
                javafx.scene.image.Image("https://raw.githubusercontent.com/CSCI513-Spring20/assignmengt2-team-pooja/master/Assignment2/island.jpg", 50, 50, true, true);

        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {

                if (oceanMap.isIsland(i, j)) {
                    this.islandImageView = new ImageView(islandImage);


                    this.islandImageView.setX(i * scale);
                    this.islandImageView.setY(j * scale);

                    this.root.getChildren().add(islandImageView);
                }
            }
        }

    }

    private void loadShipImage() {
        // Load image. It takes 5 arguments:
        // (1) file name, width, height, maintain ratio (T/F), smoothing (T/F)
        this.shipImage = new Image("https://raw.githubusercontent.com/CSCI513-Spring20/assignmengt2-team-pooja/master/Assignment2/ship.png", 50, 50, true, true);

        // Now instantiate and load the image View. Actually this probably needs to be
        // a class level variable so you would already have defined ImageView shipImageview
        this.shipImageView = new ImageView(shipImage);

        // Assuming that you’ve already set the ship’s starting coordinates in Ship and have
        // created a getter method.
        this.shipImageView.setX(oceanMap.getShipLocation().x * scale);
        this.shipImageView.setY(oceanMap.getShipLocation().y * scale);

        // Don’t forget to add the ImageView to the Pane!
        this.root.getChildren().add(shipImageView);
    }

    @Override
    public void start(Stage mapStage) {

        oceanMap = new OceanMap(dimensions, islandCount);
        islandMap = oceanMap.getMap();

        root = new AnchorPane();
        drawMap();

        ship = new Ship(oceanMap);
        loadShipImage();

        loadIslandsImage();


        //Creating a Scene by passing the group object, height and width
        scene = new Scene(root, 500, 500);

        //setting color to the scene
        scene.setFill(Color.BLUE);

        //Setting the title to Stage.
        mapStage.setTitle("Christopher Columbus sails the blue ocean");

        //Adding the scene to Stage
        mapStage.setScene(scene);

        //Displaying the contents of the stage
        mapStage.show();
        startSailing();
    }
}
