package school.team.musictuner;
/**
* Represents a note on a musical scale, such as C# and Eb
 */
public class Note implements Comparable<Note> {
    /**
    * The frequency of the note
     */
    final double frequency;
    /**
    * The name of the note
     */
    final String name;
    /**
    * the tuning scheme the note belongs to
     */
    final TuneSet tuneSet;

    public Note (double frequency, String name, TuneSet tuneSet)
    {
        this.frequency = frequency;
        this.name = name;
        this.tuneSet = tuneSet;
    }

    /**
    * note1.compareTo(note2) = note1.frequency < / = / > note2.frequency
     */
    public int compareTo(Note other) {
        return 0;
    }

    /**
    * returns a note from the given frequency and tuneSet.
     */
    public static Note getNote(double frequency, TuneSet tuneSet) {
        return tuneSet.getNote(frequency);
    }

    public boolean isSharp() {
        return false;
    }

    public boolean isFlat() {
        return false;
    }

    /**
    * return
    * # sharp,
    * b flat,
    * or [blank] natural.
     */
    public char halfStep() {
        return 'b';
    } //flat b, or sharp #

    /**
    * returns the capitalized letter name of the note
     */
    public char letter() { //capitalized
        return 'C';
    }

    /**
    * returns the note's octave - C4 = middle C
     */
    public int octave() {
        return 0;
    }

}
