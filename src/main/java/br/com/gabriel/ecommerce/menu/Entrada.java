/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.gabriel.ecommerce.menu;

import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 *
 * @author gabriel-almeida
 */
public class Entrada {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static String lerTexto (){
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.err.println("Erro: Nenhum dado encontrado na entrada ou fluxo encerrado.");
            return ""; // Retorna uma string vazia como fallback ou um valor padrão
        } catch (IllegalStateException e) {
            System.err.println("Erro: O leitor de entrada (Scanner) foi fechado.");
            return "";
        } catch (Exception e) {
            System.err.println("Erro desconhecido ao ler a entrada: " + e.getMessage());
            return "";
        }
    }
    public static int LerInteiro(){
        while (true) {
        try {
            // Tenta ler a linha e converter para inteiro
            String entrada = scanner.nextLine();
            return Integer.parseInt(entrada.trim()); // .trim() remove espaços em branco acidentais
            
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, digite um número inteiro válido.");

            } catch (NoSuchElementException e) {
                System.out.println("Erro: Fluxo de entrada encerrado.");
                return 0; 

            } catch (IllegalStateException e) {
                System.out.println("Erro: O leitor (Scanner) foi fechado.");
                return 0;
            }
        }
    }
    public static char Lerchar(){
        while (true) {
        try {
            String entrada = scanner.nextLine().trim(); // Lê a linha inteira e remove espaços
            
            return entrada.charAt(0);
                    
            } catch (NoSuchElementException e) {
                System.out.println("Erro: Fluxo de entrada encerrado.");
                return 'N'; // Valor padrão de segurança

            } catch (IllegalStateException e) {
                System.out.println("Erro: O leitor (Scanner) foi fechado.");
                return 'N';
            }
        }
    } 
        
    public static float LerFloat(){
        while (true) {
        try {
            // Tenta ler a linha e converter para inteiro
            String entrada = scanner.nextLine();
            return Float.parseFloat(entrada.trim()); // .trim() remove espaços em branco acidentais
            
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, digite um número inteiro válido.");

            } catch (NoSuchElementException e) {
                System.out.println("Erro: Fluxo de entrada encerrado.");
                return 0; 

            } catch (IllegalStateException e) {
                System.out.println("Erro: O leitor (Scanner) foi fechado.");
                return 0;
            }
        }
    }
    public static double LerDouble(){
        while (true) {
        try {
            // Tenta ler a linha e converter para inteiro
            String entrada = scanner.nextLine();
            return Double.parseDouble(entrada.trim()); // .trim() remove espaços em branco acidentais
            
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, digite um número inteiro válido.");

            } catch (NoSuchElementException e) {
                System.out.println("Erro: Fluxo de entrada encerrado.");
                return 0; 

            } catch (IllegalStateException e) {
                System.out.println("Erro: O leitor (Scanner) foi fechado.");
                return 0;
            }
        }
    }
}
