package com.bchannel.kemmon.web.rest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.bchannel.kemmon.KemmonApp;
import com.bchannel.kemmon.domain.Dokumen;
import com.bchannel.kemmon.repository.DokumenRepository;
import com.bchannel.kemmon.repository.search.DokumenSearchRepository;
import com.bchannel.kemmon.service.DokumenService;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private static final String DEFAULT_DOKUMEN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOKUMEN_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_INPUTED = false;
    private static final Boolean UPDATED_IS_INPUTED = true;

    private static final LocalDate DEFAULT_INPUTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INPUTED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_INPUTED_BY = 1;
    private static final Integer UPDATED_INPUTED_BY = 2;

    private static final Boolean DEFAULT_IS_PPK_APPROVED = false;
    private static final Boolean UPDATED_IS_PPK_APPROVED = true;

    private static final LocalDate DEFAULT_PPK_APPROVED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PPK_APPROVED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_PPK_APPROVED_BY = 1;
    private static final Integer UPDATED_PPK_APPROVED_BY = 2;

    private static final Boolean DEFAULT_IS_SPM_APPROVED = false;
    private static final Boolean UPDATED_IS_SPM_APPROVED = true;

    private static final LocalDate DEFAULT_SPM_APPROVED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SPM_APPROVED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_SPM_APPROVED_BY = 1;
    private static final Integer UPDATED_SPM_APPROVED_BY = 2;

    private static final Boolean DEFAULT_IS_KPPN_APPROVED = false;
    private static final Boolean UPDATED_IS_KPPN_APPROVED = true;

    private static final LocalDate DEFAULT_KPPN_APPROVED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_KPPN_APPROVED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_KPPN_APPROVED_BY = 1;
    private static final Integer UPDATED_KPPN_APPROVED_BY = 2;

    private static final String DEFAULT_LAST_PROCESS = "AAAAAAAAAA";
    private static final String UPDATED_LAST_PROCESS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LAST_PROCESS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_PROCESS_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LAST_MODIFIED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_MODIFIED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_LAST_MODIFIED_BY = 1;
    private static final Integer UPDATED_LAST_MODIFIED_BY = 2;

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final LocalDate DEFAULT_DELETED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DELETED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_DELETED_BY = 1;
    private static final Integer UPDATED_DELETED_BY = 2;

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
                .dokumen_number(DEFAULT_DOKUMEN_NUMBER)
                .dokumen_name(DEFAULT_DOKUMEN_NAME)
                .is_inputed(DEFAULT_IS_INPUTED)
                .inputed_date(DEFAULT_INPUTED_DATE)
                .inputed_by(DEFAULT_INPUTED_BY)
                .is_ppk_approved(DEFAULT_IS_PPK_APPROVED)
                .ppk_approved_date(DEFAULT_PPK_APPROVED_DATE)
                .ppk_approved_by(DEFAULT_PPK_APPROVED_BY)
                .is_spm_approved(DEFAULT_IS_SPM_APPROVED)
                .spm_approved_date(DEFAULT_SPM_APPROVED_DATE)
                .spm_approved_by(DEFAULT_SPM_APPROVED_BY)
                .is_kppn_approved(DEFAULT_IS_KPPN_APPROVED)
                .kppn_approved_date(DEFAULT_KPPN_APPROVED_DATE)
                .kppn_approved_by(DEFAULT_KPPN_APPROVED_BY)
                .lastProcess(DEFAULT_LAST_PROCESS)
                .last_process_date(DEFAULT_LAST_PROCESS_DATE)
                .last_modified_date(DEFAULT_LAST_MODIFIED_DATE)
                .last_modified_by(DEFAULT_LAST_MODIFIED_BY)
                .is_deleted(DEFAULT_IS_DELETED)
                .deleted_date(DEFAULT_DELETED_DATE)
                .deleted_by(DEFAULT_DELETED_BY);
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
        assertThat(testDokumen.getDokumen_name()).isEqualTo(DEFAULT_DOKUMEN_NAME);
        assertThat(testDokumen.isIs_inputed()).isEqualTo(DEFAULT_IS_INPUTED);
        assertThat(testDokumen.getInputed_date()).isEqualTo(DEFAULT_INPUTED_DATE);
        assertThat(testDokumen.getInputed_by()).isEqualTo(DEFAULT_INPUTED_BY);
        assertThat(testDokumen.isIs_ppk_approved()).isEqualTo(DEFAULT_IS_PPK_APPROVED);
        assertThat(testDokumen.getPpk_approved_date()).isEqualTo(DEFAULT_PPK_APPROVED_DATE);
        assertThat(testDokumen.getPpk_approved_by()).isEqualTo(DEFAULT_PPK_APPROVED_BY);
        assertThat(testDokumen.isIs_spm_approved()).isEqualTo(DEFAULT_IS_SPM_APPROVED);
        assertThat(testDokumen.getSpm_approved_date()).isEqualTo(DEFAULT_SPM_APPROVED_DATE);
        assertThat(testDokumen.getSpm_approved_by()).isEqualTo(DEFAULT_SPM_APPROVED_BY);
        assertThat(testDokumen.isIs_kppn_approved()).isEqualTo(DEFAULT_IS_KPPN_APPROVED);
        assertThat(testDokumen.getKppn_approved_date()).isEqualTo(DEFAULT_KPPN_APPROVED_DATE);
        assertThat(testDokumen.getKppn_approved_by()).isEqualTo(DEFAULT_KPPN_APPROVED_BY);
        assertThat(testDokumen.getLastProcess()).isEqualTo(DEFAULT_LAST_PROCESS);
        assertThat(testDokumen.getLast_process_date()).isEqualTo(DEFAULT_LAST_PROCESS_DATE);
        assertThat(testDokumen.getLast_modified_date()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testDokumen.getLast_modified_by()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testDokumen.isIs_deleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testDokumen.getDeleted_date()).isEqualTo(DEFAULT_DELETED_DATE);
        assertThat(testDokumen.getDeleted_by()).isEqualTo(DEFAULT_DELETED_BY);

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
            .andExpect(jsonPath("$.[*].dokumen_number").value(hasItem(DEFAULT_DOKUMEN_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].dokumen_name").value(hasItem(DEFAULT_DOKUMEN_NAME.toString())))
            .andExpect(jsonPath("$.[*].is_inputed").value(hasItem(DEFAULT_IS_INPUTED.booleanValue())))
            .andExpect(jsonPath("$.[*].inputed_date").value(hasItem(DEFAULT_INPUTED_DATE.toString())))
            .andExpect(jsonPath("$.[*].inputed_by").value(hasItem(DEFAULT_INPUTED_BY)))
            .andExpect(jsonPath("$.[*].is_ppk_approved").value(hasItem(DEFAULT_IS_PPK_APPROVED.booleanValue())))
            .andExpect(jsonPath("$.[*].ppk_approved_date").value(hasItem(DEFAULT_PPK_APPROVED_DATE.toString())))
            .andExpect(jsonPath("$.[*].ppk_approved_by").value(hasItem(DEFAULT_PPK_APPROVED_BY)))
            .andExpect(jsonPath("$.[*].is_spm_approved").value(hasItem(DEFAULT_IS_SPM_APPROVED.booleanValue())))
            .andExpect(jsonPath("$.[*].spm_approved_date").value(hasItem(DEFAULT_SPM_APPROVED_DATE.toString())))
            .andExpect(jsonPath("$.[*].spm_approved_by").value(hasItem(DEFAULT_SPM_APPROVED_BY)))
            .andExpect(jsonPath("$.[*].is_kppn_approved").value(hasItem(DEFAULT_IS_KPPN_APPROVED.booleanValue())))
            .andExpect(jsonPath("$.[*].kppn_approved_date").value(hasItem(DEFAULT_KPPN_APPROVED_DATE.toString())))
            .andExpect(jsonPath("$.[*].kppn_approved_by").value(hasItem(DEFAULT_KPPN_APPROVED_BY)))
            .andExpect(jsonPath("$.[*].last_process").value(hasItem(DEFAULT_LAST_PROCESS.toString())))
            .andExpect(jsonPath("$.[*].last_process_date").value(hasItem(DEFAULT_LAST_PROCESS_DATE.toString())))
            .andExpect(jsonPath("$.[*].last_modified_date").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].last_modified_by").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].is_deleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].deleted_date").value(hasItem(DEFAULT_DELETED_DATE.toString())))
            .andExpect(jsonPath("$.[*].deleted_by").value(hasItem(DEFAULT_DELETED_BY)));
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
            .andExpect(jsonPath("$.dokumen_number").value(DEFAULT_DOKUMEN_NUMBER.toString()))
            .andExpect(jsonPath("$.dokumen_name").value(DEFAULT_DOKUMEN_NAME.toString()))
            .andExpect(jsonPath("$.is_inputed").value(DEFAULT_IS_INPUTED.booleanValue()))
            .andExpect(jsonPath("$.inputed_date").value(DEFAULT_INPUTED_DATE.toString()))
            .andExpect(jsonPath("$.inputed_by").value(DEFAULT_INPUTED_BY))
            .andExpect(jsonPath("$.is_ppk_approved").value(DEFAULT_IS_PPK_APPROVED.booleanValue()))
            .andExpect(jsonPath("$.ppk_approved_date").value(DEFAULT_PPK_APPROVED_DATE.toString()))
            .andExpect(jsonPath("$.ppk_approved_by").value(DEFAULT_PPK_APPROVED_BY))
            .andExpect(jsonPath("$.is_spm_approved").value(DEFAULT_IS_SPM_APPROVED.booleanValue()))
            .andExpect(jsonPath("$.spm_approved_date").value(DEFAULT_SPM_APPROVED_DATE.toString()))
            .andExpect(jsonPath("$.spm_approved_by").value(DEFAULT_SPM_APPROVED_BY))
            .andExpect(jsonPath("$.is_kppn_approved").value(DEFAULT_IS_KPPN_APPROVED.booleanValue()))
            .andExpect(jsonPath("$.kppn_approved_date").value(DEFAULT_KPPN_APPROVED_DATE.toString()))
            .andExpect(jsonPath("$.kppn_approved_by").value(DEFAULT_KPPN_APPROVED_BY))
            .andExpect(jsonPath("$.last_process").value(DEFAULT_LAST_PROCESS.toString()))
            .andExpect(jsonPath("$.last_process_date").value(DEFAULT_LAST_PROCESS_DATE.toString()))
            .andExpect(jsonPath("$.last_modified_date").value(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.last_modified_by").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.is_deleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.deleted_date").value(DEFAULT_DELETED_DATE.toString()))
            .andExpect(jsonPath("$.deleted_by").value(DEFAULT_DELETED_BY));
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
                .dokumen_number(UPDATED_DOKUMEN_NUMBER)
                .dokumen_name(UPDATED_DOKUMEN_NAME)
                .is_inputed(UPDATED_IS_INPUTED)
                .inputed_date(UPDATED_INPUTED_DATE)
                .inputed_by(UPDATED_INPUTED_BY)
                .is_ppk_approved(UPDATED_IS_PPK_APPROVED)
                .ppk_approved_date(UPDATED_PPK_APPROVED_DATE)
                .ppk_approved_by(UPDATED_PPK_APPROVED_BY)
                .is_spm_approved(UPDATED_IS_SPM_APPROVED)
                .spm_approved_date(UPDATED_SPM_APPROVED_DATE)
                .spm_approved_by(UPDATED_SPM_APPROVED_BY)
                .is_kppn_approved(UPDATED_IS_KPPN_APPROVED)
                .kppn_approved_date(UPDATED_KPPN_APPROVED_DATE)
                .kppn_approved_by(UPDATED_KPPN_APPROVED_BY)
                .lastProcess(UPDATED_LAST_PROCESS)
                .last_process_date(UPDATED_LAST_PROCESS_DATE)
                .last_modified_date(UPDATED_LAST_MODIFIED_DATE)
                .last_modified_by(UPDATED_LAST_MODIFIED_BY)
                .is_deleted(UPDATED_IS_DELETED)
                .deleted_date(UPDATED_DELETED_DATE)
                .deleted_by(UPDATED_DELETED_BY);

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
        assertThat(testDokumen.getDokumen_name()).isEqualTo(UPDATED_DOKUMEN_NAME);
        assertThat(testDokumen.isIs_inputed()).isEqualTo(UPDATED_IS_INPUTED);
        assertThat(testDokumen.getInputed_date()).isEqualTo(UPDATED_INPUTED_DATE);
        assertThat(testDokumen.getInputed_by()).isEqualTo(UPDATED_INPUTED_BY);
        assertThat(testDokumen.isIs_ppk_approved()).isEqualTo(UPDATED_IS_PPK_APPROVED);
        assertThat(testDokumen.getPpk_approved_date()).isEqualTo(UPDATED_PPK_APPROVED_DATE);
        assertThat(testDokumen.getPpk_approved_by()).isEqualTo(UPDATED_PPK_APPROVED_BY);
        assertThat(testDokumen.isIs_spm_approved()).isEqualTo(UPDATED_IS_SPM_APPROVED);
        assertThat(testDokumen.getSpm_approved_date()).isEqualTo(UPDATED_SPM_APPROVED_DATE);
        assertThat(testDokumen.getSpm_approved_by()).isEqualTo(UPDATED_SPM_APPROVED_BY);
        assertThat(testDokumen.isIs_kppn_approved()).isEqualTo(UPDATED_IS_KPPN_APPROVED);
        assertThat(testDokumen.getKppn_approved_date()).isEqualTo(UPDATED_KPPN_APPROVED_DATE);
        assertThat(testDokumen.getKppn_approved_by()).isEqualTo(UPDATED_KPPN_APPROVED_BY);
        assertThat(testDokumen.getLastProcess()).isEqualTo(UPDATED_LAST_PROCESS);
        assertThat(testDokumen.getLast_process_date()).isEqualTo(UPDATED_LAST_PROCESS_DATE);
        assertThat(testDokumen.getLast_modified_date()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testDokumen.getLast_modified_by()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testDokumen.isIs_deleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testDokumen.getDeleted_date()).isEqualTo(UPDATED_DELETED_DATE);
        assertThat(testDokumen.getDeleted_by()).isEqualTo(UPDATED_DELETED_BY);

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
            .andExpect(jsonPath("$.[*].dokumen_number").value(hasItem(DEFAULT_DOKUMEN_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].dokumen_name").value(hasItem(DEFAULT_DOKUMEN_NAME.toString())))
            .andExpect(jsonPath("$.[*].is_inputed").value(hasItem(DEFAULT_IS_INPUTED.booleanValue())))
            .andExpect(jsonPath("$.[*].inputed_date").value(hasItem(DEFAULT_INPUTED_DATE.toString())))
            .andExpect(jsonPath("$.[*].inputed_by").value(hasItem(DEFAULT_INPUTED_BY)))
            .andExpect(jsonPath("$.[*].is_ppk_approved").value(hasItem(DEFAULT_IS_PPK_APPROVED.booleanValue())))
            .andExpect(jsonPath("$.[*].ppk_approved_date").value(hasItem(DEFAULT_PPK_APPROVED_DATE.toString())))
            .andExpect(jsonPath("$.[*].ppk_approved_by").value(hasItem(DEFAULT_PPK_APPROVED_BY)))
            .andExpect(jsonPath("$.[*].is_spm_approved").value(hasItem(DEFAULT_IS_SPM_APPROVED.booleanValue())))
            .andExpect(jsonPath("$.[*].spm_approved_date").value(hasItem(DEFAULT_SPM_APPROVED_DATE.toString())))
            .andExpect(jsonPath("$.[*].spm_approved_by").value(hasItem(DEFAULT_SPM_APPROVED_BY)))
            .andExpect(jsonPath("$.[*].is_kppn_approved").value(hasItem(DEFAULT_IS_KPPN_APPROVED.booleanValue())))
            .andExpect(jsonPath("$.[*].kppn_approved_date").value(hasItem(DEFAULT_KPPN_APPROVED_DATE.toString())))
            .andExpect(jsonPath("$.[*].kppn_approved_by").value(hasItem(DEFAULT_KPPN_APPROVED_BY)))
            .andExpect(jsonPath("$.[*].last_process").value(hasItem(DEFAULT_LAST_PROCESS.toString())))
            .andExpect(jsonPath("$.[*].last_process_date").value(hasItem(DEFAULT_LAST_PROCESS_DATE.toString())))
            .andExpect(jsonPath("$.[*].last_modified_date").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].last_modified_by").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].is_deleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].deleted_date").value(hasItem(DEFAULT_DELETED_DATE.toString())))
            .andExpect(jsonPath("$.[*].deleted_by").value(hasItem(DEFAULT_DELETED_BY)));
    }
}
