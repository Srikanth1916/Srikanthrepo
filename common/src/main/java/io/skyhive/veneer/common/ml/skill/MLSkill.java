package io.skyhive.veneer.common.ml.skill;

import lombok.Getter;
import lombok.Setter;

/**
 * @author krishna
 * @created 05/01/22
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class MLSkill {
    private String name;
    private double importance;
    private String id;
    private int count;
    private double score;
}
