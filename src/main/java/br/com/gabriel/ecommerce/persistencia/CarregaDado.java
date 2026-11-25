/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.gabriel.ecommerce.persistencia;
        
/**
 *
 * @author gabriel-almeida
 */

import br.com.gabriel.ecommerce.dominio.Produto;
import br.com.gabriel.ecommerce.dominio.ProdutoLivro;
import br.com.gabriel.ecommerce.dominio.ProdutoEletronico;
import br.com.gabriel.ecommerce.dominio.ProdutoDigital;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CarregaDado {
    
    public CarregaDado(){
        
    }

    public static final String NOME_ARQ =
            "/br/com/gabriel/ecommerce/data/produtos.txt";

    public ArrayList<Produto> retornaCatalogo() {

        ArrayList<Produto> catalogo = new ArrayList<>();

        InputStream arquivo =
                CarregaDado.class.getResourceAsStream(NOME_ARQ);

        if (arquivo == null) {
            System.out.println(
                    "Arquivo não encontrado: " + NOME_ARQ);
            System.out.println("Classpath raiz: " + CarregaDado.class.getResource("/"));
            return catalogo;
        }

        try (BufferedReader leitor = new BufferedReader(
                new InputStreamReader(
                        arquivo, StandardCharsets.UTF_8))) {

        
            Map<String, String> dados = new HashMap<>();
            String linha;

            while ((linha = leitor.readLine()) != null) {

                linha = linha.trim();

                // Linha vazia: finaliza o produto atual.
                if (linha.isEmpty()) {

                    if (!dados.isEmpty()) {
                        Produto produto = criarProduto(dados);

                        if (produto != null) {
                            catalogo.add(produto);
                        }

                        dados.clear();
                    }

                    continue;
                }

                // Separa o nome do campo de seu valor.
                int separador = linha.indexOf(':');

                if (separador == -1) {
                    System.out.println(
                            "Linha inválida: " + linha);
                    continue;
                }

                String chave = linha.substring(0, separador)
                                    .trim();

                String valor = linha.substring(separador + 1)
                                    .trim();

                dados.put(chave, valor);
            }

            // Processa o último produto, mesmo sem linha vazia no fim.
            if (!dados.isEmpty()) {
                Produto produto = criarProduto(dados);

                if (produto != null) {
                    catalogo.add(produto);
                }
            }

        } catch (IOException e) {
            System.out.println(
                    "Erro ao carregar catálogo: "
                    + e.getMessage());
        }

        return catalogo;
    }

    private Produto criarProduto(Map<String, String> dados) {

        try {
            String categoria = obter(dados, "Categoria");
            String nome = obter(dados, "Nome");

            double preco = converterPreco(
                    obter(dados, "Preco"));

            int estoque = Integer.parseInt(
                    obter(dados, "Estoque"));

            switch (categoria.toLowerCase()) {

                case "eletronico":

                    int garantia = converterInteiro(
                            obter(dados, "Garantia"));

                    String marca = obter(dados, "Marca");

                    int voltagem = converterInteiro(
                            obter(dados, "Voltagem"));

                    String cor = obter(dados, "Cor");

                    return new ProdutoEletronico(
                            nome,
                            preco,
                            estoque,
                            garantia,
                            marca,
                            voltagem,
                            cor
                    );

                case "livros":

                    String autor = obter(dados, "Autor");
                    String isbn = obter(dados, "ISBN");
                    String editora = obter(dados, "Editora");

                    int paginas = converterInteiro(
                            obter(dados, "N de paginas"));

                    return new ProdutoLivro(
                            nome,
                            preco,
                            estoque,
                            autor,
                            isbn,
                            editora,
                            paginas
                    );

                case "produtos digitais":

                    String link = obter(
                            dados, "Link de download");

                    int tamanho = converterInteiro(
                            obter(dados, "Tamanho do arquivo"));

                    String formato = obter(
                            dados, "Formato");

                    return new ProdutoDigital(
                            nome,
                            preco,
                            estoque,
                            link,
                            tamanho,
                            formato
                    );

                default:
                    System.out.println(
                            "Categoria desconhecida: "
                            + categoria);

                    return null;
            }

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Erro nos dados do produto: "
                    + e.getMessage());

            return null;
        }
    }

    private String obter(
            Map<String, String> dados, String chave) {

        String valor = dados.get(chave);

        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(
                    "Campo ausente ou vazio: " + chave);
        }

        return valor;
    }

    private double converterPreco(String valor) {

        String preco = valor.replace("R$", "")
                            .trim()
                            .replace(".", "")
                            .replace(",", ".");

        return Double.parseDouble(preco);
    }

    private int converterInteiro(String valor) {

        String numero = valor.replaceAll("[^0-9]", "");

        if (numero.isEmpty()) {
            throw new IllegalArgumentException(
                    "Número inválido: " + valor);
        }

        return Integer.parseInt(numero);
    }
}