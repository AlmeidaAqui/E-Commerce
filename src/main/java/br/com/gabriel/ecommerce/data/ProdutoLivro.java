/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.gabriel.ecommerce.data;

/**
 *
 * @author gabriel-almeida
 */

public class ProdutoLivro extends Produto {

    private String autor;
    private String isbn;
    private String editora;
    private int numeroPaginas;

    public ProdutoLivro(
            String nome,
            double preco,
            int estoque,
            String autor,
            String isbn,
            String editora,
            int numeroPaginas) {

        super(nome, preco, estoque);

        this.autor = autor;
        this.isbn = isbn;
        this.editora = editora;
        this.numeroPaginas = numeroPaginas;
    }

    public String getAutor() {
        return autor;
    }

    public String getISBN() {
        return isbn;
    }

    public String getEditora() {
        return editora;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    @Override
    public String getCategoria() {
        return "Livro";
    }
    
    @Override
    public void imprimirDados() {
        super.imprimirDados();
        System.out.println("Autor: " + autor);
        System.out.println("Editora: " + editora);
        //Pulando linha no terminal
        System.out.println();
    }
}