/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensup.gestionscolairespringboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.ensup.gestionscolairespringboot.domaine.Cours;

/**
 *
 * @author lorris
 */
public interface CoursRepository extends JpaRepository<Cours, Integer>{
    
}
