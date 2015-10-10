package edu.unlam.pacman.ui.modules.pacman;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import edu.unlam.pacman.common.Coordenada;
import edu.unlam.pacman.common.Direction;
import edu.unlam.pacman.comunication.bus.async.Callback;
import edu.unlam.pacman.comunication.bus.async.Request;
import edu.unlam.pacman.comunication.bus.events.MoveEvent;
import edu.unlam.pacman.ui.mvp.Presenter;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class PacmanPresenter extends Presenter<PacmanView> implements PacmanView.MyView {
    private Pacman pacman;

    public PacmanPresenter() {
        super(new PacmanView());
        this.pacman = new Pacman();
        this.pacman.setActive(true);
    }

    @Override
    public void move(Direction direction) {
        if (pacman.isActive()) {
            eventBus.post(new Request<>(new MoveEvent(pacman.getId(), new Coordenada(pacman.getX(), pacman.getY()), direction)));
        }
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleMoveEventCallback(Callback<MoveEvent> callback) {
        MoveEvent moveEvent = callback.getEvent();
        if (pacman.getId().equals(moveEvent.getSubject())) {
            int x = 0;
            int y = 0;
            Direction direction = moveEvent.getDireccion();
            switch (direction) {
                case UP:
                    x = 0;
                    y = -50;
                    break;
                case DOWN:
                    x = 0;
                    y = 50;
                    break;
                case LEFT:
                    x = -50;
                    y = 0;
                    break;
                case RIGHT:
                    x = 50;
                    y = 0;
                    break;
                default:
                    break;
            }
            pacman.setX(moveEvent.getOrigen().getX() + x);
            pacman.setY(moveEvent.getOrigen().getY() + y);
            pacman.setDirection(direction);
            paintPacman();
        }
    }

    @Override
    public void paintPacman() {
        getView().paintPacman(pacman.getX(), pacman.getY(), pacman.getWidth(), pacman.getHeight(), pacman.getDirection());
        getView().repaint();
    }
}