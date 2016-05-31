package gui.core;

import java.awt.Component;
import java.awt.event.ActionListener;

public abstract class UiBuilder <E extends Component> {
    public abstract E build(ActionListener listener);
}