import tw.waterball.foop.hw2.provided.Target;
import java.util.ArrayList;
import java.util.List;

public class Unit implements Target{
    private String name;
    private int troop_index;
    private int HP;
    private int MP;
    private int STR;
    private Boolean activate;
    private int buff_damage;
    private State state;
    private List<Action> action_list;
    private List<Unit> heal_list;

    public Unit(int troop_index, String status) {
        this.troop_index = troop_index;
        String[] unit_status = status.split(" ");
        this.name = unit_status[0];
        this.HP = Integer.parseInt(unit_status[1]);
        this.MP = Integer.parseInt(unit_status[2]);
        this.STR = Integer.parseInt(unit_status[3]);
        this.activate = true;
        this.buff_damage = 0;
        this.state = (State) new Normal();
        this.action_list = new ArrayList<>();
        this.heal_list = new ArrayList<Unit>();

        Action new_action = (Action) new Basic_Attack();
        action_list.add(new_action);
        Skill_list skill_list = new Skill_list();
        for (int i = 4; i < unit_status.length; i++) {
            new_action = Skill_list.append_skill(unit_status[i]);
            action_list.add(new_action);
        }
    }

    public String get_name() {
        return this.name;
    }

    public int get_troop_index(){
        return this.troop_index;
    }

    public int get_HP() {
        return this.HP;
    }

    public int get_MP() {
        return this.MP;
    }

    public int get_STR() {
        return this.STR;
    }

    public Boolean get_activate(){
        return this.activate && this.HP>0;
    }

    public String get_state_name() {
        return this.state.get_name();
    }

    public State get_state(){
        return this.state;
    }

    public Action get_action(int index){
        return this.action_list.get(index);
    }

    public void deactivate(){
        this.activate = false;
    }

    public void activate(){
        this.activate = true;
    }

    public String[] get_action_list() {
        String[] action_array = new String[action_list.size()];
        for(int i=0;i<action_list.size();i++){
            action_array[i] = action_list.get(i).get_name();
        }
        return action_array;
    }

    public void show_info(){
        System.out.printf("%s's turn (HP: %d, MP: %d, STR: %d, State: %s)\n", name,
        HP, MP, STR, this.state.get_name());
    }

    public void take_damage(int damage) {
        this.HP -= damage;
        if (this.HP <= 0) {
            System.out.printf("[%d]%s dies.\n", this.get_troop_index(), this.get_name());
            if(heal_list.size()>0){
                int heal = this.get_MP();
                for(int i=0;i<heal_list.size();i++){
                    heal_list.get(i).add_HP(heal);
                }
            }
        }
    }

    public void cost_MP(int amount){
        this.MP -= amount;
    }

    public void add_HP(int heal){
        this.HP += heal;
    }

    public void change_state(State new_state){
        this.state = new_state;
    }

    public void buff_damage(int buff){
        this.buff_damage = buff;
    }

    public int get_buff_damage(){
        return this.buff_damage;
    }

    public void append_heal_list(Unit unit){
        this.heal_list.add(unit);
    }

    @Override
    public void takeOnePunchDamage(int damage) {
        damage += this.buff_damage;
        System.out.printf("causes %d damage to [%d]%s.\n", damage, this.get_troop_index(), this.get_name());
        take_damage(damage);
    }
}