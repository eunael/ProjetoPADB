/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author natan
 */
public class Carro {
    
    private String placa;
    private String cor;
    private String tipo;
    private String marca;
    private long cpf_dono;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public long getCpf_dono() {
        return cpf_dono;
    }

    public void setCpf_dono(long cpf_dono) {
        this.cpf_dono = cpf_dono;
    }
    
    @Override
    public String toString(){
        return getPlaca();
    }
}
