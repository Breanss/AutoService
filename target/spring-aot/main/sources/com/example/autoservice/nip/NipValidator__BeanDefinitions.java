package com.example.autoservice.nip;

import java.lang.Class;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link NipValidator}.
 */
public class NipValidator__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'nipValidator'.
   */
  private static BeanInstanceSupplier<NipValidator> getNipValidatorInstanceSupplier() {
    return BeanInstanceSupplier.<NipValidator>forConstructor(NipRepository.class)
            .withGenerator((registeredBean, args) -> new NipValidator(args.get(0)));
  }

  /**
   * Get the bean definition for 'nipValidator'.
   */
  public static BeanDefinition getNipValidatorBeanDefinition() {
    Class<?> beanType = NipValidator.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(getNipValidatorInstanceSupplier());
    return beanDefinition;
  }
}
