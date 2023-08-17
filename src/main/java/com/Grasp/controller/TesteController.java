package com.Grasp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Grasp.entity.Graph;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@GetMapping
	public Graph test() {
		Graph test = new Graph("teste Graph");
		
		return test;
	}
	
	@PostMapping
	public Graph createGraph(@RequestBody Graph graph) {
		Graph newGraph = new Graph(graph.getNome());
		
		return newGraph;
	}
	
	

}
