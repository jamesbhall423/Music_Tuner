package school.team.musictuner;

/**
 * Represents a tuning scheme.
 */
public abstract class TuneSet {
    public static final double STANDARD_A4_TUNING = 440.0;
    public static final double STANDARD_MIDDLE_C_FREQUENCY = getMiddleCFrequency();
    private static double getMiddleCFrequency() {
        int halfSteps = 2+2+1+2+2;
        return STANDARD_A4_TUNING/Math.pow(2,halfSteps/12.0);
    }
    public abstract Note getNote(double frequency);

    /**
    * The tune set that represent standard tuning.
     */
    public static final TuneSet STANDARD = new TuneSet() {
        @Override
        public Note getNote(double frequency) {
            double logDif = 12*Math.log(frequency/STANDARD_MIDDLE_C_FREQUENCY)/Math.log(2);
            logDif = Math.round(logDif);
            int octave = 4+(int) (logDif/12);
            int halfSteps = (int)logDif%12;
            char letter;
            char sharp = ' ';
            switch (halfSteps) {
                case 0:
                    letter = 'C';
                    break;
                case 1:
                    letter = 'C';
                    sharp = '#';
                    break;
                case 2:
                    letter = 'D';
                    break;
                case 3:
                    letter = 'E';
                    sharp = 'b';
                    break;
                case 4:
                    letter = 'E';
                    break;
                case 5:
                    letter = 'F';
                    break;
                case 6:
                    letter = 'F';
                    sharp = '#';
                    break;
                case 7:
                    letter = 'G';
                    break;
                case 8:
                    letter = 'G';
                    sharp = '#';
                    break;
                case 9:
                    letter = 'A';
                    break;
                case 10:
                    letter = 'B';
                    sharp = 'b';
                    break;
                case 11:
                    letter = 'B';
                    break;
                default:
                    throw new Error();
            }
            String out = ""+letter;
            if (sharp!=' ') out+=sharp;
            out+=octave;
            double freqOut = STANDARD_MIDDLE_C_FREQUENCY * Math.pow(2,logDif/12.0);
            return new Note(freqOut,out,this,sharp,octave,letter);
        }
    };

    /**
    * Returns an array representing all possible tuneSets.
     */
    public static TuneSet[] allTuneSets() {
        TuneSet[] out = {STANDARD};
        return out;
    }
    /**
    * Return the name of this tuneSet.
     */
    public String toString() {
        return "";
    }

}
