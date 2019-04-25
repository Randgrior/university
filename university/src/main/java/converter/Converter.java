package converter;

import java.util.List;
import java.util.Set;

public interface Converter<J, E> {
    E from(J item);

    J to(E entity);

    List<E> from(Set<J> items);

    Set<J> to(List<E> entities);
}
