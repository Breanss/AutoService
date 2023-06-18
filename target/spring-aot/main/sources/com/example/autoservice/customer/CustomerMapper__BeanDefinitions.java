package com.example.autoservice.customer;

import com.example.autoservice.nip.NipMapper;
import java.lang.Class;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link CustomerMapper}.
 */
public class CustomerMapper__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'customerMapper'.
   */
  private static BeanInstanceSupplier<CustomerMapper> getCustomerMapperInstanceSupplier() {
    return BeanInstanceSupplier.<CustomerMapper>forConstructor(NipMapper.class)
            .withGenerator((registeredBean, args) -> new CustomerMapper(args.get(0)));
  }

  /**
   * Get the bean definition for 'customerMapper'.
   */
  public static BeanDefinition getCustomerMapperBeanDefinition() {
    Class<?> beanType = CustomerMapper.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(getCustomerMapperInstanceSupplier());
    return beanDefinition;
  }
}
