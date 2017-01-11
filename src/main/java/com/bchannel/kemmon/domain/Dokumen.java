package com.bchannel.kemmon.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

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

    @Column(name = "dokumen_name")
    private String dokumen_name;

    @Column(name = "is_inputed")
    private Boolean is_inputed;

    @Column(name = "inputed_date")
    private LocalDate inputed_date;

    @Column(name = "inputed_by")
    private Integer inputed_by;

    @Column(name = "is_ppk_approved")
    private Boolean is_ppk_approved;

    @Column(name = "ppk_approved_date")
    private LocalDate ppk_approved_date;

    @Column(name = "ppk_approved_by")
    private Integer ppk_approved_by;

    @Column(name = "is_spm_approved")
    private Boolean is_spm_approved;

    @Column(name = "spm_approved_date")
    private LocalDate spm_approved_date;

    @Column(name = "spm_approved_by")
    private Integer spm_approved_by;

    @Column(name = "is_kppn_approved")
    private Boolean is_kppn_approved;

    @Column(name = "kppn_approved_date")
    private LocalDate kppn_approved_date;

    @Column(name = "kppn_approved_by")
    private Integer kppn_approved_by;

    @Column(name = "last_process")
    private String lastProcess;

    @Column(name = "last_process_date")
    private LocalDate last_process_date;

    @Column(name = "last_modified_date")
    private LocalDate last_modified_date;

    @Column(name = "last_modified_by")
    private Integer last_modified_by;

    @Column(name = "is_deleted")
    private Boolean is_deleted;

    @Column(name = "deleted_date")
    private LocalDate deleted_date;

    @Column(name = "deleted_by")
    private Integer deleted_by;

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

    public String getDokumen_name() {
        return dokumen_name;
    }

    public Dokumen dokumen_name(String dokumen_name) {
        this.dokumen_name = dokumen_name;
        return this;
    }

    public void setDokumen_name(String dokumen_name) {
        this.dokumen_name = dokumen_name;
    }

    public Boolean isIs_inputed() {
        return is_inputed;
    }

    public Dokumen is_inputed(Boolean is_inputed) {
        this.is_inputed = is_inputed;
        return this;
    }

    public void setIs_inputed(Boolean is_inputed) {
        this.is_inputed = is_inputed;
    }

    public LocalDate getInputed_date() {
        return inputed_date;
    }

    public Dokumen inputed_date(LocalDate inputed_date) {
        this.inputed_date = inputed_date;
        return this;
    }

    public void setInputed_date(LocalDate inputed_date) {
        this.inputed_date = inputed_date;
    }

    public Integer getInputed_by() {
        return inputed_by;
    }

    public Dokumen inputed_by(Integer inputed_by) {
        this.inputed_by = inputed_by;
        return this;
    }

    public void setInputed_by(Integer inputed_by) {
        this.inputed_by = inputed_by;
    }

    public Boolean isIs_ppk_approved() {
        return is_ppk_approved;
    }

    public Dokumen is_ppk_approved(Boolean is_ppk_approved) {
        this.is_ppk_approved = is_ppk_approved;
        return this;
    }

    public void setIs_ppk_approved(Boolean is_ppk_approved) {
        this.is_ppk_approved = is_ppk_approved;
    }

    public LocalDate getPpk_approved_date() {
        return ppk_approved_date;
    }

    public Dokumen ppk_approved_date(LocalDate ppk_approved_date) {
        this.ppk_approved_date = ppk_approved_date;
        return this;
    }

    public void setPpk_approved_date(LocalDate ppk_approved_date) {
        this.ppk_approved_date = ppk_approved_date;
    }

    public Integer getPpk_approved_by() {
        return ppk_approved_by;
    }

    public Dokumen ppk_approved_by(Integer ppk_approved_by) {
        this.ppk_approved_by = ppk_approved_by;
        return this;
    }

    public void setPpk_approved_by(Integer ppk_approved_by) {
        this.ppk_approved_by = ppk_approved_by;
    }

    public Boolean isIs_spm_approved() {
        return is_spm_approved;
    }

    public Dokumen is_spm_approved(Boolean is_spm_approved) {
        this.is_spm_approved = is_spm_approved;
        return this;
    }

    public void setIs_spm_approved(Boolean is_spm_approved) {
        this.is_spm_approved = is_spm_approved;
    }

    public LocalDate getSpm_approved_date() {
        return spm_approved_date;
    }

    public Dokumen spm_approved_date(LocalDate spm_approved_date) {
        this.spm_approved_date = spm_approved_date;
        return this;
    }

    public void setSpm_approved_date(LocalDate spm_approved_date) {
        this.spm_approved_date = spm_approved_date;
    }

    public Integer getSpm_approved_by() {
        return spm_approved_by;
    }

    public Dokumen spm_approved_by(Integer spm_approved_by) {
        this.spm_approved_by = spm_approved_by;
        return this;
    }

    public void setSpm_approved_by(Integer spm_approved_by) {
        this.spm_approved_by = spm_approved_by;
    }

    public Boolean isIs_kppn_approved() {
        return is_kppn_approved;
    }

    public Dokumen is_kppn_approved(Boolean is_kppn_approved) {
        this.is_kppn_approved = is_kppn_approved;
        return this;
    }

    public void setIs_kppn_approved(Boolean is_kppn_approved) {
        this.is_kppn_approved = is_kppn_approved;
    }

    public LocalDate getKppn_approved_date() {
        return kppn_approved_date;
    }

    public Dokumen kppn_approved_date(LocalDate kppn_approved_date) {
        this.kppn_approved_date = kppn_approved_date;
        return this;
    }

    public void setKppn_approved_date(LocalDate kppn_approved_date) {
        this.kppn_approved_date = kppn_approved_date;
    }

    public Integer getKppn_approved_by() {
        return kppn_approved_by;
    }

    public Dokumen kppn_approved_by(Integer kppn_approved_by) {
        this.kppn_approved_by = kppn_approved_by;
        return this;
    }

    public void setKppn_approved_by(Integer kppn_approved_by) {
        this.kppn_approved_by = kppn_approved_by;
    }

    public String getLastProcess() {
        return lastProcess;
    }

    public Dokumen lastProcess(String lastProcess) {
        this.lastProcess = lastProcess;
        return this;
    }

    public void setLastProcess(String last_process) {
        this.lastProcess = last_process;
    }

    public LocalDate getLast_process_date() {
        return last_process_date;
    }

    public Dokumen last_process_date(LocalDate last_process_date) {
        this.last_process_date = last_process_date;
        return this;
    }

    public void setLast_process_date(LocalDate last_process_date) {
        this.last_process_date = last_process_date;
    }

    public LocalDate getLast_modified_date() {
        return last_modified_date;
    }

    public Dokumen last_modified_date(LocalDate last_modified_date) {
        this.last_modified_date = last_modified_date;
        return this;
    }

    public void setLast_modified_date(LocalDate last_modified_date) {
        this.last_modified_date = last_modified_date;
    }

    public Integer getLast_modified_by() {
        return last_modified_by;
    }

    public Dokumen last_modified_by(Integer last_modified_by) {
        this.last_modified_by = last_modified_by;
        return this;
    }

    public void setLast_modified_by(Integer last_modified_by) {
        this.last_modified_by = last_modified_by;
    }

    public Boolean isIs_deleted() {
        return is_deleted;
    }

    public Dokumen is_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
        return this;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public LocalDate getDeleted_date() {
        return deleted_date;
    }

    public Dokumen deleted_date(LocalDate deleted_date) {
        this.deleted_date = deleted_date;
        return this;
    }

    public void setDeleted_date(LocalDate deleted_date) {
        this.deleted_date = deleted_date;
    }

    public Integer getDeleted_by() {
        return deleted_by;
    }

    public Dokumen deleted_by(Integer deleted_by) {
        this.deleted_by = deleted_by;
        return this;
    }

    public void setDeleted_by(Integer deleted_by) {
        this.deleted_by = deleted_by;
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
            ", dokumen_name='" + dokumen_name + "'" +
            ", is_inputed='" + is_inputed + "'" +
            ", inputed_date='" + inputed_date + "'" +
            ", inputed_by='" + inputed_by + "'" +
            ", is_ppk_approved='" + is_ppk_approved + "'" +
            ", ppk_approved_date='" + ppk_approved_date + "'" +
            ", ppk_approved_by='" + ppk_approved_by + "'" +
            ", is_spm_approved='" + is_spm_approved + "'" +
            ", spm_approved_date='" + spm_approved_date + "'" +
            ", spm_approved_by='" + spm_approved_by + "'" +
            ", is_kppn_approved='" + is_kppn_approved + "'" +
            ", kppn_approved_date='" + kppn_approved_date + "'" +
            ", kppn_approved_by='" + kppn_approved_by + "'" +
            ", last_process='" + lastProcess + "'" +
            ", last_process_date='" + last_process_date + "'" +
            ", last_modified_date='" + last_modified_date + "'" +
            ", last_modified_by='" + last_modified_by + "'" +
            ", is_deleted='" + is_deleted + "'" +
            ", deleted_date='" + deleted_date + "'" +
            ", deleted_by='" + deleted_by + "'" +
            '}';
    }
}
