package toaster;

public class ThreadReordena extends Thread {
    private Toaster toaster;
    private Integer posicaoX;
    private Integer posicaoY;

    public ThreadReordena(Toaster toaster, int posicaoX, int posicaoY) {
        this.toaster = toaster;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    @SuppressWarnings("static-access")
    public void run() {
        int posicaoyInicial = toaster.getLocation().y;
        while (posicaoyInicial < posicaoY) {
            toaster.setLocation(posicaoX, posicaoyInicial);
            posicaoyInicial++;
            try {
                this.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
