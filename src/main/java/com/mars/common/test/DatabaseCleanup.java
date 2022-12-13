package com.mars.common.test;

import com.google.common.base.CaseFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public abstract class DatabaseCleanup implements InitializingBean {

  private List<String> tableNames;

  @Transactional
  public void execute() {
    getEntityManager().flush();
    getEntityManager().createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

    for (final var tableName : tableNames) {
      getEntityManager().createNativeQuery("TRUNCATE  TABLE " + tableName).executeUpdate();
    }

    getEntityManager().createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
  }

  @Override
  public void afterPropertiesSet() {
    tableNames = getEntityManager().getMetamodel().getEntities().stream()
                                   .filter(DatabaseCleanup::checkNull)
                                   .map(DatabaseCleanup::toTableName)
                                   .collect(Collectors.toList());
  }

  private static boolean checkNull(EntityType<?> e) {
    return e.getJavaType().getAnnotation(Entity.class) != null;
  }

  private static String toTableName(EntityType<?> entityType) {
    final var table = entityType.getJavaType().getAnnotation(Table.class);
    if (table == null || StringUtils.isBlank(table.name())) {
      return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityType.getName());
    }
    return table.name();
  }

  public abstract EntityManager getEntityManager();
}
