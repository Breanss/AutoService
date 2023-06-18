package com.example.autoservice.customer;

import com.example.autoservice.nip.NipRepository;
import com.example.autoservice.nip.NipValidator;
import com.example.autoservice.vehicle.VehicleRepository;
import java.lang.Class;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link CustomerService}.
 */
public class CustomerService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'customerService'.
   */
  private static BeanInstanceSupplier<CustomerService> getCustomerServiceInstanceSupplier() {
    return BeanInstanceSupplier.<CustomerService>forConstructor(CustomerRepository.class, VehicleRepository.class, NipRepository.class, CustomerMapper.class, CustomerValidator.class, NipValidator.class)
            .withGenerator((registeredBean, args) -> new CustomerService(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
  }

  /**
   * Get the bean definition for 'customerService'.
   */
  public static BeanDefinition getCustomerServiceBeanDefinition() {
    Class<?> beanType = CustomerService.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(getCustomerServiceInstanceSupplier());
    return beanDefinition;
  }
}
