public class Filosofo extends Thread {
    final static int tempoMaximo = 100;
    Mesa mesa;
    int filosofo;

    public Filosofo(String nome, Mesa mesaDeJantar, int filo) {
        super(nome);
        mesa = mesaDeJantar;
        filosofo = filo;
    }

    public void run() {
        int tempo = 0;
        while (true) {
            tempo = (int) (Math.random() * tempoMaximo);
            pensar(tempo);
            mesa.getGarfos(filosofo);
            tempo = (int) (Math.random() * tempoMaximo);
            comer(tempo);
            mesa.returnGarfos(filosofo);
        }
    }

    public void pensar(int tempo) {
        try {
            sleep(tempo);
        } catch (InterruptedException e) {
            System.out.println("O filosofo pensou demais e morrou de fome");
        }
    }

    public void comer(int tempo) {
        try {
            sleep(tempo);
        } catch (InterruptedException e) {
            System.out.println("O filosofo comeu demais e morreu embuchado.");
        }
    }
}
