/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.gabriel.ecommerce.menu.MenuCliente;
import java.util.ArrayList;
import br.com.gabriel.ecommerce.data.Produto;

/**
 *
 * @author gabriel-almeida
 */

public class BuscaProduto {

    public ArrayList<Produto> buscar(ArrayList<Produto> catalogo, String chave) {
        boolean encontrado = false;
        
        ArrayList<Produto> encontrados = new ArrayList<>();

        for (Produto produto : catalogo) {
            /*Compara a palavra buscada com o nome do produto
              As letras de ambas as palavras são comparadas em minusculo
            */        
            if ((produto.getnome_produto().toLowerCase()).contains(chave.toLowerCase())) {
                produto.imprimirDados();
                encontrado = true;
                
                encontrados.add(produto);
            }
        }

        if (!encontrado) {
            System.out.println("Produto não encontrado!");
        }
        
        return encontrados;
    }
}
