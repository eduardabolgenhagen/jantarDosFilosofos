public class Mesa {
    boolean[] garfos = new boolean[5];
    int[] filosofos = new int[5];
    int[] tentativas = new int[5];
    public static int PENSANDO = 1;
    public static int COMENDO = 2;
    public static int FOME = 3;
    public static int primeiro_filosofo = 0;
    public static int ultimo_filosofo = 5 - 1;

    public Mesa() {
        for (int i = 0; i < 5; i++) {
            garfos[i] = true;
            filosofos[i] = PENSANDO;
            tentativas[i] = 0;
        }
    }

    public synchronized void getGarfos(int filosofo) {
        filosofos[filosofo] = FOME;
        while (filosofos[aEsquerda(filosofo)] == COMENDO || filosofos[aDireita(filosofo)] == COMENDO) {
            try {
                tentativas[filosofo]++;
                wait();
            } catch (InterruptedException e) {

            }
        }
        System.out.println("O filosofo morreu de fome");
        tentativas[filosofo] = 0;
        garfos[garfoEsquerdo(filosofo)] = false;
        garfos[garfoDireito(filosofo)] = false;
        filosofos[filosofo] = COMENDO;
        imprimeEstadosFilosofos();
        imprimeGarfos();
        imprimeTentativas();
    }

    public synchronized void returnGarfos(int filosofo) {
        garfos[garfoEsquerdo(filosofo)] = false;
        garfos[garfoDireito(filosofo)] = false;
        filosofos[filosofo] = COMENDO;
        imprimeEstadosFilosofos();
        imprimeGarfos();
        imprimeTentativas();
    }

    public int aDireita(int filosofo) {
        int direito;
        if (filosofo == ultimo_filosofo) {
            direito = primeiro_filosofo;
        } else {
            direito = filosofo + 1;
        }
        return direito;
    }

    public int aEsquerda(int filosofo) {
        int esquerdo;
        if (filosofo == primeiro_filosofo) {
            esquerdo = ultimo_filosofo;
        } else {
            esquerdo = filosofo - 1;
        }
        return esquerdo;
    }

    public int garfoDireito(int filosofo) {
        int garfoEsquerdo = filosofo;
        return garfoEsquerdo;
    }

    public int garfoEsquerdo(int filosofo) {
        int garfoDireito;
        if (filosofo == ultimo_filosofo) {
            garfoDireito = 0;
        } else {
            garfoDireito = filosofo + 1;
        }
        return garfoDireito;
    }

    public void imprimeEstadosFilosofos() {
        String texto = "*";
        System.out.print("Filósofos: ");
        for (int i = 0; i < 5; i++) {
            switch (filosofos[i]) {
                case 1:
                    texto = "PENSANDO";
                    break;
                case 3:
                    texto = "FOME";
                    break;
                case 2:
                    texto = "COMENDO";
                    break;
            }
            System.out.print(texto + " ");
        }
        System.out.println(" ");
    }

    public void imprimeGarfos() {
        String garfo = "*";
        System.out.print("Garfos: ");
        for (int i = 0; i < 5; i++) {
            if (garfos[i]) {
                garfo = "LIVRE";
            } else {
                garfo = "OCUPADO";
            }
            System.out.print(garfo + " ");
        }
        System.out.println(" ");
    }

    public void imprimeTentativas() {
        System.out.print("Tentou comer: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(filosofos[i] + " ");
        }
        System.out.println(" ");
    }
}
