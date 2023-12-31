package org.example.mbeans;

import javax.management.DescriptorKey;
import java.lang.annotation.*;

@Documented 
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) 
public @interface DisplayName {
    @DescriptorKey("displayName")
    String value(); 
} 