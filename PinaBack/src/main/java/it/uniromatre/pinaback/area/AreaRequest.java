package it.uniromatre.pinaback.area;


import it.uniromatre.pinaback.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AreaRequest {

    @NotEmpty(message = "il nome dell'area è Obbligatorio")
    @NotBlank(message = "il nome dell'area è Obbligatorio")
    private String name;




}
