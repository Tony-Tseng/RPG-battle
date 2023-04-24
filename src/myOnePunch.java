import tw.waterball.foop.hw2.provided.OnePunch;
import java.util.ArrayList;
import java.util.List;

public class myOnePunch implements Action{
    private int MPcost = 180;
    private String name = "OnePunch";
    private int target_num = 1;

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
        final OnePunch onePunch = new OnePunch();
        Unit target_unit = selected_unit.get(0);
        int buff_damage = attack_unit.get_buff_damage();
        int target_buff_damage = target_unit.get_buff_damage();
        
        target_unit.buff_damage(buff_damage);
        System.out.printf("[%d]%s ", attack_unit.get_troop_index(), attack_unit.get_name());
        onePunch.perform(target_unit);
        target_unit.buff_damage(target_buff_damage);
        
        attack_unit.cost_MP(MPcost);
    }

    public void attack_output(Unit attack_unit, Unit target_unit, int damage){
    }

    public void aim_output(Unit attack_unit, List<Unit> selected_unit){
        Unit target_unit = selected_unit.get(0);
        System.out.printf("[%d]%s uses OnePunch on [%d]%s.\n", attack_unit.get_troop_index(), attack_unit.get_name(), target_unit.get_troop_index(), target_unit.get_name());
    }
}