package com.example.autoservice.service;

import com.example.autoservice.servicetype.ServiceTypeRepository;
import java.lang.Class;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ServiceValidator}.
 */
public class ServiceValidator__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'serviceValidator'.
   */
  private static BeanInstanceSupplier<ServiceValidator> getServiceValidatorInstanceSupplier() {
    return BeanInstanceSupplier.<ServiceValidator>forConstructor(ServiceTypeRepository.class)
            .withGenerator((registeredBean, args) -> new ServiceValidator(args.get(0)));
  }

  /**
   * Get the bean definition for 'serviceValidator'.
   */
  public static BeanDefinition getServiceValidatorBeanDefinition() {
    Class<?> beanType = ServiceValidator.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(getServiceValidatorInstanceSupplier());
    return beanDefinition;
  }
}
