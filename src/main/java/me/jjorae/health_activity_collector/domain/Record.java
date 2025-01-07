package me.jjorae.health_activity_collector.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "record")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Record extends BaseEntity {
    @Id
    private String recordKey;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "record_key")
    private List<Entry> entries;
    private String productName;
    private String productVender;
    private Integer sourceMode;
    private String sourceName;
    private String sourceType;
    private String memo;
    private OffsetDateTime lastUpdate;
    private String type;

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
