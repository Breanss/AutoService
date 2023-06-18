package com.example.autoservice.service;

import com.example.autoservice.servicetype.ServiceTypeRepository;
import com.example.autoservice.vehicle.VehicleMapper;
import com.example.autoservice.vehicle.VehicleRepository;
import java.lang.Class;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ServiceService}.
 */
public class ServiceService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'serviceService'.
   */
  private static BeanInstanceSupplier<ServiceService> getServiceServiceInstanceSupplier() {
    return BeanInstanceSupplier.<ServiceService>forConstructor(ServiceRepository.class, VehicleRepository.class, ServiceMapper.class, VehicleMapper.class, ServiceValidator.class, ServiceTypeRepository.class)
            .withGenerator((registeredBean, args) -> new ServiceService(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
  }

  /**
   * Get the bean definition for 'serviceService'.
   */
  public static BeanDefinition getServiceServiceBeanDefinition() {
    Class<?> beanType = ServiceService.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(getServiceServiceInstanceSupplier());
    return beanDefinition;
  }
}
