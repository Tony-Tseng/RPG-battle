import java.util.ArrayList;
import java.util.List;
public class SelfExplosion implements Action{
    private int MPcost = 200;
    private String name = "SelfExplosion";
    private int target_num;

    public String get_name(){
        return this.name;
    }

    public int get_MPcost(){
        return MPcost;
    }

    public int get_target_num(List<Unit> target_unit) {
        return target_unit.size();
    }

    public List<Unit> get_target_unit(Unit attack_unit, List<Unit> battle_unit){
        List<Unit> target_troop = new ArrayList<Unit>();
        for(Unit target_unit: battle_unit){
            if(target_unit!= attack_unit){
                target_troop.add(target_unit);
            }
        }
        return target_troop;
    }

    public void perform_action(Unit attack_unit, List<Unit> selected_unit){
        int buff_damage = attack_unit.get_buff_damage();
        int damage = 150 + buff_damage;
        Unit target_unit;

        for(int i=0;i<selected_unit.size();i++){
            target_unit = selected_unit.get(i);
            attack_output(attack_unit, target_unit, damage);
            target_unit.take_damage(damage);
        }

        attack_unit.cost_MP(MPcost);
        attack_unit.take_damage(attack_unit.get_HP());
    }

    public void attack_output(Unit attack_unit, Unit target_unit, int damage){
        System.out.printf("[%d]%s causes %d damage to [%d]%s.\n", attack_unit.get_troop_index(), attack_unit.get_name(),
                damage, target_unit.get_troop_index(), target_unit.get_name());
    }

    public void aim_output(Unit attack_unit, List<Unit> selected_unit){
        Unit target_unit;
        System.out.printf("[%d]%s uses SelfExplosion on", attack_unit.get_troop_index(), attack_unit.get_name());

        for(int i=0;i<selected_unit.size();i++){
            target_unit = selected_unit.get(i);
            if(i<selected_unit.size()-1){
                System.out.printf(" [%d]%s,", target_unit.get_troop_index(), target_unit.get_name());
            }
            else{
                System.out.printf(" [%d]%s.\n", target_unit.get_troop_index(), target_unit.get_name());
            }
        }
    }
}