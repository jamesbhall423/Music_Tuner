package school.team.musictuner;

public class Pitch implements Comparable<Pitch> {

    private double frequency, amplitude;
    double getFrequency() {
        return frequency;
    }
    void setFrequency(double frequency) {
        this.frequency = frequency;
    }
    double getAmplitude() {
        return amplitude;
    }
    void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }
    Note getNote(TuneSet tuneSet) {
        return  tuneSet.getNote(frequency);
    }
    void tuneNote() {

    }


    @Override
    public int compareTo(Pitch o) {
        return 0;
    }
}
