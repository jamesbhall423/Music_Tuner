package school.team.musictuner;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;
import static school.team.musictuner.TuneSet.*;

public class TuneSetTest {
    @Test
    public void testStandardTuneSet() {
        testNote(3.0/4,3,'G',' ');
        testNote(8.0/3,5,'F',' ');
        testNote(9.0/4, 5, 'D',' ');
        testNote(27.0/16,4,'A',' ');
        testNote(81.0/256,2,'E',' ');
        testNote(243.0/64,5,'B',' ');
        testNote(16.0/9,4,'B','b','A','#');
        testNote(32.0/27,4,'E','b','D','#');
        testNote(128.0/81,4,'A','b','G','#');
        testNote(256.0/243,4,'D','b','C','#');
        testNote(1024.0/729,4,'G','b','F','#');
    }
    private void testNote(double multiple, int octave, char letter, char halfStep) {
        Note note = STANDARD.getNote(multiple*STANDARD_MIDDLE_C_FREQUENCY);
        assertTrue(note.toString(),note.letter()==letter&&note.octave()==octave&&note.halfStep()==halfStep);
    }
    private void testNote(double multiple, int octave, char letter1, char halfStep1, char letter2, char halfStep2) {
        Note note = STANDARD.getNote(multiple*STANDARD_MIDDLE_C_FREQUENCY);
        assertTrue(note.toString(),note.octave()==octave&&(note.letter()==letter1&&note.halfStep()==halfStep1||note.letter()==letter2&&note.halfStep()==halfStep2));
    }
}
