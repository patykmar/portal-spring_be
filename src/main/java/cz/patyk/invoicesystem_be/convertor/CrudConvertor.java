package cz.patyk.invoicesystem_be.convertor;

public interface CrudConvertor<I, O, E> {
    E inputToEntity(I userInput);
    O entityToDto(E entity);
}
