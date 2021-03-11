package DemoShellExpertSystem;

import DemoShellExpertSystem.base.rule.Rule;
import DemoShellExpertSystem.base.fact.IFact;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author prog
 */
public class Main implements HumanInterface {

    public Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    // Funcionamiento del programa, con el ejemplo de las polígonos
    public void run() {
        // Creación del motor
        Motor motor = new Motor(this);

        // Agregar las reglas
        System.out.println("Agregando reglas...");
        motor.addRule("R1 : IF (Orden=3(¿Cuál es el orden?)) THEN  Triángulo");
        motor.addRule("R2 : IF (Triángulo AND Ángulo Recto(¿La figura tiene al menos un ángulo recto?)) THEN Triángulo Rectángulo");
        motor.addRule("R3 : IF (Triángulo AND Lados Iguales=2(¿Cuántos lados iguales tiene la figura?)) THEN Triángulo Isósceles");
        motor.addRule("R4 : IF (Triángulo rectángulo AND Triángulo Isósceles) THEN Triángulo Rectángulo Isósceles");
        motor.addRule("R5 : IF (Triángulo AND Lados Iguales=3(¿Cuántos lados iguales tiene la figura?)) THEN Triángulo Equilátero");
        motor.addRule("R6 : IF (Orden=4(¿Cuál es el orden?)) THEN Cuadrilátero");
        motor.addRule("R7 : IF (Cuadrilátero AND Lados Paralelos=2(¿Cuántos lados paralelos entre sí - 0, 2 o 4?)) THEN Trapecio");
        motor.addRule("R8 : IF (Cuadrilátero AND Lados Paralelos=4(¿Cuántos lados paralelos entre sí - 0, 2 o 4?)) THEN Paralelogramo");
        motor.addRule("R9 : IF (Paralelogramo AND Ángulo Recto(¿La figura tiene al menos un ángulo recto?)) THEN Rectángulo");
        motor.addRule("R10 : IF (Paralelogramo AND Lados Iguales=4(¿Cuántos lados iguales tiene la figura?)) THEN Rombo");
        motor.addRule("R11 : IF (Rectángulo AND Rombo THEN Cuadrado");

        // Resolución
        do {
            System.out.println("\n** Resolución **");
            motor.solve();
            System.out.println("¿Desea salir? (s/n)");
        } while (!keyboard.next().equalsIgnoreCase("s"));
    }

    // Pide una valor entero al usuario, sin verificaciones (0 en caso de problema)
    @Override
    public int askIntValue(String question) {
        System.out.println(question);

        try {
            return keyboard.nextInt();
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // Solicita un valor booleano, con sí (verdadero) o no. 
    // Se ignorarn los errores (devuelve falso)
    @Override
    public boolean askBoolValue(String question) {
        try {
            System.out.println(question + " (si, no)");
            String res = keyboard.next();
            return (res.equals("si"));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Muestra la lista de hechos de nivel >0 y por orden decreciente de nivel
    @Override
    public void printFacts(List<IFact> facts) {
        String res = "Solución(s) encontrada(s) : \n";

        Collections.sort(facts, (IFact f1, IFact f2) -> {
            return Integer.compare(f2.getLevel(), f1.getLevel());
        });

        for (IFact f : facts) {
            if (f.getLevel() != 0) {
                res += f.toString() + "\n";
            }
        }

        System.out.println(res);
    }

    // Muestra las reglas contenidas en la base
    @Override
    public void printRules(List<Rule> rules) {
        String res = "";
        for (Rule r : rules) {
            res += r.toString() + "\n";
        }
        System.out.println(res);
    }
}
