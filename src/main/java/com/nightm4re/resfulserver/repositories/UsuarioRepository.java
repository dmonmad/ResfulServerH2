/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.resfulserver.repositories;

import com.nightm4re.resfulserver.model.Usuario;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nightm4re
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    @Query(
    value="SELECT * FROM usuario AS i WHERE i.estado LIKE %?1%",
            nativeQuery=true)
    public Set<Usuario> getByEstado(String estado);
    
}
