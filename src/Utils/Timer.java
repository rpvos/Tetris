package Utils;

public class Timer {
    private int interval;
    private long lasttick;

    public Timer(int interval) {
        this.interval = interval;
        this.lasttick = System.currentTimeMillis();
    }

    public boolean timeout() {
        long now = System.currentTimeMillis();
        if (now > this.lasttick + (long)this.interval) {
            this.lasttick += (long)this.interval;
            return true;
        } else {
            return false;
        }
    }

    public void mark() {
        this.lasttick = System.currentTimeMillis();
    }

    public void setInterval(int interval) {
        this.interval = interval;
        this.lasttick = System.currentTimeMillis();
    }
}