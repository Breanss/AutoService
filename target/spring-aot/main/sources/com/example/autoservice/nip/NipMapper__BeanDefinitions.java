package com.example.autoservice.nip;

import java.lang.Class;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link NipMapper}.
 */
public class NipMapper__BeanDefinitions {
  /**
   * Get the bean definition for 'nipMapper'.
   */
  public static BeanDefinition getNipMapperBeanDefinition() {
    Class<?> beanType = NipMapper.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(NipMapper::new);
    return beanDefinition;
  }
}
