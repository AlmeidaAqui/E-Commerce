/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.gabriel.ecommerce.dominio;

/**
 *
 * @author gabriel-almeida
 */

import br.com.gabriel.ecommerce.excecoes.CarrinhoVazioException;
import br.com.gabriel.ecommerce.excecoes.EstoqueInsuficienteException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int numero;
    private final LocalDateTime data;
    private final List<ItemCarrinho> itens = new ArrayList<>();
    private final double total;

    private Pedido(int numero, Carrinho carrinho) {
        this.numero = numero;
        this.data = LocalDateTime.now();
        for (ItemCarrinho i : carrinho.getItens()) {
            itens.add(new ItemCarrinho(i.getProduto(), i.getQuantidade()));
        }
        this.total = carrinho.getTotal();
    }

    /* Valida tudo antes de baixar o estoque, para não deixar o pedido pela metade. */
    public static Pedido finalizar(int numero, Carrinho carrinho)
            throws CarrinhoVazioException, EstoqueInsuficienteException {

        if (carrinho.estaVazio()) throw new CarrinhoVazioException();

        for (ItemCarrinho i : carrinho.getItens()) {
            if (i.getQuantidade() > i.getProduto().getEstoque()) {
                throw new EstoqueInsuficienteException(
                    "Estoque insuficiente para '" + i.getProduto().getnome_produto()
                    + "'. Disponível: " + i.getProduto().getEstoque());
            }
        }
        for (ItemCarrinho i : carrinho.getItens()) {
            i.getProduto().baixarEstoque(i.getQuantidade());
        }
        return new Pedido(numero, carrinho);
    }

    public void imprimir() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("--- Pedido #" + numero + " (" + data.format(f) + ") ---");
        for (ItemCarrinho i : itens) {
            System.out.printf("  %s | %d x R$ %.2f%n",
                i.getProduto().getnome_produto(), i.getQuantidade(), i.getProduto().getPreco());
        }
        System.out.printf("  Total: R$ %.2f%n", total);
    }
}
