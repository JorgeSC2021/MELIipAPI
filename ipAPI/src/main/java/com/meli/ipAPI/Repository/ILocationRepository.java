package com.meli.ipAPI.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meli.ipAPI.Entity.Location;
import com.meli.ipAPI.DTO.*;

@Repository
public interface ILocationRepository extends JpaRepository<Location, Long>{
    
	@Query(value = "SELECT l FROM Location l ORDER BY distancia DESC LIMIT 1")
	Optional<Location> findCityMaxDistance();
}