package com.bchannel.kemmon.repository.search;

import com.bchannel.kemmon.domain.Dokumen;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Dokumen entity.
 */
public interface DokumenSearchRepository extends ElasticsearchRepository<Dokumen, Long> {
}
