/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author guerig
 */
public class Margaritas {
    /*
    Implementa un programa que permita saber a Romeo si su amada Julieta lo quiere o no lo quiere, en función del número de hojas que 
    tienen las margaritas del jardín y de si empieza a quitar hojas con “me quiere” o “no me quiere”. Para decidir si empieza a deshojar 
    con “me quiere” o “no me quiere” utiliza un dado de seis caras. Si sale par siempre empieza con “me quiere”, si sale impar entonces 
    empieza con “no me quiere”. Como el tres le da mala suerte, si saca ese valor vuelve a tirar hasta que saque otro que no sea tres.
    
    El programa debe solicitar cuántas margaritas quiere deshojar Romeo. Por cada margarita:
    Hay que preguntar a Romeo cuantas hojas tiene esa margarita.
    El programa muestra la tirada del dado de Romeo y también si se empieza con “me quiere” o “no me quiere”. Recuerda que con valor 
    tres se debe volver a tirar.
    Finalmente, según el dado y el número de hojas, se debe mostrar si su amada “lo quiere” o “no lo quiere”.
    Un ejemplo de salida, por cada margarita, podría ser el siguiente:

    “La margarita tiene 4 hojas, Romeo sacó dos en el dado, empezó a quitar hojas con <<me quiere>> y el resultado final es que su amada 
    no lo quiere”

    Una vez finalizada la ejecución se debe preguntar al usuario si se quiere ejecutar otra vez. En caso de escribir “sí” se ejecutará de
    nuevo, en caso de escribir “no”, se debe terminar y si el usuario escribe otra cosa que no sea “sí” o “no” se debe informar de que 
    esa opción no es correcta y se debe volver a preguntar.

    Todos los datos se leen con Scanner y se escriben en consola.

    Realiza los siguientes métodos estáticos:
    Generador de tiradas del dado. El método debe devolver el número que sale al tirar el dado. 
    Leer el número de margaritas a evaluar. Debe gestionar las excepciones que puedan ocurrir. Este método devuelve el número de 
    margaritas, entre 1 y 10 margaritas.
    Método que tenga como parámetro el número que ha salido en el dado y devuelva un booleano indicando si empieza con “me quiere” o “no 
    me quiere”. 

   
    */
    
    private static Scanner teclado = new Scanner(System.in);
    
    public static void main(String[] args) {
        int margaritas, hojas, dado;
        boolean resultadoMeQuiereDado;
        String resultadoFinal, respuestaRepetir = "";
        
        do{
        
        //  NÚMERO DE MARGARITAS
        margaritas = numeroMargaritas();
                
        

        
        // Bucle que se ejecuta hasta que i valga lo mismo que el número de margaritas
        for(int i = 1; i <= margaritas; i++){
            
            // NÚMERO DE HOJAS
            hojas = numeroHojas(margaritas);
            
            
            // NÚMERO DEL DADO        

            dado = excepcionDado();

            // RESULTADO DE SI ME QUIERE O NO
            resultadoMeQuiereDado = resultadoMeQuiereDado(dado);
            
            if(resultadoMeQuiereDado){
                System.out.println("La tirada fue " + dado + " y empieza con me quiere");
            }else{
                System.out.println("La tirada fue " + dado + " y empieza con no me quiere");

            }
            
            // RESULTADO FINAL
            resultadoFinal = resultadoFinal(resultadoMeQuiereDado, hojas);
            
            System.out.println("El resultado final es " + resultadoFinal);
            
        }  
        
        // REPETIR
        do{
            teclado.nextLine(); // Limpiar buffer
            System.out.println("¿Quieres repetir?");
            respuestaRepetir = teclado.nextLine();
            
            if(!(respuestaRepetir.equalsIgnoreCase("si") ||
                respuestaRepetir.equalsIgnoreCase("sí") ||
                respuestaRepetir.equalsIgnoreCase("no"))){
                System.out.println("Esa opción no es correcta. Escribe si o no"); // Mensaje de error si el usuario escribe otro dato de los permitidos
            }
            
        }while(!(respuestaRepetir.equalsIgnoreCase("si") || // Se ejecuta mientras el usuario introduzca un dato que sea diferente a estos
                respuestaRepetir.equalsIgnoreCase("sí") ||
                respuestaRepetir.equalsIgnoreCase("no")));
        
        }while(respuestaRepetir.equalsIgnoreCase("si") || respuestaRepetir.equalsIgnoreCase("sí"));
        
    }
    
    // SOLICITAMOS LAS MARGARITAS
    public static int numeroMargaritas(){
        int margaritas = 0;
        
        do{
            try{
                System.out.println("¿Cuántas margaritas tienes(entre 1 y 10)?");
                margaritas = teclado.nextInt();
                
                System.out.println("Número de margaritas " + margaritas);
                
            }catch(InputMismatchException ime){
                System.out.println("El tipo de dato introducido es erroneo, vuelve a escribirlo");
                teclado.nextLine(); // Limpiar el buffer para que el dato no se quedé almacenado 
            }
        }while(margaritas < 1 || margaritas > 10); // Se ejecuta mientras el número de margaritas no este en ese rango
            
        
        return margaritas;
    }
   
    // GENERAMOS EL DADO
    public static int numeroDado(){
        int dados = 0;
        
        Random generador = new Random();
        
        dados = generador.nextInt(1, 6);
        
        return dados;
    }
    
    // EXCEPCIÓN SI EL DADO SALE 3
    public static int excepcionDado(){
        int dado = 0;
        do{
            dado = numeroDado();
        
        }while(dado == 3); // Mientras el número del dado sea 3, se repite el bucle
        
        return dado;
    }
    
    // COMPROBAMOS SI EL USUARIO EMPIEZA CON "ME QUIERE" O NO
    public static boolean resultadoMeQuiereDado(int numeroDado){
        boolean resultado = false;
        int resultadoDado = numeroDado % 2;
        
        if(resultadoDado == 0){
            resultado = true;
        }
        
        return resultado;
    }
    
    // SOLICITAMOS EL NÚMERO DE HOJAS
    public static int numeroHojas(int margaritas){
        int hojas = 0, dado = 0;
        boolean resultadoMeQuiereDado;
        do{
            System.out.println("¿Cuántas hojas tiene la margarita?");
            hojas = teclado.nextInt();
        }while(hojas == 0);
        return hojas;
    }
    
    // RESULTADO FINAL QUE DEVUELVE "ME QUIERE" O "NO ME QUIERE"
    public static String resultadoFinal(boolean resultadoMeQuiereDado, int hojas){
        String resultado = "";
        int resultadoHojas;
        
        // Calculamos si Julieta le quiere        
        if(resultadoMeQuiereDado){
            if(hojas % 2 == 0){
                resultado = "no me quiere";
            }else{
                resultado = "me quiere";
            }
        }else{
            if(hojas % 2 == 1){
                resultado = "me quiere";
            }else{
                resultado = "no me quiere";
            }
        }
        
        return resultado;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
