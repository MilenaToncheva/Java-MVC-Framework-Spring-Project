package softuni.artgallery.data.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public enum Category {
    CERAMICS,
    PAINTING,
    DRAWING,
    SCULPTURE

}