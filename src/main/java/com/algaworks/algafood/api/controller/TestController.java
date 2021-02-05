package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
public class TestController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping(value = "/cozinhas/por")
	public List<Cozinha> todos(String nome) {
		return cozinhaRepository.findByNomeContaining(nome);
	}

	@GetMapping(value = "/cozinhas/existe")
	public boolean exists(String nome) {
		return cozinhaRepository.existsByNome(nome);
	}

	@GetMapping(value = "/cozinhas/taxa")
	public List<Restaurante> taxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	@GetMapping(value = "/cozinhas/nomec")
	public List<Restaurante> nomeCozinha(String nome, Long cozinhaId) {
		return restauranteRepository.consultarNome(nome, cozinhaId);
	}

	@GetMapping(value = "/cozinhas/primeiro")
	public Optional<Restaurante> primeiroNome(String nome) {
		return restauranteRepository.findFirstByNomeContaining(nome);
	}

	@GetMapping(value = "/cozinhas/top")
	public List<Restaurante> topCozinha(String nome) {
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}

	@GetMapping(value = "/cozinhas/count")
	public int countCozinha(Long cozinhaId) {
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}

	@GetMapping(value = "/cozinhas/taxan")
	public List<Restaurante> taxaFreteAndNome(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.find(nome, taxaInicial, taxaFinal);
	}

	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome) {
		return restauranteRepository.findComFreteGratis(nome);
	}

}
