package it.uniromatre.pinaback.area;

import it.uniromatre.pinaback.opere.Opera;
import it.uniromatre.pinaback.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "area")
@NoArgsConstructor
@AllArgsConstructor
public class Area {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private User curatore;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Opera> opere;




}
