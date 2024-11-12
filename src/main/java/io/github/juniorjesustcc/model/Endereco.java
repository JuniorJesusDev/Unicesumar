package io.github.juniorjesustcc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String nomeSindico;
    private String telefoneSindico;
    private LocalDate ultimoAtendimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNomeSindico() {
        return nomeSindico;
    }

    public void setNomeSindico(String nomeSindico) {
        this.nomeSindico = nomeSindico;
    }

    public String getTelefoneSindico() {
        return telefoneSindico;
    }

    public void setTelefoneSindico(String telefoneSindico) {
        this.telefoneSindico = telefoneSindico;
    }

    public LocalDate getUltimoAtendimento() {
        return ultimoAtendimento;
    }

    public void setUltimoAtendimento(LocalDate ultimoAtendimento) {
        this.ultimoAtendimento = ultimoAtendimento;
    }
}
