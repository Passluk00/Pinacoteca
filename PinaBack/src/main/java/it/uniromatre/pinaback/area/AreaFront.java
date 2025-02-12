package it.uniromatre.pinaback.area;

import it.uniromatre.pinaback.opere.OperaFront;
import it.uniromatre.pinaback.user.UserFront;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class AreaFront {

    private Integer id;

    private String name;

    private UserFront curatore;

    private List<OperaFront> items;


}
