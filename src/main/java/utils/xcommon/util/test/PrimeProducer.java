package utils.xcommon.util.test;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;
 
    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }
 
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted())
                queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException consumed) {
            /* Allow thread to exit */
        }
    }
 
    public void cancel() { interrupt(); }
}