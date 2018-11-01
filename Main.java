import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    Image[] imagess= {new Image("mario0.jpg"), new Image("mario1.jpg"),new Image("mario2.jpg"),
            new Image("mario3.jpg"),new Image("mario4.jpg"),new Image("mario5.jpg"),new Image("mario6.jpg"),
            new Image("mario7.jpg"),new Image("mario8.jpg")};

    ImageView[] imageViews = new ImageView[9];
    Boolean[] position= new Boolean[9];
    public static void main(String[] args) {launch(args); }


    static Stage stage =new Stage();

    @Override
    public void start(Stage primaryStage) {


        for (int i=0;i<imagess.length;i++)
        {imageViews[i]= new ImageView(imagess[i]);}

        GridPane bord= new GridPane();
        creergrille(bord);

        Scene scene= new Scene(bord,600,650);
        scene.setOnKeyPressed(event->{
            if(event.getCode() == KeyCode.M&&event.isControlDown())
            {
                try{
                creergrille(bord);
                primaryStage.setScene(new Scene(bord));
                primaryStage.show();}
                catch(IllegalArgumentException i) {}
            }
            });
        primaryStage.setMaximized(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public GridPane creergrille(GridPane pain){

        pain.getChildren().clear();

        for(int i=0;i<position.length;i++ ){
            position[i]=false;
        }


        int random= (int)(Math.random()*9);

        for(int x=0;x<3;x++) {

            for(int y =0;y<3;y++){

                while(position[random])
                {random= (int)(Math.random()*9);}

                position[random]=true;
                pain.add(imageViews[random],x,y);
                }
            }
            gererdnd(pain);
        return pain;
    }
    public void gererdnd(GridPane pain) {


        for (ImageView fuk : imageViews) {
            fuk.setOnDragDetected(event -> {
                Dragboard db = fuk.startDragAndDrop(TransferMode.ANY);
                ClipboardContent ct = new ClipboardContent();
                ct.putString("");
                db.setContent(ct);
            });
            fuk.setOnDragOver(event ->
                    event.acceptTransferModes(TransferMode.ANY)
            );
            fuk.setOnDragDropped(event -> {
                ImageView source = (ImageView) event.getGestureSource();
                Image target = fuk.getImage();

                fuk.setImage(source.getImage());
                source.setImage(target);

                event.setDropCompleted(true);

            });
            fuk.setOnDragDone(event -> {
                //Rendu ici, je n'ai jamais pu me servir du DEBUGGER DÉSUET quand on utilise le Drag and Drop.
                //À la seconde où on drag une image, l'application plante. Je veux bien me débrouiller, mais j'ai
                //mes limites.

            });
        }
    }
}
