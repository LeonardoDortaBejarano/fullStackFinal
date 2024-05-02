package com.formacion.app.Roadmap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;


@Repository
public interface RoadmapRepository extends JpaRepository<Roadmap,Integer>{

    
} 