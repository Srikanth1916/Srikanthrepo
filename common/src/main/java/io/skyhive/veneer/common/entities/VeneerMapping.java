package io.skyhive.veneer.common.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * The type Veneer mapping.
 */
@Getter
@Setter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"type", "externalId"})
})
@NoArgsConstructor
@AllArgsConstructor
public class VeneerMapping implements Serializable {
    @Id
    private String skyhiveId;
    private String externalId;
    private String type;
    private String userName;
    private String password;
}
