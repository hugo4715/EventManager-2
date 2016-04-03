package fr.hugo4715.eventmanager2.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.hugo4715.eventmanager2.EventManager;
import fr.hugo4715.eventmanager2.event.Cancellable;
import fr.hugo4715.eventmanager2.event.Event;
import fr.hugo4715.eventmanager2.handler.EventHandler;
import fr.hugo4715.eventmanager2.handler.EventPriority;
import fr.hugo4715.eventmanager2.handler.Listener;

public class BasicEventTest implements Listener{
	class TestEvent extends Event implements Cancellable{
		protected boolean cancel = false;
		@Override
		public String getEventName() {
			return "BasicEvent";
		}

		@Override
		public boolean isCancelled() {
			return cancel;
		}

		@Override
		public void setCancelled(boolean cancelled) {
			cancel = cancelled;
		}
		
	}
	
	boolean success = false;
	
	@Test
	public void test() {
		EventManager.getInstance().registerEvents(this);
		EventManager.getInstance().callEvent(new TestEvent());
		assertEquals(true, success);
	}
	
	
	@EventHandler(ignoreCanceled=true)
	public void onEvent(TestEvent e){
		System.out.println("event");
		success = true;
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onEvent2(TestEvent e){
		System.out.println("event2");e.setCancelled(true);
		success = false;
	}

}
