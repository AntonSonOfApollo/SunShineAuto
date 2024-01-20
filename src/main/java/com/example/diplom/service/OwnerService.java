package com.example.diplom.service;

import com.example.diplom.entity.Owner;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface OwnerService {

    Iterable<Owner> findAll();

    Optional<Owner> findById(Integer id);

    Optional<Owner> save(Owner owner);

    Optional<Owner> deleteById(Integer id);

    Optional<Owner> update(Owner owner);

}
