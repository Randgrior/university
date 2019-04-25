package converter;

import dto.DegreeDTO;
import entity.Degree;

public class DegreeConverter extends AbstractConverter<Degree, DegreeDTO> {
    @Override
    public DegreeDTO from(Degree item) {
        DegreeDTO entity = new DegreeDTO();
        CloneUtils.copyProps(item, entity);

        return entity;

    }

    @Override
    public Degree to(DegreeDTO entity) {
        Degree item = new Degree();
        CloneUtils.copyProps(entity, item);

        return item;
    }
}
