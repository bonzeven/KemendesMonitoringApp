package com.bchannel.kemmon.service.impl;

import com.bchannel.kemmon.service.DokumenService;
import com.bchannel.kemmon.domain.Dokumen;
import com.bchannel.kemmon.repository.DokumenRepository;
import com.bchannel.kemmon.repository.search.DokumenSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Dokumen.
 */
@Service
@Transactional
public class DokumenServiceImpl implements DokumenService{

    private final Logger log = LoggerFactory.getLogger(DokumenServiceImpl.class);
    
    @Inject
    private DokumenRepository dokumenRepository;

    @Inject
    private DokumenSearchRepository dokumenSearchRepository;

    /**
     * Save a dokumen.
     *
     * @param dokumen the entity to save
     * @return the persisted entity
     */
    public Dokumen save(Dokumen dokumen) {
        log.debug("Request to save Dokumen : {}", dokumen);
        Dokumen result = dokumenRepository.save(dokumen);
        dokumenSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the dokumen.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Dokumen> findAll(Pageable pageable) {
        log.debug("Request to get all Dokumen");
        Page<Dokumen> result = dokumenRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one dokumen by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Dokumen findOne(Long id) {
        log.debug("Request to get Dokumen : {}", id);
        Dokumen dokumen = dokumenRepository.findOne(id);
        return dokumen;
    }

    /**
     *  Delete the  dokumen by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Dokumen : {}", id);
        dokumenRepository.delete(id);
        dokumenSearchRepository.delete(id);
    }

    /**
     * Search for the dokumen corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Dokumen> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Dokumen for query {}", query);
        Page<Dokumen> result = dokumenSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
