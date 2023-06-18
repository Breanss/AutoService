package com.example.autoservice.vehicle;

import com.example.autoservice.customer.CustomerMapper;
import com.example.autoservice.customer.CustomerRepository;
import java.lang.Class;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link VehicleService}.
 */
public class VehicleService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'vehicleService'.
   */
  private static BeanInstanceSupplier<VehicleService> getVehicleServiceInstanceSupplier() {
    return BeanInstanceSupplier.<VehicleService>forConstructor(VehicleValidator.class, CustomerRepository.class, VehicleRepository.class, VehicleMapper.class, CustomerMapper.class)
            .withGenerator((registeredBean, args) -> new VehicleService(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4)));
  }

  /**
   * Get the bean definition for 'vehicleService'.
   */
  public static BeanDefinition getVehicleServiceBeanDefinition() {
    Class<?> beanType = VehicleService.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(getVehicleServiceInstanceSupplier());
    return beanDefinition;
  }
}
