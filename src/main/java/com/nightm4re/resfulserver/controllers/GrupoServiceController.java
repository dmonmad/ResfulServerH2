/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.resfulserver.controllers;

/**
 *
 * @author Nightm4re
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.nightm4re.resfulserver.model.Grupo;
import com.nightm4re.resfulserver.exceptions.RecordNotFoundException;
import com.nightm4re.resfulserver.services.GrupoService;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
 
@RestController
@RequestMapping("/grupo")
public class GrupoServiceController
{
    @Autowired
    GrupoService service;
 
    @GetMapping
    public ResponseEntity<Set<Grupo>> getAllGrupos() {
        Set<Grupo> list = service.getAllGrupos();
 
        return new ResponseEntity<Set<Grupo>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Grupo> getGrupoById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
    	Grupo entity = service.getGrupoById(id);
 
        return new ResponseEntity<Grupo>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/search/{nombre}")
    public ResponseEntity<Set<Grupo>> getGruposByTitle(@PathVariable("nombre") String nombre) {
    	Set<Grupo> list = service.getGrupoByNombre(nombre);
 
        return new ResponseEntity<Set<Grupo>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
   @PostMapping
    public ResponseEntity<Grupo> createGrupo(@Valid @RequestBody Grupo myGrupo){
    	Grupo created = service.createGrupo(myGrupo);
        return new ResponseEntity<Grupo>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<Grupo> UpdateGrupo(@Valid @RequestBody Grupo myGrupo)
                                                    throws RecordNotFoundException {
    	Grupo updated = service.updateGrupo(myGrupo);
        return new ResponseEntity<Grupo>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteGrupoById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteGrupoById(id);
        return HttpStatus.ACCEPTED;
    }
 
}
