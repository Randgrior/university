package converter;

import dto.DepartmentDTO;
import dto.LectorDTO;
import entity.Department;
import entity.Lector;

import java.util.List;
import java.util.Set;

public class DepartmentConverter extends AbstractConverter<Department, DepartmentDTO> {

    private LectorConverter lectorConverter = new LectorConverter();

    @Override
    public DepartmentDTO from(Department item) {
        DepartmentDTO entity = new DepartmentDTO();
        CloneUtils.copyProps(item, entity);

        Set<Lector> lectors = item.getLectors();
        entity.setLectors(lectorConverter.from(lectors));

        return entity;
    }

    @Override
    public Department to(DepartmentDTO entity) {
        Department item = new Department();
        CloneUtils.copyProps(entity, item);

        List<LectorDTO> lectors = entity.getLectors();
        item.setLectors(lectorConverter.to(lectors));

        return item;
    }
}
