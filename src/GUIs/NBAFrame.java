package GUIs;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import user.User;
/*
 * abstract frame for NBA Simulatior GUIs
 * 
 */

public abstract class NBAFrame extends JFrame{
	/**
	 * has User loggedUser variable which can be null
	 * 	or can be logged user
	 */
	static User loggedUser = null;

	
	/**
	 * In windowBuilder method, components of an NBAFrame is put, 
	 * 	and used at constructor methods.
	 */
	protected abstract void windowBuilder();
	
	/**
	 * Used to move one window from another if action Listener of button needs
	 */
	void close() {this.dispose();}
	
	public NBAFrame() throws HeadlessException {
		setTitle("NBA Simulator");
		windowBuilder();
	}
	
	public NBAFrame(User loggedUser) throws HeadlessException {
		setTitle("NBA Simulator");
		this.loggedUser = loggedUser;
		windowBuilder();
	}

}
