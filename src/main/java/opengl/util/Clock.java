package opengl.util;

import java.util.ArrayList;

public class Clock {
    public static final int FPS = 60;
    private ArrayList<Tickable> tickables = new ArrayList<Tickable>();
    private ArrayList<Runnable> onDestroy = new ArrayList<Runnable>();
    private Thread clockThread = new Thread(new Runnable() {
        @Override
        public void run() {
            long lastTime = System.nanoTime();
            for (Tickable t : tickables) {
                t.init();
            }
            while (running) {
                if (!paused) {
                    double delta = (System.nanoTime() - lastTime)/1E9D;
                    for (Tickable t : tickables) {
                        if (t.tick(delta)) stop();
                    }
                    lastTime = System.nanoTime();
                }
                try {
                    Thread.sleep(1000/FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (Tickable t : tickables) {
                t.destroy();
            }
            for (Runnable r : onDestroy) {
                r.run();
            }
        }
    });
    private boolean everStarted = false, running = false, paused = false;

    public void start() {
        if (!everStarted) {
            everStarted = true;
            running = true;
            clockThread.start();
        } else {
            System.err.println("You can't start a clock twice");
        }
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public void stop() {
        running = false;

    }

    public boolean isRunning() {
        return clockThread.isAlive() && running && !paused;
    }

    public void add(Tickable t) {
        if (everStarted) {
            System.err.println("Clock has already started");
        } else {
            tickables.add(t);
        }
    }

    public void addDestroyListener(Runnable r) {
        onDestroy.add(r);
    }
}
