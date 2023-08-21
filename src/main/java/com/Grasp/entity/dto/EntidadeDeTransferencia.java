package com.Grasp.entity.dto;

import java.util.List;

import com.Grasp.entity.Item;

public record EntidadeDeTransferencia(Integer capacidade, Integer numeroDeItens, List<Item> itens) {

}
