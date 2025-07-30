package com.matheusmendes.rastreamento.model;

public enum VehicleState {
    EM_ROTA("Em Rota"),
    ATRASADO("Atrasado"),
    CONCLUIDO("Concluído");

    private final String descricao;

    VehicleState(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
