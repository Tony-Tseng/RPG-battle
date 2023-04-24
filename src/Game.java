import tw.waterball.foop.hw2.provided.AI;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import java.util.*;

public class Game extends Main {
    public final static Scanner scanner = new Scanner(System.in);
    static final AI ai = new AI();

    public Game() {
        super();
    }

    public static List<Unit> init_game() {
        List<Unit> battle_unit = new ArrayList<Unit>();
        Unit new_unit;
        String read_command = scanner.nextLine();
        // Start to read the first Troop
        read_command = scanner.nextLine();

        while (!"#END-TROOP-1".equals(read_command)) {
            new_unit = new Unit(1, read_command);
            battle_unit.add(new_unit);
            read_command = scanner.nextLine();
        }

        // Start to read the Second Troop
        read_command = scanner.nextLine();
        read_command = scanner.nextLine();

        while (!"#END-TROOP-2".equals(read_command)) {
            new_unit = new Unit(2, read_command);
            battle_unit.add(new_unit);
            read_command = scanner.nextLine();
        }

        return battle_unit;
    }

    public static void start_round(Unit attack_unit, List<Unit> battle_unit) {
        Action attack_action;
        List<Unit> selected_unit, final_unit;
        State attack_state;

        attack_state = attack_unit.get_state();
        attack_unit_info(attack_unit);
        attack_state.perform_state(attack_unit);

        if (attack_unit.get_activate()){
            attack_action = stage1(attack_unit);
            selected_unit = attack_action.get_target_unit(attack_unit, battle_unit);
            final_unit = stage2(attack_unit, selected_unit, attack_action);
            stage3(attack_unit, final_unit, attack_action);
        }
    }

    /*
     * Stage 1 For hero: get input action and check is valid or not For AI: check
     * action is valid and select by AI Select actions that attack_unit is able to
     * perform. (check MP cost)
     */
    public static Action stage1(Unit attack_unit) {
        int action_index;
        Action attack_action;
        String read;
        List<Integer> index_list, valid_index_list;
        String[] action_list = attack_unit.get_action_list();
        index_list = IntStream.rangeClosed(0, action_list.length - 1).boxed().collect(Collectors.toList());

        if ("Hero".equals(attack_unit.get_name())) {
            action_index = Integer.parseInt(scanner.nextLine());
            while (!valid_action(attack_unit, action_index)) {
                output_stage1(action_list, index_list);
                System.out.println("You can't perform the action: insufficient MP.");
                action_index = Integer.parseInt(scanner.nextLine());
            }
        } else {
            // Check MP cost is sufficient of not.
            valid_index_list = parse_valid_action(attack_unit, index_list);
            action_index = ai.selectAction(valid_index_list);
        }
        attack_action = attack_unit.get_action(action_index);
        output_stage1(action_list, index_list);

        return attack_action;
    }

    /*
     * To-do: Not every action needs to select targets.
     */
    public static List<Unit> stage2(Unit attack_unit, List<Unit> selected_unit, Action attack_action) {
        List<Integer> targets;
        List<Unit> final_unit = new ArrayList<Unit>();
        String[] target_list;
        int target_num = attack_action.get_target_num(selected_unit);

        if(target_num==0){
            targets = new ArrayList<Integer>();
        }
        else if ("Hero".equals(attack_unit.get_name())) {
            if (selected_unit.size() > target_num) {
                target_list = scanner.nextLine().split(", ");
                output_stage2(target_num, selected_unit);
                targets = Arrays.stream(target_list).map(Integer::valueOf).collect(Collectors.toList());
            } else {
                return selected_unit;
                // targets = IntStream.rangeClosed(0, selected_unit.size() - 1).boxed().collect(Collectors.toList());
            }
        } else {
            if (selected_unit.size() > target_num) {
                targets = ai.selectTarget(selected_unit.size(), target_num);
            } else {
                return selected_unit;
                // targets = IntStream.rangeClosed(0, selected_unit.size() - 1).boxed().collect(Collectors.toList());
            }
        }

        for(int i=0;i<targets.size();i++){
            final_unit.add(selected_unit.get(targets.get(i)));
        }

        return final_unit;
    }

    /*
     * To-do: 1. Need to consider curse skill. -> Put in Curse class. 
     *        2. Each action needs to print different outputs.
     */
    public static void stage3(Unit attack_unit, List<Unit> final_unit, Action attack_action) {
        attack_action.aim_output(attack_unit, final_unit);
        attack_action.perform_action(attack_unit, final_unit);
    }

    public static Boolean valid_action(Unit attack_unit, int action_index) {
        return attack_unit.get_action(action_index).get_MPcost() <= attack_unit.get_MP();
    }

    public static List<Integer> parse_valid_action(Unit attack_unit, List<Integer> index_list) {
        List<Integer> result_list = new ArrayList<Integer>();
        int action_index;
        for (int i = 0; i < index_list.size(); i++) {
            action_index = index_list.get(i);
            if (valid_action(attack_unit, action_index)) {
                result_list.add(action_index);
            }
        }
        return result_list;
    }

    public static void attack_unit_info(Unit attack_unit) {
        System.out.printf("[%d]%s's turn (HP: %d, MP: %d, STR: %d, State: %s).\n", attack_unit.get_troop_index(),
                attack_unit.get_name(), attack_unit.get_HP(), attack_unit.get_MP(), attack_unit.get_STR(),
                attack_unit.get_state_name());
    }

    public static void output_stage1(String[] action_list, List<Integer>index_list) {
        System.out.printf("Select an action:");
        for(int i=0;i<index_list.size();i++){
            System.out.printf(" (%d) %s", i, action_list[index_list.get(i)]);
        }
        System.out.println("");
    }

    public static void output_stage2(int num_target, List<Unit> target_troop) {
        System.out.printf("Select %d targets:", num_target);
        int i=0;
        
        for (Unit target_unit: target_troop) {
            System.out.printf(" (%d) [%d]%s", i, target_unit.get_troop_index(), target_unit.get_name());
            i++;
        }
        System.out.println("");
    }
}