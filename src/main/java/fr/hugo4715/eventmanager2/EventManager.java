package fr.hugo4715.eventmanager2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import fr.hugo4715.eventmanager2.event.Event;
import fr.hugo4715.eventmanager2.handler.EventHandler;
import fr.hugo4715.eventmanager2.handler.EventPriority;
import fr.hugo4715.eventmanager2.handler.Listener;

public class EventManager {
	private static EventManager instance;

	public static EventManager getInstance() {
		if (null == instance) {
			instance = new EventManager();
		}
		return instance;
	}

	protected List<RegistredHandler> handlers;

	private EventManager() {
		handlers = new ArrayList<>();
	}


	/*
	 * API
	 */

	public void registerEvents(Listener l){
		for(Method m : l.getClass().getDeclaredMethods()){
			
			EventHandler h = m.getAnnotation(EventHandler.class);
			if(h == null)continue;
			
			
			handlers.add(new RegistredHandler(l,m));
		}
		
	}

	public void callEvent(Event e){
		
		for(int i = 0; i < EventPriority.values().length; i++){
			for(RegistredHandler r: handlers){
				if(r.getPriority().equals(EventPriority.values()[i])){
					r.callEvent(e);
				}
			}
		}
		
	}

}
