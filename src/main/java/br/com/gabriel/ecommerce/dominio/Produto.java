/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.gabriel.ecommerce.dominio;

import br.com.gabriel.ecommerce.excecoes.EstoqueInsuficienteException;
import java.io.Serializable;

/**
 *
 * @author gabriel-almeida
 */

public abstract class Produto implements Serializable {

    //Atributos de caracteristicas
    private static int contador = 0;
    private final int id;
    private String nome_produto;
    private double preco;
    private int estoque;
    
    //Atributo para serealizacao
    private static final long serialVersionUID = 1L;

    public Produto(String nome_produto, double preco, int estoque) {
        this.id = ++contador;
        this.nome_produto = nome_produto;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getId(){
        return id;
    }
    
    public String getnome_produto() {
        return nome_produto;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public abstract String getCategoria();
    
    public void imprimirDados() {
        System.out.println("Nome: " + nome_produto);
        System.out.println("Preço: R$ " + preco);
        System.out.println("Estoque: " + estoque);
    }
    
    //Quando houver compra de produtos diminui estoque
    public void baixarEstoque(int quantidade) throws EstoqueInsuficienteException {
        if (quantidade > estoque) {
            throw new EstoqueInsuficienteException(
                "Estoque insuficiente para '" + nome_produto
                + "'. Disponível: " + estoque);
        }
        estoque -= quantidade;
    }

}

