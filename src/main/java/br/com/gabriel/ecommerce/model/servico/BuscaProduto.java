/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.gabriel.ecommerce.servico;

/**
 *
 * @author gabriel-almeida
 */

import java.util.ArrayList;
import br.com.gabriel.ecommerce.dominio.Produto;

public class BuscaProduto {

    public ArrayList<Produto> buscar(ArrayList<Produto> catalogo, String chave) {
        ArrayList<Produto> encontrados = new ArrayList<>();
        String termo = chave.trim().toLowerCase();

        for (Produto produto : catalogo) {
            if (produto.getnome_produto().toLowerCase().contains(termo)) {
                encontrados.add(produto);
            }
        }
        return encontrados;
    }
}
