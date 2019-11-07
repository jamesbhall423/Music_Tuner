package school.team.musictuner;

public class Sound {
private int length;
    public Sound(double samplesPerSecond, int length) {

    }
    public Sound(double time) { //listens to sound audio for the given number of seconds

    }
    public Sound(String file) { //retrieves sound from the given file - throws IOException

    }
    public static Object startRecording(double maxTime) { //starts listening to audio input – maxTime = seconds

        return null;
    }
    public static Sound endRecording(Object key) {//retrieves the Sound recorded

        return null;
    }

    public double getDataAt(int point) {
        return 0.0;
    }
    public void setDataAt(int point, double value) { //should only be called when building the sound

    }
    public double samplesPerSecond() {
        return 0.0;
    }
    public Sound getCompressedSound(double samplesPerSecond) {
        return null;
    }
    public int length() {
        return length;
    }
    public Sound getSound(int on, int off) {
        return null;
    }

}
