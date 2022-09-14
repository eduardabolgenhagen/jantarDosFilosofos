public class Jantar {

    public static void main(String[] args) {
        //criando uma mesa
        Mesa mesa = new Mesa();
        //iniciando uma estrutura de repetição e criando os filosofos para o Jantar
        for (int filosofo = 0; filosofo < 5; filosofo++) {
            new Filosofo("Filosofo " + filosofo, mesa, filosofo).start();
        }
    }
}
