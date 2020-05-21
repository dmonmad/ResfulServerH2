/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.resfulserver.repositories;

import com.nightm4re.resfulserver.model.Puntuacion;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nightm4re
 */
@Repository
public interface PuntuacionRepository extends JpaRepository<Puntuacion, Long> {
    
    @Query(
    value="SELECT * FROM puntuacion AS i WHERE i.cantidad LIKE %?1%",
            nativeQuery=true)
    public Set<Puntuacion> getByCantidad(int nick);
    
}
