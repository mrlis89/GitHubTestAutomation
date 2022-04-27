package common;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Builder
@Getter
public class Issue {
    private String title;
    private String comment;
}

