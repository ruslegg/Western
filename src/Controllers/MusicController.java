package Controllers;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class MusicController {

    public void playMusic(boolean play) throws IOException, SQLException, UnsupportedAudioFileException {
        AudioData data = new AudioStream(new FileInputStream("music.wav")).getData();
        ContinuousAudioDataStream BGM = new ContinuousAudioDataStream(data);

        if (play)
            AudioPlayer.player.start(BGM);

        else
            AudioPlayer.player.stop(BGM);
    }
}
