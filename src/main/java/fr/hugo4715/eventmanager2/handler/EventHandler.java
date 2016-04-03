package fr.hugo4715.eventmanager2.handler;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
	public EventPriority priority() default EventPriority.NORMAL;

	public boolean ignoreCanceled() default true;
}
