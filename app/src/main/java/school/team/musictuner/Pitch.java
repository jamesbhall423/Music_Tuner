package school.team.musictuner;

public class Pitch implements Comparable<Pitch> {

    private double frequency, amplitude;
    public double getFrequency() {
        return frequency;
    }
    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }
    public double getAmplitude() {
        return amplitude;
    }
    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }
    public Note getNote(TuneSet tuneSet) {
        return  tuneSet.getNote(frequency);
    }
    public void tuneNote() {

    }


    @Override
    public int compareTo(Pitch o) {
        if (this.frequency == o.frequency)
            return 0;
        else
            return 1;
    }
}
