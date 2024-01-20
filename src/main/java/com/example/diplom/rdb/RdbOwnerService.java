package com.example.diplom.rdb;

import com.example.diplom.entity.Owner;
import com.example.diplom.rdb.repository.OwnerRepository;
import com.example.diplom.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RdbOwnerService implements OwnerService {

    private final OwnerRepository ownerRepository;

    public RdbOwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Iterable<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Optional<Owner> findById(Integer id) {
        return ownerRepository.findById(id);
    }

    @Override
    public Optional<Owner> save(Owner owner) {
        return Optional.of(ownerRepository.save(owner));
    }

    @Override
    public Optional<Owner> deleteById(Integer id) {
        Optional<Owner> deleted = findById(id);
        if (deleted.isPresent()) {
            ownerRepository.deleteById(id);
        }
        return deleted;
    }

    @Override
    public Optional<Owner> update(Owner owner) {
        Optional<Owner> updated = findById(owner.getId());
        if (updated.isPresent()) {
            updated = Optional.of(ownerRepository.save(owner));
        }
        return updated;
    }
}
