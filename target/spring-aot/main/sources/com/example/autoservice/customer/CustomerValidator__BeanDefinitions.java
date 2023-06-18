package com.example.autoservice.customer;

import java.lang.Class;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link CustomerValidator}.
 */
public class CustomerValidator__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'customerValidator'.
   */
  private static BeanInstanceSupplier<CustomerValidator> getCustomerValidatorInstanceSupplier() {
    return BeanInstanceSupplier.<CustomerValidator>forConstructor(CustomerRepository.class)
            .withGenerator((registeredBean, args) -> new CustomerValidator(args.get(0)));
  }

  /**
   * Get the bean definition for 'customerValidator'.
   */
  public static BeanDefinition getCustomerValidatorBeanDefinition() {
    Class<?> beanType = CustomerValidator.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(getCustomerValidatorInstanceSupplier());
    return beanDefinition;
  }
}
