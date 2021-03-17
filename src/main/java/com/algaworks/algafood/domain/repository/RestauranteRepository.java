package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository
		extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

	@Query("from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento")
	List<Restaurante> findAll();
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

	Optional<Restaurante> findFirstByNomeContaining(String nome);

	List<Restaurante> findTop2ByNomeContaining(String nome);

	int countByCozinhaId(Long cozinhaId);

	@Query("FROM Restaurante WHERE nome LIKE %:nome% AND cozinha.id = :id")
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);

	List<Restaurante> consultarNome(String nome, @Param("id") Long cozinha);

}
