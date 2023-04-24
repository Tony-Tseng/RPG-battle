/**
 * The RPG game will be started from the Main class.
 */
import java.util.*;

public class Main {
    static int attack_flag=0;

    public static void main(String[] args){   
        List<Unit> battle_unit;
        Unit attack_unit;

        // Set up players of two teams
        battle_unit = Game.init_game();
        int i = 0;
        // Attack troop will swap with target troop after all attack units performs.
        while(!check_end(battle_unit)){
            attack_unit = battle_unit.get(i);
            Game.start_round(attack_unit, battle_unit);
            // Next round and deduct remove units.
            i -= remove_die(attack_unit, battle_unit);
            if(i==battle_unit.size()-1){
                add_state_counter(battle_unit);
                i = 0;
            }
            else{
                i++;
            }
        }
    }

    public static Boolean check_end(List<Unit> battle_unit){
        Boolean hero_survive = false;
        Boolean first_troop = false;
        Boolean second_troop = false;

        for(Unit unit: battle_unit){
            if("Hero".equals(unit.get_name())){
                hero_survive = true;
                first_troop = true;
            }
            else if(unit.get_troop_index()==2){
                second_troop = true;
            }
        }

        if(!hero_survive || !first_troop){
            System.out.println("You lose.");
            return true;
        }
        else if(!second_troop){
            System.out.println("You win.");
            return true;
        }
        else{
            return false;
        } 
    }

    public static void show_unit(List<Unit> battle_unit){
        for(Unit unit: battle_unit){
            unit.show_info();
        }
    }

    public static void add_state_counter(List<Unit> battle_troop){
        for(Unit unit: battle_troop){
            unit.get_state().add_counter();
        }
    }

    public static int remove_die(Unit attack_unit, List<Unit> battle_unit){
        Boolean flag = false;
        int counter = 0;

        for(int i=battle_unit.size()-1;i>=0;i--){
            if(battle_unit.get(i)==attack_unit){
                flag = true;
            }
            if(battle_unit.get(i).get_HP()<=0){
                battle_unit.remove(i);
                if(flag) counter++;
            }
        }
        return counter;
    }
}
