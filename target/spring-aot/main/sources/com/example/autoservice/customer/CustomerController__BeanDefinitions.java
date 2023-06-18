package com.example.autoservice.customer;

import java.lang.Class;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link CustomerController}.
 */
public class CustomerController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'customerController'.
   */
  private static BeanInstanceSupplier<CustomerController> getCustomerControllerInstanceSupplier() {
    return BeanInstanceSupplier.<CustomerController>forConstructor(CustomerService.class)
            .withGenerator((registeredBean, args) -> new CustomerController(args.get(0)));
  }

  /**
   * Get the bean definition for 'customerController'.
   */
  public static BeanDefinition getCustomerControllerBeanDefinition() {
    Class<?> beanType = CustomerController.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(getCustomerControllerInstanceSupplier());
    return beanDefinition;
  }
}
