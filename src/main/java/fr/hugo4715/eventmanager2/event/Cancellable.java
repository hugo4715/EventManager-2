package fr.hugo4715.eventmanager2.event;

public interface Cancellable {
	public boolean isCancelled();

	public void setCancelled(boolean cancelled);
}
