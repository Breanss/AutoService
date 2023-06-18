package com.example.autoservice.service;

import java.lang.Class;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ServiceMapper}.
 */
public class ServiceMapper__BeanDefinitions {
  /**
   * Get the bean definition for 'serviceMapper'.
   */
  public static BeanDefinition getServiceMapperBeanDefinition() {
    Class<?> beanType = ServiceMapper.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(ServiceMapper::new);
    return beanDefinition;
  }
}
