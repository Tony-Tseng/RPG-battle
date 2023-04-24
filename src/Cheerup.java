import java.util.List;
import java.util.ArrayList;

public class Cheerup implements State, Action {
    // For state
    private String name = "Cheerup";
    private int state_counter;
    public Cheerup() {
        state_counter = 0;
    }

    public String get_name(){
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
            unit.buff_damage(50);
        } else {
            unit.buff_damage(50);
            State new_state = (State) new Normal();
            unit.change_state(new_state);
        }
    }

    // For action
    private int MPcost = 100;
    private int target_num = 3;

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
            if(target_unit.get_troop_index()== attack_troop_index && target_unit!=attack_unit){
                target_troop.add(target_unit);
            }
        }
        return target_troop;
    }

    public void perform_action(Unit attack_unit, List<Unit> selected_unit){
        Unit target_unit;

        for(int i=0;i<selected_unit.size();i++){
            target_unit = selected_unit.get(i);
            State new_state = (State) new Cheerup();
            target_unit.change_state(new_state);
        }
        attack_unit.cost_MP(MPcost);
    }

    public void attack_output(Unit attack_unit, Unit target_unit, int damage){
        ;
    }

    public void aim_output(Unit attack_unit, List<Unit> selected_unit){
        Unit target_unit;

        System.out.printf("[%d]%s uses Cheerup on", attack_unit.get_troop_index(), attack_unit.get_name());
        for(int i=0;i<selected_unit.size();i++){
            target_unit = selected_unit.get(i);
            System.out.printf(" [%d]%s", target_unit.get_troop_index(), target_unit.get_name());
            if(i<selected_unit.size()-1) System.out.printf(",");
        }
        System.out.println(".");
    }
}