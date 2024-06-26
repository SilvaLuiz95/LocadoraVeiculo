package LocadoraVeiculo.entity;

public class Modelo extends Fabricante {

    private int id;
    private String nome;
    private Fabricante fabricante;

    public Modelo() {
    }

    public Modelo(int id) {
        this.id = id;
    }

    public Modelo(String nome) {
        this.nome = nome;
    }

    public Modelo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {
        return nome;
    }

}
