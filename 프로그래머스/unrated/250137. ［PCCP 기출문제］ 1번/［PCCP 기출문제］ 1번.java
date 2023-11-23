class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        int cur_health = health;
        int cur_time = 0;
        for(int i=0;i<attacks.length;i++) {
            int att_time = attacks[i][0];
            int att_damage = attacks[i][1];
            int heal_time = att_time - cur_time -1;
            if(heal_time < 0) heal_time = 0;
            cur_health += heal_time*bandage[1] + heal_time/bandage[0] * bandage[2];
            if(cur_health > health) cur_health = health;
            cur_health -= att_damage;
            cur_time = att_time;
            if(cur_health <= 0) return -1;
        }
        answer = cur_health;
        return answer;
    }
}

/*
시전시간 : 5, 초당회복량 : 1, 추가회복량 : 5
최대체력 30
공격 [2, 10], [9, 15], [10, 5], [11, 5]


*/