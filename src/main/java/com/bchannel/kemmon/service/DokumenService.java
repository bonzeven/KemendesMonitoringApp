package com.bchannel.kemmon.service;

import com.bchannel.kemmon.domain.Dokumen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Dokumen.
 */
public interface DokumenService {

    /**
     * Save a dokumen.
     *
     * @param dokumen the entity to save
     * @return the persisted entity
     */
    Dokumen save(Dokumen dokumen);

    /**
     *  Get all the dokumen.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Dokumen> findAll(Pageable pageable);

    /**
     *  Get the "id" dokumen.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Dokumen findOne(Long id);

    /**
     *  Delete the "id" dokumen.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the dokumen corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Dokumen> search(String query, Pageable pageable);
}
