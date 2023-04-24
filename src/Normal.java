public class Normal implements State{
    private String name = "Normal";
    private int state_counter;

    public Normal(){
        state_counter = 0;
    }

    public String get_name(){
        return this.name;
    }

    public int get_state_round(){
        return this.state_counter;
    }

    public void add_counter(){
        this.state_counter += 1;
    }

    public void perform_state(Unit unit){
        unit.activate();
        unit.buff_damage(0);
    }
}