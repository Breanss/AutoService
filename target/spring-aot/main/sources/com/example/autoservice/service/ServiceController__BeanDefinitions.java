package com.example.autoservice.service;

import java.lang.Class;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ServiceController}.
 */
public class ServiceController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'serviceController'.
   */
  private static BeanInstanceSupplier<ServiceController> getServiceControllerInstanceSupplier() {
    return BeanInstanceSupplier.<ServiceController>forConstructor(ServiceService.class)
            .withGenerator((registeredBean, args) -> new ServiceController(args.get(0)));
  }

  /**
   * Get the bean definition for 'serviceController'.
   */
  public static BeanDefinition getServiceControllerBeanDefinition() {
    Class<?> beanType = ServiceController.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(getServiceControllerInstanceSupplier());
    return beanDefinition;
  }
}
