package com.example.autoservice.vehicle;

import java.lang.Class;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link VehicleController}.
 */
public class VehicleController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'vehicleController'.
   */
  private static BeanInstanceSupplier<VehicleController> getVehicleControllerInstanceSupplier() {
    return BeanInstanceSupplier.<VehicleController>forConstructor(VehicleService.class)
            .withGenerator((registeredBean, args) -> new VehicleController(args.get(0)));
  }

  /**
   * Get the bean definition for 'vehicleController'.
   */
  public static BeanDefinition getVehicleControllerBeanDefinition() {
    Class<?> beanType = VehicleController.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(getVehicleControllerInstanceSupplier());
    return beanDefinition;
  }
}
