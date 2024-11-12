package io.github.juniorjesustcc.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    private LocalDate data;
    private String observacao;

    @Column(name = "status")
    private String status;

    @Column(name = "ultimo_usuario_alterou_status")
    private String ultimoUsuarioQueAlterouStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUltimoUsuarioQueAlterouStatus() {
        return ultimoUsuarioQueAlterouStatus;
    }

    public void setUltimoUsuarioQueAlterouStatus(String ultimoUsuarioQueAlterouStatus) {
        this.ultimoUsuarioQueAlterouStatus = ultimoUsuarioQueAlterouStatus;
    }

    public OrdemServico() {
        this.status = "aberto";
    }
}