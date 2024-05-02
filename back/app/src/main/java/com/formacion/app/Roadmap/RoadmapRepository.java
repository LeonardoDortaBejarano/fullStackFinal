package com.formacion.app.Roadmap;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;


@Repository
public interface RoadmapRepository extends JpaRepository<Roadmap,Integer>{
    List<Roadmap> findByUserId(Integer userId);

    
} 