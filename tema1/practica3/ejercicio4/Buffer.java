public class Buffer {
    private boolean empty;
    private int value;

    Buffer() {
        empty = true;
    }

    public boolean isEmpty() {
        return empty;
    }

    public synchronized void put(int value) {
        empty = false;
        this.value = value;
    }

    public synchronized int get() {
        empty = true;
        return this.value;
    }

}
