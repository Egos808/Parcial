import java.util.Scanner;

public class Cine {
    Pelicula peliculaActual;
    double precioEntrada;
    Asiento[][] asientos;

    public Cine(Pelicula peliculaActual, double precioEntrada, int filas, int columnas) {
        this.peliculaActual = peliculaActual;
        this.precioEntrada = precioEntrada;
        this.asientos = new Asiento[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                asientos[i][j] = new Asiento(i + 1, (char) ('A' + j));
            }
        }
    }

    public void mostrarAsientos() {
        System.out.println("Asientos disponibles:");
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[i].length; j++) {
                if (!asientos[i][j].ocupado) {
                    System.out.print(asientos[i][j].fila + "" + asientos[i][j].columna + " ");
                } else {
                    System.out.print("X  ");
                }
            }
            System.out.println();
        }
    }

    public void comprarEntrada(Espectador espectador, int fila, char columna) {
        int filaIndex = fila - 1;
        int columnaIndex = columna - 'A';

        if (filaIndex >= 0 && filaIndex < asientos.length &&
            columnaIndex >= 0 && columnaIndex < asientos[0].length) {
            Asiento asiento = asientos[filaIndex][columnaIndex];
            if (!asiento.ocupado && espectador.edad >= peliculaActual.edadMinima && espectador.dinero >= precioEntrada) {
                asiento.ocupado = true;
                espectador.dinero -= precioEntrada;
                System.out.println("¡Entrada comprada exitosamente para " + peliculaActual.titulo + "!");
            } else {
                System.out.println("No se pudo comprar la entrada.");
            }
        } else {
            System.out.println("Asiento no válido.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

Pelicula pelicula = new Pelicula("Pelicula 1", 120, 12, "Director 1");
        Cine cine = new Cine(pelicula, 10.0, 8, 9);

        System.out.println("Bienvenido al cine!");
        System.out.println("Pelicula: " + cine.peliculaActual.titulo);
        System.out.println("Precio de entrada: $" + cine.precioEntrada);

        boolean seguirComprando = true;
        while (seguirComprando) {
            cine.mostrarAsientos();

            System.out.print("Ingrese su nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese su edad: ");
            int edad = scanner.nextInt();
            System.out.print("Ingrese su dinero disponible: ");
            double dinero = scanner.nextDouble();
            scanner.nextLine(); // Consumir la nueva línea

            Espectador espectador = new Espectador(nombre, edad, dinero);

            System.out.println("Asientos disponibles:");
            cine.mostrarAsientos();

            System.out.print("Ingrese la fila del asiento que desea (1-8): ");
            int fila = scanner.nextInt();
            System.out.print("Ingrese la columna del asiento que desea (A-I): ");
            char columna = scanner.next().charAt(0);
            scanner.nextLine(); // Consumir la nueva línea

            cine.comprarEntrada(espectador, fila, columna);

            System.out.print("¿Desea comprar otra entrada? (s/n): ");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("n")) {
                seguirComprando = false;
            }
        }

        scanner.close();
    }
}
