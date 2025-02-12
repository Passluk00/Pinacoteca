package it.uniromatre.pinaback.opere;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ListOpere {

    private List<Opera> items;

}
