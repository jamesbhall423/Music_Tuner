package school.team.musictuner;

/**
 * Provides a list of useful methods converting between different types of sound classes.
 */
public class Converter {
    private Settings settings;
    private Tuner tuner;
    public void setSettings(Settings settings) {
        this.settings=settings;
    }
    public Settings getSettings() {
        return settings;
    }

    /**
     * Constructs a Signal from the given sound
     * @param sound
     * @param on the beginning sample, inclusive
     * @param off the end sample, not inclusive
     * @return
     */
    public Signal getSignal(Sound sound, int on, int off) {
        return null;
    }

    /**
     * Constructs a Signal from the given Sound
     * @param sound
     * @return
     */
    public Signal getSignal(Sound sound) {
        return new Signal();
    }

    /**
     * Constructs a Timeline from the given sound
     * @param sound
     * @return
     */
    public Timeline soundTimeline(Sound sound) {
        return null;
    }

    /**
     * Constructs a PlayedSection from the given Timeline
     * @param timeline
     * @return
     */
    public PlayedSection section(Timeline timeline) {
        return null;
    }
    void setTuner(Tuner tuner) {
        this.tuner = tuner;
    }

}
