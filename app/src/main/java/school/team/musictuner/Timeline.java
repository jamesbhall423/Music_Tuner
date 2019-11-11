package school.team.musictuner;

public class Timeline {
    private int length, on, off, moment;
    private double momentsPerSecond;
    private Signal signal;

    Timeline(int length, double momentsPerSecond) {
        this.length = length;
        this.momentsPerSecond = momentsPerSecond;
    }
    Timeline(Timeline base, double momentsPerSecond) {
        this.momentsPerSecond = momentsPerSecond;
    }
    Timeline(Timeline base, int on, int off) {
        this.on = on;
        this.off = off;
    }
    Signal getMoment(int moment) {
        return signal;
    }
    void setMoment(int moment, Signal signal) {
        this.moment = moment;
    }
    public int length() {
       return length;
    }
    public double momentsPerSecond() {
        return momentsPerSecond;
    }

}
