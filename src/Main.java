import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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

        GridPane bord= creergrille();

        Scene scene= new Scene(bord,500,500);
        scene.setOnKeyPressed(event->{
            if(event.getCode() == KeyCode.CONTROL)
            {
            }
            else if(event.getCode() == KeyCode.M){
                GridPane gp=creergrille();
                primaryStage.setScene(new Scene(gp));
                primaryStage.show();
            }});

        primaryStage.setMaximized(false);
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    public GridPane creergrille(){
        for(int i=0;i<position.length;i++ ){
            position[i]=false;
        }
        GridPane grille=new GridPane();

        int random= (int)Math.random()*9;

        for(int x=0;x<3;x++) {

            for(int y =0;y<3;y++){

                while(position[random])
                {random= (int)(Math.random()*9);}

                position[random]=true;
                grille.add(imageViews[random],x,y);
                }
            }
        return grille;
    }

}
