package school.team.musictuner;

public class Pitch implements Comparable<Pitch> {

    private double frequency, amplitude;
    private Note note;
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
    Note getNote() {
        return  note;
    }
    void tuneNote() {

    }


    @Override
    public int compareTo(Pitch o) {
        if (this.frequency == o.frequency)
            return 0;
        else
            return 1;
    }
}
