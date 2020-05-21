/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.resfulserver.services;

/**
 *
 * @author Nightm4re
 */
import com.nightm4re.resfulserver.exceptions.RecordNotFoundException;
import com.nightm4re.resfulserver.model.Puntuacion;
import com.nightm4re.resfulserver.repositories.PuntuacionRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PuntuacionService {
     
    @Autowired
    PuntuacionRepository repository;
     
    public Set<Puntuacion> getAllPuntuacions()
    {
        Set<Puntuacion> puntuacionList = repository.findAll().stream().collect(Collectors.toSet());
         
        if(puntuacionList.size() > 0) {
            return puntuacionList;
        } else {
            Set<Puntuacion> s = new ArrayList<Puntuacion>().stream().collect(Collectors.toSet());
            return s;
        }
    }
     
    public Puntuacion getPuntuacionById(Long id) throws RecordNotFoundException
    {
        Optional<Puntuacion> puntuacion = repository.findById(id);
         
        if(puntuacion.isPresent()) {
            return puntuacion.get();
        } else {
            throw new RecordNotFoundException("No puntuacion record exist for given id",id);
        }
    }
    public Puntuacion createPuntuacion(Puntuacion entity){
        entity = repository.save(entity);
        return entity;
    }
    public Puntuacion updatePuntuacion(Puntuacion entity) throws RecordNotFoundException
    {
    	    	
    	if(entity.getId()!=null)
    	{
    	  Optional<Puntuacion> puntuacion = repository.findById(entity.getId());
        
            if(puntuacion.isPresent())
            {
                Puntuacion newEntity = puntuacion.get();
                //newEntity.setId(entity.getId());
                newEntity.setCantidad(entity.getCantidad());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Puntuacion not found",entity.getId());
            }
        }else{
    		throw new RecordNotFoundException("No id of puntuacion given",0l);
    	}	    
 }
     
    public void deletePuntuacionById(Long id) throws RecordNotFoundException
    {
        Optional<Puntuacion> puntuacion = repository.findById(id);
         
        if(puntuacion.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No puntuacion record exist for given id",id);
        }
    }

    public Set<Puntuacion> getPuntuacionByCantidad(int cantidad) {
        Set<Puntuacion> puntuacionList = repository.getByCantidad(cantidad);
         
        if(puntuacionList.size() > 0) {
            return puntuacionList;
        } else {
            Set<Puntuacion> s = new ArrayList<Puntuacion>().stream().collect(Collectors.toSet());
            return s;
        }
    }
}
