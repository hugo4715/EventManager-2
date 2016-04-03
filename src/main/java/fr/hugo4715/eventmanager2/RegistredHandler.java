package fr.hugo4715.eventmanager2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fr.hugo4715.eventmanager2.event.Cancellable;
import fr.hugo4715.eventmanager2.event.Event;
import fr.hugo4715.eventmanager2.handler.EventHandler;
import fr.hugo4715.eventmanager2.handler.EventPriority;
import fr.hugo4715.eventmanager2.handler.Listener;

public class RegistredHandler {
	protected Listener listener;
	protected EventPriority pr;
	protected Method m;

	public RegistredHandler(Listener l, Method m) {
		this.listener = l;
		this.m = m;
		EventHandler h = m.getAnnotation(EventHandler.class);

		if (h == null)return;

		if (m.getParameterTypes().length != 1)return;

		pr = h.priority();
	}

	public void callEvent(Event e){
		if (!m.getParameterTypes()[0].getSimpleName().equals(e.getClass().getSimpleName()))return;

		if(e instanceof Cancellable){
			EventHandler h = m.getAnnotation(EventHandler.class);
			if(((Cancellable)e).isCancelled() && !h.ignoreCanceled())return;
		}
		try {
			m.invoke(listener, e);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			System.err.println("Error while trying to invoke event " + e.getEventName());
			e1.printStackTrace();
		}
	}

	public Listener getListener() {
		return listener;
	}

	public EventPriority getPriority() {
		return pr;
	}

}
