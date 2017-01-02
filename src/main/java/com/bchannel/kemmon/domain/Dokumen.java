package com.bchannel.kemmon.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Dokumen.
 */
@Entity
@Table(name = "dokumen")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "dokumen")
public class Dokumen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "dokumen_id")
    private Long dokumen_id;

    @Column(name = "dokumen_number")
    private String dokumen_number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDokumen_id() {
        return dokumen_id;
    }

    public Dokumen dokumen_id(Long dokumen_id) {
        this.dokumen_id = dokumen_id;
        return this;
    }

    public void setDokumen_id(Long dokumen_id) {
        this.dokumen_id = dokumen_id;
    }

    public String getDokumen_number() {
        return dokumen_number;
    }

    public Dokumen dokumen_number(String dokumen_number) {
        this.dokumen_number = dokumen_number;
        return this;
    }

    public void setDokumen_number(String dokumen_number) {
        this.dokumen_number = dokumen_number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dokumen dokumen = (Dokumen) o;
        if (dokumen.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, dokumen.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Dokumen{" +
            "id=" + id +
            ", dokumen_id='" + dokumen_id + "'" +
            ", dokumen_number='" + dokumen_number + "'" +
            '}';
    }
}
