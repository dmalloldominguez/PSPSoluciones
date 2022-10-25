public class CustomThread extends Thread{
    CustomNumber count;

    CustomThread(CustomNumber count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            count.setNumber(count.getNumber() + 1);
        }
    }
}
