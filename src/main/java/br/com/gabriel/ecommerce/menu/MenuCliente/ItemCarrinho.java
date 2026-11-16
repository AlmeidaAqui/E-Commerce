/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.gabriel.ecommerce.menu.MenuCliente;
import br.com.gabriel.ecommerce.data.Produto;

/**
 *
 * @author gabriel-almeida
 */
public class ItemCarrinho {

    private Produto produto;
    private int quantidade;

    public ItemCarrinho(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
    
    public int getQuantidade(){
        return quantidade;
    }
    
    public Produto getProduto(){
        return this.produto;
    }
    
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }
}
