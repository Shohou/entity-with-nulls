package org.acme;

import io.quarkus.runtime.Startup;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Startup
@Slf4j
public class NullEntitiesCheck {

    @PostConstruct
    public void init() throws Exception {
        insertAndCheck();
        check();
    }

    @Transactional
    public void insertAndCheck() {
        FirstEntity firstEntity = new FirstEntity();
        firstEntity.setValue("value");
        firstEntity.persistAndFlush();

        check();
    }

    @Transactional
    public void check() {
        List<FirstEntity> firstEntities = FirstEntity.listAll();
        List<SecondEntity> secondEntities = firstEntities.get(0).getSecondEntities();
        if (secondEntities == null) {
            log.error("FirstEntity.getSecondEntities() returns null!!!");
        } else {
            log.info("Bug reproduction failed :(");
        }
    }
}
