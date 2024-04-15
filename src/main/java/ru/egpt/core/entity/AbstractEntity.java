package ru.egpt.core.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity {

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private Instant created = Instant.now();

    @UpdateTimestamp
    @Column(name = "updated")
    private Instant updated;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "is_enabled")
    private Boolean enabled = true;
}
