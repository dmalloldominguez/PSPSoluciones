public class Practica3Ejercicio2 {
    public static void main(String[] args) {
        AnimalThread tortuga = new AnimalThread("tortuga", 1,0);
        AnimalThread caballo = new AnimalThread("caballo", 10,0.01);
        AnimalThread liebre = new AnimalThread("liebre", 9,0.001);
        AnimalThread perro = new AnimalThread("perro", 5,0.0005);

        tortuga.start();
        caballo.start();
        liebre.start();
        perro.start();
        try {
            tortuga.join();
            caballo.join();
            liebre.join();
            perro.join();
        } catch (InterruptedException e) {
            System.out.println("Error");
        }
        System.out.printf("Carrera finalizada.\r\n");
    }
}
