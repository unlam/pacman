package edu.unlam.pacman.client.modules.menu;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import edu.unlam.pacman.shared.comunication.bus.events.ScreenEvent;
import edu.unlam.pacman.client.modules.BaseFrame;
import edu.unlam.pacman.client.modules.login.LoginConstants;
import edu.unlam.pacman.client.modules.menu.menu.MenuPresenter;

/**
 * @author Cristian Miranda
 * @since 10/2/15 - 17:35
 */
public class Menu extends BaseFrame {
    public Menu() throws HeadlessException {
        // Create and set up the window.
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize content
        initContent();

        // Display the window.
        setSize(MenuConstants.MAX_WIDTH, LoginConstants.MAX_HEIGHT);
        setLocationRelativeTo(null);
    }

    @Override
    protected void initContent() {
        addComponent(new MenuPresenter());
    }

    @Override
    protected ScreenEvent.ScreenType getScreenType() {
        return ScreenEvent.ScreenType.MENU;
    }
}