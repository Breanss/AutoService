package com.example.autoservice.servicetype;

import java.lang.Class;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ServiceTypeService}.
 */
public class ServiceTypeService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'serviceTypeService'.
   */
  private static BeanInstanceSupplier<ServiceTypeService> getServiceTypeServiceInstanceSupplier() {
    return BeanInstanceSupplier.<ServiceTypeService>forConstructor(ServiceTypeRepository.class)
            .withGenerator((registeredBean, args) -> new ServiceTypeService(args.get(0)));
  }

  /**
   * Get the bean definition for 'serviceTypeService'.
   */
  public static BeanDefinition getServiceTypeServiceBeanDefinition() {
    Class<?> beanType = ServiceTypeService.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(getServiceTypeServiceInstanceSupplier());
    return beanDefinition;
  }
}
