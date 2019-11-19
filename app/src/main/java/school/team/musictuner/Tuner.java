package school.team.musictuner;

import java.io.Serializable;

/**
 * A class designed for removing overtones from Signals
 * This class includes several stretch methods designed to allow the tuner to be trained to the
 * overtones of a given instrument.
 */
public class Tuner implements Serializable {
    private static long SerialVersionUID = 1L;
    private Converter converter;

    public void setConverter(Converter converter) {
        this.converter=converter;
    }
    public void removeOvertones(Signal signal) {

    }

    /**
     * Starts listening for overtones from the users instrument
     */
    public void startTraining() {

    }

    /**
     * Ends listening for overtones from the user's instrument. It will use these overtones to
     * improve the remove overtones method
     */
    public void endTraining() {

    }

    /**
     * ends any training that might be going on without recording it.
     */
    public void leaveActivity() {

    }

    /**
     * Undoes the effect of any training.
     */
    public void reset() {

    }

    /**
     *
     * @return true if the Tuner removes overtones by training, false if it removes overtones by a basic algorithm
     */
    public boolean isTrained() {
        return false;
    }

}
