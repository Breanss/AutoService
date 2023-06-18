package com.example.autoservice.servicetype;

import java.lang.Class;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ServiceTypeController}.
 */
public class ServiceTypeController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'serviceTypeController'.
   */
  private static BeanInstanceSupplier<ServiceTypeController> getServiceTypeControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ServiceTypeController>forConstructor(ServiceTypeService.class)
            .withGenerator((registeredBean, args) -> new ServiceTypeController(args.get(0)));
  }

  /**
   * Get the bean definition for 'serviceTypeController'.
   */
  public static BeanDefinition getServiceTypeControllerBeanDefinition() {
    Class<?> beanType = ServiceTypeController.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(getServiceTypeControllerInstanceSupplier());
    return beanDefinition;
  }
}
