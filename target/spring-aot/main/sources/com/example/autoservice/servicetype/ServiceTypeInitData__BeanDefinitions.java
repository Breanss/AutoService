package com.example.autoservice.servicetype;

import java.lang.Class;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ServiceTypeInitData}.
 */
public class ServiceTypeInitData__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'serviceTypeInitData'.
   */
  private static BeanInstanceSupplier<ServiceTypeInitData> getServiceTypeInitDataInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ServiceTypeInitData>forConstructor(ServiceTypeRepository.class)
            .withGenerator((registeredBean, args) -> new ServiceTypeInitData(args.get(0)));
  }

  /**
   * Get the bean definition for 'serviceTypeInitData'.
   */
  public static BeanDefinition getServiceTypeInitDataBeanDefinition() {
    Class<?> beanType = ServiceTypeInitData.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(getServiceTypeInitDataInstanceSupplier());
    return beanDefinition;
  }
}
