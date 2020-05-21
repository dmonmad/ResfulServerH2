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
 
import com.nightm4re.resfulserver.model.Usuario;
import com.nightm4re.resfulserver.exceptions.RecordNotFoundException;
import com.nightm4re.resfulserver.services.UsuarioService;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
 
@RestController
@RequestMapping("/usuario")
public class UsuarioServiceController
{
    @Autowired
    UsuarioService service;
 
    @GetMapping
    public ResponseEntity<Set<Usuario>> getAllUsuarios() {
        Set<Usuario> list = service.getAllUsuarios();
 
        return new ResponseEntity<Set<Usuario>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
    	Usuario entity = service.getUsuarioById(id);
 
        return new ResponseEntity<Usuario>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/search/{estado}")
    public ResponseEntity<Set<Usuario>> getUsuariosByTitle(@PathVariable("estado") String estado) {
    	Set<Usuario> list = service.getUsuariosByEstado(estado);
 
        return new ResponseEntity<Set<Usuario>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
   @PostMapping
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario myUsuario){
    	Usuario created = service.createUsuario(myUsuario);
        return new ResponseEntity<Usuario>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<Usuario> UpdateUsuario(@Valid @RequestBody Usuario myUsuario)
                                                    throws RecordNotFoundException {
    	Usuario updated = service.updateUsuario(myUsuario);
        return new ResponseEntity<Usuario>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteUsuarioById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteUsuarioById(id);
        return HttpStatus.ACCEPTED;
    }
 
}
