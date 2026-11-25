/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.gabriel.ecommerce.repositorio;

/**
 *
 * @author gabriel-almeida
 */

import br.com.gabriel.ecommerce.dominio.Carrinho;
import br.com.gabriel.ecommerce.dominio.Pedido;
import java.io.*; 
import java.util.ArrayList;

public class Persistencia {

    private static final String ARQ_CARRINHO = "carrinho.ser";
    private static final String ARQ_PEDIDOS = "pedidos.ser";

    public static void salvarCarrinho(Carrinho carrinho) {
        salvar(carrinho, ARQ_CARRINHO);
    }

    public static Carrinho carregarCarrinho() {
        Object obj = carregar(ARQ_CARRINHO);
        return (obj instanceof Carrinho) ? (Carrinho) obj : new Carrinho();
    }

    public static void salvarPedidos(ArrayList<Pedido> pedidos) {
        salvar(pedidos, ARQ_PEDIDOS);
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Pedido> carregarPedidos() {
        Object obj = carregar(ARQ_PEDIDOS);
        return (obj instanceof ArrayList) ? (ArrayList<Pedido>) obj : new ArrayList<>();
    }

    private static void salvar(Object objeto, String arquivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivo))) {
            out.writeObject(objeto);
        } catch (IOException e) {
            System.err.println("Erro ao salvar '" + arquivo + "': " + e.getMessage());
        }
    }

    private static Object carregar(String arquivo) {
        File f = new File(arquivo);
        if (!f.exists()) return null; // primeira execução: nada salvo ainda

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar '" + arquivo + "': " + e.getMessage());
            return null;
        }
    }
}
