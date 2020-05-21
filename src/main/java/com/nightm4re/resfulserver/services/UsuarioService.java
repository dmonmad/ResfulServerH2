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
import com.nightm4re.resfulserver.model.Usuario;
import com.nightm4re.resfulserver.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
     
    @Autowired
    UsuarioRepository repository;
     
    public Set<Usuario> getAllUsuarios()
    {
        Set<Usuario> usuarioList = repository.findAll().stream().collect(Collectors.toSet());
         
        if(usuarioList.size() > 0) {
            return usuarioList;
        } else {
            Set<Usuario> s = new ArrayList<Usuario>().stream().collect(Collectors.toSet());
            return s;
        }
    }
     
    public Usuario getUsuarioById(Long id) throws RecordNotFoundException
    {
        Optional<Usuario> usuario = repository.findById(id);
         
        if(usuario.isPresent()) {
            return usuario.get();
        } else {
            throw new RecordNotFoundException("No usuario record exist for given id",id);
        }
    }
    public Usuario createUsuario(Usuario entity){
        entity = repository.save(entity);
        return entity;
    }
    public Usuario updateUsuario(Usuario entity) throws RecordNotFoundException
    {
    	    	
    	if(entity.getId()!=null)
    	{
    	  Optional<Usuario> usuario = repository.findById(entity.getId());
        
            if(usuario.isPresent())
            {
                Usuario newEntity = usuario.get();
                //newEntity.setId(entity.getId());
                newEntity.setEstado(entity.getEstado());
                newEntity.setGrupos(entity.getGrupos());
                newEntity.setPuntuaciones(entity.getPuntuaciones());
                newEntity.setEstado(entity.getEstado());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Usuario not found",entity.getId());
            }
        }else{
    		throw new RecordNotFoundException("No id of usuario given",0l);
    	}	    
 }
     
    public void deleteUsuarioById(Long id) throws RecordNotFoundException
    {
        Optional<Usuario> usuario = repository.findById(id);
         
        if(usuario.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No usuario record exist for given id",id);
        }
    }

    public Set<Usuario> getUsuariosByEstado(String nick) {
        Set<Usuario> usuarioList = repository.getByEstado(nick);
         
        if(usuarioList.size() > 0) {
            return usuarioList;
        } else {
            Set<Usuario> s = new ArrayList<Usuario>().stream().collect(Collectors.toSet());
            return s;
        }
    }
}
