package converter;

import dto.LectorDTO;
import entity.Lector;

public class LectorConverter extends AbstractConverter<Lector, LectorDTO> {
    @Override
    public LectorDTO from(Lector item) {
        LectorDTO entity = new LectorDTO();
        CloneUtils.copyProps(item, entity);

        return entity;
    }

    @Override
    public Lector to(LectorDTO entity) {
        Lector item = new Lector();
        CloneUtils.copyProps(entity, item);

        return item;
    }
}
