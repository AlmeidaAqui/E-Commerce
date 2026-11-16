/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.gabriel.ecommerce.data;

/**
 *
 * @author gabriel-almeida
 */

public class ProdutoEletronico extends Produto {

    private int garantia_meses;
    private String marca;
    private int voltagemV;
    private String cor;

    public ProdutoEletronico(
            String nome_produto,
            double preco,
            int estoque,
            int garantia_meses,
            String marca,
            int voltagemV,
            String cor) {

        super(nome_produto, preco, estoque);

        this.garantia_meses = garantia_meses;
        this.marca = marca;
        this.voltagemV = voltagemV;
        this.cor = cor;
    }

    public int getGarantia_meses() {
        return garantia_meses;
    }

    public String getMarca() {
        return marca;
    }

    public int getVoltagemV() {
        return voltagemV;
    }

    public String getCor() {
        return cor;
    }

    @Override
    public String getCategoria() {
        return "Eletronico";
    }
    
    @Override
    public void imprimirDados() {
        super.imprimirDados();
        System.out.println("Marca " + marca);
        System.out.println("Garantia em meses: " + garantia_meses);
        System.out.println("Cor: " + cor);
        //Pulando linha no terminal
        System.out.println();
    }
}