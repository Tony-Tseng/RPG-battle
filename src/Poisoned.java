import java.util.List;

public class Poisoned implements State {
    private String name = "Poisoned";
    private int state_counter;

    public Poisoned() {
        state_counter = 0;
    }

    public String get_name() {
        return this.name;
    }

    public int get_state_round() {
        return this.state_counter;
    }

    public void add_counter() {
        this.state_counter += 1;
    }

    public void perform_state(Unit unit) {
        unit.activate();
        if (state_counter < 2) {
            unit.take_damage(30);
        } else {
            unit.take_damage(30);
            State new_state = (State) new Normal();
            unit.change_state(new_state);
        }
    }
}