import java.util.ArrayList;
import java.util.List;

public class Skill_list{
    private static List<Action> skill_list = new ArrayList<>();
    
    public Skill_list(){
        skill_list.add((Action) new Waterball());
        skill_list.add((Action) new Fireball());
        skill_list.add((Action) new SelfHealing());
        skill_list.add((Action) new Petrochemical());
        skill_list.add((Action) new Poison());
        skill_list.add((Action) new Summon());
        skill_list.add((Action) new SelfExplosion());
        skill_list.add((Action) new Cheerup());
        skill_list.add((Action) new Curse());
        skill_list.add((Action) new myOnePunch());
    }

    public static Action append_skill(String name){
        Action skill = (Action) new Basic_Attack();
        for(int i=0;i<skill_list.size();i++){
            skill = skill_list.get(i);
            if(skill.get_name().equals(name)){
                return skill;
            }
        }
        return skill;
    }
    
}