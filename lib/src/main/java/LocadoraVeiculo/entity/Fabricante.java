package LocadoraVeiculo.entity;

public class Fabricante {

    private int id;
    private String nome;

    public Fabricante() {
    }

    public Fabricante(int id) {
        this.id = id;
    }
    
    public Fabricante(String nome) {
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

    @Override
    public String toString() {
        return nome;
    }
    
}
