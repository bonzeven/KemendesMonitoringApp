package com.bchannel.kemmon.web.rest;

import com.bchannel.kemmon.KemmonApp;

import com.bchannel.kemmon.domain.Dokumen;
import com.bchannel.kemmon.repository.DokumenRepository;
import com.bchannel.kemmon.service.DokumenService;
import com.bchannel.kemmon.repository.search.DokumenSearchRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DokumenResource REST controller.
 *
 * @see DokumenResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KemmonApp.class)
public class DokumenResourceIntTest {

    private static final Long DEFAULT_DOKUMEN_ID = 1L;
    private static final Long UPDATED_DOKUMEN_ID = 2L;

    private static final String DEFAULT_DOKUMEN_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DOKUMEN_NUMBER = "BBBBBBBBBB";

    @Inject
    private DokumenRepository dokumenRepository;

    @Inject
    private DokumenService dokumenService;

    @Inject
    private DokumenSearchRepository dokumenSearchRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restDokumenMockMvc;

    private Dokumen dokumen;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DokumenResource dokumenResource = new DokumenResource();
        ReflectionTestUtils.setField(dokumenResource, "dokumenService", dokumenService);
        this.restDokumenMockMvc = MockMvcBuilders.standaloneSetup(dokumenResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dokumen createEntity(EntityManager em) {
        Dokumen dokumen = new Dokumen()
                .dokumen_id(DEFAULT_DOKUMEN_ID)
                .dokumen_number(DEFAULT_DOKUMEN_NUMBER);
        return dokumen;
    }

    @Before
    public void initTest() {
        dokumenSearchRepository.deleteAll();
        dokumen = createEntity(em);
    }

    @Test
    @Transactional
    public void createDokumen() throws Exception {
        int databaseSizeBeforeCreate = dokumenRepository.findAll().size();

        // Create the Dokumen

        restDokumenMockMvc.perform(post("/api/dokumen")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dokumen)))
            .andExpect(status().isCreated());

        // Validate the Dokumen in the database
        List<Dokumen> dokumenList = dokumenRepository.findAll();
        assertThat(dokumenList).hasSize(databaseSizeBeforeCreate + 1);
        Dokumen testDokumen = dokumenList.get(dokumenList.size() - 1);
        assertThat(testDokumen.getDokumen_id()).isEqualTo(DEFAULT_DOKUMEN_ID);
        assertThat(testDokumen.getDokumen_number()).isEqualTo(DEFAULT_DOKUMEN_NUMBER);

        // Validate the Dokumen in ElasticSearch
        Dokumen dokumenEs = dokumenSearchRepository.findOne(testDokumen.getId());
        assertThat(dokumenEs).isEqualToComparingFieldByField(testDokumen);
    }

    @Test
    @Transactional
    public void createDokumenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dokumenRepository.findAll().size();

        // Create the Dokumen with an existing ID
        Dokumen existingDokumen = new Dokumen();
        existingDokumen.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDokumenMockMvc.perform(post("/api/dokumen")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingDokumen)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Dokumen> dokumenList = dokumenRepository.findAll();
        assertThat(dokumenList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDokumen() throws Exception {
        // Initialize the database
        dokumenRepository.saveAndFlush(dokumen);

        // Get all the dokumenList
        restDokumenMockMvc.perform(get("/api/dokumen?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dokumen.getId().intValue())))
            .andExpect(jsonPath("$.[*].dokumen_id").value(hasItem(DEFAULT_DOKUMEN_ID.intValue())))
            .andExpect(jsonPath("$.[*].dokumen_number").value(hasItem(DEFAULT_DOKUMEN_NUMBER.toString())));
    }

    @Test
    @Transactional
    public void getDokumen() throws Exception {
        // Initialize the database
        dokumenRepository.saveAndFlush(dokumen);

        // Get the dokumen
        restDokumenMockMvc.perform(get("/api/dokumen/{id}", dokumen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dokumen.getId().intValue()))
            .andExpect(jsonPath("$.dokumen_id").value(DEFAULT_DOKUMEN_ID.intValue()))
            .andExpect(jsonPath("$.dokumen_number").value(DEFAULT_DOKUMEN_NUMBER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDokumen() throws Exception {
        // Get the dokumen
        restDokumenMockMvc.perform(get("/api/dokumen/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDokumen() throws Exception {
        // Initialize the database
        dokumenService.save(dokumen);

        int databaseSizeBeforeUpdate = dokumenRepository.findAll().size();

        // Update the dokumen
        Dokumen updatedDokumen = dokumenRepository.findOne(dokumen.getId());
        updatedDokumen
                .dokumen_id(UPDATED_DOKUMEN_ID)
                .dokumen_number(UPDATED_DOKUMEN_NUMBER);

        restDokumenMockMvc.perform(put("/api/dokumen")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDokumen)))
            .andExpect(status().isOk());

        // Validate the Dokumen in the database
        List<Dokumen> dokumenList = dokumenRepository.findAll();
        assertThat(dokumenList).hasSize(databaseSizeBeforeUpdate);
        Dokumen testDokumen = dokumenList.get(dokumenList.size() - 1);
        assertThat(testDokumen.getDokumen_id()).isEqualTo(UPDATED_DOKUMEN_ID);
        assertThat(testDokumen.getDokumen_number()).isEqualTo(UPDATED_DOKUMEN_NUMBER);

        // Validate the Dokumen in ElasticSearch
        Dokumen dokumenEs = dokumenSearchRepository.findOne(testDokumen.getId());
        assertThat(dokumenEs).isEqualToComparingFieldByField(testDokumen);
    }

    @Test
    @Transactional
    public void updateNonExistingDokumen() throws Exception {
        int databaseSizeBeforeUpdate = dokumenRepository.findAll().size();

        // Create the Dokumen

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDokumenMockMvc.perform(put("/api/dokumen")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dokumen)))
            .andExpect(status().isCreated());

        // Validate the Dokumen in the database
        List<Dokumen> dokumenList = dokumenRepository.findAll();
        assertThat(dokumenList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDokumen() throws Exception {
        // Initialize the database
        dokumenService.save(dokumen);

        int databaseSizeBeforeDelete = dokumenRepository.findAll().size();

        // Get the dokumen
        restDokumenMockMvc.perform(delete("/api/dokumen/{id}", dokumen.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean dokumenExistsInEs = dokumenSearchRepository.exists(dokumen.getId());
        assertThat(dokumenExistsInEs).isFalse();

        // Validate the database is empty
        List<Dokumen> dokumenList = dokumenRepository.findAll();
        assertThat(dokumenList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchDokumen() throws Exception {
        // Initialize the database
        dokumenService.save(dokumen);

        // Search the dokumen
        restDokumenMockMvc.perform(get("/api/_search/dokumen?query=id:" + dokumen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dokumen.getId().intValue())))
            .andExpect(jsonPath("$.[*].dokumen_id").value(hasItem(DEFAULT_DOKUMEN_ID.intValue())))
            .andExpect(jsonPath("$.[*].dokumen_number").value(hasItem(DEFAULT_DOKUMEN_NUMBER.toString())));
    }
}
