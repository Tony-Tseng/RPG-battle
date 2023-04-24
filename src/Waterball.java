import java.util.ArrayList;
import java.util.List;
public class Waterball implements Action {
    private int MPcost = 50;
    private String name = "Waterball";
    private int target_num = 1;

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
        int buff_damage = attack_unit.get_buff_damage();
        int damage = 120 + buff_damage;
        Unit target_unit = selected_unit.get(0);

        attack_output(attack_unit, target_unit, damage);
        target_unit.take_damage(damage);
        attack_unit.cost_MP(MPcost);
    }

    public void attack_output(Unit attack_unit, Unit target_unit, int damage){
        System.out.printf("[%d]%s causes %d damage to [%d]%s.\n", attack_unit.get_troop_index(), attack_unit.get_name(),
                damage, target_unit.get_troop_index(), target_unit.get_name());
    }

    public void aim_output(Unit attack_unit, List<Unit> selected_unit){
        Unit target_unit = selected_unit.get(0);
        System.out.printf("[%d]%s uses Waterball on [%d]%s.\n", attack_unit.get_troop_index(), attack_unit.get_name(), target_unit.get_troop_index(), target_unit.get_name());
    }
}