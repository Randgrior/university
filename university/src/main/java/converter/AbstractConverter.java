package converter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractConverter<J, E> implements Converter<J, E> {
    @Override
    public List<E> from(Set<J> items) {
        return items.stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public Set<J> to(List<E> entities) {
        return entities.stream().map(this::to).collect(Collectors.toSet());
    }
}
