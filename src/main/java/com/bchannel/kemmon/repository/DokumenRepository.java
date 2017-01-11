package com.bchannel.kemmon.repository;

import com.bchannel.kemmon.domain.Dokumen;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Dokumen entity.
 */
@SuppressWarnings("unused")
public interface DokumenRepository extends JpaRepository<Dokumen,Long> {
    //Page<Dokumen> findByLastprocess(String userType,Pageable pageable);

    Page<Dokumen> findAllByLastProcess(String userType, Pageable pageable);
}
