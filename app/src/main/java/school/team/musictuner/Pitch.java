package school.team.musictuner;
/*
* Represents a frequency in the sound audio.
 */
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
    /*
    * returns the Note closest in frequency to this pitch.
     */
    public Note getNote(TuneSet tuneSet) {
        return  tuneSet.getNote(frequency);
    }
    /*
    * tunes the frequency to the nearest Note (as in Note class)
     */
    public void tuneNote() {

    }


    /*
    * compare frequency <, ==, > o.frequency;
     */
    @Override
    public int compareTo(Pitch o) {
        return (int)Math.signum(this.frequency-o.frequency);
    }
}
