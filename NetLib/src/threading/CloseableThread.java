package threading;

import java.io.Closeable;
import java.io.IOException;

public abstract class CloseableThread implements Closeable, Runnable {

    private boolean running = false;
    
    public boolean isRunning() { return running; }

    public final void start() {
        running = true;
        new Thread(this).start();
    }

    protected abstract void doInBackground() throws Exception;
    
    protected void onInterrupt() {}
    
    @Override
    public void run() {
        while (running) {    
            try {
                doInBackground();
            } catch (Exception e) {
                //e.printStackTrace(System.err);
                if (running) onInterrupt();
                running = false;
            }
        }
    }
    
    @Override
    public void close() throws IOException {
        running = false;
    }
    
}