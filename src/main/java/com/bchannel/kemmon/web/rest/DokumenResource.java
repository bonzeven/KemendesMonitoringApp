package com.bchannel.kemmon.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import com.bchannel.kemmon.domain.Dokumen;
import com.bchannel.kemmon.service.DokumenService;
import com.bchannel.kemmon.web.rest.util.HeaderUtil;
import com.bchannel.kemmon.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing Dokumen.
 */
@RestController
@RequestMapping("/api")
public class DokumenResource {

    private final Logger log = LoggerFactory.getLogger(DokumenResource.class);

    @Inject
    private DokumenService dokumenService;

    /**
     * POST  /dokumen : Create a new dokumen.
     *
     * @param dokumen the dokumen to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dokumen, or with status 400 (Bad Request) if the dokumen has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dokumen")
    @Timed
    public ResponseEntity<Dokumen> createDokumen(@RequestBody Dokumen dokumen) throws URISyntaxException {
        log.debug("REST request to save Dokumen : {}", dokumen);
        if (dokumen.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("dokumen", "idexists", "A new dokumen cannot already have an ID")).body(null);
        }
        Dokumen result = dokumenService.save(dokumen);
        return ResponseEntity.created(new URI("/api/dokumen/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("dokumen", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dokumen : Updates an existing dokumen.
     *
     * @param dokumen the dokumen to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dokumen,
     * or with status 400 (Bad Request) if the dokumen is not valid,
     * or with status 500 (Internal Server Error) if the dokumen couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dokumen")
    @Timed
    public ResponseEntity<Dokumen> updateDokumen(@RequestBody Dokumen dokumen) throws URISyntaxException {
        log.debug("REST request to update Dokumen : {}", dokumen);
        if (dokumen.getId() == null) {
            return createDokumen(dokumen);
        }
        Dokumen result = dokumenService.save(dokumen);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("dokumen", dokumen.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dokumen : get all the dokumen.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dokumen in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/dokumen")
    @Timed
    public ResponseEntity<List<Dokumen>> getAllDokumen(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Dokumen");
        Page<Dokumen> page = dokumenService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dokumen");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /dokumen/:id : get the "id" dokumen.
     *
     * @param id the id of the dokumen to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dokumen, or with status 404 (Not Found)
     */
    @GetMapping("/dokumen/{id}")
    @Timed
    public ResponseEntity<Dokumen> getDokumen(@PathVariable Long id) {
        log.debug("REST request to get Dokumen : {}", id);
        Dokumen dokumen = dokumenService.findOne(id);
        return Optional.ofNullable(dokumen)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /dokumen/:id : delete the "id" dokumen.
     *
     * @param id the id of the dokumen to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dokumen/{id}")
    @Timed
    public ResponseEntity<Void> deleteDokumen(@PathVariable Long id) {
        log.debug("REST request to delete Dokumen : {}", id);
        dokumenService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("dokumen", id.toString())).build();
    }

    /**
     * SEARCH  /_search/dokumen?query=:query : search for the dokumen corresponding
     * to the query.
     *
     * @param query the query of the dokumen search
     * @param pageable the pagination information
     * @return the result of the search
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/_search/dokumen")
    @Timed
    public ResponseEntity<List<Dokumen>> searchDokumen(@RequestParam String query, @ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to search for a page of Dokumen for query {}", query);
        Page<Dokumen> page = dokumenService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/dokumen");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
