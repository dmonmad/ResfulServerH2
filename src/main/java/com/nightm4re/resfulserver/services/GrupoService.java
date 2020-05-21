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
import com.nightm4re.resfulserver.model.Grupo;
import com.nightm4re.resfulserver.repositories.GrupoRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrupoService {
     
    @Autowired
    GrupoRepository repository;
     
    public Set<Grupo> getAllGrupos()
    {
        Set<Grupo> grupoList = repository.findAll().stream().collect(Collectors.toSet());
         
        if(grupoList.size() > 0) {
            return grupoList;
        } else {
            Set<Grupo> s = new ArrayList<Grupo>().stream().collect(Collectors.toSet());
            return s;
        }
    }
     
    public Grupo getGrupoById(Long id) throws RecordNotFoundException
    {
        Optional<Grupo> grupo = repository.findById(id);
         
        if(grupo.isPresent()) {
            return grupo.get();
        } else {
            throw new RecordNotFoundException("No grupo record exist for given id",id);
        }
    }
    public Grupo createGrupo(Grupo entity){
        entity = repository.save(entity);
        return entity;
    }
    public Grupo updateGrupo(Grupo entity) throws RecordNotFoundException
    {
    	    	
    	if(entity.getId()!=null)
    	{
    	  Optional<Grupo> grupo = repository.findById(entity.getId());
        
            if(grupo.isPresent())
            {
                Grupo newEntity = grupo.get();
                //newEntity.setId(entity.getId());
                newEntity.setNombre(entity.getNombre());
                newEntity.setUsuarios(entity.getUsuarios());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Grupo not found",entity.getId());
            }
        }else{
    		throw new RecordNotFoundException("No id of grupo given",0l);
    	}	    
 }
     
    public void deleteGrupoById(Long id) throws RecordNotFoundException
    {
        Optional<Grupo> grupo = repository.findById(id);
         
        if(grupo.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No grupo record exist for given id",id);
        }
    }

    public Set<Grupo> getGrupoByNombre(String nombre) {
        Set<Grupo> grupoList = repository.getByNombre(nombre);
         
        if(grupoList.size() > 0) {
            return grupoList;
        } else {
            Set<Grupo> s = new ArrayList<Grupo>().stream().collect(Collectors.toSet());
            return s;
        }
    }
}
