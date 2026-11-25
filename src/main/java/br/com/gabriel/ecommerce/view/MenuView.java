/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.gabriel.ecommerce.view;

/**
 *
 * @author gabriel-almeida
 */

import br.com.gabriel.ecommerce.menu.Entrada;

public class MenuView {

    public void exibirMenu() {
        System.out.println("\n===== E-COMMERCE =====");
        System.out.println("1. Listar produtos");
        System.out.println("2. Buscar produto");
        System.out.println("3. Ver carrinho");
        System.out.println("4. Remover item");
        System.out.println("5. Finalizar pedido");
        System.out.println("6. Consultar pedidos");
        System.out.println("0. Sair");
    }

    public int obterOpcao() {
        return Entrada.LerInteiro();
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}