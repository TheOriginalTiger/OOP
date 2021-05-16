public class Synchronizer
{
    volatile boolean val;

    Synchronizer() {
        val = true;
    }
    public synchronized void setFalse() {
        val = false;
    }
}
