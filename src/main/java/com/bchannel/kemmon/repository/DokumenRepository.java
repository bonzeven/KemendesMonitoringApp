package com.bchannel.kemmon.repository;

import com.bchannel.kemmon.domain.Dokumen;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Dokumen entity.
 */
@SuppressWarnings("unused")
public interface DokumenRepository extends JpaRepository<Dokumen,Long> {

}
