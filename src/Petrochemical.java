import java.util.List;
import java.util.ArrayList;

public class Petrochemical implements State, Action {
    // For state
    private String name = "Petrochemical";
    private int state_counter;
    public Petrochemical() {
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
        if (state_counter < 2) {
            unit.deactivate();
        } else {
            State new_state = (State) new Normal();
            unit.change_state(new_state);
        }
    }

    // For action
    private int MPcost = 100;
    private int target_num = 1;

    public int get_MPcost() {
        return MPcost;
    }

    public int get_target_num(List<Unit> target_unit) {
        return target_num;
    }

    public List<Unit> get_target_unit(Unit attack_unit, List<Unit> battle_unit){
        int attack_troop_index = attack_unit.get_troop_index();
        List<Unit> target_troop = new ArrayList<Unit>();
        for(Unit target_unit: battle_unit){
            if(target_unit.get_troop_index()!= attack_troop_index){
                target_troop.add(target_unit);
            }
        }
        return target_troop;
    }

    public void perform_action(Unit attack_unit, List<Unit> selected_unit){
        Unit target_unit = selected_unit.get(0);
        State new_state = (State) new Petrochemical();
        target_unit.change_state(new_state);
        // target_unit.deactivate();
        attack_unit.cost_MP(MPcost);
    }

    public void attack_output(Unit attack_unit, Unit target_unit, int damage){
        ;
    }

    public void aim_output(Unit attack_unit, List<Unit> selected_unit){
        Unit target_unit = selected_unit.get(0);
        System.out.printf("[%d]%s uses Petrochemical on [%d]%s.\n", attack_unit.get_troop_index(), attack_unit.get_name(),
                target_unit.get_troop_index(), target_unit.get_name());
    }
}