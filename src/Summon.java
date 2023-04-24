import java.util.List;

public class Summon implements Action {
    // For state
    private int MPcost = 150;
    private int target_num = 0;
    private String name = "Summon";

    public String get_name() {
        return this.name;
    }
    
    public int get_MPcost() {
        return MPcost;
    }

    public int get_target_num(List<Unit> target_unit) {
        return target_unit.size();
    }

    public List<Unit> get_target_unit(Unit attack_unit, List<Unit> battle_unit){
        return battle_unit;
    }

    public void perform_action(Unit attack_unit, List<Unit> selected_unit){
        int troop_index = attack_unit.get_troop_index();
        Unit slime = new Unit(troop_index, "Slime 100 0 50");
        int flag=-1;
        
        if(troop_index==1){
            for(int i=0;i<selected_unit.size() && flag==-1;i++){
                if(selected_unit.get(i).get_troop_index()==2){
                    flag = i;
                } 
            }
            selected_unit.add(flag, slime);
        }
        else{
            selected_unit.add(slime);
        }
        attack_unit.cost_MP(MPcost);
    }

    public void attack_output(Unit attack_unit, Unit target_unit, int damage){
        ;
    }

    public void aim_output(Unit attack_unit, List<Unit> selected_unit){
        System.out.printf("[%d]%s uses Summon.\n", attack_unit.get_troop_index(), attack_unit.get_name());
    }
}