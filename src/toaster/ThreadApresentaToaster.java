package toaster;

public class ThreadApresentaToaster extends Thread {
    private Toaster toaster;
    private Integer posicaoyInicial;
    private Integer posicaox;
    private Integer posicaoyFinal;

    public ThreadApresentaToaster(Toaster toaster, Integer posicaox,
            Integer posicaoyInicial) {
        this.toaster = toaster;
        this.posicaox = posicaox;
        this.posicaoyInicial = posicaoyInicial;
        this.posicaoyFinal = posicaoyInicial - 305;
    }

    @SuppressWarnings("static-access")
    public void run() {
        while (posicaoyInicial > posicaoyFinal) {
            try {
                toaster.setLocation(posicaox, posicaoyInicial);
                posicaoyInicial--;
                sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            this.sleep(10000);
            toaster.dispose();
        } catch (Exception e) {

        }
    }
}
