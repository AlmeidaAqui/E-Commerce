/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.gabriel.ecommerce;

import br.com.gabriel.ecommerce.persistencia.Persistencia;
import br.com.gabriel.ecommerce.persistencia.CarregaDado;
import br.com.gabriel.ecommerce.menu.menuCliente.BuscaProduto;
import br.com.gabriel.ecommerce.menu.Entrada;
import br.com.gabriel.ecommerce.dominio.Pedido;
import br.com.gabriel.ecommerce.dominio.Produto;
import br.com.gabriel.ecommerce.dominio.Carrinho;
import br.com.gabriel.ecommerce.excecoes.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        CarregaDado executa = new CarregaDado();
        ArrayList<Produto> cat = executa.retornaCatalogo();

        // Carrega o carrinho e pedidos salvos (ou cria vazios)
        Carrinho carrinho = Persistencia.carregarCarrinho();
        ArrayList<Pedido> pedidos = Persistencia.carregarPedidos();

        int descartados = carrinho.reconciliar(cat);
        if (descartados > 0) {
            System.out.println(descartados + " item(ns) do carrinho salvo não estão mais disponíveis e foram removidos.");
        }

        int opcao = 1;
        while (opcao != 0) {
            System.out.println("\n===== E-COMMERCE =====");
            System.out.println("1. Listar produtos");
            System.out.println("2. Buscar produto");
            System.out.println("3. Ver carrinho");
            System.out.println("4. Remover item");
            System.out.println("5. Finalizar pedido");
            System.out.println("6. Consultar pedidos");
            System.out.println("0. Sair");

            opcao = Entrada.LerInteiro();

            switch (opcao) {
                case 0:
                    Persistencia.salvarCarrinho(carrinho);
                    System.out.println("Até logo!");
                    break;

                case 1:
                    int n = 0;
                    for (Produto p : cat) {
                        n++;
                        System.out.println(n + ". " + p.getnome_produto());
                    }
                    break;

                case 2:
                    buscarEAdicionar(cat, carrinho);
                    break;

                case 3:
                    carrinho.listarProdutos();
                    break;

                case 4:
                    removerItem(carrinho);
                    break;

                case 5:
                    finalizarPedido(carrinho, pedidos);
                    break;

                case 6:
                    if (pedidos.isEmpty()) {
                        System.out.println("Nenhum pedido realizado.");
                    } else {
                        for (Pedido p : pedidos) p.imprimir();
                    }
                    break;

                default:
                    System.out.println("\nOpcao indisponivel\n");
            }
        }
    }

    private static void buscarEAdicionar(ArrayList<Produto> cat, Carrinho carrinho) {
        System.out.println("Digite o produto a ser procurado: ");
        String palavra = Entrada.lerTexto();

        BuscaProduto busca = new BuscaProduto();
        ArrayList<Produto> encontrados = busca.buscar(cat, palavra);

        if (encontrados.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
            return;
        }

        // Lista numerada
        int n = 0;
        for (Produto p : encontrados) {
            n++;
            System.out.printf("%d. %s - R$ %.2f%n", n, p.getnome_produto(), p.getPreco());
        }

        System.out.print("Deseja inserir algum item no carrinho? [S/n]: ");
        char escolha = Entrada.Lerchar();

        if (escolha == 'N' || escolha == 'n') return;
        if (escolha != 'S' && escolha != 's') {
            System.out.println("Opcao invalida!");
            return;
        }

        System.out.print("Escolha a enumeracao do produto: ");
        int indice = Entrada.LerInteiro();

        if (indice < 1 || indice > encontrados.size()) {
            System.out.println("Opcao invalida, quantidade de itens: " + encontrados.size());
            return;
        }

        Produto escolhido = encontrados.get(indice - 1);
        escolhido.imprimirDados();   // detalhes só do produto escolhido

        System.out.print("Quantidade: ");
        int qtd = Entrada.LerInteiro();

        try {
            carrinho.adicionar(escolhido, qtd);
            Persistencia.salvarCarrinho(carrinho);
            System.out.println("Produto inserido no carrinho!");
        } catch (EstoqueInsuficienteException | IllegalArgumentException e) {
            System.out.println("Não foi possível adicionar: " + e.getMessage());
        }
    }

    private static void removerItem(Carrinho carrinho) {
        if (carrinho.estaVazio()) {
            System.out.println("Carrinho vazio.");
            return;
        }
        carrinho.listarProdutos();
        System.out.print("Número do item a remover: ");
        int pos = Entrada.LerInteiro();

        try {
            carrinho.remover(pos);
            Persistencia.salvarCarrinho(carrinho);
            System.out.println("Item removido!");
        } catch (ItemNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void finalizarPedido(Carrinho carrinho, ArrayList<Pedido> pedidos) {
        try {
            carrinho.listarProdutos();
            System.out.print("Confirmar pedido? [S/n]: ");
            char c = Entrada.Lerchar();
            if (c != 'S' && c != 's') {
                System.out.println("Pedido cancelado.");
                return;
            }

            Pedido pedido = Pedido.finalizar(pedidos.size() + 1, carrinho);
            pedidos.add(pedido);
            carrinho.limpar();

            Persistencia.salvarPedidos(pedidos);
            Persistencia.salvarCarrinho(carrinho);

            System.out.println("Pedido finalizado com sucesso!");
            pedido.imprimir();
        } catch (CarrinhoVazioException | EstoqueInsuficienteException e) {
            System.out.println("Não foi possível finalizar: " + e.getMessage());
        }
    }
}









