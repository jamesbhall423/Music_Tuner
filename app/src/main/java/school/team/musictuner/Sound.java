package school.team.musictuner;

public class Sound {
private int length;
    Sound(double samplesPerSecond, int length) {

    }
    Sound(double time) { //listens to sound audio for the given number of seconds

    }
    Sound(String file) { //retrieves sound from the given file - throws IOException

    }
    static Object startRecording(double maxTime) { //starts listening to audio input – maxTime = seconds

        return null;
    }
    static Sound endRecording(Object key) {//retrieves the Sound recorded

        return null;
    }

    double getDataAt(int point) {
        return 0.0;
    }
    void setDataAt(int point, double value) { //should only be called when building the sound

    }
    double samplesPerSecond() {
        return 0.0;
    }
    Sound getCompressedSound(double samplesPerSecond) {
        return null;
    }
    int length() {
        return length;
    }
    Sound getSound(int on, int off) {
        return null;
    }

}
