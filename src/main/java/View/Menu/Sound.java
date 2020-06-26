package View.Menu;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Sound {

    String file;
    Media sound;
    MediaPlayer player;

    public void setFile(String file) {
        this.file = file;
    }

    public Sound() {
        this.sound = new Media(new File(file).toURI().toString());
        this.player = new MediaPlayer(sound);
    }

    public void play(String file){
        this.file = file;
        this.sound = new Media(new File(file).toURI().toString());
        this.player = new MediaPlayer(sound);
        player.play();
    }

    public void stop(){
        player.stop();
    }

    public void setVolume(double value){
        player.setVolume(value);
    }

    public double getVolume(){
        return player.getVolume();
    }
}