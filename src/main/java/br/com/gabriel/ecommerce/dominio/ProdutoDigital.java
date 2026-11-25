/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.gabriel.ecommerce.dominio;

/**
 *
 * @author gabriel-almeida
 */

public class ProdutoDigital extends Produto {

    private String link_download;
    private int tamanho_arq;
    private String formato;

    public ProdutoDigital(
            String nome_produto,
            double preco,
            int estoque,
            String link_download,
            int tamanho_arq,
            String formato) {

        super(nome_produto, preco, estoque);

        this.link_download = link_download;
        this.tamanho_arq = tamanho_arq;
        this.formato = formato;
    }

    public String getLink_download() {
        return link_download;
    }

    public int getTamanho_arq() {
        return tamanho_arq;
    }

    public String getFormato() {
        return formato;
    }

    @Override
    public String getCategoria() {
        return "Produto digital";
    }
    
    @Override
    public void imprimirDados() {
        super.imprimirDados();
        System.out.println("Link: " + link_download);
        System.out.println("Tamanho: " + tamanho_arq);
        //Pulando linha no terminal
        System.out.println();
    }
}
