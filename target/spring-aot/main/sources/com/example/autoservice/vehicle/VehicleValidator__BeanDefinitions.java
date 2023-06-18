package com.example.autoservice.vehicle;

import java.lang.Class;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link VehicleValidator}.
 */
public class VehicleValidator__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'vehicleValidator'.
   */
  private static BeanInstanceSupplier<VehicleValidator> getVehicleValidatorInstanceSupplier() {
    return BeanInstanceSupplier.<VehicleValidator>forConstructor(VehicleRepository.class)
            .withGenerator((registeredBean, args) -> new VehicleValidator(args.get(0)));
  }

  /**
   * Get the bean definition for 'vehicleValidator'.
   */
  public static BeanDefinition getVehicleValidatorBeanDefinition() {
    Class<?> beanType = VehicleValidator.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(getVehicleValidatorInstanceSupplier());
    return beanDefinition;
  }
}
