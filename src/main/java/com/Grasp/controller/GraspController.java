package com.Grasp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Grasp.entity.Graph;
import com.Grasp.entity.dto.EntidadeDeTransferencia;

import com.Grasp.entity.Item;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/teste")
public class GraspController {
	
	@GetMapping
	public Graph test() {
		Graph test = new Graph("teste Graph");
		
		return test;
	}
	
	@PostMapping
	public List<Item> createGraph(@RequestBody EntidadeDeTransferencia entidadeDeTransferencia) {
		
		int iterations = 10000;

        List<Item> bestSolution = graspMochila(entidadeDeTransferencia.itens(), entidadeDeTransferencia.capacidade(), iterations);

        int totalValue = 0;
        int totalWeight = 0;
        for (Item item : bestSolution) {
            totalValue += item.getValor();
            totalWeight += item.getPeso();
        }
		
		return bestSolution;
	}
	
	public static List<Item> graspMochila(List<Item> items, int maxWeight, int iterations) {
        List<Item> bestSolution = new ArrayList<>();
        int bestValue = 0;

        for (int i = 0; i < iterations; i++) {
            List<Item> solution = greedyRandomizedConstruction(items, maxWeight);
            int solutionValue = calculateTotalValue(solution);

            if (solutionValue > bestValue && calculateTotalWeight(solution) <= maxWeight) {
                bestSolution = new ArrayList<>(solution);
                bestValue = solutionValue;
            }
        }

        return bestSolution;
    }

    public static List<Item> greedyRandomizedConstruction(List<Item> items, int maxWeight) {
        List<Item> solution = new ArrayList<>();
        List<Item> remainingItems = new ArrayList<>(items);

        while (!remainingItems.isEmpty()) {
            double alpha = 0.5; // Adjustable parameter
            double randomValue = Math.random();

            List<Item> candidateList = new ArrayList<>();
            for (Item item : remainingItems) {
                if ((double) item.getValor() / item.getPeso() >= alpha) {
                    candidateList.add(item);
                }
            }

            if (candidateList.isEmpty()) {
                break;
            }

            int randomIndex = (int) (Math.random() * candidateList.size());
            Item selected = candidateList.get(randomIndex);
            
            if (calculateTotalWeight(solution) + selected.getPeso() > maxWeight) {
            	break;
            }
            
            solution.add(selected);
            
            remainingItems.remove(selected);
        }
        
        return solution;
    }

    public static int calculateTotalValue(List<Item> items) {
        int totalValue = 0;
        for (Item item : items) {
            totalValue += item.getValor();
        }
        return totalValue;
    }

    public static int calculateTotalWeight(List<Item> items) {
        int totalWeight = 0;
        for (Item item : items) {
            totalWeight += item.getPeso();
        }
        return totalWeight;
    
	}

}
