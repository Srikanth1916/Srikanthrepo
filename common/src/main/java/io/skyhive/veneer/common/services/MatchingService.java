package io.skyhive.veneer.common.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.elasticsearch.common.util.set.Sets;
import org.springframework.util.CollectionUtils;

import io.skyhive.veneer.common.responses.SkillsMatchPercentageResponse;
import io.skyhive.veneer.models.es.MatchingSkill;

/**
 * @author krishna
 * @created 28/12/21
 * @project skyhive-veeneer
 */
public final class MatchingService {
    public static SkillsMatchPercentageResponse caliculateMatchPercentage(Set<MatchingSkill> employeeSkills, Set<MatchingSkill> jobSkills){
    	 SkillsMatchPercentageResponse employeeJobSkillsMatchPercentageResponse = new SkillsMatchPercentageResponse();
    	 employeeJobSkillsMatchPercentageResponse.setQuality(getMatchScore(employeeSkills, jobSkills));
    	 employeeJobSkillsMatchPercentageResponse.setMatchingSkills(Sets.intersection(employeeSkills, jobSkills));
    	 employeeJobSkillsMatchPercentageResponse.setMissingSkills(Sets.difference(employeeSkills, jobSkills));
    	 return employeeJobSkillsMatchPercentageResponse;
    }

	public static double getMatchScore(Set<MatchingSkill> employeeSkills,
                                  Set<MatchingSkill> jobSkills) {
        if(CollectionUtils.isEmpty(employeeSkills) || CollectionUtils.isEmpty(jobSkills)) {
            return 0.0;
        }
		int skillValue = 0;
        HashMap<String, MatchingSkill> skillMap = new HashMap<>();
        for(MatchingSkill matchingSkill: employeeSkills) {
            skillMap.put(matchingSkill.getId(), matchingSkill);
            skillValue +=
                    matchingSkill.getComplexity() * matchingSkill.getRequired().getScore();
        }
        skillValue = 100/skillValue;
        Map<String, MatchingSkill> jobSkillsMap =
                jobSkills.stream().collect(Collectors.toMap(MatchingSkill::getId,
                Function.identity()));
        int score = 100;
        for(String key :skillMap.keySet()) {
            MatchingSkill skillRequired = skillMap.get(key);
            var currentValue =
                    skillValue * skillRequired.getComplexity() * skillRequired.getRequired().getScore();
           if(!jobSkillsMap.containsKey(key)){
                score = score - currentValue;
            }
           else{
               MatchingSkill skillGiven = jobSkillsMap.get(key);
               if(skillGiven.getLevel().getScore() > skillRequired.getLevel().getScore()){
                   score =
                           score - (currentValue*(skillGiven.getLevel().getScore() - skillRequired.getLevel().getScore())/10);
               }
           }
        }
        double scoreDouble = score/100.0;
        if(scoreDouble > 0.5){
            scoreDouble = 1.061/(1 + Math.pow(Math.E,
                    -7 * (scoreDouble -0.5))) - 0.03;
        }
        return scoreDouble*100;
	}
}
