package org.acme;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Data
public class FirstEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private long id;

    private String value;

    @OneToMany(mappedBy = "firstEntity")
    private List<SecondEntity> secondEntities;
}
