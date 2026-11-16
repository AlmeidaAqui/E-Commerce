/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.gabriel.ecommerce;

import br.com.gabriel.ecommerce.dataLoader.*;
import br.com.gabriel.ecommerce.data.*;
import br.com.gabriel.ecommerce.menu.MenuCliente.*;
//import br.com.gabriel.ecommerce.Entrada;
import java.util.ArrayList;

/**
 *
 * @author gabriel-almeida
 */
public class ECommerce {

    public static void main(String[] args) {
        
        //Carregando os dados da loja:
        CarregaDado executa = new CarregaDado();
        ArrayList<Produto> cat = executa.retornaCatalogo();
        
        //Criando carrinho vazio do cliente
        Carrinho carrinho = new Carrinho();
        
        int opcao = 1;
        while(opcao != 0){
            System.out.println("===== E-COMMERCE =====");
            System.out.println("1. Listar produtos");
            System.out.println("2. Buscar produto");
            System.out.println("3. Ver carrinho");
            System.out.println("4. Remover item");
            System.out.println("5. Finalizar pedido");
            System.out.println("6. Consultar pedidos");
            System.out.println("0. Sair");
            
            opcao = Entrada.LerInteiro();
        
            switch(opcao){
            //Finaliza compra
                case 0:
                    opcao = 0;
                    break;
                    
            //Lista produtos
                case 1:
                    int n=0;
                    for (Produto atributo : cat) {
                        n++;
                        System.out.println(n + ". " + atributo.getnome_produto());
                    }
                    break;
                    
            //Busca produtos
                case 2:
                    System.out.println("Digite o protudo a ser procurado: ");
                    String palavra = Entrada.lerTexto();
                    
                    BuscaProduto busca = new BuscaProduto();
                    ArrayList<Produto> encontrados = busca.buscar(cat, palavra);
                    
                    if (encontrados.isEmpty()) {
                        System.out.println("Nenhum produto encontrado.");
                    }else{
                    
                        System.out.print("Deseja inserir algum item no carrinho? [S/n]: ");
                        char escolha = Entrada.Lerchar();

                        if (escolha=='S' || escolha=='s'){
                            System.out.print("Escolha a enumeracao do produto: ");
                            opcao = Entrada.LerInteiro();

                          //Inserindo no carrinho
                            if (opcao < 1 || opcao > encontrados.size()){
                                System.out.println(" Opcao invalida, quantidade de itens:" + encontrados.size());
                            }else{
                                encontrados.get(opcao-1).imprimirDados();
                                carrinho.add(encontrados.get(opcao-1));

                                System.out.println("Produto inserido no carrinho!");
                                try {
                            // Pausa a execução por 3000 milissegundos (3 segundos)
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    System.err.println("A pausa foi interrompida: " + e.getMessage());
                                    Thread.currentThread().interrupt(); // Restaura o status de interrupção
                                }

                            }

                        }else if (escolha == 'N' || escolha == 'n'){
                            break;
                        }else{
                            System.out.println("Opcao invalida!");
                        }
                    }
                    break;
                   
            //Visualiza carrinho
                case 3:
                    carrinho.listarProdutos();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    System.out.println("\nOpcao indisponivel\n");
            }
        }
        
        
        
    }
}









