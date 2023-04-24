import java.util.List;
import java.util.ArrayList;

interface Action{
    public String get_name();
    public int get_MPcost();
    public int get_target_num(List<Unit> target_unit);
    public List<Unit> get_target_unit(Unit attack_unit, List<Unit> battle_unit);
    public void perform_action(Unit attack_unit, List<Unit> selected_unit);
    public void attack_output(Unit attack_unit, Unit target_unit, int damage);
    public void aim_output(Unit attack_unit, List<Unit> selected_unit);
}