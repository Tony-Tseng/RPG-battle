import java.util.List;
import java.util.ArrayList;
public class SelfHealing implements Action{
    private int MPcost = 50;
    private String name = "SelfHealing";
    private int target_num = 0;

    public String get_name(){
        return this.name;
    }

    public int get_MPcost(){
        return MPcost;
    }

    public int get_target_num(List<Unit> target_unit) {
        return target_num;
    }

    public List<Unit> get_target_unit(Unit attack_unit, List<Unit> battle_unit){
        List<Unit> target_unit = new ArrayList<Unit>();
        target_unit.add(attack_unit);
        return target_unit;
    }

    public void perform_action(Unit attack_unit, List<Unit> selected_unit){
        int heal = 150;

        attack_unit.add_HP(heal);
        attack_unit.cost_MP(MPcost);
    }

    public void attack_output(Unit attack_unit, Unit target_unit, int damage){
        ;
    }

    public void aim_output(Unit attack_unit, List<Unit> selected_unit){
        System.out.printf("[%d]%s uses %s.\n", attack_unit.get_troop_index(), attack_unit.get_name(), this.name);
    }
}