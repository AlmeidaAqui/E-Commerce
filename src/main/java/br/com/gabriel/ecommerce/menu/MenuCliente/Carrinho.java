/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.gabriel.ecommerce.menu.MenuCliente;
import br.com.gabriel.ecommerce.data.Produto;
import java.util.HashMap;

/**
 *
 * @author gabriel-almeida
 */
public class Carrinho{
    private HashMap<Integer, ItemCarrinho> itens;

    public Carrinho() {
        itens = new HashMap<>();
    }

    public void adicionarProduto(Produto produto) {

        int id = produto.getId();

        //Se o produto ja foi inserido antes
        if (itens.containsKey(id)) {
            ItemCarrinho item = itens.get(id);
            item.setQuantidade(item.getQuantidade()+1);
        } else {
            itens.put(id, new ItemCarrinho(produto, 1));
        }
    }

    public void removerProduto(int id) {
        itens.remove(id);
    }

    public void listarProdutos() {
        if (itens.isEmpty()){
            System.out.println("Carrinho vazio!");
        }
        else{   
            int n = 0;

            for (ItemCarrinho item : itens.values()) {
                n++;

                System.out.println(n + ". " +
                    item.getProduto().getnome_produto());

                System.out.println("  Preço: R$ " +
                    item.getProduto().getPreco());

                System.out.println("  Quantidade: " +
                    item.getQuantidade());
            }
        }
    }
    
}


    