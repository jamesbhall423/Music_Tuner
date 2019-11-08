package school.team.musictuner;

public class Converter {
    private Settings settings;
    private Tuner tuner;
    public void setSettings(Settings settings) {
        this.settings=settings;
    }
    public Settings getSettings() {
        return settings;
    }
    public Signal getSignal(Sound sound, int on, int off) {
        return null;
    }
    Signal getSignal(Sound sound) {
        return new Signal();
    }

    public Timeline soundTimeline(Sound sound) {
        return null;
    }
    public PlayedSection section(Timeline timeline) {
        return null;
    }
    void setTuner(Tuner tuner) {
        this.tuner = tuner;
    }

}
