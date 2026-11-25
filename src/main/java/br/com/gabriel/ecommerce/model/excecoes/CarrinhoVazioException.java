/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.gabriel.ecommerce.excecoes;

/**
 *
 * @author gabriel-almeida
 */
public class CarrinhoVazioException extends Exception {
    public CarrinhoVazioException() {
        super("O carrinho está vazio.");
    }
}
