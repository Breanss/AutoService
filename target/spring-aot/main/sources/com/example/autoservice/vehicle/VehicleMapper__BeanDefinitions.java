package com.example.autoservice.vehicle;

import com.example.autoservice.customer.CustomerMapper;
import com.example.autoservice.service.ServiceMapper;
import java.lang.Class;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link VehicleMapper}.
 */
public class VehicleMapper__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'vehicleMapper'.
   */
  private static BeanInstanceSupplier<VehicleMapper> getVehicleMapperInstanceSupplier() {
    return BeanInstanceSupplier.<VehicleMapper>forConstructor(CustomerMapper.class, ServiceMapper.class)
            .withGenerator((registeredBean, args) -> new VehicleMapper(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'vehicleMapper'.
   */
  public static BeanDefinition getVehicleMapperBeanDefinition() {
    Class<?> beanType = VehicleMapper.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(getVehicleMapperInstanceSupplier());
    return beanDefinition;
  }
}
