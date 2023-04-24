import java.util.List;
import java.util.ArrayList;

public class Poison implements Action {
    private int MPcost = 80;
    private int target_num = 1;
    private String name = "Poison";

    public String get_name() {
        return this.name;
    }
    
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
        State new_state = (State) new Poisoned();
        target_unit.change_state(new_state);
        attack_unit.cost_MP(MPcost);
    }

    public void attack_output(Unit attack_unit, Unit target_unit, int damage){
        ;
    }

    public void aim_output(Unit attack_unit, List<Unit> selected_unit){
        Unit target_unit = selected_unit.get(0);
        System.out.printf("[%d]%s uses Poison on [%d]%s.\n", attack_unit.get_troop_index(), attack_unit.get_name(),
                target_unit.get_troop_index(), target_unit.get_name());
    }
}