public class AF {
    static class Alfabeto {
        final char LETRA_CERO = '0';
        final char LETRA_UNO = '1';

        public void desplegarAlfabeto() {
            System.out.println("Sigma = { " + LETRA_CERO + ", " + LETRA_UNO + " }");
        }
    }

    static class Estados {
        final int ESTADO_UNO = 1;
        final int ESTADO_DOS = 2;
        final int INICIAL = ESTADO_UNO;
        final int FINAL = ESTADO_UNO;
        int actual = ESTADO_UNO;

        public void desplegarEstadosiniciales() {
            System.out.println("Q = { S" + ESTADO_UNO + ", S" + ESTADO_DOS + " }");
            System.out.println("q_0 = S" + INICIAL);
            System.out.println("F = { S" + FINAL + " }");
        }

        public void desplegarEstadoactual() {
            System.out.println("q_actual dado por delta = S" + getActual());
        }

        public void setActual(int estado) {
            actual = estado;
        }

        public int getActual() {
            return actual;
        }
    }

    public static class Automata_Finito_Determinista {
        Alfabeto alfabeto;
        Estados estados;
        char[] palabra_a_evaluar;
        int indice;
        
        public Automata_Finito_Determinista(char[] palabra) {
            alfabeto = new Alfabeto();
            estados = new Estados();
            indice = 0;
            palabra_a_evaluar = palabra;
        }

        //si la palabra ingresada es válida según el AFD
        public void desplegarMembresia() {
            String mensaje_membresia;
            String palabra_string = new String(palabra_a_evaluar);

            if (estados.getActual()==estados.FINAL)
            {
                mensaje_membresia = new String("\nLa palabra \"" + palabra_string + 
                "\" SI es miembro del AFD!!!");
                
                System.out.println(mensaje_membresia);
            }
            else
            {
                mensaje_membresia = new String("\nLa palabra \"" + palabra_string + 
                "\" NO es miembro del AFD!!!");
                
                System.out.println(mensaje_membresia);
            }
        }

        //tabla de la función delta de transición
        public void desplegarFunciontransicion() {
            System.out.println("delta = ");
            System.out.println("    +----+----+----+");
            System.out.println("    |    | 0  | 1  |");
            System.out.println("    +----+----+----+");
            System.out.println("    | S1 | S2 | S1 |");
            System.out.println("    +----+----+----+");
            System.out.println("    | S2 | S1 | S2 |");
            System.out.println("    +----+----+----+");
            System.out.println();
        }

        public void desplegarDatosautomata() {
            String palabra_string = new String(palabra_a_evaluar);

            System.out.println("--------DATOS DEL AUTOMATA--------\n");
            System.out.println("Palabra a evaluar: \"" + palabra_string + "\"");
            System.out.println("A = ( Q, , q_0, F, Sigma, delta )");
            estados.desplegarEstadosiniciales(); //Q, q_0 y F
            alfabeto.desplegarAlfabeto();       //Sigma
            desplegarFunciontransicion();       //delta
        }

        //que hacer cuando se estado en S1???
        public void transicionUno() {
            boolean en_estado_uno;
    
            en_estado_uno = estados.getActual()==estados.ESTADO_UNO;
    
            if (en_estado_uno) {
                if (palabra_a_evaluar[indice]==alfabeto.LETRA_UNO)
                    estados.setActual(estados.ESTADO_UNO);
                else
                    estados.setActual(estados.ESTADO_DOS);
    
                estados.desplegarEstadoactual();
                indice++;
                funcionTransicion();
            }
            else
                transicionDos();
        }

        // ... S2???
        public void transicionDos() {
            boolean en_estado_dos;

            en_estado_dos = estados.getActual()==estados.ESTADO_DOS;

            if (en_estado_dos)
            {
                if (palabra_a_evaluar[indice]==alfabeto.LETRA_UNO)
                    estados.setActual(estados.ESTADO_DOS);
                else
                    estados.setActual(estados.ESTADO_UNO);

                estados.desplegarEstadoactual();
                indice++;
                funcionTransicion(); //reinicio del ciclo (recursivamente)
            } //como no hay otros estados, no es necesario llamar al bloque else
        }

        // Función delta de transición, se aplica de manera recursiva
        public void funcionTransicion() {
            
            if (indice < palabra_a_evaluar.length)
                transicionUno();
            else
                desplegarMembresia();
        }
    }

    public static void main(String[] args) {
        //la palabra a evaluar se escribe al ejecutar el programa en consola
        Automata_Finito_Determinista AFD = new Automata_Finito_Determinista(args[0].toCharArray()); 
        
        System.out.println("\nUnidad 3 - Aplicaciones: Automatas Finitos Deterministicos en Java\n");
        AFD.desplegarDatosautomata();
        System.out.println("--------INICIO DE EVALUACION--------\n");
        AFD.funcionTransicion();
        System.out.println("\n--------FIN DE LA EVALUACION--------");
    }
}