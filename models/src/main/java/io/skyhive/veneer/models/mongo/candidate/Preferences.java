package io.skyhive.veneer.models.mongo.candidate;

import io.skyhive.veneer.models.mongo.Compensation;
import io.skyhive.veneer.models.mongo.Relocate;
import io.skyhive.veneer.models.mongo.WorkType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author krishna
 * @created 20/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class Preferences {
    private Compensation compensation;
    private Relocate relocate;
    private WorkType remote;
    private boolean isPrivate;
}
