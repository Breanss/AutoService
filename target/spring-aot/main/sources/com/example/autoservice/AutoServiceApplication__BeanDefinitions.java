package com.example.autoservice;

import java.lang.Class;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link AutoServiceApplication}.
 */
public class AutoServiceApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'autoServiceApplication'.
   */
  public static BeanDefinition getAutoServiceApplicationBeanDefinition() {
    Class<?> beanType = AutoServiceApplication.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    ConfigurationClassUtils.initializeConfigurationClass(AutoServiceApplication.class);
    beanDefinition.setInstanceSupplier(AutoServiceApplication$$SpringCGLIB$$0::new);
    return beanDefinition;
  }
}
