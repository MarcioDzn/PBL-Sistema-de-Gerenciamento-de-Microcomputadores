package model;

public class Peca extends Componente {
    private String nome;
    private String fabricante;

    public Peca(double custo, double preco, String nome, String fabricante) {
        super(custo, preco);
        this.nome = nome;
        this.fabricante = fabricante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
}
