package school.team.musictuner;

public class Note implements Comparable<Note> {

    final double frequency;
    final String name;
    final TuneSet tuneSet;

    public Note (double frequency, String name, TuneSet tuneSet)
    {
        this.frequency = frequency;
        this.name=name;
        this.tuneSet=tuneSet;
    }

    public int compareTo(Note other) {
        return 0;
    }

    //Design Plan had an error. These are the proper arguments for the method name.
    public static Note getNote(double frequency, TuneSet tuneSet) {
        return tuneSet.getNote(frequency);
    }

    public boolean isSharp() {
        return false;
    }

    public boolean isFlat() {
        return false;
    }

    public char halfStep() {
        return 'b';
    } //flat b, or sharp #

    public char letter() { //capitalized
        return 'C';
    }

    public int octave() {
        return 0;
    }

}
