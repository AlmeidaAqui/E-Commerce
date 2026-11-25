/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.gabriel.ecommerce.dominio;
import br.com.gabriel.ecommerce.dominio.Produto;
import br.com.gabriel.ecommerce.excecoes.EstoqueInsuficienteException;
import br.com.gabriel.ecommerce.excecoes.ItemNaoEncontradoException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author gabriel-almeida
 */

public class Carrinho implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<ItemCarrinho> itens = new ArrayList<>();

    public void adicionar(Produto produto, int quantidade)
            throws EstoqueInsuficienteException {

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        ItemCarrinho existente = buscarItem(produto);
        int jaNoCarrinho = (existente == null) ? 0 : existente.getQuantidade();

        if (jaNoCarrinho + quantidade > produto.getEstoque()) {
            throw new EstoqueInsuficienteException(
                "Estoque insuficiente para '" + produto.getnome_produto()
                + "'. Disponível: " + produto.getEstoque()
                + " | Já no carrinho: " + jaNoCarrinho);
        }

        if (existente != null) {
            existente.setQuantidade(jaNoCarrinho + quantidade);
        } else {
            itens.add(new ItemCarrinho(produto, quantidade));
        }
    }

    // posicao começa em 1 (como aparece na listagem)
    public void remover(int posicao) throws ItemNaoEncontradoException {
        if (posicao < 1 || posicao > itens.size()) {
            throw new ItemNaoEncontradoException(
                "Item " + posicao + " não existe no carrinho.");
        }
        itens.remove(posicao - 1);
    }

    public void listarProdutos() {
        if (itens.isEmpty()) {
            System.out.println("Carrinho vazio.");
            return;
        }
        int n = 0;
        for (ItemCarrinho item : itens) {
            n++;
            System.out.printf("%d. %s | %d x R$ %.2f = R$ %.2f%n",
                n, item.getProduto().getnome_produto(),
                item.getQuantidade(), item.getProduto().getPreco(),
                item.getSubtotal());
        }
        System.out.printf("TOTAL: R$ %.2f%n", getTotal());
    }

    public double getTotal() {
        double total = 0;
        for (ItemCarrinho item : itens) total += item.getSubtotal();
        return total;
    }

    public boolean estaVazio() { return itens.isEmpty(); }
    public List<ItemCarrinho> getItens() { return itens; }
    public void limpar() { itens.clear(); }

    /*
     * Após carregar do arquivo, os produtos do carrinho são cópias.
     * Aqui eles são trocados pelos objetos do catálogo atual (pelo nome),
     * garantindo preço/estoque atualizados. Retorna quantos itens foram descartados.
     */
    public int reconciliar(List<Produto> catalogo) {
        int descartados = 0;
        Iterator<ItemCarrinho> it = itens.iterator();
        while (it.hasNext()) {
            ItemCarrinho item = it.next();
            Produto atual = null;
            for (Produto p : catalogo) {
                if (p.getnome_produto().equalsIgnoreCase(item.getProduto().getnome_produto())) {
                    atual = p;
                    break;
                }
            }
            if (atual == null || atual.getEstoque() == 0) {
                it.remove();
                descartados++;
            } else {
                item.setProduto(atual);
                if (item.getQuantidade() > atual.getEstoque()) {
                    item.setQuantidade(atual.getEstoque());
                }
            }
        }
        return descartados;
    }

    private ItemCarrinho buscarItem(Produto produto) {
        for (ItemCarrinho item : itens) {
            if (item.getProduto() == produto) return item;
        }
        return null;
    }
}


    